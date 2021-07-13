package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    CardView cvGH,cvSP,cvTK,cvUser;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setControl();
        setEvent();
    }

    private void setEvent() {
        cvSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,QLSPActivity.class));
            }
        });

        cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cvTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,TKKActivity.class));
            }
        });

        cvGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,QLGHActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }

    private void setControl() {
        cvGH = findViewById(R.id.cvGioHang);
        cvSP = findViewById(R.id.cvSanPham);
        cvTK = findViewById(R.id.cvThongKe);
        cvUser = findViewById(R.id.cvUser);
        button = findViewById(R.id.btnDX);
    }
}