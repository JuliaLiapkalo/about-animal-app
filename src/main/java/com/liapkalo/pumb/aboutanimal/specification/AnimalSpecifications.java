package com.liapkalo.pumb.aboutanimal.specification;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.FilterAnimalDto;
import org.springframework.data.jpa.domain.Specification;

public class AnimalSpecifications {

    public static Specification<Animal> filterByTypeCategoryAndGender(FilterAnimalDto filterAnimalDto) {
        return Specification.where(
                filterAnimalDto.getType() == null ? null :
                        AnimalSpecifications.hasType(filterAnimalDto.getType()))

                .and(filterAnimalDto.getCategory() == null ? null :
                        AnimalSpecifications.hasCategory(filterAnimalDto.getCategory()))

                .and(filterAnimalDto.getSex() == null ? null :
                        AnimalSpecifications.hasGender(filterAnimalDto.getSex()));
    }

    private static Specification<Animal> hasType(String type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type.toUpperCase());
    }

    private static Specification<Animal> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

    private static Specification<Animal> hasGender(String sex) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sex"), sex.toUpperCase());
    }
}
