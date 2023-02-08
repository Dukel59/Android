package com.example.skills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        CheckBox checkJava = findViewById(R.id.java);
        CheckBox checkXML = findViewById(R.id.xml);
        CheckBox checkAndroid = findViewById(R.id.android);

        checkJava.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                TextView view = findViewById(R.id.textJava);
                if(isChecked)
                    view.setVisibility(View.VISIBLE);
                else
                    view.setVisibility(View.INVISIBLE);
                Toast.makeText(
                        compoundButton.getContext(),
                        checkJava.getText() + " выбран",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        checkXML.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                TextView view = findViewById(R.id.textXML);
                if(isChecked)
                    view.setVisibility(View.VISIBLE);
                else
                    view.setVisibility(View.GONE);
                Toast.makeText(
                        compoundButton.getContext(),
                        checkXML.getText() + " выбран",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        checkAndroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                TextView view = findViewById(R.id.textAndroid);
                if(isChecked)
                    view.setVisibility(View.VISIBLE);
                else
                    view.setVisibility(View.INVISIBLE);
                Toast.makeText(
                        compoundButton.getContext(),
                        checkAndroid.getText() + " выбран",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}