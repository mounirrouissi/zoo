package com.animals.ng.controller;

import com.animals.ng.dto.AnimalDto;
import com.animals.ng.dto.GlobalResponse;
import com.animals.ng.entity.Animal;
import com.animals.ng.exception.GlobalExceptionHandler;
import com.animals.ng.service.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.LogManager;

/**
 * this controller for animals
 */
@RestController
@RequestMapping("v1/api/animals")
@Api(tags = "Animal Management")
@CrossOrigin("*")
public class AnimalController {
    private AnimalService service;
    private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);

    public AnimalController(AnimalService service) {
        this.service = service;
    }



    @GetMapping
    public ResponseEntity<GlobalResponse> getAllAnimals() {
        logger.info("getAllAnimals called ");
        try {
            List<AnimalDto> animals = service.getAllAnimals();
            GlobalResponse response = new GlobalResponse("SUCCESS", "Animals fetched successfully", animals);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage()); // This will be caught by the global exception handler
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getAllAnimalById(Long id) {
        logger.info("get animal by id  called ");
            try {
                Animal animal = service.getAnimal(id);
                AnimalDto dto = new AnimalDto.Builder().id(animal.getId()).name(animal.getName()).type(animal.getType()).build();
                    GlobalResponse response = new GlobalResponse("SUCCESS", "Animal Found successfully", dto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch(GlobalExceptionHandler.AnimalNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            catch(IllegalArgumentException e) {
                GlobalResponse response = new GlobalResponse("ERROR", e.getMessage(), null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }



    }


    @ApiOperation(value = "Creates a new animal")
    @PostMapping
    public ResponseEntity<GlobalResponse> createAnimal(@ApiParam(value = "The animal to create", required = true) @RequestBody @Valid Animal animal) {
        try {
            Animal createdAnimal = service.addAnimal(new AnimalDto.Builder().id(animal.getId()).name(animal.getName()).type(animal.getType()).build());
            GlobalResponse response = new GlobalResponse("SUCCESS", "Animal created successfully", createdAnimal);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage()); // This will be caught by the global exception handler
        }
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<GlobalResponse> getAnimal(@PathVariable Long id) {
//        try {
//            Animal animal = service.getAnimal(id);
//            GlobalResponse response = new GlobalResponse("SUCCESS", "Animal fetched successfully", animal);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex.getMessage()); // This will be caught by the global exception handler
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        try {
            Animal updatedAnimal = service.updateAnimal(id, new AnimalDto.Builder().id(animal.getId()).name(animal.getName()).type(animal.getType()).build());
            GlobalResponse response = new GlobalResponse("SUCCESS", "Animal updated successfully", updatedAnimal);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage()); // This will be caught by the global exception handler
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteAnimal(@PathVariable Long id) {
        try {
            service.deleteAnimal(id);
            GlobalResponse response = new GlobalResponse("SUCCESS", "Animal deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage()); // This will be caught by the global exception handler
        }
    }

    // Assuming AnimalDto is a Data Transfer Object for Animal with fields name and type
    // and Animal is an entity with fields id, name, and type
}

