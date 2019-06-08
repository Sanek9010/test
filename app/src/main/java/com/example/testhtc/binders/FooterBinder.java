package com.example.testhtc.binders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testhtc.R;

public class FooterBinder extends DataBinder<FooterBinder.ViewHolder> {
    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    public FooterBinder(DataBindAdapter dataBindAdapter, String status) {
        super(dataBindAdapter);
        this.status=status;
    }

    @Override
    public FooterBinder.ViewHolder newViewHolder(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View convertView =
                layoutInflater.inflate(R.layout.footer_item, parent, false);
        return new FooterBinder.ViewHolder(convertView);
    }

    @Override
    public void bindViewHolder(FooterBinder.ViewHolder holder, int position) {
        holder.statusMessage.setText(status);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView statusMessage;

        public ViewHolder(View convertView) {
            super(convertView);
            statusMessage = convertView.findViewById(R.id.status);
        }
    }
}
