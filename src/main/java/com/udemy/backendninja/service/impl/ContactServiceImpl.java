package com.udemy.backendninja.service.impl;

import com.udemy.backendninja.entity.Contact;
import com.udemy.backendninja.repository.ContactRepository;
import com.udemy.backendninja.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

    @Autowired
    @Qualifier("contactRepository")
    private ContactRepository contactRepository;

    @Override
    public Contact addContact(Contact contact){
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> listAllContacts(){
        return contactRepository.findAll();
    }

    @Override
    public Contact findContactById(int id) {
        return contactRepository.findById(id);
    }

    @Override
    public void removeContact(int id) {
        Contact contact = findContactById(id);
        if(null != contact){
            contactRepository.delete(contact);
        }
    }
}
