package com.rigoberto.transport.services;

import com.rigoberto.transport.entities.Transport;

import java.util.Collection;

public interface TransportService extends GeneryService<Transport, Integer> {
    Collection<Transport> findAllByIdIn(Collection<Integer> transportIds);
}
