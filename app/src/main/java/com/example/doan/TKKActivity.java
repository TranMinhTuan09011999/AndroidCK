package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class TKKActivity extends AppCompatActivity {

    RadioButton rdbtn1,rdbtn2;
    Button btn;
    TextView textView;
    Spinner spinner;
    ArrayList<Integer> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_k_k);
        setControl();
        setEvent();
    }

    private void setEvent() {
        for (int  i = 1; i <= 12 ; i++){
            list.add(i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.item_spiner,list);
        spinner.setAdapter(adapter);
        rdbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.INVISIBLE);
            }
        });

        rdbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TKKActivity.this,TKActivity.class);
                intent.putExtra("month",(int)spinner.getSelectedItem());
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        btn = findViewById(R.id.btnBieuDo);
        textView = findViewById(R.id.textView7);
        spinner = findViewById(R.id.spinner2);
    }
}