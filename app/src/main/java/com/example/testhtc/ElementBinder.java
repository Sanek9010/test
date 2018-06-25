package com.example.testhtc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ElementBinder extends DataBinder<ElementBinder.ViewHolder> {
    public void setCompany(Company с) {
        this.company = с;
    }

    private Company company;
    public ElementBinder(DataBindAdapter dataBindAdapter, Company company) {
        super(dataBindAdapter);
        this.company = company;
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View convertView =
                layoutInflater.inflate(R.layout.list_employee_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(company.getEmployees().get(position).getName());
        holder.phoneNumber.setText(company.getEmployees().get(position).getPhone_Number());
        holder.skills.setText(company.getEmployees().get(position).getStringOfSkills());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView phoneNumber;
        public TextView skills;
        public ViewHolder(View convertView) {
            super(convertView);
            name = convertView.findViewById(R.id.name);
            phoneNumber = convertView.findViewById(R.id.phone_Number);
            skills= convertView.findViewById(R.id.skills);
        }
    }
}

