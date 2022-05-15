package com.example.listofemployeewithrecyclerview.service.entity;

import java.io.Serializable;
import java.util.Random;

public class Employee implements Serializable {
    public int id;
    public String name;
    public String username;
    public String email;
    public Address address;
    public String phone;
    public String website;
    public Company company;
    public boolean isSelected = false;
    public boolean isMan = new Random().nextBoolean();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}
