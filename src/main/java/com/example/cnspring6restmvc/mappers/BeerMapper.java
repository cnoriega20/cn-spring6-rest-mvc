package com.example.cnspring6restmvc.mappers;

import com.example.cnspring6restmvc.entities.Beer;
import com.example.cnspring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtotoBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);
}
