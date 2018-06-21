package com.example.testhtc;

import android.support.annotation.NonNull;
import java.util.ArrayList;

public class Employee implements Comparable<Employee>{
    private String name;
    private String phoneNumber;
    private ArrayList<String> skills;

    public Employee(Employee employee){
        this.name = employee.name;
        this.phoneNumber = employee.phoneNumber;
        this.skills.addAll(employee.getSkills());
    }

    public Employee(String name, String phoneNumber, ArrayList<String> skills) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_Number() {return phoneNumber;}

    public void setPhone_Number(String phone_Number) {
        this.phoneNumber = phone_Number;
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
