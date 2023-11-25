package com.example.cnspring6restmvc.controller;

import com.example.cnspring6restmvc.model.Beer;
import com.example.cnspring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
    private final BeerService beerService;

    @GetMapping()
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }
    @GetMapping("{id}")
    public Beer getBeerById(@PathVariable("id") UUID id){
        log.info("Get Beer by Id - In Controller");
        return beerService.getBeerById(id);
    }
}
