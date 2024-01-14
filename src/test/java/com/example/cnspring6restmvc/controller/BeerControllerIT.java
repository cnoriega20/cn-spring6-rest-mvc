package com.example.cnspring6restmvc.controller;

import com.example.cnspring6restmvc.entities.Beer;
import com.example.cnspring6restmvc.exception.NotFoundException;
import com.example.cnspring6restmvc.model.BeerDTO;
import com.example.cnspring6restmvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;


    @Test
    void testGetById(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerController.getBeerById(beer.getId());
        Assertions.assertThat(beerDTO).isNotNull();
    }

    @Test
    void testBeerIdNotFound(){
        assertThrows(NotFoundException.class, () -> {
            beerController.getBeerById(UUID.randomUUID());
        });
    }
    @Test
    void testListBeer() {
        List<BeerDTO> dtos = beerController.listBeers();
        Assertions.assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void TestEmptyList(){
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();
        Assertions.assertThat(dtos.size()).isEqualTo(0);

    }
}