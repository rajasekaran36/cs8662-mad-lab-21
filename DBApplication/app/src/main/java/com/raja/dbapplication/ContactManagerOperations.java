package com.raja.dbapplication;

import java.util.List;

public interface ContactManagerOperations{
    public boolean addContact(Contact contact);
    public List<Contact> getAllContacts();
    public Contact getContactByName(String name);
}
