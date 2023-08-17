package com.rigoberto.transport.services.impl;

import com.rigoberto.transport.entities.Transport;
import com.rigoberto.transport.repositories.TransportRepository;
import com.rigoberto.transport.services.TransportService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransportServiceImpl implements TransportService {
    protected final TransportRepository repository;

    @Override
    public Iterable<Transport> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Transport> findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Transport save(Transport entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<Transport> findAllByIdIn(Collection<Integer> transportIds) {
        return repository.findAllByIdIn(transportIds);
    }
}
