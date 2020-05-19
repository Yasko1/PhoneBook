package com.task.contact.exception;

public class PersonNotFoundException extends Exception {
    private int person_id;

    public PersonNotFoundException(String person_name) {
        super(String.format("Person is not found with name : '%s'", person_name));
    }

    public PersonNotFoundException(int person_id) {
        super(String.format("Book is not found with id : '%s'", person_id));
    }
}
