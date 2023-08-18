package com.rigoberto.event.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventDto {
	private Integer id;
	private String date;
	private String priority;
	private String description;
}
