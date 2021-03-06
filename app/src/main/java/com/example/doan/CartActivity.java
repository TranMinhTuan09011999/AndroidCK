package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.adapter.CartAdapter;
import com.example.doan.adapter.LSMHAdapter;
import com.example.doan.model.Cart;
import com.example.doan.model.Cart1;
import com.example.doan.model.CartRequest;
import com.example.doan.model.DetailCart;
import com.example.doan.model.Order;
import com.example.doan.model.SanPham;
import com.example.doan.retrofit.APIUltils;
import com.example.doan.retrofit.DataClient;
import com.example.doan.session.Session;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {

    public static CartAdapter adapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    public static TextView txtTT,txtTB;
    Button btnTT,btnMH;
    public static int idCart = 0;
    Session session;
    String token;
    List<Cart1> list = new ArrayList<>();
    //Double tongtien = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setControll();
        setEvent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart){
            startActivity(new Intent(getApplicationContext(),CartActivity.class));
        }
        return true;
    }


    private void setEvent() {
        //L???y d??? li???u ?????i t?????ng ???? g???i t??? MainActivity (2 d??ng code ??? d?????i)
        Intent intent = getIntent();
        SanPham sp = (SanPham) intent.getSerializableExtra("SANPHAM");



        session = new Session(getApplicationContext());
        Long userId = session.getId();
        token = "Bearer " + session.getToken();

        Call<List<Cart1>> callback = APIUltils.getData().getCartByUser(token,userId);

        callback.enqueue(new Callback<List<Cart1>>() {
            @Override
            public void onResponse(Call<List<Cart1>> call, Response<List<Cart1>> response) {
                list = response.body();
                System.out.println(list.size());
                adapter = new CartAdapter(CartActivity.this,list);
                //T???o layout scroll l??n xu???ng, c?? 1 c???t
                GridLayoutManager layout = new GridLayoutManager(CartActivity.this,1);
                //????? cho list ch???y m?????t h??n
                recyclerView.setHasFixedSize(false);
                //set list view theo chi???u d???c
                layout.setOrientation(GridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layout);
                recyclerView.setAdapter(adapter);
                DecimalFormat format  = new DecimalFormat("###,###,###");
                if (list.size() ==0){
                    txtTT.setText("T???NG TI???N : 0 VN??");
                    txtTB.setVisibility(View.VISIBLE);
                }
                else{

                    for (Cart1 cart : list) {
                        MainActivity.tongtien += (cart.getPrice() - cart.getPrice()*cart.getProduct().getPromotion()/100)*cart.getQuantity();
                    }
                    txtTT.setText("T???NG TI???N : " + format.format(MainActivity.tongtien) + " $");
                    txtTB.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<Cart1>> call, Throwable t) {

            }
        });

        btnTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 0){
                    Toast.makeText(CartActivity.this, "Gi??? H??ng ??ang Tr???ng.Vui L??ng Ch???n S???n Ph???m", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(CartActivity.this,ConfirmActivity.class));
                    //startActivity(new Intent(CartActivity.this,ConfirmActivity.class));
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
//                    try {
//
//                        insertCart();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });

        btnMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }

    private void setControll() {
        toolbar = findViewById(R.id.toolbarCart);
        recyclerView = findViewById(R.id.recyclerViewCart);
        txtTT = findViewById(R.id.txtTongTien);
        btnMH = findViewById(R.id.btnTTMH);
        btnTT = findViewById(R.id.btnThanhToan);
        txtTB = findViewById(R.id.txtTB);
    }
}