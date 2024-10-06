package com.example.cnspring6restmvc.repositories;

import com.example.cnspring6restmvc.entities.Beer;
import com.example.cnspring6restmvc.entities.BeerOrder;
import com.example.cnspring6restmvc.entities.Customer;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BeerOrderRepositoryTest {

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }

    @Transactional
    @Test
    void testBeerOrders(){
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test customer ref")
                .customer(testCustomer)
                .build();
        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);
        log.info(savedBeerOrder.getCustomerRef());
    }
}