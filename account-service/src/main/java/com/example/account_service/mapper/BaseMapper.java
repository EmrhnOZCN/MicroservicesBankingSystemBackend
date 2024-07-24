package com.example.account_service.mapper;

import java.util.List;

public interface BaseMapper<D,E>{
    E dtoToEntity (D d );
    D entityToDto (E e);
    List<E> dtoListToEntityList(List<D> d);
    List<D> EntityListToDtoList(List<E> e);
}
