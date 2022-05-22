package com.catandtoys.catAndToys.repo;

import com.catandtoys.catAndToys.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository <Cat, Long> {
}
