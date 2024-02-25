package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.entities.Beer;
import com.example.cnspring6restmvc.model.BeerCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BeerCsvServiceImpl implements BeerCsvService {
    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile) {

        try {
            List<BeerCSVRecord> beerCSVRecords = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                    .withType(BeerCSVRecord.class)
                    .build().parse();

        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        return null;
    }
}
