package com.rigoberto.passenger.services;

import java.util.Optional;

public interface GeneryService<T, K> {
    Iterable<T> findAll();
    Optional<T> findById(K id);
    T save(T entity);
    void deleteById(K id);
}
