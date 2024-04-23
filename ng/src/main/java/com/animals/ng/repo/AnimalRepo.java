package com.animals.ng.repo;

import com.animals.ng.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepo extends JpaRepository<Animal, Long>{
}
