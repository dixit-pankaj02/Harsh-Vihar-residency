package com.example.harshviharresidency;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class residentList extends Fragment implements OnItemClickListener {

    View view;
    RecyclerView recyclerView;
    ResidentListAdapter adapter;
    ArrayList<ResidentsProfiles> personNames = new ArrayList<ResidentsProfiles>();
    DatabaseReference myRef;
    RelativeLayout relLayoutResidentlist;
    Bundle b;

    public residentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_resident_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        relLayoutResidentlist = view.findViewById(R.id.relLayoutResidentlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRef = FirebaseDatabase.getInstance().getReference().child("ResidentProfile");
        Query query = myRef.orderByChild("houseNumber");
        query.addValueEventListener(postListner);
        clear();
        adapter = new ResidentListAdapter(getActivity(), personNames, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String Id, Bundle bundle) {
        if (bundle != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentLayout, updateprofile.getFrag(bundle), "PROFILE");
            fragmentTransaction.addToBackStack(null);
            getFragmentManager().executePendingTransactions();
            fragmentTransaction.commit();
        }
    }

    public void clear() {
        final int size = personNames.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                personNames.remove(0);
            }
        }
    }

    ValueEventListener postListner = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.getChildrenCount() > 0) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ResidentsProfiles postdata = postSnapshot.getValue(ResidentsProfiles.class);
                    personNames.add(postdata);
                    adapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed: " + databaseError.getCode());
        }
    };

}
