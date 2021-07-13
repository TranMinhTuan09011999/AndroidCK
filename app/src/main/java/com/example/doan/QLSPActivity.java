package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import com.example.doan.adapter.QLSPAdapter;
import com.example.doan.adapter.SPMNAdapter;
import com.example.doan.model.SanPham;
import com.example.doan.retrofit.APIUltils;
import com.example.doan.retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLSPActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    List<SanPham> listSP;
    QLSPAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_s_p);

        setControl();
        setEvent();
        ActionBar();
        getDSSP();
    }

    private void getDSSP() {
        DataClient data = APIUltils.getData();
        Call<List<SanPham>> callback = data.QLSP();
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                List<SanPham> list = response.body();
                for (SanPham item : list){
                    listSP.add(new SanPham(item.getId(),item.getTensp(),item.getGia(),item.getKhuyenmai(),item.getMota(),item.getHinhanh(),item.getIdloaisp(),item.getTtx()));
                }
                Log.d("AAA",String.valueOf(listSP.size()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Log.d("AAA","Loi");
            }
        });
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setEvent() {
        listSP = new ArrayList<>();
        /*adapter = new QLSPAdapter(this,listSP);*/

        GridLayoutManager layout = new GridLayoutManager(QLSPActivity.this,1);
        recyclerView.setHasFixedSize(false);
        layout.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
    }

    private void setControl() {
        toolbar = findViewById(R.id.toolbarSP);
        recyclerView = findViewById(R.id.recyclerViewQLSP);
    }
}