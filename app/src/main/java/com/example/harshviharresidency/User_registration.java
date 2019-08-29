package com.example.harshviharresidency;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class User_registration extends Fragment {
    View view;
    DatabaseProcessor database;
    List<residentUser> residentUser;
    ArrayAdapter adapter;
    String[] employeeArray;
    ListView listView;
    EditText txtusername, txtpassword;
    Button btnregister;
    DatabaseReference FCMdatabase;

    public User_registration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        database = new DatabaseProcessor(this.getContext());
        view = inflater.inflate(R.layout.fragment_user_registration, container, false);
        txtusername = view.findViewById(R.id.txtusername);
        txtpassword = view.findViewById(R.id.txtpassword);
        btnregister = view.findViewById(R.id.btnRegister);
        FCMdatabase = FirebaseDatabase.getInstance().getReference("ResidentsLogin");

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences preferences = getActivity().getSharedPreferences("userdata", getActivity().MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//
//                editor.putString("username", txtusername.getText().toString());
//                editor.putString("password", txtpassword.getText().toString());
//                editor.commit();
//                database.registerUser(txtusername.getText().toString(), txtpassword.getText().toString());

                String Id = FCMdatabase.push().getKey();
                residentUser user = new residentUser(Id.toString(), txtusername.getText().toString(), txtpassword.getText().toString(), "");
                FCMdatabase.child(Id).setValue(user);
                Toast.makeText(getContext(), "user added successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
