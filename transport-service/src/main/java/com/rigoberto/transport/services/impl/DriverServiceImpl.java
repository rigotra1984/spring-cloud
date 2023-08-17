package com.rigoberto.transport.services.impl;

import com.rigoberto.transport.entities.Driver;
import com.rigoberto.transport.repositories.DriverRepository;
import com.rigoberto.transport.services.DriverService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {
    protected final DriverRepository repository;

    @Override
    public Iterable<Driver> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Driver save(Driver entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<Driver> findAllByIdIn(Collection<Integer> driverIds) {
        return repository.findAllByIdIn(driverIds);
    }
}
