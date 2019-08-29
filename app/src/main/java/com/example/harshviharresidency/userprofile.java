package com.example.harshviharresidency;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.harshviharresidency.preferences.Provider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class userprofile extends Fragment {

    Button updateProfile;
    EditText txtfirstname, txtlastname, txtHno, txtMobilenumber, txtfamilymember;
    TextView lblEmail;
    View view;
    DatabaseReference FCMdatabase;

    static Bundle bundle = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        txtfirstname = (EditText) view.findViewById(R.id.txtfirstname);
        txtlastname = (EditText) view.findViewById(R.id.txtlastname);
        txtHno = (EditText) view.findViewById(R.id.txtHno);
        txtMobilenumber = (EditText) view.findViewById(R.id.txtMobilenumber);
        lblEmail = (TextView) view.findViewById(R.id.txtemail);
        txtfamilymember = (EditText) view.findViewById(R.id.txtfamilymember);
        updateProfile = view.findViewById(R.id.updateProfile);
        FCMdatabase = FirebaseDatabase.getInstance().getReference("ResidentProfile");
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (Provider.getStorage() != null) {
            txtfirstname.setText(Provider.getStorage().getUserFName());
            txtlastname.setText(Provider.getStorage().getUserLName());
            txtHno.setText(Provider.getStorage().getHouseNo());
            txtMobilenumber.setText(Provider.getStorage().getMobileNo());
            lblEmail.setText(Provider.getStorage().getEmail());
            txtfamilymember.setText(Provider.getStorage().getFamilyMember());

            final String Id = Provider.getStorage().getUserId();
            updateProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResidentsProfiles coll = new ResidentsProfiles(
                            txtfirstname.getText().toString(),
                            txtlastname.getText().toString(),
                            txtHno.getText().toString(),
                            txtMobilenumber.getText().toString(),
                            lblEmail.getText().toString(),
                            Id.toString(),
                            Integer.parseInt(txtfamilymember.getText().toString()));
                    FCMdatabase.child(Id).setValue(coll);
                    Toast.makeText(getContext(), "User profile updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
