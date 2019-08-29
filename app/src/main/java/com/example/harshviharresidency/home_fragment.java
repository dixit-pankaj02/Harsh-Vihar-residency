package com.example.harshviharresidency;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    LinearLayout linearLayout_profile, societyComplains, layoutExpenseEntry, layoutCollectionEntry;
    LinearLayout layoutResidentList, layoutSocietyExpenses, layoutSocietyCollection;
    SharedViewModel model;
    TextView Titletext;
    View V;

    public home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        imageView1 = V.findViewById(R.id.imgone);
        imageView2 = V.findViewById(R.id.imgtwo);
        imageView3 = V.findViewById(R.id.imgthree);
        imageView4 = V.findViewById(R.id.imgfour);
        imageView5 = V.findViewById(R.id.imgfive);
        imageView6 = V.findViewById(R.id.imgsix);
        linearLayout_profile = V.findViewById(R.id.layout_profile);
        layoutExpenseEntry = V.findViewById(R.id.layoutExpenseEntry);
        layoutCollectionEntry = V.findViewById(R.id.layoutCollectionEntry);
        layoutResidentList = V.findViewById(R.id.layoutResidentList);
        layoutSocietyExpenses = V.findViewById(R.id.layoutSocietyExpenses);
        layoutSocietyCollection = V.findViewById(R.id.layoutSocietyCollection);
        societyComplains = V.findViewById(R.id.SocietyComplains);
//        Titletext = V.findViewById(R.id.Titletext);
        return V;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearLayout_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new updateprofile(), "PROFILE");
                fragmentTransaction.addToBackStack(null);
                getFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        layoutExpenseEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new ExpensesEntries(), "EXPENSES_Entry");
                fragmentTransaction.addToBackStack(null);
                getFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        layoutCollectionEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new CollectionEntries(), "COLLECTION_Entry");
                fragmentTransaction.addToBackStack(null);
                getFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        layoutResidentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new residentList(), "ResidentList");
                fragmentTransaction.addToBackStack(null);
                getFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        layoutSocietyExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new Expenses_list(), "ExpensesList");
                fragmentTransaction.addToBackStack(null);
                getFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        layoutSocietyCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentLayout, new Collection_list(), "CollectionList");
                fragmentTransaction.addToBackStack(null);
                getFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        });
        GlideApp
                .with(getActivity())
                .load(R.drawable.ic_mobile_layout_user)
                .centerCrop()
                .fitCenter()
                .circleCrop()
                .into(imageView1);
        GlideApp
                .with(getActivity())
                .load(R.drawable.viewuser)
                .centerCrop()
                .fitCenter()
                .circleCrop()
                .into(imageView2);
        GlideApp
                .with(getActivity())
                .load(R.drawable.expenses)
                .centerCrop()
                .fitCenter()
                .circleCrop()
                .into(imageView3);
        GlideApp
                .with(getActivity())
                .load(R.drawable.collection)
                .centerCrop()
                .fitCenter()
                .circleCrop()
                .into(imageView4);
        GlideApp
                .with(getActivity())
                .load(R.drawable.complain)
                .centerCrop()
                .fitCenter()
                .circleCrop()
                .into(imageView5);
        GlideApp
                .with(getActivity())
                .load(R.drawable.viewcomplain)
                .centerCrop()
                .fitCenter()
                .circleCrop()
                .into(imageView6);
    }
}
