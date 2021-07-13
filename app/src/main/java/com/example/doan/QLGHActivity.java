package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.doan.adapter.QLGHAdapter;
import com.example.doan.model.Cart;
import com.example.doan.retrofit.APIUltils;
import com.example.doan.retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLGHActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Cart> list = new ArrayList<>();
    QLGHAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_g_h);
        setControl();
        setEvent();
        getDSGH();
    }

    private void getDSGH() {
        DataClient data = APIUltils.getData();
        Call<List<Cart>> callback = data.getCart();
        callback.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> listCart = new ArrayList<>();
                listCart = response.body();
                for(Cart item : listCart){
                    list.add(new Cart(item.getId(),item.getIdUser(),item.getNgayMua(),item.getTongtien(),item.getTrangthai()));
                }
                Log.d("AAA",String.valueOf(list.size()));
                // Khi nội dung của List đó bị thay đổi, thì ta phải gọi notifyDataSetChanged () để update lại data
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
            }
        });
    }

    private void setControl(){
        recyclerView = findViewById(R.id.recyclerViewCart_QLGH);
    }

    private void setEvent() {
        adapter = new QLGHAdapter(this,list);
        GridLayoutManager layout = new GridLayoutManager(QLGHActivity.this,1);
        recyclerView.setHasFixedSize(false);
        layout.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
    }
}