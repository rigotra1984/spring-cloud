package com.rigoberto.event.controllers;

import com.rigoberto.event.dtos.CreateEventDto;
import com.rigoberto.event.entities.Event;
import com.rigoberto.event.mappers.EventMapper;
import com.rigoberto.event.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor
public class EventController {
    private EventService service;
    private final EventMapper mapper;

    @GetMapping
    public Flux<Event> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Event> getById(@PathVariable Integer id ){
        return service.getById(id);
    }

    @PostMapping
    public Mono<Event> create(@RequestBody @Valid CreateEventDto dto){
        return service.create(mapper.convertToEntity(dto));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Event>> update(@PathVariable Integer id, @RequestBody @Valid CreateEventDto dto){
        return service.update(id, mapper.convertToEntity(dto))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id){
        return service.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
