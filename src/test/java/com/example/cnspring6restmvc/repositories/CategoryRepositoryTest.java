package com.example.cnspring6restmvc.repositories;

import com.example.cnspring6restmvc.entities.Beer;
import com.example.cnspring6restmvc.entities.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repository;

    @Autowired
    BeerRepository beerRepository;
    Beer testBeer;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        testBeer = beerRepository.findAll().get(0);
    }

    @Test
    void testAddCategory() {
        Category savedCategory = categoryRepository.save(Category.builder()
                        .description("lagers")
                .build());
        testBeer.addCategory(savedCategory);
        Beer savedBeer = beerRepository.save(testBeer);

        log.info(savedBeer.getBeerName());

    }
}