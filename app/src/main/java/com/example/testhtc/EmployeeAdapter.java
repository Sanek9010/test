package com.example.testhtc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter {
    private ArrayList<Employee> employees;
    private LayoutInflater vi;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        super(context,0, employees);
        this.employees = employees;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolderEmployee viewHolder;
        if (convertView == null) {
            convertView = vi.inflate(R.layout.list_employee_item, parent, false);
            viewHolder = new ViewHolderEmployee();
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.phone_Number = convertView.findViewById(R.id.phone_Number);
            viewHolder.skills = convertView.findViewById(R.id.skills);
            convertView.setTag(viewHolder);
        } else {
            viewHolder=(ViewHolderEmployee) convertView.getTag();
        }
        Employee i = employees.get(position);
        StringBuilder stringOfSkills =new StringBuilder();

        for (String skill: i.getSkills()) {
            stringOfSkills.append(skill);
            stringOfSkills.append(", ");
        }
        viewHolder.skills.setText(stringOfSkills.substring(0,stringOfSkills.length()-2));
        viewHolder.name.setText(i.getName());
        viewHolder.phone_Number.setText(i.getPhone_Number());
        return convertView;
    }
}
