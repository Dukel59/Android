package com.example.listofemployeewithrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listofemployeewithrecyclerview.adapter.EmployeeAdapter;
import com.example.listofemployeewithrecyclerview.rest.ApiService;
import com.example.listofemployeewithrecyclerview.rest.UserRepository;
import com.example.listofemployeewithrecyclerview.service.entity.Address;
import com.example.listofemployeewithrecyclerview.service.entity.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AddHumanDialogFragment.Listener {
    FragmentManager supportFragmentManager;
    RecyclerView recyclerView;
    Employee employee;

    private final static String TAG = MainActivity.class.getSimpleName();

    private final UserRepository userRepository = new UserRepository(
            ApiService.Factory.create()
    );

    private final EmployeeAdapter employeeAdapter = new EmployeeAdapter(employee -> {
        this.employee = employee;
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            supportFragmentManager = getSupportFragmentManager();
        }
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(employeeAdapter);

        userRepository.getUsers().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> employees = response.body();
                employeeAdapter.setEmployees(employees);
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AddHumanDialogFragment dialogFragment;
        switch (item.getItemId()) {
            case R.id.add_employee:
                dialogFragment = AddHumanDialogFragment.newInstance(null);
                dialogFragment.show(supportFragmentManager, "DialogFragment");
                return true;
            case R.id.edit_employee:
                if (this.employee == null) {
                    Log.d(TAG, "Employee is null");
                    return true;
                }
                dialogFragment = AddHumanDialogFragment.newInstance(this.employee);
                dialogFragment.show(supportFragmentManager, "EditDialogFragment");
                return true;
            case R.id.remove_employee:
                employeeAdapter.removeEmployee();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onUpdate(Employee employee) {
        employeeAdapter.editEmployee(employee, true);
    }

    @Override
    public void onAdd(Employee employee) {
        employeeAdapter.editEmployee(employee, false);
        recyclerView.smoothScrollToPosition(0);
    }
}