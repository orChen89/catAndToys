package com.catandtoys.catAndToys.clr;

import com.catandtoys.catAndToys.model.Cat;
import com.catandtoys.catAndToys.model.Toy;
import com.catandtoys.catAndToys.repo.CatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class Test implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final CatRepository catRepository;

    @Override
    public void run(String... args) throws Exception {

        catCreationByDefault();
        System.out.println(catCreationByController());
        System.out.println(catDeletion());
        System.out.println(getCat());
        System.out.println(getAllCats());
        System.out.println(getCatsByNameAndWeight());
        System.out.println(getCatsByNameOrWeight());
        System.out.println(getCatsByWeightUpOrder());
        System.out.println(getCatsByWeightDownOrder());
        System.out.println(getCatsByNameOrder());
        System.out.println(getCatsWeightAvg());
    }

    //------------------------------------------Creating Cats by default--------------------------------------------

    public void catCreationByDefault() {

        Toy toy1 = Toy.builder().
                name("Zemerball").
                build();

        Toy toy2 = Toy.builder().
                name("Biter").
                build();

        Set<Toy> cat1Toys = new HashSet<>();

        cat1Toys.add(toy1);
        cat1Toys.add(toy2);

        Toy toy3 = Toy.builder().
                name("Kadur Gumi").
                build();

        Toy toy4 = Toy.builder().
                name("Salsela").
                build();

        Set<Toy> cat2Toys = new HashSet<>();

        cat2Toys.add(toy3);
        cat2Toys.add(toy4);

        Cat cat1 = Cat.builder().
                name("Hatuli").
                weight(25.5).
                toys(cat1Toys).
                build();

        Cat cat2 = Cat.builder().
                name("Shmulik").
                weight(40.7).
                toys(cat2Toys).
                build();

        catRepository.save(cat1);
        catRepository.save(cat2);

    }

    //------------------------------------------Creating Cats --------------------------------------------------

    public boolean catCreationByController() {

        Toy toyy = Toy.builder().
                name("wallaktoy").
                build();

        Toy toyyy = Toy.builder().
                name("Mouse Toy").
                build();

        Set<Toy> catToys = new HashSet<>();

        catToys.add(toyy);
        catToys.add(toyyy);

        Cat cat = Cat.builder().
                name("Mitzi").
                weight(35.5).
                toys(catToys).
                build();
        try {
            //Setting a response entity of cat and activating the controller post method
            final ResponseEntity<Cat> catEntity = restTemplate.
                    postForEntity(TestUrlConstants.POST_CAT_URL, cat, Cat.class);

            //Checking the cat body if it's not null
            Cat newCat = catEntity.getBody();
            if (newCat != null) {
                System.out.println(newCat);
                log.info("Test Passed!");
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    //------------------------------------------Deleting Cat--------------------------------------------------

    public boolean catDeletion() {

        try {
            //Activating the controller delete method on url
            restTemplate.delete(TestUrlConstants.CAT_DELETION_URL + "1");
            log.info("Test Passed!");
            log.info("The selected cat has been deleted!");
            return true;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    //------------------------------------------Getting a Cat--------------------------------------------------

    public boolean getCat() {

        try {
            //Setting a response entity of cat and activating the controller get method
            final ResponseEntity<Cat> catEntity = restTemplate.
                    getForEntity(TestUrlConstants.GETTING_CAT_URL + "2", Cat.class);

            //Getting a cat body
            Cat cat = catEntity.getBody();
            System.out.println(cat);
            log.info("Test Passed!");
            return cat != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    //------------------------------------------Getting all Cats--------------------------------------------------

    public boolean getAllCats() {

        try {
            //Setting a response entity of Cats and activating the controller get all method
            final ResponseEntity<Cat[]> allCats = restTemplate.
                    getForEntity(TestUrlConstants.GETTING_ALL_CATS_URL, Cat[].class);

            //Getting a cats set to an objects array body
            Cat[] cats = allCats.getBody();

            assert cats != null;
            //Placing the received body of cats set in a set variable
            Set<Cat> currentCatsList = (Set.of(cats));

            System.out.println(currentCatsList);
            log.info("Test Passed!");
            return currentCatsList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    //------------------------------------Getting all Cats By name & weight-----------------------------------

    public boolean getCatsByNameAndWeight() {

        try {
            //Setting a response entity of cats and activating the controller get all method
            final ResponseEntity<Cat[]> allCats = restTemplate.
                    getForEntity("http://localhost:8080/cats/catsByWeightAndName/50/Shmulik", Cat[].class);

            //Getting a cats set to an objects array body
            Cat[] cats = allCats.getBody();

            assert cats != null;
            //Placing the received body of cats set in a set variable
            Set<Cat> currentCatsList = (Set.of(cats));
            System.out.println(currentCatsList);
            log.info("Test Passed!");
            return currentCatsList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    //------------------------------------Getting all Cats By name or weight-----------------------------------

    public boolean getCatsByNameOrWeight() {

        try {
            //Setting a response entity of cats and activating the controller get all method
            final ResponseEntity<Cat[]> allCats = restTemplate.
                    getForEntity("http://localhost:8080/cats/catsByWeightOrName/40.0/Yoyo", Cat[].class);

            //Getting a cats set to an objects array body
            Cat[] cats = allCats.getBody();

            assert cats != null;
            //Placing the received body of cats set in a set variable
            Set<Cat> currentCatsList = (Set.of(cats));
            System.out.println(currentCatsList);
            log.info("Test Passed!");
            return currentCatsList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    //------------------------------------Getting all Cats By weight up order-----------------------------------

    public boolean getCatsByWeightUpOrder() {

        try {
            //Setting a response entity of cats and activating the controller get all method
            final ResponseEntity<Cat[]> allCats = restTemplate.
                    getForEntity(TestUrlConstants.GET_CATS_WEIGHT_BY_ORDER_UP_URL, Cat[].class);

            //Getting a cats set to an objects array body
            Cat[] cats = allCats.getBody();

            assert cats != null;
            //Placing the received body of cats set in a set variable
            List<Cat> currentCatsList = (List.of(cats));
            System.out.println(currentCatsList);
            log.info("Test Passed!");
            return currentCatsList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    //------------------------------------Getting all Cats By weight downed order-----------------------------------

    public boolean getCatsByWeightDownOrder() {

        try {
            //Setting a response entity of cats and activating the controller get all method
            final ResponseEntity<Cat[]> allCats = restTemplate.
                    getForEntity(TestUrlConstants.GET_CATS_WEIGHT_BY_ORDER_DOWN_URL, Cat[].class);

            //Getting a cats set to an objects array body
            Cat[] cats = allCats.getBody();

            assert cats != null;
            //Placing the received body of cats set in a set variable
            List<Cat> currentCatsList = (List.of(cats));
            System.out.println(currentCatsList);
            log.info("Test Passed!");
            return currentCatsList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    //------------------------------------Getting all Cats By name order-----------------------------------

    public boolean getCatsByNameOrder() {

        try {
            //Setting a response entity of cats and activating the controller get all method
            final ResponseEntity<Cat[]> allCats = restTemplate.
                    getForEntity(TestUrlConstants.GET_CATS_NAME_BY_ORDER_URL, Cat[].class);

            //Getting a cats set to an objects array body
            Cat[] cats = allCats.getBody();

            assert cats != null;
            //Placing the received body of cats set in a set variable
            List<Cat> currentCatsList = (List.of(cats));
            System.out.println(currentCatsList);
            log.info("Test Passed!");
            return currentCatsList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    //------------------------------------Getting weight avg of all cats-----------------------------------

    public boolean getCatsWeightAvg() {

        try {
            //Setting a response entity of String and activating the controller get method
            final ResponseEntity<String> avgEntity = restTemplate.
                    getForEntity(TestUrlConstants.GET_CATS_WEIGHT_AVG_URL, String.class);

            //Getting a String body
            String avg = avgEntity.getBody();
            System.out.println(avg);
            log.info("Test Passed!");
            return avg != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
