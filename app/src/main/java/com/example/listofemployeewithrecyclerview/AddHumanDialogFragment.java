package com.example.listofemployeewithrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.listofemployeewithrecyclerview.service.entity.Address;
import com.example.listofemployeewithrecyclerview.service.entity.Employee;

public class AddHumanDialogFragment extends DialogFragment {
    private EditText name;
    private EditText email;
    private EditText phone;
    private TextView humanSex;
    private ImageView sexImg;
    private Listener listener;
    private Employee employee;
    private Button btn;

    private final static String EMPLOYEE_ARG = "employee_arg";

    public static AddHumanDialogFragment newInstance(Employee employee) {
        Bundle args = new Bundle();
        args.putSerializable(EMPLOYEE_ARG, employee);
        AddHumanDialogFragment fragment = new AddHumanDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (Listener) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setCancelable(false);
        View view = inflater.inflate(R.layout.add_employee_fragment, container, false);

        name = view.findViewById(R.id.add_name);
        email = view.findViewById(R.id.add_email);
        humanSex = view.findViewById(R.id.add_sex_text_view);
        phone = view.findViewById(R.id.add_phone);
        sexImg = view.findViewById(R.id.sex_image);
        btn = view.findViewById(R.id.button_add);

        if (getArguments() != null) {
            employee = (Employee) getArguments().getSerializable(EMPLOYEE_ARG);
        }
        if (employee != null) {
            name.setText(employee.name);
            email.setText(employee.email);
            phone.setText(employee.phone);
            if(employee.isMan){
                humanSex.setText("Man");
                sexImg.setImageResource(R.mipmap.dipper2);

            } else{
                humanSex.setText("Woman");
                sexImg.setImageResource(R.mipmap.mable3);
            }
            btn.setText("Update");
        } else {
            sexImg.setImageResource(R.mipmap.mable3);
        }

        LinearLayout linearAddLayout = view.findViewById(R.id.add_sex_linear_layout);
        linearAddLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(humanSex.getText().toString() == "Woman")
                {
                    humanSex.setText("Man");
                    sexImg.setImageResource(R.mipmap.dipper2);
                } else {
                    humanSex.setText("Woman");
                    sexImg.setImageResource(R.mipmap.mable3);
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().findViewById(R.id.button_add).setOnClickListener(v -> {
            boolean isEdit = this.employee != null;
            if (!isEdit) {
                employee = new Employee();
            }
            employee.name = name.getText().toString();
            employee.email = email.getText().toString();
            employee.phone = phone.getText().toString();
            if(humanSex.getText() == "Man"){
                employee.isMan = true;
            } else {
                employee.isMan = false;
            }

            if (isEdit) {
                listener.onUpdate(employee);
            } else {
                listener.onAdd(employee);
            }
            dismiss();
        });

        getDialog().findViewById(R.id.button_cancel).setOnClickListener((v -> {
            dismiss();
        }));
    }

    public interface Listener {

        void onUpdate(Employee employee);

        void onAdd(Employee employee);

    }

}
