package com.rigoberto.passenger.services.impl;

import com.rigoberto.passenger.entities.Address;
import com.rigoberto.passenger.entities.Passenger;
import com.rigoberto.passenger.repositories.PassengerRepository;
import com.rigoberto.passenger.services.PassengerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PassengerServiceimpl implements PassengerService {
    protected final PassengerRepository repository;

    @Override
    public Iterable<Passenger> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Passenger> findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Passenger save(Passenger entity) {
        Passenger entityNew = entity;

        if(entity.getId() == null) {
            Address address = entity.getAddress();
            entity.setAddress(null);

            entityNew = repository.save(entity);
            address.setPassenger(entityNew);
            entityNew.setAddress(address);
        }

        return repository.save(entityNew);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
