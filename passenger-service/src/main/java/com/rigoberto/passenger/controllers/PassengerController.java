package com.rigoberto.passenger.controllers;

import com.rigoberto.passenger.client.TransportClient;
import com.rigoberto.passenger.dtos.*;
import com.rigoberto.passenger.entities.Passenger;
import com.rigoberto.passenger.exceptions.NotFoundException;
import com.rigoberto.passenger.mappers.PassengerMapper;
import com.rigoberto.passenger.services.PassengerService;
import com.rigoberto.passenger.utils.Streams;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService service;
    private final PassengerMapper mapper;
    private final TransportClient transportClient;

    @Operation(summary = "Get all passengers")
    @GetMapping
    public Collection<PassengerDto> getAll() {
        return Streams.streamOf(service.findAll()).map(mapper::convertToDto).peek(p -> p.setTransport(transportClient.findById(p.getTransport().getId()).orElse(null))).collect(Collectors.toList());
    }

    @Operation(summary = "Get a passenger by its id")
    @GetMapping("/{id}")
    public PassengerDto getById(@PathVariable Integer id) {
        Optional<Passenger> inbox = service.findById(id);

        if (inbox.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        PassengerDto dtoResult = mapper.convertToDto(inbox.get());
        dtoResult.setTransport(transportClient.findById(dtoResult.getTransport().getId()).orElse(null));
        return dtoResult;
    }

    @Operation(summary = "Create new passenger")
    @PostMapping
    public PassengerDto create(@Valid @RequestBody CreatePassengerDto dto) {
        Optional<BaseTransportDto> transport = transportClient.findById(dto.getTransportId());

        if (transport.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        Passenger entity = mapper.convertToEntity(dto);
        entity.setTransportId(dto.getTransportId());

        Passenger result = service.save(entity);

        PassengerDto dtoResult = mapper.convertToDto(result);
        dtoResult.setTransport(transportClient.findById(dto.getTransportId()).orElse(null));
        return dtoResult;
    }

    @Operation(summary = "Update a passenger by its id")
    @PutMapping("/{id}")
    public PassengerDto update(@PathVariable Integer id, @Valid @RequestBody CreatePassengerDto dto) {
        Optional<Passenger> entity = service.findById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        Optional<BaseTransportDto> transport = transportClient.findById(dto.getTransportId());

        if (transport.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        Passenger event = entity.get();
        event.setName(dto.getName());
        event.setTransportId(dto.getTransportId());
        event.getAddress().setStreet(dto.getAddress().getStreet());
        event.getAddress().setCity(dto.getAddress().getCity());
        Passenger result = service.save(event);

        PassengerDto dtoResult = mapper.convertToDto(result);
        dtoResult.setTransport(transportClient.findById(dto.getTransportId()).orElse(null));
        return dtoResult;
    }

    @Operation(summary = "Delete a passenger by its id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Passenger> inbox = service.findById(id);

        if (inbox.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        service.deleteById(id);
    }

}
