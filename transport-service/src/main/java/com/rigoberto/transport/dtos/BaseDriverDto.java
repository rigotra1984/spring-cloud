package com.rigoberto.transport.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseDriverDto {
    protected Integer id;
    protected String name;
    protected String passport;
}
