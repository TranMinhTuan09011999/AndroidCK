package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.doan.model.DetailCart;
import com.example.doan.retrofit.APIUltils;
import com.example.doan.retrofit.DataClient;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TKActivity extends AppCompatActivity {

    List<Integer> list = new ArrayList<>();
    Spinner spinner;
    Button btn;
    PieChart pieChart;
    Map<Integer,Integer> map = new HashMap<>();
    Map<Integer,String> mapName = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_k);
        setControl();
        setEvent();
        //createChart();
    }

    private void createChart() {
        Map<Integer,String> map = new HashMap<>();
        map.put(5,"MSI GE66 Raider");
        map.put(6,"MSI GF65 Thin 2020");
        map.put(9,"Asus TUF A15 FA506IV");
        map.put(5,"MSI Stealth 15M A11SDK 060VN");
        map.put(4,"MSI Stealth 15M A11SDK 060VN");

        ArrayList<PieEntry> cart = new ArrayList<>();
        for (int key : map.keySet()){
            cart.add(new PieEntry(key,map.get(key)));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }


        PieDataSet pieDataSet = new PieDataSet(cart,"CARTS");
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);

        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.animateY(1500, Easing.EaseInOutQuad);
        pieChart.setDrawHoleEnabled(true);
        pieChart.getDescription().setEnabled(false);;

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void setControl() {
        pieChart = findViewById(R.id.pieChar);
    }

    private void setEvent(){
        Intent intent = getIntent();
        int month = intent.getIntExtra("month",1);
                String begin = "2021-"+String.valueOf(month)+"-01";
                String end = "2021-"+String.valueOf(month+1)+"-01";
                DataClient data = APIUltils.getData();
                Call<List<DetailCart>> callback = data.hihi(begin,end);
                callback.enqueue(new Callback<List<DetailCart>>() {
                    @Override
                    public void onResponse(Call<List<DetailCart>> call, Response<List<DetailCart>> response) {
                        List<DetailCart> listCart = response.body();
                        for (DetailCart item : listCart){
                            if (map.containsKey(item.getIdSP())){
                                map.put(item.getIdSP(),map.get(item.getIdSP()) + 1);
                            }
                            else{
                                map.put(item.getIdSP(),1);
                            }
                            if (!mapName.containsKey(item.getIdSP())){
                                mapName.put(item.getIdSP(),item.getTen());
                            }
                        }
                        ArrayList<PieEntry> cart = new ArrayList<>();
                        for (int key : map.keySet()){
                            Log.d("CCC",mapName.get(key));
                            cart.add(new PieEntry(map.get(key),mapName.get(key)));
                        }
                        ArrayList<Integer> colors = new ArrayList<>();
                        for (int color : ColorTemplate.MATERIAL_COLORS){
                            colors.add(color);
                        }

                        for (int color : ColorTemplate.VORDIPLOM_COLORS){
                            colors.add(color);
                        }


                        PieDataSet pieDataSet = new PieDataSet(cart,"CARTS");
                        pieDataSet.setColors(colors);

                        PieData pieData = new PieData(pieDataSet);
                        pieData.setDrawValues(true);
                        pieData.setValueFormatter(new PercentFormatter(pieChart));
                        pieData.setValueTextSize(12f);
                        pieData.setValueTextColor(Color.BLACK);

                        pieChart.setData(pieData);
                        pieChart.invalidate();
                        pieChart.animateY(1500, Easing.EaseInOutQuad);
                        pieChart.setDrawHoleEnabled(true);
                        pieChart.getDescription().setEnabled(false);;

                        Legend l = pieChart.getLegend();
                        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                        l.setOrientation(Legend.LegendOrientation.VERTICAL);
                        l.setDrawInside(false);
                        l.setEnabled(true);
                    }

                    @Override
                    public void onFailure(Call<List<DetailCart>> call, Throwable t) {
                        Log.d("AAA","LOI");
                    }
                });

            }
}