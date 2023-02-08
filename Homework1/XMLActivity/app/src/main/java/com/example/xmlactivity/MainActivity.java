package com.example.xmlactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_buttons);

        GridLayout mainGrid = new GridLayout(this);
        FrameLayout secondGrid = new FrameLayout(this);
        GridLayout thirdGrid = new GridLayout(this);
        GridLayout fourthGrid = new GridLayout(this);

        thirdGrid.setRowCount(2);
        thirdGrid.setColumnCount(2);

        fourthGrid.setRowCount(2);
        fourthGrid.setColumnCount(2);

        mainGrid.setRowCount(2);
        GridLayout.LayoutParams lp = new GridLayout.LayoutParams(GridLayout.spec(0,1f), GridLayout.spec(0));
        lp.width = GridLayout.LayoutParams.MATCH_PARENT;
        lp.height = 0;
        lp.setMargins((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()));

        for (int i = 0; i<4; i++){
            TextView text = new TextView(this);
            FrameLayout layout = new FrameLayout(this);
            layout.setBackgroundColor(Color.parseColor("#EAEAEA"));
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            text.setText(String.valueOf(i+1));
            text.setGravity(Gravity.CENTER);
            text.setPadding((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()));
            text.setLayoutParams(params);
            layout.addView(text);
            GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
            gridParams.setMargins((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()));
            thirdGrid.addView(layout, gridParams);
        }

        for (int i = 0; i<4; i++){
            TextView text = new TextView(this);
            FrameLayout layout = new FrameLayout(this);
            layout.setBackgroundColor(Color.parseColor("#EAEAEA"));
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            text.setText(String.valueOf(i+1));
            text.setGravity(Gravity.CENTER);
            text.setPadding((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()));
            text.setLayoutParams(params);
            layout.addView(text);
            GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
            gridParams.setMargins((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics()));
            fourthGrid.addView(layout, gridParams);
        }

        FrameLayout.LayoutParams frameParam = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        frameParam.gravity = Gravity.CENTER;
        secondGrid.setBackgroundColor(Color.parseColor("#C5E1C1"));
        thirdGrid.setLayoutParams(frameParam);
        secondGrid.addView(thirdGrid);

        mainGrid.addView(secondGrid, lp);

        FrameLayout secondGrid_2 = new FrameLayout(this);
        secondGrid_2.setBackgroundColor(Color.parseColor("#DDB0B0"));
        fourthGrid.setLayoutParams(frameParam);
        secondGrid_2.addView(fourthGrid);
        GridLayout.LayoutParams lp1 = new GridLayout.LayoutParams(GridLayout.spec(1,1f), GridLayout.spec(0));
        lp1.width = GridLayout.LayoutParams.MATCH_PARENT;
        lp1.height = 0;
        lp1.setMargins((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()));
        mainGrid.addView(secondGrid_2, lp1);

        setContentView(mainGrid);
    }
}