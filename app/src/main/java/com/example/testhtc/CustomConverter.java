package com.example.testhtc;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CustomConverter implements JsonDeserializer<Company> {
        public Company deserialize(JsonElement json, Type type,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String name = object.get("name").getAsString();
        int age = object.get("age").getAsInt();
        ArrayList<String> competences = new ArrayList<>();
        for(JsonElement item: object.get("competences").getAsJsonArray()){
            competences.add(item.getAsString());
        }
        ArrayList<Employee> employees = new ArrayList<>();
        for(JsonElement item: object.get("employees").getAsJsonArray()){
            String nameEmployee = item.getAsJsonObject().get("name").getAsString();
            String phoneNumber = item.getAsJsonObject().get("phone_number").getAsString();
            ArrayList<String> skills = new ArrayList<>();
            for(JsonElement skill: item.getAsJsonObject().get("skills").getAsJsonArray()){
                skills.add(skill.getAsString());
            }
            employees.add(new Employee(nameEmployee,phoneNumber,skills));
        }

        return new Company(name,age,competences,employees);
    }
}
