package com.avizhen.converter;

public interface Converter< E, D> {
    E convertToEntity(D dto);
    E convertToExistingEntity(D dto, E entity);
}
