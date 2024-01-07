package com.example.cnspring6restmvc.repositories;

import com.example.cnspring6restmvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveBeerWithRepoTest(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("ComsBeer")
                .build());
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();

   }
}