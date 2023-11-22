package com.example.cnspring6restmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class BeerControllerTest {

    @Autowired
    BeerController beerControllerTest;
    @Test
    void getBeerById() {
        log.info( "Get Beer by Id : {} ", beerControllerTest.getBeerById(UUID.randomUUID()));
    }
}