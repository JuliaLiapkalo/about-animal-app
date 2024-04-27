package com.liapkalo.pumb.aboutanimal.service;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;
import com.liapkalo.pumb.aboutanimal.repository.AnimalRepository;
import com.liapkalo.pumb.aboutanimal.service.impl.AnimalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.liapkalo.pumb.aboutanimal.utils.AnimalUtils.buildCatAnimal;
import static com.liapkalo.pumb.aboutanimal.utils.AnimalUtils.buildDogAnimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnimalServiceTests {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalServiceImpl animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAnimal() {
        AnimalDto animalDto = new AnimalDto("Luna", "Cat", "Female", 5, 50, 3);
        animalService.createAnimal(animalDto);
        verify(animalRepository).save(any(Animal.class));
    }

    @Test
    void testGetFilteredAnimals() {
        Animal animalCat = buildCatAnimal();
        Animal animalCat2 = buildCatAnimal();
        animalCat2.setId(3L);
        animalCat2.setName("Ali");
        Animal animalDog = buildDogAnimal();
        when(animalRepository.findAllAnimalIds()).thenReturn(Optional.of(Arrays.asList(1L, 2L, 3L)));
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animalCat));
        when(animalRepository.findById(2L)).thenReturn(Optional.of(animalDog));
        when(animalRepository.findById(3L)).thenReturn(Optional.of(animalCat2));

        List<Animal> filteredAnimals = animalService.getFilteredAnimals("cat", "1", "male", "name");

        assertEquals(2, filteredAnimals.size());
        assertEquals("Ali", filteredAnimals.get(0).getName());
    }

}
