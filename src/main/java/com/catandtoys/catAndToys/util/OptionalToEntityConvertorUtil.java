package com.catandtoys.catAndToys.util;

import com.catandtoys.catAndToys.model.Cat;
import com.catandtoys.catAndToys.model.Toy;

import java.util.Optional;

public class OptionalToEntityConvertorUtil {

    public static Cat optionalCat(final Optional<Cat> cat) {

        return cat.orElse(null);
    }

    public static Toy optionalToy(final Optional<Toy> toy) {

        return toy.orElse(null);
    }
}
