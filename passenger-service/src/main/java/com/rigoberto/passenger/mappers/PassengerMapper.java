package com.rigoberto.passenger.mappers;

import com.rigoberto.passenger.dtos.*;
import com.rigoberto.passenger.entities.Passenger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {
    private final ModelMapper modelMapper;

    public PassengerMapper() {
        modelMapper = new ModelMapper();

        TypeMap<CreatePassengerDto, Passenger> propertyMapper2 = modelMapper.createTypeMap(CreatePassengerDto.class, Passenger.class);
        propertyMapper2.addMappings(mapper -> mapper.skip(Passenger::setId));
    }

    public Passenger convertToEntity(CreatePassengerDto dto) {
        return modelMapper.map(dto, Passenger.class);
    }

    public PassengerDto convertToDto(Passenger entity) {
        return modelMapper.map(entity, PassengerDto.class);
    }
}
