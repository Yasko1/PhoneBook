package com.task.contact.mapper.dto;

import com.task.contact.entity.Contact;

import java.util.Set;

public class PersonDTO {

    private Integer Id;
    private String name;
    private Set<Contact> contacts;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
