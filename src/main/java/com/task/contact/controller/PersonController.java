package com.task.contact.controller;

import com.task.contact.entity.Person;
import com.task.contact.mapper.dto.PersonDTO;
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

    @GetMapping("/all")
    public List<PersonDTO> getAllPersons() {
        return personService.getAll();
    }

    @GetMapping("/personByName")
    public List<PersonDTO> getByName(@ModelAttribute("name") String name) {
        List<PersonDTO> person = personService.getPersonByName(name);
        return person;
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Integer id) {
        PersonDTO person = personService.getPersonById(id);
        return new ResponseEntity<PersonDTO>(person, HttpStatus.OK);
    }

    @PostMapping("/create-person")
    public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
        boolean flag = personService.addPerson(person);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable(value = "id") Integer personId,
                                             @RequestBody Person personDetails) throws PersonNotFoundException {
        personService.updatePerson(personId, personDetails);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerson(@ModelAttribute("id") Integer id) throws PersonNotFoundException {
        personService.deletePerson(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
