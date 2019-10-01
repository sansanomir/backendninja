package com.udemy.backendninja.service;

import com.udemy.backendninja.entity.Contact;

import java.util.List;


public interface ContactService {
    public abstract Contact addContact(Contact contact);
    public abstract List<Contact> listAllContacts();
    public abstract Contact findContactById(int id);
    public abstract void removeContact(int id);
}
