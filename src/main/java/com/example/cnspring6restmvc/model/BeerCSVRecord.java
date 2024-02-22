package com.example.cnspring6restmvc.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeerCSVRecord {

    @CsvBindByName
    private Integer row;

    @CsvBindByName(column = "count.x")
    private Integer count;

    @CsvBindByName
    private String abv;
    @CsvBindByName
    private String ibu;

    @CsvBindByName
    private Integer id;

    @CsvBindByName
    private String beer;

    @CsvBindByName
    private String style;

    @CsvBindByName
    private Integer breweryId;

    @CsvBindByName
    private Float ounces;

    @CsvBindByName
    private String style2;

    @CsvBindByName
    private String count_y;

    @CsvBindByName
    private String city;

    @CsvBindByName
    private String state;

    @CsvBindByName
    private String label;

}
