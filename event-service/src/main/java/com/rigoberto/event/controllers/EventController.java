package com.rigoberto.event.controllers;

import com.rigoberto.event.dtos.CreateEventDto;
import com.rigoberto.event.dtos.EventDto;
import com.rigoberto.event.mappers.EventMapper;
import com.rigoberto.event.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public Flux<EventDto> getAll(){
        return service.getAll().map(mapper::convertToDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<EventDto> getById(@PathVariable Integer id ){
        return service.getById(id).map(mapper::convertToDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<EventDto> create(@RequestBody @Valid CreateEventDto dto){
        return service.create(mapper.convertToEntity(dto)).map(mapper::convertToDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<EventDto> update(@PathVariable Integer id, @RequestBody @Valid CreateEventDto dto){
        return service.update(id, mapper.convertToEntity(dto)).map(mapper::convertToDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id){
        return service.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
