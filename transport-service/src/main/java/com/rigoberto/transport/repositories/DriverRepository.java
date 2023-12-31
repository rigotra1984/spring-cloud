package com.rigoberto.transport.repositories;

import com.rigoberto.transport.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Collection<Driver> findAllByIdIn(Collection<Integer> driverIds);
}
