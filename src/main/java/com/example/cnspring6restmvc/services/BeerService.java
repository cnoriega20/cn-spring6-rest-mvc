package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.BeerDTO;
import com.example.cnspring6restmvc.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Optional<BeerDTO> getBeerById(UUID id);
    List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDTO>  patchBeerById(UUID beerId, BeerDTO beer);
}
