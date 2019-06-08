package com.example.testhtc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.testhtc.binders.DataBindAdapter;
import com.example.testhtc.binders.DataBinder;
import com.example.testhtc.binders.ElementBinder;
import com.example.testhtc.binders.FooterBinder;
import com.example.testhtc.binders.HeaderBinder;


public class RecyclerViewAdapter extends DataBindAdapter {
    private Company company = new Company();
    private String status = "";
    private HeaderBinder headerBinder;
    private ElementBinder elementBinder;
    private FooterBinder footerBinder;


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else if(position == (getItemCount()-1)){
            return 2;
        }else{
            return 1;
        }
    }

    public RecyclerViewAdapter() {
        headerBinder = new HeaderBinder(this, company);
        elementBinder = new ElementBinder(this, company);
        footerBinder = new FooterBinder(this, status);
    }

    public void setData(Company company, String status){
        this.company = company;
        this.status = status;
        headerBinder.setCompany(company);
        elementBinder.setCompany(company);
        footerBinder.setStatus(status);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
    }
    @Override
    public int getItemCount() {
        return company.getEmployees().size()+2;
    }//2 - footer and header

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        if(viewType == 0) {
            return (T)headerBinder;
        }else if(viewType == 2){
            return (T)footerBinder;
        }else{
            return (T)elementBinder;
        }
    }

    @Override
    public int getBinderPosition(int position) {
        if(position>0) {
            if(position<getItemCount()-1){
                return position - 1;
            }else{
                return getItemCount()-1;
            }
        }else{
            return 1;
        }
    }
}
