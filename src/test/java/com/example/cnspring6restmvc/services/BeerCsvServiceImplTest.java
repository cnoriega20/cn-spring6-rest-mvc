package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BeerCsvServiceImplTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCsvTest() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/beers");
        List<BeerCSVRecord> records = beerCsvService.convertCSV(file);
        System.out.println(records.size());

        assertThat(records.size()).isGreaterThan(0);

    }
}
