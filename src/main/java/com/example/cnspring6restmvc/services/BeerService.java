package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
    List<Beer> listBeers();
}
