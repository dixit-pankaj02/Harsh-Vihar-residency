package com.example.harshviharresidency;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Expenses_list extends Fragment implements OnItemClickListener {

    DatabaseReference FCMdatabase = FirebaseDatabase.getInstance().getReference("SocietyExpense");
    View V;
    RecyclerView recyclerView;
    ExpenseListAdapter adapter;
    ArrayList<societyExpenses> expensesDetails = new ArrayList<societyExpenses>();
    societyExpenses postdata = null;

    public Expenses_list() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        V = inflater.inflate(R.layout.fragment_expenses_list, container, false);
        recyclerView = V.findViewById(R.id.recyclerExpenseView);
        return V;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        FCMdatabase.addValueEventListener(postListner);
        clear();
        adapter = new ExpenseListAdapter(getActivity(), expensesDetails, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String Id, Bundle bundle) {
        if (bundle != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentLayout, ExpensesEntries.getFrag(bundle), "EXPENSES_Entry");
            fragmentTransaction.addToBackStack("EXPENSES_Entry");
            getFragmentManager().executePendingTransactions();
            fragmentTransaction.commit();
        }
    }

    ValueEventListener postListner = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.getChildrenCount() > 0) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    postdata = postSnapshot.getValue(societyExpenses.class);
                    expensesDetails.add(postdata);
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
        final int size = expensesDetails.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                expensesDetails.remove(0);
            }
        }
    }
}
