package com.task.contact;

import com.task.contact.entity.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RestTest {
    public void getPersonByIdDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/person/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Person.class, 1);
        Person person = responseEntity.getBody();
        System.out.println("Id:"+person.getId()+", Name:"+person.getName()
                +", Contacts:"+person.getContacts());
    }
    public void getAllPersonsDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/persons";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Person[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Person[].class);
        Person[] persons = responseEntity.getBody();
        for(Person person : persons) {
            System.out.println("Id:"+person.getId()+", Name:"+person.getName()
                    +", Contacts:"+person.getContacts());
        }
    }
    public void addPersonDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/person";
        Person objArticle = new Person();
        objArticle.setName("Spring");
        HttpEntity<Person> requestEntity = new HttpEntity<Person>(objArticle, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }
    public void updatePersonDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/person";
        Person objArticle = new Person();
        objArticle.setName("Java");
        HttpEntity<Person> requestEntity = new HttpEntity<Person>(objArticle, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deletePersonDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/person/{id}";
        HttpEntity<Person> requestEntity = new HttpEntity<Person>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
    }
    public static void main(String args[]) {
        System.out.println("main");
        RestTest util = new RestTest();
        System.out.println("add");
        util.addPersonDemo();
        System.out.println("update");
        util.updatePersonDemo();
        System.out.println("delete");
        util.deletePersonDemo();
        System.out.println("all");
        util.getAllPersonsDemo();
    }

}
