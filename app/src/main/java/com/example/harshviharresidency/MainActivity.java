package com.example.harshviharresidency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.harshviharresidency.UIUtils.ActionItem;
import com.example.harshviharresidency.UIUtils.QuickAction;
import com.example.harshviharresidency.preferences.Provider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private home_fragment home;
    private User_registration userRegistration;
    private updateprofile updateprofile;
    SharedViewModel model;
    TextView txtWelcome, txtBack;
    ImageView imgBack;
    FragmentManager fm;
    ImageView imgfive;
    //action id
    private static final int ID_USER_PROFILE = 1;
    private static final int ID_CHANGE_PWD = 2;
    private static final int ID_SIGN_OUT = 3;
    QuickAction quickAction;
    FragmentTransaction ft;
    DatabaseReference myRef;
    ResidentsProfiles userDetails;

    ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWelcome = findViewById(R.id.welcomeUser);
        imgBack = findViewById(R.id.imgBack);
        txtBack = findViewById(R.id.txtBack);
        imgfive = findViewById(R.id.imgfive);
        Intent intent = getIntent();
        String message = intent.getStringExtra("TYPE");
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        if (savedInstanceState == null) {
            displayFragment(message, fm);
        }

        String name = Provider.getStorage().getEmail();
        myRef = FirebaseDatabase.getInstance().getReference();
        final Query query = myRef.child("ResidentProfile").orderByChild("emailId").equalTo(name.toLowerCase());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        ResidentsProfiles reUser = postSnapshot.getValue(ResidentsProfiles.class);
                        fillUserDetails(reUser);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        quickAction = new QuickAction(this, QuickAction.VERTICAL);
        showOptionMenuItems();
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new home_fragment(), "HOME");
                fragmentTransaction.addToBackStack(null);
                getSupportFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new home_fragment(), "HOME");
                fragmentTransaction.addToBackStack(null);
                getSupportFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        imgfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quickAction.show(v);
            }
        });
    }


    protected void displayFragment(String Type, FragmentManager fm) {
        if (Type.equals("HOME")) { // if the fragment is already in container
            ft.add(R.id.fragmentLayout, new home_fragment(), "HOME");
        }
        if (Type.equals("REGISTER")) {
            ft.add(R.id.fragmentLayout, new User_registration(), "register");
        }
        if (Type.equals("LOGIN")) {
            ft.add(R.id.fragmentLayout, new User_registration(), "login");
        }
        ft.commit();
    }

    private void showOptionMenuItems() {
        ActionItem user_profile = new ActionItem(ID_USER_PROFILE, "User Profile");
        ActionItem change_pwd = new ActionItem(ID_CHANGE_PWD, "Change Password");
        ActionItem sign_out = new ActionItem(ID_SIGN_OUT, "Logout");

        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
        user_profile.setSticky(false);
        change_pwd.setSticky(false);
        sign_out.setSticky(false);

        quickAction.addActionItem(user_profile);
        quickAction.addActionItem(change_pwd);
        quickAction.addActionItem(sign_out);

        //Set listener for action item clicked
        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);
                //here we can filter which action item was clicked with pos or actionId parameter
                if (actionId == ID_USER_PROFILE) {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentLayout, new userprofile(), "USERPROFILE");
                    fragmentTransaction.addToBackStack(null);
                    getFragmentManager().executePendingTransactions();
                    fragmentTransaction.commit();
                } else if (actionId == ID_CHANGE_PWD) {
                    Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                } else if (actionId == ID_SIGN_OUT) {
                    logOut();
                }
            }
        });
    }

    public void fillUserDetails(ResidentsProfiles resUser) {
        if (resUser != null) {
            Provider.getStorage().setUserFName(resUser.getFirstName());
            Provider.getStorage().setUserLName(resUser.getLastName());
            Provider.getStorage().setFullName(resUser.getFirstName() + " " + resUser.getLastName());
            Provider.getStorage().setEmail(resUser.getEmailId());
            Provider.getStorage().setMobileNo(resUser.getMobileNumber());
            Provider.getStorage().setHouseNo(resUser.getHouseNumber());
            Provider.getStorage().setUserId(resUser.getId());
            Provider.getStorage().setFamilyMember(String.valueOf(resUser.getFamilyMember()));

            txtWelcome.setText("Welcome " + Provider.getStorage().getFullName());
        }
    }

    private void logOut() {
        if (Provider.getStorage().getUserFName() != null
                && Provider.getStorage().getUserLName().length() > 0) {
            Provider.getStorage().setHouseNo(null);
            Provider.getStorage().setUserId(null);
            Provider.getStorage().setFullName(null);
            Provider.getStorage().setMobileNo(null);
            Provider.getStorage().setFamilyMember(null);
            Provider.getStorage().setUserFName(null);
            Provider.getStorage().setUserLName(null);
            Provider.getStorage().setProfileImage(null);
            Provider.getStorage().setEmail(null);
        }
        startActivity(new Intent(this.getApplicationContext(), login_screen.class));
        finish();
    }
}
