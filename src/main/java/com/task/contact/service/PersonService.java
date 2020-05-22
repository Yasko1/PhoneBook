package com.task.contact.service;

import com.task.contact.entity.Contact;
import com.task.contact.entity.Person;
import com.task.contact.exception.PersonNotFoundException;
import com.task.contact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person getPersonById(Integer id) {
        Person obj = personRepository.findById(id).get();
        return obj;
    }

    @Override
    @GetMapping("/getall")
    public List<Person> getAllPerson() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    // get person by name and his all contacts
    @GetMapping("/person/{name}")
    public Person getNoteByName(@PathVariable(value = "name") String personName)
            throws PersonNotFoundException {
        List<Person> customers = (List<Person>) personRepository.findAll();
        int id=0;
        for(Person person:customers){
            if(personName.equals(person.getName())){
                id=person.getId();
            }
        }
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(personName));
    }

    @Override
    @PostMapping("/create-person")
    public boolean addPerson(@RequestBody Person person) {
        Integer searchId = person.getId();
        List<Person> list = (List<Person>) personRepository.findByName(person.getName());
        if (list.size() > 0) {
            return false;
        } else {
            personRepository.save(person);
            return true;
        }
    }

    //create contact for user(using his id)
    @PostMapping("/create-contact/{name}")
    public Person createContact(@PathVariable(value = "name")String name,
                                @RequestBody Contact contacts) throws PersonNotFoundException {
        Person person = getNoteByName(name);
        person.addContacts(contacts);
        return personRepository.save(person);
    }

    // update
    @PutMapping(value="/persons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePerson(@PathVariable(value = "id") int personId,
                             @RequestBody Person personDetails) throws PersonNotFoundException {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        person.setName(personDetails.getName());

        Person updatedperson = personRepository.save(person);
    }

    @Override
    public void updatePerson(Person article) {
        personRepository.save(article);
    }

    // delete person by id with all contacts
    @Override
    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable(value = "id") Integer personId) throws PersonNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        personRepository.delete(person);
    }
}
