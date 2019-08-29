package com.example.harshviharresidency;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.MyExpenseViewHolder> {

    ArrayList<societyExpenses> expensesDetails;
    Context context;
    OnItemClickListener onItemClickListener;

    public ExpenseListAdapter(Context context, ArrayList<societyExpenses> personNames, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.expensesDetails = personNames;
        this.onItemClickListener = onItemClickListener;
    }

    public ExpenseListAdapter() {

    }

    @NonNull
    @Override
    public MyExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_expense_list, parent, false);

        // set the view's size, margins, paddings and layout parameters
        MyExpenseViewHolder mvh = new MyExpenseViewHolder(v); // pass the view to View Holder
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyExpenseViewHolder holder, final int position) {

        holder.lblBillerName.setText(expensesDetails.get(position).getBillerName().toString());
        holder.lblBillingDate.setText(expensesDetails.get(position).getExpenseDate().toString());
        holder.lblExpType.setText((String.valueOf(expensesDetails.get(position).getExpensesType())));
        holder.lblExpAmount.setText(String.valueOf(expensesDetails.get(position).getAmount()).toString());
        holder.lblPmtMode.setText(expensesDetails.get(position).getPaymentMode().toString());
        holder.lblTrasNo.setText(expensesDetails.get(position).getTransactionNumber().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("id", expensesDetails.get(position).getId().toString());
                b.putString("billername", expensesDetails.get(position).getBillerName().toString());
                b.putString("expenseDate", expensesDetails.get(position).getExpenseDate().toString());
                b.putString("expenseType", expensesDetails.get(position).getExpensesType().toString());
                b.putString("amount", String.valueOf(expensesDetails.get(position).getAmount()).toString());
                b.putString("paymentMode", expensesDetails.get(position).getPaymentMode().toString());
                b.putString("transNumber", expensesDetails.get(position).getTransactionNumber().toString());
                onItemClickListener.onItemClick(expensesDetails.get(position).getId().toString(), b);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (expensesDetails != null)
            return expensesDetails.size();
        else
            return 0;
    }

    public class MyExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView lblBillerName;
        TextView lblBillingDate;
        TextView lblExpType;
        TextView lblExpAmount;
        TextView lblPmtMode;
        TextView lblTrasNo;

        public MyExpenseViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            lblBillerName = (TextView) itemView.findViewById(R.id.lblBillerName);
            lblBillingDate = (TextView) itemView.findViewById(R.id.lblBillingDate);
            lblExpType = (TextView) itemView.findViewById(R.id.lblExpType);
            lblExpAmount = (TextView) itemView.findViewById(R.id.lblExpAmount);
            lblPmtMode = (TextView) itemView.findViewById(R.id.lblPmtMode);
            lblTrasNo = (TextView) itemView.findViewById(R.id.lblTrasNo);

        }
    }
}
