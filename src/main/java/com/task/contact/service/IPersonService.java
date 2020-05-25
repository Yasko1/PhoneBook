package com.task.contact.service;

import com.task.contact.entity.Person;
import com.task.contact.mapper.dto.PersonDTO;
import com.task.contact.exception.PersonNotFoundException;

import java.util.List;

/**
 * Interface provides methods to work with {@link com.task.contact.repository.PersonRepository} objects.
 */
public interface IPersonService {

    /**
     * Method designed for searching persons in database.
     *
     * @return a {@link List} implementation with a {@link Person} objects.
     */
    List<PersonDTO> getAll();

    /**
     * The method searches for user with given identifier.
     *
     * @param id an object identifier in database
     * @return a {@link Person} implementation with object.
     */
    public PersonDTO getPersonById(Integer id);

    /**
     * The method searches for user with given identifier.
     *
     * @param name an object identifier in database
     * @return a {@link Person} implementation with object.
     */
    public List<PersonDTO> getPersonByName(String name);

    /**
     * Method designed for searching persons in database.
     *
     * @return a {@link List} implementation with a {@link Person} objects.
     */
    public List<PersonDTO> getAllPerson();

    /**
     * Method designed for adding new {@link Person} object.
     */
    public boolean addPerson(Person person);

    /**
     * Method designed for updating new {@link Person} object.
     */
    public void updatePerson(Integer personId, Person person) throws PersonNotFoundException;

    /**
     * Method designed for deleting {@link Person} object.
     *
     * @throws PersonNotFoundException
     */
    public void deletePerson(Integer id) throws PersonNotFoundException;
}
