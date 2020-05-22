package com.task.contact.service;

import com.task.contact.entity.Person;
import com.task.contact.exception.PersonNotFoundException;

import java.util.List;

/**
 * Interface provides methods to work with {@link com.task.contact.repository.PersonRepository} objects.
 */
public interface IPersonService {

    /**
     * The method searches for user with given identifier.
     *
     * @param id an object identifier in database
     * @return a {@link Person} implementation with object.
     */
    public Person getPersonById(Integer id);

    /**
     * Method designed for searching persons in database.
     *
     * @return a {@link List} implementation with a {@link Person} objects.
     */
    public List<Person> getAllPerson();

    /**
     * Method designed for adding new {@link Person} object.
     *
     */
    public boolean addPerson(Person person);

    /**
     * Method designed for updating new {@link Person} object.
     *
     */
    public void updatePerson(Person person);

    /**
     * Method designed for deleting {@link Person} object.
     *
     * @throws PersonNotFoundException
     */
    public void deletePerson(Integer id) throws PersonNotFoundException;
}
