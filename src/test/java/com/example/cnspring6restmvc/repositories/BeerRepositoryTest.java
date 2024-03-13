package com.example.cnspring6restmvc.repositories;

import com.example.cnspring6restmvc.data.loaders.Bootstrap;
import com.example.cnspring6restmvc.entities.Beer;
import com.example.cnspring6restmvc.model.BeerStyle;
import com.example.cnspring6restmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE
@Import({Bootstrap.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerListByName(){
        Page<Beer> beerList = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%", null);
        assertThat(beerList.getContent().size()).isEqualTo(336);
    }
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

    @Test
    void saveBeerWithRepoTestNameTooLong() {
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("ComsBeer 7675275272752757527525273868292622926992662692232546787654321987654")
                    .beerStyle(BeerStyle.IPA)
                    .upc("378388")
                    .price(new BigDecimal("12.59"))
                    .build());

            beerRepository.flush();
        });

    }
}