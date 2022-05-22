package com.catandtoys.catAndToys.errors;


import com.catandtoys.catAndToys.enums.EntityType;

public class EntityNotExistException extends SystemException {

           public EntityNotExistException(final EntityType entityType, final Constraint constraint) {

            super("This " + entityType + constraint.getErrorMsg());
        }
}
