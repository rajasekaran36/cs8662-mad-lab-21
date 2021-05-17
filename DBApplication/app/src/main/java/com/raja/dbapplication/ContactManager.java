package com.raja.dbapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactManager implements ContactManagerOperations{
    private List<Contact> contacts;
    private ContactDBHelper dbHelper;
    public ContactManager(Context context){
        contacts = new ArrayList<>();
        dbHelper = new ContactDBHelper(context);
    }

    @Override
    public boolean addContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            contacts.add(contact);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public Contact getContactByName(String name) {
        for(Contact contact:contacts){
            if(contact.getName().equals(name))
                return contact;
        }
        return null;
    }
}
