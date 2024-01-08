package com.example.cnspring6restmvc.data.loaders;

import com.example.cnspring6restmvc.repositories.BeerRepository;
import com.example.cnspring6restmvc.repositories.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    Bootstrap bootstrapData;
    @BeforeEach
    void setUp() {
        bootstrapData = new Bootstrap(beerRepository, customerRepository);
    }

    @Test
    void testRun() throws Exception {
        bootstrapData.run(null);
        Assertions.assertThat(beerRepository.count()).isEqualTo(3);
        Assertions.assertThat(customerRepository.count()).isEqualTo(3);
    }
}