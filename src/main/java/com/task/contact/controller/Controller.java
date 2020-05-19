package com.task.contact.controller;

import com.task.contact.entity.Contact;
import com.task.contact.entity.Person;
import com.task.contact.exception.PersonNotFoundException;
import com.task.contact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    PersonRepository personRepository;

    //get all persons with their contacts
    @GetMapping("/getall")
    public ResponseEntity<List<Person>> getAllCustomers() {
        return new ResponseEntity<List<Person>>
                ((List<Person>) personRepository.findAll(),
                        HttpStatus.OK);
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

    // create person
    @PostMapping("/create-person")
    public Person createNote(@RequestBody Person person) {
        return personRepository.save(person);
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
    public Person updateNote(@PathVariable(value = "id") int personId,
                              @RequestBody Person personDetails) throws PersonNotFoundException {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        person.setName(personDetails.getName());

        Person updatedperson = personRepository.save(person);
        return updatedperson;
    }

    // delete person by id with all contacts
    @DeleteMapping("/persons/{id}")
    public ResponseEntity deletePerson(@PathVariable(value = "id") int personId) throws PersonNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        personRepository.delete(person);
        return ResponseEntity.ok().build();
    }
}
