package com.liapkalo.pumb.aboutanimal.entity;

import com.liapkalo.pumb.aboutanimal.enums.Sex;
import com.liapkalo.pumb.aboutanimal.enums.Type;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "animals", indexes = {@Index(name = "filter_type_category_sex_index", columnList = "type, sex, category")})
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    Type type;

    @Enumerated(EnumType.STRING)
    Sex sex;

    int weight;

    int cost;

    int category;

}
