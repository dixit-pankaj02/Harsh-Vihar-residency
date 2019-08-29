package com.example.harshviharresidency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harshviharresidency.preferences.IStorage;
import com.example.harshviharresidency.preferences.PreferenceImpl;
import com.example.harshviharresidency.preferences.Provider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class login_screen extends AppCompatActivity {
    //    DatabaseProcessor database;
    Button btnlogin, btnhome, btnregister;
    String FRAG_REGISTER = "REGISTER", FRAG_HOME = "HOME", FRAG_LOGIN = "LOGIN";
    EditText txtusername, txtpassword;
    //    FirebaseDatabase database;
    DatabaseReference myRef;
    public String TAG = "LoginActivity";
    residentUser reUser;
    ArrayList<residentUser> userDetails = new ArrayList<>();
    boolean IsExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        View relLaout = (View) findViewById(R.id.login_frame);
        btnlogin = findViewById(R.id.btnlogin);
//        btnhome = findViewById(R.id.btnhome);
//        btnregister = findViewById(R.id.btnregister);
        txtusername = findViewById(R.id.txtusername);
        txtpassword = findViewById(R.id.txtpassword);
//        database = new DatabaseProcessor(this);
        // Write a message to the database

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intentregister = new Intent(getApplicationContext(), MainActivity.class);
                intentregister.putExtra("TYPE", FRAG_HOME);
                startActivity(intentregister);*/
                final String userName = txtusername.getText().toString();
                userDetails.clear();
                if (userName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter user name", Toast.LENGTH_SHORT).show();
                }
                if (txtpassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter password", Toast.LENGTH_SHORT).show();
                }
                if (!userName.isEmpty() && !txtpassword.getText().toString().isEmpty()) {
                    myRef = FirebaseDatabase.getInstance().getReference();
                    final Query query = myRef.child("ResidentsLogin").orderByChild("username");
                    query.equalTo(userName.toLowerCase());
                    if (userDetails.size() == 0) {
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getChildrenCount() > 0) {
                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                        reUser = postSnapshot.getValue(residentUser.class);
                                        userDetails.add(reUser);
                                    }
                                }
                                loginSuccess(userDetails);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });
                    }
                }
            }
        });
//        btnhome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
//                intenthome.putExtra("TYPE", FRAG_HOME);
//                startActivity(intenthome);
//            }
//        });
//        btnregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentregister = new Intent(getApplicationContext(), MainActivity.class);
//                intentregister.putExtra("TYPE", FRAG_REGISTER);
//                startActivity(intentregister);
//            }
//        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.getChildrenCount() > 0) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    reUser = postSnapshot.getValue(residentUser.class);
                    userDetails.add(reUser);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed: " + databaseError.getCode());
        }
    };

    public boolean isValid(ArrayList<residentUser> resUser) {
        residentUser objResUser;
        for (int i = 0; i < resUser.size(); i++) {
            objResUser = resUser.get(i);
            if (objResUser.getUsername().equals(txtusername.getText().toString()) && objResUser.getPassword().equals(txtpassword.getText().toString())) {
                IsExist = true;
                break;
            } else
                IsExist = false;
        }
        return IsExist;
    }

    public void loginSuccess(ArrayList<residentUser> resUser) {
        IStorage storage = IStorage.Factory.get();
        ((PreferenceImpl) storage).init(getApplicationContext());
        if (resUser != null && resUser.size() > 0) {
            if (isValid(resUser)) {
                Provider.getStorage().setEmail(txtusername.getText().toString());
                Intent intentregister = new Intent(getApplicationContext(), MainActivity.class);
                intentregister.putExtra("TYPE", FRAG_HOME);
                startActivity(intentregister);
            } else
                Toast.makeText(getApplicationContext(), "Invalid user name or password", Toast.LENGTH_SHORT).show();
        }
    }
}
