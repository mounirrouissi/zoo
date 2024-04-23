package com.animals.ng.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Animal {
    public Animal() {
    }

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "AnimalName")
    @NotEmpty(message = "Name is required")
    private String name;
    @Column(name = "AnimalType")
    @NotEmpty(message = "Type is required")
    private String type;
    @Version
    private Long version;

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && Objects.equals(name, animal.name) && Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
