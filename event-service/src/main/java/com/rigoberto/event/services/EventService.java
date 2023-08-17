package com.rigoberto.event.services;

import com.rigoberto.event.entities.Event;
import com.rigoberto.event.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository repository;

    public Flux<Event> getAll(){
        return repository.findAll();
    }

    public Mono<Event> getById(Integer id ){
        return repository.findById(id);
    }

    public Mono<Event> create(Event event){
        return repository.save(event);
    }

    public Mono<Event> update(Integer id,  Event event){
        return repository.findById(id)
                .flatMap(dbEvent -> {
                    dbEvent.setPriority(event.getPriority());
                    dbEvent.setDescription(event.getDescription());
                    return repository.save(dbEvent);
                });
    }

    public Mono<Event> delete(Integer id){
        return repository.findById(id)
                .flatMap(dbEvent -> repository.delete(dbEvent)
                        .then(Mono.just(dbEvent)));
    }
}
