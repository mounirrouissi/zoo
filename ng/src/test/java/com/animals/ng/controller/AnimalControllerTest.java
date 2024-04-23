package com.animals.ng.controller;

import com.animals.ng.dto.AnimalDto;
import com.animals.ng.entity.Animal;
import com.animals.ng.service.AnimalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(AnimalController.class)
class AnimalControllerTest {
    @MockBean
    private AnimalService  animalService;
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllAnimals() throws Exception {
        //given
        var animalDto = new AnimalDto.Builder().id(1L).name("POP").type("DOG").build();
        //when
when(animalService.getAllAnimals()).thenReturn(List.of(animalDto));
        //then
        mvc.perform(get("http://localhost:8080/v1/api/animals"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Dod"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("Dog"));
    }



@Test
void addAnimalHappyPath() throws Exception {
    AnimalDto animal  = new AnimalDto.Builder().id(2L).name("POP").type("DOG").build();;

    when(animalService.addAnimal(animal)).thenReturn(new Animal(animal.getName(),animal.getType()));
    mvc.perform(post("http://localhost:8081/v1/api/animals")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(animal)))
            .andExpect(status().isCreated());
    verify(animalService,times(1)).addAnimal(any(AnimalDto.class));
}



        @Test
        public void addAnimal_validRequest_returnsCreatedStatus() throws Exception {

            // Arrange
            AnimalDto animal =  new AnimalDto.Builder().id(1L).name("POP").type("DOG").build();
            Animal savedAnimal = new Animal("Fluffy", "Cat");

            when(animalService.addAnimal(animal)).thenReturn(savedAnimal);

            // Act
//            ResponseEntity<Animal> response = controll.addAnimal(animal);
            mvc.perform(post("http://localhost:8081/v1/api/animals")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(animal)))
                    .andExpect(status().isCreated());


        }





    @Test
    public void addAnimal_invalidRequest_callsService() throws Exception {

        // Create a test animal object
        AnimalDto animal = new AnimalDto.Builder().id(1L).name("POP").type("DOG").build();
        animal.setName("");

        // Mock service method call
//        doNothing().when(animalService).addAnimal(animal);

        // Call controller method
        mvc.perform(post("http://localhost:8081/v1/api/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(animal)))
                .andExpect(status().isBadRequest());

        // Verify service method was called
      // verify(animalService, times(1)).addAnimal(animal);

    }



    private String mapToJson(Object obj) throws JsonProcessingException {
        // ObjectMapper to convert object to JSON string
        return new ObjectMapper().writeValueAsString(obj);
    }

}


