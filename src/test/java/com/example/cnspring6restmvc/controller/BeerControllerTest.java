package com.example.cnspring6restmvc.controller;

import com.example.cnspring6restmvc.model.Beer;
import com.example.cnspring6restmvc.services.BeerService;
import com.example.cnspring6restmvc.services.BeerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.example.cnspring6restmvc.controller.BeerController.BEER_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
@Slf4j
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgCaptor;

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void deleteBeerTest() throws Exception{
        Beer testBeer = beerServiceImpl.listBeers().get(0);

        mockMvc.perform(delete(BEER_PATH +  "/" + testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
        //ArgumentCaptor<UUID> uuidArgCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(beerService).deleteById(uuidArgCaptor.capture());

        assertThat(testBeer.getId()).isEqualTo(uuidArgCaptor.getValue());
    }
    @Test
    void updatedBeerTest() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().get(0);

        mockMvc.perform(put(BEER_PATH +  "/" + testBeer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBeer)))
                .andExpect(status().isNoContent());
        verify(beerService).updateBeerById(any(UUID.class), any(Beer.class));
    }
    @Test
    void createNewBeerTest() throws Exception {
        Beer beer = beerServiceImpl.listBeers().get(0);
        beer.setId(null);
        beer.setVersion(null);

        given(beerService.saveNewBeer(any(Beer.class))).willReturn(beerServiceImpl.listBeers().get(1));

        mockMvc.perform(post(BEER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(beer)))
                        .andExpect(status().isCreated())
                                .andExpect(header().exists("Location"));

        //log.info(objectMapper.writeValueAsString(beer));
    }
    @Test
    void listBeersTest() throws Exception {
        given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

        mockMvc.perform(get(BEER_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.length()", is(3)));
    }
    @Test
    void getBeerByIdTest() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().get(0);
        //given
        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);

        mockMvc.perform(get("/api/v1/beer/" + testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));

    }
}