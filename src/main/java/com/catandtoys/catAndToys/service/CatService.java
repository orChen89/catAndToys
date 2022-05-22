package com.catandtoys.catAndToys.service;

import com.catandtoys.catAndToys.enums.EntityType;
import com.catandtoys.catAndToys.errors.*;
import com.catandtoys.catAndToys.model.Cat;
import com.catandtoys.catAndToys.repo.CatRepository;
import com.catandtoys.catAndToys.util.OptionalToEntityConvertorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    //------------------------------------------Creating Cat---------------------------------------------------

    public Cat createCat(final Cat cat) throws SystemException {

        //Checking if the specific Cat is already exist
        for (Cat c : catRepository.findAll()) {
            if (c.getName().equals(cat.getName())) {
                throw new EntityAlreadyExistsException(EntityType.CAT, Constraint.ENTITY_ALREADY_EXISTS);
            }
        }
        //Creating the Cat entity
        Cat newCat = catRepository.save(cat);
        log.info("The new Cat: " + cat.getName() + " has been created successfully!");

        return newCat;
    }


    //------------------------------------------Deleting a Cat---------------------------------------------------

    public void deleteCat(final Long catId) throws SystemException {

        //Checking if Cat is not exists
        if (!catRepository.existsById(catId)) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        //Deleting the specific Cat
        log.info("The selected Cat has been deleted!");
        catRepository.deleteById(catId);
    }

    //------------------------------------------Getting all cats----------------------------------------------

    public Set<Cat> getAllCats() throws SystemException {

        //Setting and adding all cats in a set
        Set<Cat> catsList = new HashSet<>(catRepository.findAll());

        //Checking if cats list not exist
        if (catsList == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        return catsList;
    }

    //------------------------------------------Getting a Cat----------------------------------------------

    public Cat getCat(final Long catId) throws SystemException {

        //Setting a specific Cat to a variable
        Cat cat = OptionalToEntityConvertorUtil.optionalCat(catRepository.findById(catId));

        //Checking if the Cat is not exist
        if (cat == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        return cat;
    }

    //------------------------------------------Getting cats by specific weight & name----------------------------

    public Set<Cat> getCatsByNameAndWeight(final double weight, final String name) throws SystemException {

        //Setting a set of all existing Cats
        Set<Cat> cats = getAllCats();

        //Checking if set is not exists
        if (cats == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        //Checking if the weight entered correctly
        if (weight < 0) {
            throw new InvalidWeightInsertion(Constraint.INVALID_WEIGHT);
        }

        //Checking for each cat if it is according to inserted parameters
        return cats.
                stream().
                filter(cat -> cat.getWeight() <= weight && cat.getName().equals(name)).
                collect(Collectors.toSet());
    }

    //------------------------------------------Getting cats by specific weight Or name----------------------------

    public Set<Cat> getCatsByNameOrWeight(final double weight, final String name) throws SystemException {

        //Setting a set of all existing Cats
        Set<Cat> cats = getAllCats();

        //Checking if set is not exists
        if (cats == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        //Checking if the weight entered correctly
        if (weight < 0) {
            throw new InvalidWeightInsertion(Constraint.INVALID_WEIGHT);
        }

        //Checking for each cat if it is according to inserted parameters
        return cats.
                stream().
                filter(cat -> cat.getWeight() <= weight || cat.getName().equals(name)).
                collect(Collectors.toSet());
    }

    //------------------------------------------Getting cats by increased weight ----------------------------

    public List<Cat> getCatsByWeightUpOrder() throws SystemException {

        //Setting a set of all existing Cats
        List<Cat> cats = catRepository.findAll();

        //Checking if set is not exists
        if (cats == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        //Getting each cat by increased weight order
        return cats.
                stream().sorted(Comparator.comparingDouble(Cat::getWeight)).
                collect(Collectors.toList());
    }

    //------------------------------------------Getting cats by decreased weight ----------------------------

    public List<Cat> getCatsByWeightDownOrder() throws SystemException {

        //Setting a set of all existing Cats
        List<Cat> cats = catRepository.findAll();

        //Checking if set is not exists
        if (cats == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        //Getting each cat by increased weight order
        return cats.
                stream().sorted(Comparator.comparingDouble(Cat::getWeight).reversed()).
                collect(Collectors.toList());
    }

    //------------------------------------------Getting cats by AlphaBet ----------------------------

    public List<Cat> getCatsByNameOrder() throws SystemException {

        //Setting a set of all existing Cats
        List<Cat> cats = catRepository.findAll();

        //Checking if set is not exists
        if (cats == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        //Getting each cat by increased weight order
        return cats.
                stream().sorted(Comparator.comparing(Cat::getName)).
                collect(Collectors.toList());
    }

    //------------------------------------------Getting avg weight of all cats----------------------------------------

    public String getCatsWeightAvg() throws SystemException {

        double sumOfWeights = 0;
        double avg;

        //Setting a set of all existing cats
        Set<Cat> cats = getAllCats();

        //Checking if set is not exists
        if (cats == null) {
            throw new EntityNotExistException(EntityType.CAT, Constraint.ENTITY_NOT_EXISTS);
        }

        //Summing for each cat its weight
        for (Cat cat : cats) {
            sumOfWeights += cat.getWeight();
        }

        avg = sumOfWeights / cats.size();

        return "The average weight of all cats is: " +  avg;
    }
}
