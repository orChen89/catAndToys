package com.catandtoys.catAndToys.errors;

import com.catandtoys.catAndToys.enums.EntityType;

public class EntityAlreadyExistsException extends SystemException {

            public EntityAlreadyExistsException(final EntityType entityType, final Constraint constraint){

            super("This " + entityType + constraint.getErrorMsg());
        }
}
