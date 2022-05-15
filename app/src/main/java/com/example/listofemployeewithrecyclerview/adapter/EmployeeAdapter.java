package com.example.listofemployeewithrecyclerview.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listofemployeewithrecyclerview.R;
import com.example.listofemployeewithrecyclerview.service.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private final Listener listener;
    private List<Employee> employees = new ArrayList<>();

    public EmployeeAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.tvName.setText(employee.name);
        holder.tvEmail.setText(employee.email);
        holder.tvPhone.setText(employee.phone);
        if(employee.isMan){
            holder.ivSex.setImageResource(R.mipmap.dipper2);
        }else {
            holder.ivSex.setImageResource(R.mipmap.mable3);
        }

        holder.itemView.setBackgroundColor(employee.isSelected ? Color.YELLOW : Color.TRANSPARENT);

        holder.itemView.setOnClickListener(view -> {
            listener.onClick(employee);
            for (Employee e : employees) {
                e.isSelected = false;
            }
            employee.isSelected = true;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void editEmployee(Employee editEmployee, boolean edit) {
        if (edit) {
            int index = employees.indexOf(editEmployee);
            if (index != -1) {
                Employee employee = employees.get(index);
                employee.name = editEmployee.name;
                employee.email = editEmployee.email;
                employee.phone = editEmployee.phone;
                employee.isMan = editEmployee.isMan;
                employees.set(index, employee);
                notifyItemChanged(index);
            }
        } else {
            addEmployee(editEmployee);
        }
    }

    public void removeEmployee() {
        Employee selectedEmployee = null;
        for (Employee e : employees) {
            if (e.isSelected) {
                selectedEmployee = e;
                break;
            }
        }
        int indexSelectedEmployee = employees.indexOf(selectedEmployee);
        if (indexSelectedEmployee != -1) {
            employees.remove(indexSelectedEmployee);
            notifyItemRemoved(indexSelectedEmployee);
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(0, employee);
        notifyItemInserted(0);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        TextView tvPhone;
        ImageView ivSex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.employee_name);
            tvEmail = itemView.findViewById(R.id.employee_email);
            tvPhone = itemView.findViewById(R.id.employee_phone);
            ivSex = itemView.findViewById(R.id.sex_img);
        }
    }

    public interface Listener {
        void onClick(Employee employee);
    }
}
