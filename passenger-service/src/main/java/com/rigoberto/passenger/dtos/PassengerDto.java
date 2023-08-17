package com.rigoberto.passenger.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PassengerDto extends BasePassengerDto {
    private BaseTransportDto transport;
    private AddressDto address;
}
