package com.rigoberto.event.repositories;

import com.rigoberto.event.entities.Event;
import com.rigoberto.event.entities.Priority;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends R2dbcRepository<Event, Integer> {
    Flux<Event> findAllByPriority(Priority priority);
}
