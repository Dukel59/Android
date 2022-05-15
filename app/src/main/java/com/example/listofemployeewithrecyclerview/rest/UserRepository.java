package com.example.listofemployeewithrecyclerview.rest;

import com.example.listofemployeewithrecyclerview.service.entity.Employee;

import java.util.List;

import retrofit2.Call;

public class UserRepository {
    private final ApiService apiService;

    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<List<Employee>> getUsers(){
        return apiService.getEmployees();
    }
}
