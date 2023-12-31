package com.rigoberto.passenger.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseTransportDto {
    protected Integer id;
    protected Date registrationDate;
    protected String destination;
    protected String typeVehicle;
    protected String brand;
}
