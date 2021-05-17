package com.raja.dbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ContactDBHelper extends SQLiteOpenHelper implements ContactManagerOperations{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "contactsdb";
    private static final String TABLE_NAME = "contactstable";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String MOBILE_NO = "mobileNo";

    public ContactDBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY, "+NAME+" TEXT, "+MOBILE_NO+" TEXT)";
        db.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(QUERY);
    }

    @Override
    public boolean addContact(Contact contact) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAME, contact.getName());
            values.put(MOBILE_NO, contact.getMobileNo());
            db.insert(TABLE_NAME, null, values);
            System.out.println("Contacts Added");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Contact> getAllContacts() {

        List<Contact> listOfContacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(QUERY,null);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact("","");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setMobileNo(cursor.getString(2));
                listOfContacts.add(contact);
            }while (cursor.moveToNext());
        }
        System.out.println("GETTING ALL CONTACTS");
        return listOfContacts;
    }

    @Override
    public Contact getContactByName(String name) {
        return null;
    }
}
