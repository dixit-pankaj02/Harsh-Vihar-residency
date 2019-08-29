package com.example.harshviharresidency;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResidentListAdapter extends RecyclerView.Adapter<ResidentListAdapter.MyViewHolder> {

    ArrayList<ResidentsProfiles> residentDetails;
    Context context;
    OnItemClickListener onItemClickListener;

    public ResidentListAdapter(Context context, ArrayList<ResidentsProfiles> personNames, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.residentDetails = personNames;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_resident_list, parent, false);

        // set the view's size, margins, paddings and layout parameters
        MyViewHolder mvh = new MyViewHolder(v); // pass the view to View Holder
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String fullName = residentDetails.get(position).getFirstName().toString()
                + " " +
                residentDetails.get(position).getLastName().toString();

        holder.lblHouseno.setText(residentDetails.get(position).getHouseNumber().toString());
        holder.lblName.setText(fullName);
        holder.lblNos.setText((String.valueOf(residentDetails.get(position).getFamilyMember())));
        holder.lblMobile.setText(residentDetails.get(position).getMobileNumber().toString());
        holder.lblemailId.setText(residentDetails.get(position).getEmailId().toString());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("id", residentDetails.get(position).getId().toString());
                b.putString("firstname", residentDetails.get(position).getFirstName().toString());
                b.putString("lastname", residentDetails.get(position).getLastName().toString());
                b.putString("houseNo", residentDetails.get(position).getHouseNumber().toString());
                b.putString("familyNos", String.valueOf(residentDetails.get(position).getFamilyMember()).toString());
                b.putString("mobile", residentDetails.get(position).getMobileNumber().toString());
                b.putString("emailId", residentDetails.get(position).getEmailId().toString());
                onItemClickListener.onItemClick(residentDetails.get(position).getId().toString(), b);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (residentDetails != null)
            return residentDetails.size();
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lblHouseno;
        TextView lblName;
        TextView lblNos;
        TextView lblMobile;
        TextView lblemailId;
        RelativeLayout rl;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            rl = (RelativeLayout) itemView.findViewById(R.id.relLayoutResidentlist);
            lblHouseno = (TextView) itemView.findViewById(R.id.lblHouseno);
            lblName = (TextView) itemView.findViewById(R.id.lblName);
            lblNos = (TextView) itemView.findViewById(R.id.lblNos);
            lblMobile = (TextView) itemView.findViewById(R.id.lblMobile);
            lblemailId = (TextView) itemView.findViewById(R.id.lblemailId);
        }
    }
}
