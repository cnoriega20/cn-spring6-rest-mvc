package com.example.cnspring6restmvc.repositories;

import com.example.cnspring6restmvc.entities.Beer;
import com.example.cnspring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveBeerWithRepoTest(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("ComsBeer")
                .beerStyle(BeerStyle.IPA)
                .upc("378388")
                .price(new BigDecimal("12.59"))
                .build());

        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();

   }
}