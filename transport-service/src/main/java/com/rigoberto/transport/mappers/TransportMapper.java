package com.rigoberto.transport.mappers;

import com.rigoberto.transport.dtos.*;
import com.rigoberto.transport.entities.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class TransportMapper {

    private final ModelMapper modelMapper;

    public TransportMapper() {
        modelMapper = new ModelMapper();

        //ajustar mapeos
        TypeMap<Transport, TransportDto> propertyMapper1 = modelMapper.createTypeMap(Transport.class, TransportDto.class);

        Converter<Destination, String> destinationToString = c -> c.getSource().toString();
        propertyMapper1.addMappings(
                mapper -> mapper.using(destinationToString).map(Transport::getDestination, TransportDto::setDestination)
        );
        Converter<Set<Driver>, List<BaseDriverDto>> driversToDto = c -> c.getSource() != null? c.getSource().stream().map(x -> new BaseDriverDto(x.getId(), x.getName(), x.getPassport())).toList(): new ArrayList<>();
        propertyMapper1.addMappings(
                mapper -> mapper.using(driversToDto).map(Transport::getDrivers, TransportDto::setDrivers)
        );

        Converter<TypeVehicle, String> typeVehicleToString = c -> c.getSource().toString();
        propertyMapper1.addMappings(
                mapper -> mapper.using(typeVehicleToString).map(Transport::getTypeVehicle, TransportDto::setTypeVehicle)
        );

        TypeMap<CreateTransportDto, Transport> propertyMapper2 = modelMapper.createTypeMap(CreateTransportDto.class, Transport.class);
        propertyMapper2.addMappings(mapper -> mapper.skip(Transport::setId));
        propertyMapper2.addMappings(
                mapper -> mapper.map(src -> new Date(System.currentTimeMillis()), Transport::setRegistrationDate)
        );
        Converter<String, Destination> stringToDestination = c -> Destination.fromString(c.getSource());
        propertyMapper2.addMappings(
                mapper -> mapper.using(stringToDestination).map(CreateTransportDto::getDestination, Transport::setDestination)
        );
        Converter<String, TypeVehicle> stringToTypeVehicle = c -> TypeVehicle.fromString(c.getSource());
        propertyMapper2.addMappings(
                mapper -> mapper.using(stringToTypeVehicle).map(CreateTransportDto::getTypeVehicle, Transport::setTypeVehicle)
        );
    }

    public Transport convertToEntity(CreateTransportDto dto) {
        return modelMapper.map(dto, Transport.class);
    }

    public TransportDto convertToDto(Transport entity) {
        return modelMapper.map(entity, TransportDto.class);
    }
}
