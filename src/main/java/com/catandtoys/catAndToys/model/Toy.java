package com.catandtoys.catAndToys.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "toys")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;
}
