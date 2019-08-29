package com.example.harshviharresidency;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class updateprofile extends Fragment {

    EditText spinnerName, txtType, txtRefNo, txtAmount;
    Button saveProfile, updateProfile;
    EditText txtfirstname, txtlastname, txtHno, txtMobilenumber, txtemail, txtfamilymember;
    EditText txtpassword;
    TextView txtusername;
    Button btnregister;
    Spinner sUsertype;
    View view;
    DatabaseReference FCMdatabase;
    ArrayAdapter<String> adapterUsertype;
    ArrayList<String> spinnerUsertype;


    static Bundle bundle = null;

    public updateprofile() {
        bundle = null;
    }

    public static updateprofile getFrag(Bundle b) {
        updateprofile update = new updateprofile();
        bundle = b;
        return update;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_updateprofile, container, false);

        txtfirstname = (EditText) view.findViewById(R.id.txtfirstname);
        txtlastname = (EditText) view.findViewById(R.id.txtlastname);
        txtHno = (EditText) view.findViewById(R.id.txtHno);
        txtMobilenumber = (EditText) view.findViewById(R.id.txtMobilenumber);
        txtemail = (EditText) view.findViewById(R.id.txtemail);
        txtfamilymember = (EditText) view.findViewById(R.id.txtfamilymember);
        saveProfile = view.findViewById(R.id.saveProfile);
        updateProfile = view.findViewById(R.id.updateProfile);
        txtusername = (TextView) view.findViewById(R.id.txtusername);
        txtpassword = view.findViewById(R.id.txtpassword);
        btnregister = view.findViewById(R.id.btnRegister);
        fillUsertype();

        if (bundle == null) {
            saveProfile.setVisibility(View.VISIBLE);
            updateProfile.setVisibility(View.INVISIBLE);
            txtfirstname.setText("");
            txtlastname.setText("");
            txtHno.setText("");
            txtMobilenumber.setText("");
            txtemail.setText("");
            txtfamilymember.setText("");
        } else {
            saveProfile.setVisibility(View.INVISIBLE);
            updateProfile.setVisibility(View.VISIBLE);
        }
        FCMdatabase = FirebaseDatabase.getInstance().getReference("ResidentProfile");
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (bundle != null) {
            txtfirstname.setText(bundle.getString("firstname"));
            txtlastname.setText(bundle.getString("lastname"));
            txtHno.setText(bundle.getString("houseNo"));
            txtMobilenumber.setText(bundle.getString("mobile"));
            txtemail.setText(bundle.getString("emailId"));
            txtusername.setText(bundle.getString("emailId"));
            txtfamilymember.setText(bundle.getString("familyNos"));
            final String Id = bundle.getString("id");
            updateProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResidentsProfiles coll = new ResidentsProfiles(
                            txtfirstname.getText().toString(),
                            txtlastname.getText().toString(),
                            txtHno.getText().toString(),
                            txtMobilenumber.getText().toString(),
                            txtemail.getText().toString(),
                            Id.toString(),
                            Integer.parseInt(txtfamilymember.getText().toString()));
                    FCMdatabase.child(Id).setValue(coll);
                    Toast.makeText(getContext(), "User profile updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
            btnregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FCMdatabase = FirebaseDatabase.getInstance().getReference("ResidentsLogin");
                    String Id = FCMdatabase.push().getKey();
                    residentUser user = new residentUser(Id.toString(), txtusername.getText().toString(), txtpassword.getText().toString(), txtemail.getText().toString());
                    FCMdatabase.child(Id).setValue(user);
                    txtusername.setText(txtemail.getText().toString());
                    Toast.makeText(getContext(), "user registered successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String GuId = FCMdatabase.push().getKey();
                ResidentsProfiles coll = new ResidentsProfiles(
                        txtfirstname.getText().toString(),
                        txtlastname.getText().toString(),
                        txtHno.getText().toString(),
                        txtMobilenumber.getText().toString(),
                        txtemail.getText().toString(),
                        GuId.toString(),
                        Integer.parseInt(txtfamilymember.getText().toString()));
                FCMdatabase.child(GuId).setValue(coll);
                Toast.makeText(getContext(), "User profile added successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void fillUsertype() {
        spinnerUsertype = new ArrayList<String>();
        spinnerUsertype.add("Administrator");
        spinnerUsertype.add("Resident");
        adapterUsertype = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerUsertype);
        adapterUsertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sUsertype = view.findViewById(R.id.spnUserType);
        sUsertype.setAdapter(adapterUsertype);
    }
}
