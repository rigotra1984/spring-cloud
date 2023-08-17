package com.rigoberto.passenger.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BasePassengerDto {
    protected Integer id;
    protected String name;
}
