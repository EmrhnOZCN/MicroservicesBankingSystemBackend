package com.example.auth_service.client;

import com.example.auth_service.dto.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.auth_service.dto.LoginRequestDTO;
import com.example.auth_service.dto.RegisterRequestDTO;
import com.example.auth_service.dto.AuthResponseDTO;

@FeignClient(name = "customer-service", path = "/api/v1/customers")
public interface CustomerServiceClient {

    @PostMapping("/authenticate")
    UserVO authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO);

    @PostMapping
    AuthResponseDTO registerUser(@RequestBody RegisterRequestDTO registerRequestDTO);
}