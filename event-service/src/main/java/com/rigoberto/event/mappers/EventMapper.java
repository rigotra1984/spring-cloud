package com.rigoberto.event.mappers;

import com.rigoberto.event.dtos.CreateEventDto;
import com.rigoberto.event.dtos.EventDto;
import com.rigoberto.event.entities.Event;
import com.rigoberto.event.entities.Priority;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EventMapper {
    private final ModelMapper modelMapper;

    public EventMapper() {
        modelMapper = new ModelMapper();

        //ajustar mapeos
        TypeMap<Event, EventDto> propertyMapper1 = modelMapper.createTypeMap(Event.class, EventDto.class);
        Converter<Priority, String> priorityToString = c -> c.getSource().toString();
        propertyMapper1.addMappings(
                mapper -> mapper.using(priorityToString).map(Event::getPriority, EventDto::setPriority)
        );

        TypeMap<CreateEventDto, Event> propertyMapper2 = modelMapper.createTypeMap(CreateEventDto.class, Event.class);
        propertyMapper2.addMappings(mapper -> mapper.skip(Event::setId));
        propertyMapper2.addMappings(
                mapper -> mapper.map(src -> new Date(System.currentTimeMillis()), Event::setDate)
        );
        Converter<String, Priority> stringToPriority = c -> Priority.fromString(c.getSource());
        propertyMapper2.addMappings(
                mapper -> mapper.using(stringToPriority).map(CreateEventDto::getPriority, Event::setPriority)
        );
    }

    public Event convertToEntity(CreateEventDto dto) {
        return modelMapper.map(dto, Event.class);
    }

    public EventDto convertToDto(Event entity) {
        return modelMapper.map(entity, EventDto.class);
    }
}
