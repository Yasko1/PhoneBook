package com.task.contact.mapper;

import com.task.contact.entity.Contact;
import com.task.contact.mapper.dto.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper CONTACTS_MAPPER = Mappers.getMapper(ContactMapper.class);

    //ContactDTO fromContact(Person person);

    ContactDTO toContact(Contact contact);
}
