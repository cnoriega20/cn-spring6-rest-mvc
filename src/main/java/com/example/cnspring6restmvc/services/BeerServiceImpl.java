package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.Beer;
import com.example.cnspring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService{
    @Override
    public Beer getBeerById(UUID id) {
      log.info("Get Beer by Id - In BeerServiceImpl with id {]", id.toString());
      return Beer.builder()
              .id(id)
              .version(1)
              .beerName("Galaxy Cat")
              .beerStyle(BeerStyle.PALE_ALE)
              .upc("12356")
              .price(new BigDecimal("12.99"))
              .quantityOnHand(122)
              .createdDate(LocalDateTime.now())
              .updateDate(LocalDateTime.now())
              .build();
    }
}
