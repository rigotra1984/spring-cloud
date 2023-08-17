package com.rigoberto.transport.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DriverDto extends BaseDriverDto {
    private List<BaseTransportDto> transports;
}
