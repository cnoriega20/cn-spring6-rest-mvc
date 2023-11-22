package com.example.cnspring6restmvc.controller;

import com.example.cnspring6restmvc.model.Beer;
import com.example.cnspring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {
    private final BeerService beerService;
    public Beer getBeerById(UUID id){
        log.info("Get Beer by Id - In Controller");
        return beerService.getBeerById(id);
    }
}
