package com.rigoberto.transport.controllers;

import com.rigoberto.transport.dtos.*;
import com.rigoberto.transport.entities.Driver;
import com.rigoberto.transport.entities.Transport;
import com.rigoberto.transport.exceptions.NotFoundException;
import com.rigoberto.transport.mappers.DriverMapper;
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
@RequestMapping("/driver")
public class DriverController {
    private final DriverService service;
    private final TransportService transportService;
    private final DriverMapper mapper;

    @Operation(summary = "Get all drivers")
    @GetMapping
    public Collection<DriverDto> getAll() {
        return Streams.streamOf(service.findAll()).map(mapper::convertToDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get a driver by its id")
    @GetMapping("/{id}")
    public DriverDto getById(@PathVariable Integer id) {
        Optional<Driver> inbox = service.findById(id);

        if (inbox.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        return mapper.convertToDto(inbox.get());
    }

    @Operation(summary = "Create new driver")
    @PostMapping
    public DriverDto create(@Valid @RequestBody CreateDriverDto dto) {
        Driver entity = mapper.convertToEntity(dto);

        Collection<Transport> transports = transportService.findAllByIdIn(dto.getTransports());
        entity.setTransports(Set.copyOf(transports));

        Driver result = service.save(entity);

        return mapper.convertToDto(result);
    }

    @Operation(summary = "Update a driver by its id")
    @PutMapping("/{id}")
    public DriverDto update(@PathVariable Integer id, @Valid @RequestBody CreateDriverDto dto) {
        Optional<Driver> entity = service.findById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        Collection<Transport> transports = transportService.findAllByIdIn(dto.getTransports());

        Driver driver = entity.get();
        driver.setName(dto.getName());
        driver.setPassport(dto.getPassport());
        driver.setTransports(Set.copyOf(transports));
        Driver result = service.save(driver);

        return mapper.convertToDto(result);
    }

    @Operation(summary = "Delete a driver by its id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Driver> inbox = service.findById(id);

        if (inbox.isEmpty()) {
            throw new NotFoundException("Element not found");
        }

        service.deleteById(id);
    }

}
