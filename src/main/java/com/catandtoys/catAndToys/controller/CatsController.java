package com.catandtoys.catAndToys.controller;

import com.catandtoys.catAndToys.errors.SystemException;
import com.catandtoys.catAndToys.model.Cat;
import com.catandtoys.catAndToys.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("cats")
@RequiredArgsConstructor
public class CatsController {

    private final CatService catService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cat createCat(@RequestBody final Cat cat) throws SystemException {
       return catService.createCat(cat);
    }


    @DeleteMapping("{catId}")
    public void deleteCat(@PathVariable final long catId) throws SystemException {
        catService.deleteCat(catId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{catId}")
    public Cat getCat(@PathVariable final long catId) throws SystemException {
        return catService.getCat(catId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Set<Cat> getAllCats() throws SystemException {
        return catService.getAllCats();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("catsByWeightAndName/{weight}/{name}")
    public Set<Cat> getCatsByNameAndWeight(@PathVariable(name = "weight") final double weight,
                                            @PathVariable(name = "name")
                                    final String name) throws SystemException {
        return catService.getCatsByNameAndWeight(weight, name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("catsByWeightOrName/{weight}/{name}")
    public Set<Cat> getCatsByNameOrWeight(@PathVariable(name = "weight") final double weight,
                                           @PathVariable(name = "name")
                                           final String name) throws SystemException {
        return catService.getCatsByNameOrWeight(weight, name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getCatsByWeightUpOrder")
    public List<Cat> getCatsByWeightUpOrder() throws SystemException {
        return catService.getCatsByWeightUpOrder();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getCatsByWeightDownOrder")
    public List<Cat> getCatsByWeightDownOrder() throws SystemException {
        return catService.getCatsByWeightDownOrder();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getCatsByNameOrder")
    public List<Cat> getCatsByNameOrder() throws SystemException {
        return catService.getCatsByNameOrder();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/catsWeightAvg")
    public String getCatsWeightAvg() throws SystemException {
        return catService.getCatsWeightAvg();
    }
}
