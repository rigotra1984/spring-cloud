package com.rigoberto.transport.controllers;

import com.rigoberto.transport.dtos.CreateTransportDto;
import com.rigoberto.transport.dtos.TransportDto;
import com.rigoberto.transport.entities.Destination;
import com.rigoberto.transport.entities.Driver;
import com.rigoberto.transport.entities.Transport;
import com.rigoberto.transport.entities.TypeVehicle;
import com.rigoberto.transport.exceptions.NotFoundException;
import com.rigoberto.transport.mappers.TransportMapper;
import com.rigoberto.transport.services.DriverService;
import com.rigoberto.transport.services.TransportService;
import com.rigoberto.transport.utils.Streams;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/transport")
public class TransportController {
    private final TransportService service;
    private final DriverService driverService;
    private final TransportMapper mapper;

    @Operation(summary = "Get all transports")
    @GetMapping
    public Collection<TransportDto> getAll() {
        return Streams.streamOf(service.findAll()).map(mapper::convertToDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get a transport by its id")
    @GetMapping("/{id}")
    public TransportDto getById(@PathVariable Integer id) {
        Optional<Transport> inbox = service.findById(id);

        if (inbox.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        return mapper.convertToDto(inbox.get());
    }

    @Operation(summary = "Create new transport")
    @PostMapping
    public TransportDto create(@Valid @RequestBody CreateTransportDto dto) {
        Transport entity = mapper.convertToEntity(dto);

        Collection<Driver> drivers = driverService.findAllByIdIn(dto.getDrivers());
        entity.setDrivers(Set.copyOf(drivers));

        Transport result = service.save(entity);

        return mapper.convertToDto(result);
    }

    @Operation(summary = "Update a transport by its id")
    @PutMapping("/{id}")
    public TransportDto update(@PathVariable Integer id, @Valid @RequestBody CreateTransportDto dto) {
        Optional<Transport> entity = service.findById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        Collection<Driver> drivers = driverService.findAllByIdIn(dto.getDrivers());

        Transport transport = entity.get();
        transport.setDestination(Destination.fromString(dto.getDestination()));
        transport.setTypeVehicle(TypeVehicle.fromString(dto.getTypeVehicle()));
        transport.setBrand(dto.getBrand());
        transport.setDrivers(Set.copyOf(drivers));
        Transport result = service.save(transport);

        return mapper.convertToDto(result);
    }

    @Operation(summary = "Delete a transport by its id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Transport> inbox = service.findById(id);

        if (inbox.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        service.deleteById(id);
    }
}
