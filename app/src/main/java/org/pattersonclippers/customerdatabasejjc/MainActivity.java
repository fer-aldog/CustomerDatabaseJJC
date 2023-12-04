package org.pattersonclippers.customerdatabasejjc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView nameTV, emailTV, phoneTV, zipcodeTV, marketEmailTV, contactCount;
    Button travelActBTN;
    ImageButton prevBTN, nextBTN;
    int currentIndex;
    ArrayList<Customer> myCustomers;
    final String TAG = "L+RATIO";

    //write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTV = (TextView) findViewById(R.id.nameTV);
        emailTV = (TextView) findViewById(R.id.emailTV);
        phoneTV = (TextView) findViewById(R.id.phoneTV);
        zipcodeTV = (TextView) findViewById(R.id.zipcodeTV);
        marketEmailTV = (TextView) findViewById(R.id.marketEmailTV);
        contactCount = (TextView) findViewById(R.id.contactCount);
        travelActBTN = (Button) findViewById(R.id.travelActBTN);
        prevBTN = (ImageButton) findViewById(R.id.prevBTN);
        nextBTN = (ImageButton) findViewById(R.id.nextBTN);
        myCustomers = new ArrayList<Customer>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Customer");

        //Create a new listener that gets all of the Customers in a single call to the database
        ValueEventListener allCustomerQueryEventListener =
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        // Iterate through all the children in the snapshot, this should be
                        // all the children in the "customers" object
                        for (DataSnapshot customerSnapShot : snapshot.getChildren()) {

                            //From our snapshot, get the value of our key/value pair. This value
                            //contains a customer object
                            Customer myCustomer = customerSnapShot.getValue(Customer.class);

                            Log.d("onDataChange()", "New Customer: " + myCustomer.getName());
                            myCustomers.add(myCustomer);
                        }

                        //Check if we have any customers
                        if (myCustomers.size() > 0) {
                            //Set the current index to 0, which is the first entry in the array
                            currentIndex = 0;
                            //Get the first customer
                            Customer firstCustomer = myCustomers.get(currentIndex);
                            nameTV.setText(firstCustomer.getName());
                            emailTV.setText(firstCustomer.getEmail());
                            phoneTV.setText(firstCustomer.getPhone());
                            zipcodeTV.setText("" + firstCustomer.getZipcode());
                            //save boolean settings to a string
                            marketEmailTV.setText("" + firstCustomer.getEmailSettings());

                        }
                        //Check if we have more than 1 customer
                        /*if (myCustomers.size() > 1) {
                            //If we do, make the NEXT button visible so you can navigate to a different customer
                            binding.buttonNext.setVisibility(View.VISIBLE);
                        }*/

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    }
                };

        myRef.addValueEventListener(allCustomerQueryEventListener);

        travelActBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivity(myIntent);
            }
        });
    }
}