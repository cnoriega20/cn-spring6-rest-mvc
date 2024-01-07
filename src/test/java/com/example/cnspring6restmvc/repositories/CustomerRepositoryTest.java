package com.example.cnspring6restmvc.repositories;

import com.example.cnspring6restmvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {
    
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveCustomer() {
        Customer customer = customerRepository.save(Customer.builder()
                        .customerName("Cesar")
                .build());

        assertThat(customer.getId()).isNotNull();
    }
}