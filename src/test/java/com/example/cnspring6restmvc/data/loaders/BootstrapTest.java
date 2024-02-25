package com.example.cnspring6restmvc.data.loaders;

import com.example.cnspring6restmvc.repositories.BeerRepository;
import com.example.cnspring6restmvc.repositories.CustomerRepository;
import com.example.cnspring6restmvc.services.BeerCsvService;
import com.example.cnspring6restmvc.services.BeerCsvServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(BeerCsvServiceImpl.class)
class BootstrapTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerCsvService beerCsvService;

    Bootstrap bootstrapData;
    @BeforeEach
    void setUp() {
        bootstrapData = new Bootstrap(beerRepository, customerRepository, beerCsvService);
    }

    @Test
    void testRun() throws Exception {
        bootstrapData.run(null);
        Assertions.assertThat(beerRepository.count()).isEqualTo(2413);
        Assertions.assertThat(customerRepository.count()).isEqualTo(3);
    }
}