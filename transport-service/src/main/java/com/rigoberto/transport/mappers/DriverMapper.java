package com.rigoberto.transport.mappers;

import com.rigoberto.transport.dtos.*;
import com.rigoberto.transport.entities.Driver;
import com.rigoberto.transport.entities.Transport;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DriverMapper {
    private final ModelMapper modelMapper;

    public DriverMapper() {
        modelMapper = new ModelMapper();

        //ajustar mapeos
        TypeMap<Driver, DriverDto> propertyMapper1 = modelMapper.createTypeMap(Driver.class, DriverDto.class);
        Converter<Set<Transport>, List<BaseTransportDto>> transportToInteger = c -> c.getSource() != null? c.getSource().stream().map(x -> new BaseTransportDto(x.getId(), x.getRegistrationDate(), x.getDestination().toString(), x.getTypeVehicle().toString(), x.getBrand())).toList(): new ArrayList<>();
        propertyMapper1.addMappings(
                mapper -> mapper.using(transportToInteger).map(Driver::getTransports, DriverDto::setTransports)
        );

        TypeMap<CreateDriverDto, Driver> propertyMapper2 = modelMapper.createTypeMap(CreateDriverDto.class, Driver.class);
        propertyMapper2.addMappings(mapper -> mapper.skip(Driver::setId));
    }

    public Driver convertToEntity(CreateDriverDto dto) {
        return modelMapper.map(dto, Driver.class);
    }

    public DriverDto convertToDto(Driver entity) {
        return modelMapper.map(entity, DriverDto.class);
    }
}
