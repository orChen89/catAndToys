package com.catandtoys.catAndToys.repo;

import com.catandtoys.catAndToys.model.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository <Toy, Long> {
}
