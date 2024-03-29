package com.example.cnspring6restmvc.controller;

import com.example.cnspring6restmvc.model.BeerDTO;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.example.cnspring6restmvc.controller.BeerController.BEER_PATH;
import static com.example.cnspring6restmvc.controller.BeerController.BEER_PATH_ID;
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

    @Captor
    ArgumentCaptor<BeerDTO> beerDTOArgumentCaptor;

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void listBeersTest() throws Exception {
        given(beerService.listBeers(any(), any(), any(), any(), any()))
                .willReturn(beerServiceImpl.listBeers(null, null, false, 1, 25));

        mockMvc.perform(get(BEER_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(3)));
    }
    @Test
    void getBeerByIdTest() throws Exception {
        BeerDTO testBeer = beerServiceImpl.listBeers(null,null,
                        false, 1, 25)
                .getContent().get(0);
        //given
        given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));

        mockMvc.perform(get(BEER_PATH_ID, testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));
    }
    @Test
    void createNewBeerTest() throws Exception {
        BeerDTO beer = beerServiceImpl.listBeers(null, null, false,
                1, 25)
                .getContent().get(0);
        beer.setId(null);
        beer.setVersion(null);

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers(null,
                null, false, 1, 25).getContent().get(1));

        mockMvc.perform(post(BEER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(beer)))
                        .andExpect(status().isCreated())
                                .andExpect(header().exists("Location"));

        //log.info(objectMapper.writeValueAsString(beer));
    }

    @Test
    void getBeerIdNotFoundException() throws Exception {

        given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(BEER_PATH_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBeerTestWithNullBeerName() throws Exception {
        BeerDTO beerDTO  = BeerDTO.builder().build();

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers(null,
                null, false, 1, 25).getContent().get(1));

        MvcResult mvcResult = mockMvc.perform(post(BEER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.length()", is(6)))
                .andReturn();

        System.out.println("mvcResult: " + mvcResult.getResponse().getContentAsString());

    }


    @Test
    void updatedBeerTest() throws Exception {
        BeerDTO testBeer = beerServiceImpl.listBeers(null, null, false,
                1, 25).getContent().get(0);
        given(beerService.updateBeerById(any(), any())).willReturn(Optional.of(testBeer));
        mockMvc.perform(put(BEER_PATH_ID, testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBeer)))
                .andExpect(status().isNoContent());
        verify(beerService).updateBeerById(any(UUID.class), any(BeerDTO.class));
    }

    @Test
    void updatedBeerTestBlankName() throws Exception {
        BeerDTO testBeer = beerServiceImpl.listBeers(null, null, false, 1,
                25).getContent().get(0);
        testBeer.setBeerName("");
        given(beerService.updateBeerById(any(), any())).willReturn(Optional.of(testBeer));
        mockMvc.perform(put(BEER_PATH_ID, testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBeer)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void deleteBeerTest() throws Exception{
        BeerDTO testBeer = beerServiceImpl.listBeers(null, null, false, 1,
                25).getContent().get(0);

        //given
        given(beerService.deleteById(any())).willReturn(true);
        mockMvc.perform(delete(BEER_PATH_ID, testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //ArgumentCaptor<UUID> uuidArgCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(beerService).deleteById(uuidArgCaptor.capture());

        assertThat(testBeer.getId()).isEqualTo(uuidArgCaptor.getValue());
    }

    @Test
    void patchBeer() throws Exception {
        BeerDTO beer = beerServiceImpl.listBeers(null, null, false, 1,
                25).getContent().get(0);
        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name");

        mockMvc.perform(patch(BEER_PATH_ID,beer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(beerMap)))
                .andExpect(status().isNoContent());

        verify(beerService).patchBeerById(uuidArgCaptor.capture(), beerDTOArgumentCaptor.capture());

        assertThat(beer.getId()).isEqualTo(uuidArgCaptor.getValue());
        assertThat(beerMap.get("beerName")).isEqualTo(beerDTOArgumentCaptor.getValue().getBeerName());
    }
}