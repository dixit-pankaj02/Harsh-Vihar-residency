package com.example.harshviharresidency;


import android.app.DatePickerDialog;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesEntries extends Fragment {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    TextView dteDate;
    EditText spinnerName, txtType, txtRefNo, txtAmount;
    Button saveExpense, updateExpense;
    List<String> spinnerMode;
    ArrayAdapter<String> adapterMode;
    Spinner sMode;
    private String expenseDate;
    View V;
    DatabaseReference FCMdatabase;
    static Bundle bundle = null;

    public ExpensesEntries() {
        bundle = null;
    }

    public static ExpensesEntries getFrag(Bundle b) {
        ExpensesEntries update = new ExpensesEntries();
        bundle = b;
        return update;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V = inflater.inflate(R.layout.fragment_expenses_entries, container, false);
        dteDate = (TextView) V.findViewById(R.id.dteDate);
        spinnerName = (EditText) V.findViewById(R.id.spinnerName);
        txtType = (EditText) V.findViewById(R.id.txtType);
        txtRefNo = (EditText) V.findViewById(R.id.txtRefNo);
        txtAmount = (EditText) V.findViewById(R.id.txtAmount);
        saveExpense = V.findViewById(R.id.SaveExpense);
        updateExpense = V.findViewById(R.id.updateExpense);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        spinnerMode = new ArrayList<String>();
        spinnerMode.add("CASH");
        spinnerMode.add("PayTM");
        spinnerMode.add("NEFT");
        spinnerMode.add("RTGS");
        spinnerMode.add("OTHER");

        adapterMode = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerMode);
        adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMode = (Spinner) V.findViewById(R.id.spnMode);
        sMode.setAdapter(adapterMode);

        if (bundle == null) {
            saveExpense.setVisibility(View.VISIBLE);
            updateExpense.setVisibility(View.INVISIBLE);
            spinnerName.setText("");
            dteDate.setText("");
            txtType.setText("");
            txtRefNo.setText("");
            txtAmount.setText("");
        } else {
            saveExpense.setVisibility(View.INVISIBLE);
            updateExpense.setVisibility(View.VISIBLE);
        }

        FCMdatabase = FirebaseDatabase.getInstance().getReference("SocietyExpense");
        return V;
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
            spinnerName.setText(bundle.getString("billername"));
            dteDate.setText(bundle.getString("expenseDate"));
            txtType.setText(bundle.getString("expenseType"));
            txtRefNo.setText(bundle.getString("transNumber"));
            txtAmount.setText(bundle.getString("amount"));
            sMode.setSelection(((ArrayAdapter<String>) sMode.getAdapter()).getPosition(bundle.getString("paymentMode")));
            final String Id = bundle.getString("id");
            updateExpense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    societyExpenses coll = new societyExpenses(
                            spinnerName.getText().toString(),
                            txtType.getText().toString(),
                            sMode.getSelectedItem().toString(),
                            txtRefNo.getText().toString(),
                            Float.valueOf(txtAmount.getText().toString()),
                            dteDate.getText().toString(),
                            Id.toString());
                    FCMdatabase.child(Id).setValue(coll);
                    Toast.makeText(getContext(), "Expenses updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
        saveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = FCMdatabase.push().getKey();
                societyExpenses coll = new societyExpenses(
                        spinnerName.getText().toString(),
                        txtType.getText().toString(),
                        sMode.getSelectedItem().toString(),
                        txtRefNo.getText().toString(),
                        Float.valueOf(txtAmount.getText().toString()),
                        dteDate.getText().toString(),
                        Id.toString());
                FCMdatabase.child(Id).setValue(coll);
                Toast.makeText(getContext(), "Expenses added successfully", Toast.LENGTH_SHORT).show();
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

            expenseDate = (String.valueOf(dayOfMonth).length() == 1 ? "0" + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth))
                    + "/" + String.valueOf(monthOfYear + 1)
                    + "/" + String.valueOf(year);
            dteDate.setText(expenseDate);
        }
    };
}
