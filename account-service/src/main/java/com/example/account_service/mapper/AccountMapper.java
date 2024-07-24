package com.example.account_service.mapper;

import com.example.account_service.dto.AccountRequestDTO;
import com.example.account_service.dto.AccountResponseDTO;

import com.example.account_service.model.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseMapper<AccountRequestDTO, AccountEntity> {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountResponseDTO accountEntityToAccountResponseDTO(AccountEntity customerEntity);

    List<AccountResponseDTO> accountEntityListToAccountDtoList(List<AccountEntity> accountEntities);
}
