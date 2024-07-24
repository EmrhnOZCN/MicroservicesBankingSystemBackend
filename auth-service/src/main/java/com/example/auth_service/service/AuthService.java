package com.example.auth_service.service;

import com.example.auth_service.client.CustomerServiceClient;
import com.example.auth_service.dto.LoginRequestDTO;
import com.example.auth_service.dto.RegisterRequestDTO;
import com.example.auth_service.dto.AuthResponseDTO;
import com.example.auth_service.dto.UserVO;
import com.example.auth_service.kafka.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerServiceClient customerServiceClient;
    private final JwtUtil jwtUtil;
    private final KafkaProducerService kafkaProducerService;
    private final RedisTemplate<String, String> redisTemplate;

    private static final String ACCESS_TOKEN_CACHE = "accessTokens";
    private static final String REFRESH_TOKEN_CACHE = "refreshTokens";

    @CachePut(value = ACCESS_TOKEN_CACHE, key = "#userId")
    public void cacheAccessToken(String userId, String accessToken) {
        redisTemplate.opsForValue().set(userId + ":access", accessToken, 30, TimeUnit.MINUTES);
    }

    @CachePut(value = REFRESH_TOKEN_CACHE, key = "#userId")
    public void cacheRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set(userId + ":refresh", refreshToken, 60, TimeUnit.DAYS);
    }

    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UserVO user = customerServiceClient.authenticateUser(loginRequestDTO);
        String accessToken = jwtUtil.generate(user.getId(), user.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(user.getId(), user.getRole(), "REFRESH");

        // Cache tokens
        cacheAccessToken(user.getId(), accessToken);
        cacheRefreshToken(user.getId(), refreshToken);

        // Kafka mesajı gönder
        String message = String.format("User %s logged in successfully", user.getId());
        kafkaProducerService.sendMessage("login-topic", message);


        return new AuthResponseDTO(user.getId(),"Giriş başarılı", accessToken, refreshToken );
    }

    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        AuthResponseDTO response = customerServiceClient.registerUser(registerRequestDTO);

        // Kafka mesajı gönder
        String message = String.format("User %s registered successfully", registerRequestDTO.getIdentityNumber());
        kafkaProducerService.sendMessage("register-topic", message);

        return response;
    }

}
