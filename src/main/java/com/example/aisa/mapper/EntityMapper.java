package com.example.aisa.mapper;

public interface EntityMapper<D, E> {

    E toEntity(D dto);
    D toDto(E entity);
}
