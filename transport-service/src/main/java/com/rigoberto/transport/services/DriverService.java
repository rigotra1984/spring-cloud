package com.rigoberto.transport.services;

import com.rigoberto.transport.entities.Driver;

import java.util.Collection;

public interface DriverService extends GeneryService<Driver, Integer> {
    Collection<Driver> findAllByIdIn(Collection<Integer> driverIds);
}
