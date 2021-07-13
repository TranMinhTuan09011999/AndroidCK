package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan.model.Cart1;
import com.example.doan.model.CartRequest;
import com.example.doan.model.DetailCart;
import com.example.doan.model.SanPham;
import com.example.doan.model.SanPham1;
import com.example.doan.retrofit.APIUltils;
import com.example.doan.session.Session;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CTSPActivity extends AppCompatActivity {

    TextView txtTenSP,txtGiaSP,txtMKSP,txtMoTaSP;
    ImageView imgSP;
    Button btnMH;
    TextView editSL;
    Toolbar toolbar;
    Session session;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_t_s_p);
        setControll();
        setEvent();
        setActionBar();
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
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
        return true;
    }

    private void setActionBar() {
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
        Intent intent = getIntent();
        SanPham1 sp = (SanPham1) intent.getSerializableExtra("SANPHAM");
        txtTenSP.setText(sp.getName());
        txtMoTaSP.setText(sp.getDescription());
        Picasso.with(getApplicationContext()).load(sp.getImage()).into(imgSP);
        Double gia = Double.valueOf(sp.getPrice());
        Double gia_km = gia - gia * sp.getPromotion() / 100;
        DecimalFormat format = new DecimalFormat("###,###,###");
        if(sp.getPromotion() > 0){
            txtMKSP.setText("GIÁ : " + format.format(gia_km) + "$" + " (-" + sp.getPromotion() + "%)");
            txtGiaSP.setText(format.format(gia) + "$");
        }else{
            txtMKSP.setText("GIÁ : " + format.format(gia_km) + "$");
            txtGiaSP.setText("");
        }
        btnMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                intent.putExtra("SANPHAM", (Parcelable) sp);
                session = new Session(getApplicationContext());
                Long userId = session.getId();
                token = "Bearer " + session.getToken();

                System.out.println(userId);
                System.out.println(token);

                int qty = 1;

                CartRequest cartRequest = new CartRequest(sp.getId(), userId, 1, sp.getPrice());

                /*Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.4:8080/api/")
                        // as we are sending data in json format so
                        // we have to add Gson converter factory
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                DataClient retrofitAPI = retrofit.create(DataClient.class);*/
                Call<List<Cart1>> callback = APIUltils.getData().addCartByUserId(token,cartRequest);
                callback.enqueue(new Callback<List<Cart1>>() {
                    @Override
                    public void onResponse(Call<List<Cart1>> call, Response<List<Cart1>> response) {
                        List<Cart1> list = new ArrayList<Cart1>();
                        list = response.body();
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    }
                    @Override
                    public void onFailure(Call<List<Cart1>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void setControll() {
        txtTenSP = findViewById(R.id.txtTenSP_CTSP);
        txtGiaSP = findViewById(R.id.txtGia_CTSP);
        txtMKSP = findViewById(R.id.txtGiaKMSP_CTSP);
        txtMoTaSP = findViewById(R.id.txtMoTaSP);
        imgSP = findViewById(R.id.imgCTSP);
        btnMH = findViewById(R.id.btnMH_CTSP);
        editSL = findViewById(R.id.editSL);
        toolbar = findViewById(R.id.toolbarCTSP);
    }
}