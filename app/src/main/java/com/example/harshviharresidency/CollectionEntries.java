package com.example.harshviharresidency;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionEntries extends Fragment {

    View view;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    TextView dteDate;
    Button saveCollection, updateCollection;
    List<String> spinnerMonth, spinnerYear, spinnerMode, spinnerCollType;
    ArrayAdapter<String> adapterMonth, adapterYear, adapterMode, adapterCollection;
    Spinner sMonth, sYear, sMode, sCollection;
    EditText spinnerName, txtHno, txtCollBy, txtRefNo, txtAmount;
    DatabaseReference FCMdatabase;
    static Bundle bundle;

    public CollectionEntries() {
        bundle = null;
    }

    public static CollectionEntries getFrag(Bundle b) {
        CollectionEntries update = new CollectionEntries();
        bundle = b;
        return update;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_collection_entries, container, false);
        dteDate = (TextView) view.findViewById(R.id.dteDate);
        saveCollection = view.findViewById(R.id.saveCollection);
        updateCollection = view.findViewById(R.id.updateCollection);
        spinnerName = view.findViewById(R.id.spinnerName);
        txtHno = view.findViewById(R.id.txtHno);
        txtCollBy = view.findViewById(R.id.txtCollBy);
        txtRefNo = view.findViewById(R.id.txtRefNo);
        txtAmount = view.findViewById(R.id.txtAmount);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

// Write a message to the database


        spinnerMonth = new ArrayList<String>();
        spinnerMonth.add("January");
        spinnerMonth.add("February");
        spinnerMonth.add("March");
        spinnerMonth.add("April");
        spinnerMonth.add("May");
        spinnerMonth.add("June");
        spinnerMonth.add("July");
        spinnerMonth.add("August");
        spinnerMonth.add("September");
        spinnerMonth.add("October");
        spinnerMonth.add("November");
        spinnerMonth.add("December");

        spinnerYear = new ArrayList<String>();
        spinnerYear.add("2018");
        spinnerYear.add("2019");
        spinnerYear.add("2020");
        spinnerYear.add("2021");
        spinnerYear.add("2022");
        spinnerYear.add("2023");
        spinnerYear.add("2024");

        spinnerMode = new ArrayList<String>();
        spinnerMode.add("CASH");
        spinnerMode.add("PayTM");
        spinnerMode.add("NEFT");
        spinnerMode.add("RTGS");
        spinnerMode.add("OTHER");

        spinnerCollType = new ArrayList<String>();
        spinnerCollType.add("Maintenance Charge");
        spinnerCollType.add("Festival Contribution");
        spinnerCollType.add("Other Charges");

        adapterMonth = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerMonth);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMonth = (Spinner) view.findViewById(R.id.spnMonth);
        sMonth.setAdapter(adapterMonth);

        adapterYear = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerYear);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYear = (Spinner) view.findViewById(R.id.spnYear);
        sYear.setAdapter(adapterYear);

        adapterMode = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerMode);
        adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMode = (Spinner) view.findViewById(R.id.spnMode);
        sMode.setAdapter(adapterMode);

        adapterCollection = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerCollType);
        adapterCollection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCollection = (Spinner) view.findViewById(R.id.spnCollType);
        sCollection.setAdapter(adapterCollection);
        FCMdatabase = FirebaseDatabase.getInstance().getReference("SocietyCollection");

        if (bundle == null) {
            saveCollection.setVisibility(View.VISIBLE);
            updateCollection.setVisibility(View.INVISIBLE);
            spinnerName.setText("");
            dteDate.setText("");
            txtRefNo.setText("");
            txtAmount.setText("");

        } else {
            saveCollection.setVisibility(View.INVISIBLE);
            updateCollection.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePicker();
            }
        });
        if (bundle != null) {
            spinnerName.setText(bundle.getString("Name"));
            dteDate.setText(bundle.getString("CollDate"));
            txtRefNo.setText(bundle.getString("TransactionNumber"));
            txtAmount.setText(bundle.getString("Amount"));
            txtHno.setText(bundle.getString("houseNo"));
            txtCollBy.setText(bundle.getString("collBy"));
            sCollection.setSelection(((ArrayAdapter<String>) sCollection.getAdapter()).getPosition(bundle.getString("CollectionType")));
            sMode.setSelection(((ArrayAdapter<String>) sMode.getAdapter()).getPosition(bundle.getString("PaymentMode")));
            sMonth.setSelection(((ArrayAdapter<String>) sMonth.getAdapter()).getPosition(bundle.getString("Month")));
            sYear.setSelection(((ArrayAdapter<String>) sYear.getAdapter()).getPosition(bundle.getString("Year")));
            final String Id = bundle.getString("id");
            updateCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    societyCollections coll = new societyCollections(
                            spinnerName.getText().toString(),
                            txtHno.getText().toString(),
                            sCollection.getSelectedItem().toString(),
                            sMode.getSelectedItem().toString(),
                            txtRefNo.getText().toString(),
                            Float.valueOf(txtAmount.getText().toString()),
                            dteDate.getText().toString(),
                            sMonth.getSelectedItem().toString(),
                            Integer.parseInt(sYear.getSelectedItem().toString()),
                            Id.toString(), txtCollBy.getText().toString());
                    FCMdatabase.child(Id).setValue(coll);
                    Toast.makeText(getContext(), "Collections updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
        saveCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = FCMdatabase.push().getKey();
                societyCollections coll = new societyCollections(
                        spinnerName.getText().toString(),
                        txtHno.getText().toString(),
                        sCollection.getSelectedItem().toString(),
                        sMode.getSelectedItem().toString(),
                        txtRefNo.getText().toString(),
                        Float.valueOf(txtAmount.getText().toString()),
                        dteDate.getText().toString(),
                        sMonth.getSelectedItem().toString(),
                        Integer.parseInt(sYear.getSelectedItem().toString()),
                        Id.toString(), txtCollBy.getText().toString());
                FCMdatabase.child(Id).setValue(coll);
                Toast.makeText(getContext(), "collection added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            dteDate.setText((String.valueOf(dayOfMonth).length() == 1 ? "0" + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth))
                    + "/" + String.valueOf(monthOfYear + 1)
                    + "/" + String.valueOf(year));
        }
    };
}
