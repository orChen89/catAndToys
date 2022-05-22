package com.catandtoys.catAndToys.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "cats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "weight", nullable = false)
    private double weight;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.ALL})
    private Set<Toy> toys;
}
