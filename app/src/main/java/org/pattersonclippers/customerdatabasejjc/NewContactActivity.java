package org.pattersonclippers.customerdatabasejjc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class NewContactActivity extends AppCompatActivity {

    EditText nameET, emailET, phoneET, zipcodeET;
    CheckBox getEmailsCB;
    Button addContactBTN, exitActBTN;
    String contactName, contactEmail, contactPhone;
    boolean emailSettings;
    int contactZipcode;

    Customer newCustomer;

    final String TAG = "L+RATIO";

    //write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        nameET = (EditText) findViewById(R.id.nameET);
        emailET = (EditText) findViewById(R.id.emailET);
        phoneET = (EditText) findViewById(R.id.phoneET);
        zipcodeET = (EditText) findViewById(R.id.zipcodeET);
        getEmailsCB = (CheckBox) findViewById(R.id.getEmailsCB);
        addContactBTN = (Button) findViewById(R.id.addContactBTN);
        exitActBTN = (Button) findViewById(R.id.exitActBTN);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Customer");

        addContactBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactName = nameET.getText().toString();
                contactEmail = emailET.getText().toString();
                contactPhone = phoneET.getText().toString();
                contactZipcode = Integer.parseInt(zipcodeET.getText().toString());
                emailSettings = getEmailsCB.isChecked();

                newCustomer = new Customer(contactName, contactEmail, contactPhone, contactZipcode, emailSettings);
                String key = myRef.push().getKey();
                myRef.child(key).setValue(newCustomer);

                // Read from the database
                /*myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });*/
            }
        });

        exitActBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}