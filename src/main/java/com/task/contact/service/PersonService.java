package com.task.contact.service;

import com.task.contact.entity.Person;
import com.task.contact.mapper.dto.PersonDTO;
import com.task.contact.exception.PersonNotFoundException;
import com.task.contact.mapper.PersonMapper;
import com.task.contact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<PersonDTO> getAll() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(e -> list.add(e));
        return PersonMapper.PERSON_MAPPER.toListPersons(list);
    }

    @Override
    public PersonDTO getPersonById(Integer id) {
        Person obj = personRepository.findById(id).get();
        return PersonMapper.PERSON_MAPPER.toPersonDto(obj);
    }

    @Override
    public List<PersonDTO> getAllPerson() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(e -> list.add(e));
        return PersonMapper.PERSON_MAPPER.toListPersons(list);
    }

    // get person by name and his all contacts
    public List<PersonDTO> getPersonByName(@PathVariable(value = "name") String personName) {
        /*List<Person> customers = (List<Person>) personRepository.findAll();
        List<PersonDTO> personDTOS = new ArrayList<>();
        for(Person person:customers){
            if(personName.equals(person.getName())){
                personDTOS.add(person);
            }
        }*/
        List<Person> list = personRepository.findByName(personName);
        return PersonMapper.PERSON_MAPPER.toListPersons(list);
    }

    @Override
    public boolean addPerson(@RequestBody Person person) {
        if (person.getName() == null) {
            return false;
        } else {
            personRepository.save(person);
            return true;
        }
    }

    //create contact for user(using his id)
    /*@PostMapping("/create-contact/{name}")
    public Person createContact(@PathVariable(value = "name")String name,
                                @RequestBody Contact contacts) throws PersonNotFoundException {
        Person person = getPersonByName(name);
        person.addContacts(contacts);
        return personRepository.save(person);
    }*/

    // update
    public void updatePerson(Integer personId,
                             @RequestBody Person personDetails) throws PersonNotFoundException {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        person.setName(personDetails.getName());

        Person updatedperson = personRepository.save(person);
    }

    // delete person by id with all contacts
    @Override
    public void deletePerson(@RequestBody Integer personId) throws PersonNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        personRepository.delete(person);
    }
}
