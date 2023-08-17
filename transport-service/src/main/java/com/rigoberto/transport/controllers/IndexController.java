package com.rigoberto.transport.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping
    public String testService(HttpServletRequest request) {
        LOGGER.info("I am " + request.getRequestURL().toString());
        return request.getRequestURL().toString();
    }
}
