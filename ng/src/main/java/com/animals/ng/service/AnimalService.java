package com.animals.ng.service;

import com.animals.ng.dto.AnimalDto;
import com.animals.ng.entity.Animal;
import com.animals.ng.exception.GlobalExceptionHandler;
import com.animals.ng.repo.AnimalRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    private AnimalRepo repo;

    public AnimalService(AnimalRepo repo) {
        this.repo = repo;
    }

    public List<AnimalDto> getAllAnimals() {
        List<Animal> animals = this.repo.findAll();
        return animals.stream()
                .map(this::animalToDto)
                .collect(Collectors.toList());
    }

    private AnimalDto animalToDto(Animal animal) {
        // Assuming AnimalDto has a constructor or setters that match the fields of Animal
        return new AnimalDto.Builder().id(animal.getId()).name(animal.getName()).type(animal.getType()).build();
    }


    private List<AnimalDto> animalToDto(List<Animal> all) {
        var list = new ArrayList<AnimalDto>();
        for (var animal : all) {
            list.add(new AnimalDto.Builder().id(animal.getId()).name(animal.getName()).type(animal.getType()).build());
        }
        return list;

    }


    /**
     * @param animal
     * @return
     */
    public Animal addAnimal(AnimalDto animal) {
        var animalEntity = new Animal(animal.getName(), animal.getType());
        return  repo.save(animalEntity);
    }

    public void deleteAnimal(Long id) {
        repo.deleteById(id);
    }

    public Animal getAnimal(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        return repo.findById(id).orElseThrow(() ->
                new GlobalExceptionHandler.AnimalNotFoundException("Animal not found for id " + id));
    }

    public Animal updateAnimal(Long id, AnimalDto animalDto) {
        Animal animal = repo.findById(id).get();
        animal.setName(animalDto.getName());
        animal.setType(animalDto.getType());
        return repo.save(animal);
    }
}
