package com.example.testhtc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ElementBinder extends DataBinder<ElementBinder.ViewHolder> {
    public void setCompany(Company company) {
        this.company = company;
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
        holder.phone_Number.setText(company.getEmployees().get(position).getPhone_Number());
        StringBuilder skills = new StringBuilder();
        for (String skill : company.getEmployees().get(position).getSkills()) {
            skills.append(skill);
            skills.append(", ");
        }
        holder.skills.setText(skills.substring(0,skills.length()-2));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView phone_Number;
        public TextView skills;
        public ViewHolder(View convertView) {
            super(convertView);
            name = convertView.findViewById(R.id.name);
            phone_Number = convertView.findViewById(R.id.phone_Number);
            skills= convertView.findViewById(R.id.skills);
        }
    }
}
