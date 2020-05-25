package com.task.contact.mapper;

import com.task.contact.entity.Person;
import com.task.contact.mapper.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper PERSON_MAPPER = Mappers.getMapper(PersonMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "contacts", target = "contacts")
    })
    PersonDTO toPersonDto(Person person);

    List<PersonDTO> toListPersons(List<Person> person);
}
