package com.raja.dbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ContactManagerOperations contactManager;

    //Ref of UI Elements
    private EditText userName;
    private EditText userMobileNumber;
    private Button addButton;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.contactname);
        userMobileNumber = (EditText)findViewById(R.id.mobileno);
        addButton = (Button)findViewById(R.id.addb);
        output = (TextView)findViewById(R.id.output);

        contactManager = new ContactDBHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
                updateDisplay();
            }
        });
    }
    public void readData(){
        String name = userName.getText().toString();
        String mobilNo = userMobileNumber.getText().toString();
        Contact contact = new Contact(name,mobilNo);
        contactManager.addContact(contact);
    }
    public void updateDisplay(){
        String textToSet = "";
        List<Contact> allContacts = contactManager.getAllContacts();
        for(Contact contact:allContacts) {
            textToSet = textToSet + contact.getName() + "\n";
            textToSet = textToSet + contact.getMobileNo() + "\n\n\n";
        }
        output.setText(textToSet);
    }
}
