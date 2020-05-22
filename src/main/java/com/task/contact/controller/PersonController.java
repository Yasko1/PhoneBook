package com.task.contact.controller;

import com.task.contact.entity.Person;
import com.task.contact.exception.PersonNotFoundException;
import com.task.contact.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    IPersonService personService;

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id) {
        Person person = personService.getPersonById(id);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = personService.getAllPerson();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    @PostMapping("person")
    public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
        boolean flag = personService.addPerson(person);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) throws PersonNotFoundException {
        personService.deletePerson(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
