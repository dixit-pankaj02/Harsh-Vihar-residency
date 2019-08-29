package com.example.harshviharresidency;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class Collection_list extends Fragment implements OnItemClickListener {
    DatabaseReference FCMdatabase;
    View V;
    RecyclerView recyclerView;
    CollectionListAdapter adapter;
    ArrayList<societyCollections> CollDetails = new ArrayList<societyCollections>();

    public Collection_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V = inflater.inflate(R.layout.fragment_collection_list, container, false);
        recyclerView = V.findViewById(R.id.recyclerCollectionView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        clear();
        adapter = new CollectionListAdapter(getActivity(), CollDetails,this);
        FCMdatabase = FirebaseDatabase.getInstance().getReference("SocietyCollection");
        Query query = FCMdatabase.orderByChild("houseNumber");
        query.addValueEventListener(postListner);
        recyclerView.setAdapter(adapter);
        return V;
    }

    @Override
    public void onItemClick(String Id, Bundle bundle) {
        if (bundle != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentLayout, CollectionEntries.getFrag(bundle), "COLLECTION_Entry");
            fragmentTransaction.addToBackStack(null);
            getFragmentManager().executePendingTransactions();
            fragmentTransaction.commit();
        }
    }

    ValueEventListener postListner = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.getChildrenCount() > 0) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    societyCollections postdata = postSnapshot.getValue(societyCollections.class);
                    CollDetails.add(postdata);
                    adapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed: " + databaseError.getCode());
        }
    };

    public void clear() {
        final int size = CollDetails.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                CollDetails.remove(0);
            }
        }
    }
}
