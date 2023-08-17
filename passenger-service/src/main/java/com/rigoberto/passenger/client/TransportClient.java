package com.rigoberto.passenger.client;

import java.util.Optional;

import com.rigoberto.passenger.dtos.BaseTransportDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
//@FeignClient(name = "transport-service")
public interface TransportClient {

	@GetMapping("/{id}")
	Optional<BaseTransportDto> findById(@PathVariable("id") Integer id);

}
