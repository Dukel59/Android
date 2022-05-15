package com.example.listofemployeewithrecyclerview;

import com.example.listofemployeewithrecyclerview.service.entity.Employee;

public interface MenuActions {
//    void add(Employee employee);
    void edit(Employee employee, boolean edit);
    void remove(int position);
}
