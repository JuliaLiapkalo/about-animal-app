package com.liapkalo.pumb.aboutanimal.repository;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query(value = "select id from animals", nativeQuery = true)
    List<Long> findAllAnimalIds();
}
