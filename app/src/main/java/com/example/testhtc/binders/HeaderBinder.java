package com.example.testhtc.binders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testhtc.Company;
import com.example.testhtc.R;

public class HeaderBinder extends DataBinder<HeaderBinder.ViewHolder> {
    public void setCompany(Company company) {
        this.company = company;
    }

    private Company company;
    public HeaderBinder(DataBindAdapter dataBindAdapter, Company company) {
        super(dataBindAdapter);
        this.company = company;
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View convertView =
                layoutInflater.inflate(R.layout.header_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.companyName.setText(company.getName());
        holder.companyAge.setText(String.valueOf(company.getAge()));
        StringBuilder competences = new StringBuilder();
        for (String competence : company.getCompetences()) {
            competences.append(competence);
            competences.append(", ");
        }
        holder.competencesList.setText(competences.toString().length()==0?
                "":competences.substring(0,competences.length()-2));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView companyName;
        public TextView companyAge;
        public TextView competencesList;
        public ViewHolder(View convertView) {
            super(convertView);
            companyName = convertView.findViewById(R.id.companyName);
            companyAge = convertView.findViewById(R.id.companyAge);
            competencesList= convertView.findViewById(R.id.competencesList);
        }
    }
}
