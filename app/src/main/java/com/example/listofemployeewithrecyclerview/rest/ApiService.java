package com.example.listofemployeewithrecyclerview.rest;

import com.example.listofemployeewithrecyclerview.service.entity.Employee;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("users")
    Call<List<Employee>> getEmployees();
    @POST("user")
    void getEmployeeById(@Body EmployeeRequest employeeRequest);

    public static class Factory{
        public static ApiService create(){

            Gson gson = new Gson();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(ApiService.class);
        }
    }
}

class EmployeeRequest {
    String id;
}
