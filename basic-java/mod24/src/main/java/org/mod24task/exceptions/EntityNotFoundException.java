package org.mod24task.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(){

    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
