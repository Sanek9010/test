package com.example.testhtc;

import android.support.annotation.NonNull;
import java.util.ArrayList;

public class Employee implements Comparable<Employee>{
    private String name;
    private String phone_number;
    private ArrayList<String> skills;

    public Employee(Employee employee){
        this.name = employee.name;
        this.phone_number = employee.phone_number;
        this.skills.addAll(employee.getSkills());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_Number() {return phone_number;}

    public void setPhone_Number(String phone_Number) {
        this.phone_number = phone_Number;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public String getStringOfSkills(){
        StringBuilder stringOfSkills =new StringBuilder();
        for (String skill: skills) {
            stringOfSkills.append(skill);
            stringOfSkills.append(", ");
        }
        return stringOfSkills.substring(0,stringOfSkills.length()-2);
    }

    @Override
    public int compareTo(@NonNull Employee employee) {
        return this.getName().compareTo(employee.getName());
    }
}
