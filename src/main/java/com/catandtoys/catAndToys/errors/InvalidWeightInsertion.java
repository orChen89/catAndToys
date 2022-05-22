package com.catandtoys.catAndToys.errors;

public class InvalidWeightInsertion extends SystemException{

    public InvalidWeightInsertion(final Constraint constraint) {
        super(constraint.getErrorMsg());

    }
}
