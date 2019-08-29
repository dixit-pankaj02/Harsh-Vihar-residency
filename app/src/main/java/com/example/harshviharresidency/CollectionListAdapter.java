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

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.MyViewHolder> {

    ArrayList<societyCollections> collectionDetails;
    Context context;
    OnItemClickListener onItemClickListener;

    public CollectionListAdapter(Context context, ArrayList<societyCollections> personNames, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.collectionDetails = personNames;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_collection_list, parent, false);

        // set the view's size, margins, paddings and layout parameters
        MyViewHolder mvh = new MyViewHolder(v); // pass the view to View Holder
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String CollMthYr = collectionDetails.get(position).getMonth().toString()
                + " " + String.valueOf(collectionDetails.get(position).getYear()).toString();
        holder.lblHouseno.setText(collectionDetails.get(position).getHouseNumber().toString());
        holder.lblName.setText(collectionDetails.get(position).getName().toString());
        holder.lblpmtDate.setText((String.valueOf(collectionDetails.get(position).getCollDate())));
        holder.lblCollType.setText(collectionDetails.get(position).getCollectionType().toString());
        holder.lblAmount.setText(String.valueOf(collectionDetails.get(position).getAmount()).toString());
        holder.lblPmtMode.setText((String.valueOf(collectionDetails.get(position).getPaymentMode())));
        holder.lblCollBy.setText(collectionDetails.get(position).getCollBy().toString());
        holder.lblMthYr.setText(CollMthYr);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("id", collectionDetails.get(position).getId().toString());
                b.putString("Name", collectionDetails.get(position).getName().toString());
                b.putString("collBy", collectionDetails.get(position).getCollBy().toString());
                b.putString("houseNo", collectionDetails.get(position).getHouseNumber().toString());
                b.putString("CollectionType", String.valueOf(collectionDetails.get(position).getCollectionType()).toString());
                b.putString("PaymentMode", collectionDetails.get(position).getPaymentMode().toString());
                b.putString("TransactionNumber", collectionDetails.get(position).getTransactionNumber().toString());
                b.putString("Amount", String.valueOf(collectionDetails.get(position).getAmount()).toString());
                b.putString("CollDate", collectionDetails.get(position).getCollDate().toString());
                b.putString("Month", collectionDetails.get(position).getMonth().toString());
                b.putString("Year", String.valueOf(collectionDetails.get(position).getYear()).toString());
                onItemClickListener.onItemClick(collectionDetails.get(position).getId().toString(), b);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (collectionDetails != null)
            return collectionDetails.size();
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lblName;
        TextView lblHouseno;
        TextView lblpmtDate;
        TextView lblCollType;
        TextView lblAmount;
        TextView lblPmtMode;
        TextView lblCollBy;
        TextView lblMthYr;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            lblName = (TextView) itemView.findViewById(R.id.lblName);
            lblHouseno = (TextView) itemView.findViewById(R.id.lblHouseno);
            lblpmtDate = (TextView) itemView.findViewById(R.id.lblpmtDate);
            lblCollType = (TextView) itemView.findViewById(R.id.lblCollType);
            lblAmount = (TextView) itemView.findViewById(R.id.lblAmount);
            lblPmtMode = (TextView) itemView.findViewById(R.id.lblPmtMode);
            lblCollBy = (TextView) itemView.findViewById(R.id.lblCollBy);
            lblMthYr = (TextView) itemView.findViewById(R.id.lblMthYr);

        }
    }
}
