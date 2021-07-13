package com.example.doan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.model.Cart;
import com.example.doan.model.DetailCart;
import com.example.doan.retrofit.APIUltils;
import com.example.doan.retrofit.DataClient;
import com.ramotion.foldingcell.FoldingCell;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLGHAdapter extends RecyclerView.Adapter<QLGHAdapter.ItemHolder>{

    Context context;
    List<Cart> list;

    public QLGHAdapter(Context context, List<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qlgh,null);
        Animation ani = AnimationUtils.loadAnimation(context,R.anim.ani_itemlistview);
        view.setAnimation(ani);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Cart cart = list.get(position);
        holder.txtID.setText(position + 1 + "");
        holder.txtNM.setText("Ngày Mua : " + cart.getNgayMua());
        holder.txtTThai.setText("Trạng Thái : " + cart.getTrangthai());
        DecimalFormat format = new DecimalFormat("###,###,###");
        holder.txtTT_title.setText("Tổng Tiền : " + format.format(Double.valueOf(cart.getTongtien())) + " VNĐ");
      //  holder.txtTT_content.setText("Tổng Tiền : " + format.format(Double.valueOf(cart.getTongtien()))+ " VNĐ");

        /*List<DetailCart> cartList = new ArrayList<>();
        ITCartAdapter adapter = new ITCartAdapter(holder.foldingCell.getContext(),cartList);
        GridLayoutManager layout = new GridLayoutManager(holder.foldingCell.getContext(),1);
        // holder.recyclerView.setHasFixedSize(false);
        holder.recyclerView.setFocusable(true);
        layout.setOrientation(GridLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(layout);
        holder.recyclerView.setAdapter(adapter);

        DataClient data = APIUltils.getData();
        Call<List<DetailCart>> callback = data.getDetailCart(cart.getId());
        callback.enqueue(new Callback<List<DetailCart>>() {
            @Override
            public void onResponse(Call<List<DetailCart>> call, Response<List<DetailCart>> response) {
                List<DetailCart> list1 = response.body();
                for (DetailCart item : list1){
                    cartList.add(new DetailCart(item.getId(),item.getIdGH(),item.getIdSP(),item.getHa(),item.getTen(),item.getSoluong(),item.getGia()));
                }
                Log.d("AAA",String.valueOf(cartList.size()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DetailCart>> call, Throwable t) {

            }
        });*/

        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        FoldingCell foldingCell;
        TextView txtID,txtNM,txtTT_title,txtTT_content,txtTThai;
        RecyclerView recyclerView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            foldingCell = itemView.findViewById(R.id.fc_QLGH);
            txtID = itemView.findViewById(R.id.txtTenKH_QLGH);
            txtNM = itemView.findViewById(R.id.txtNM_QLGH);
            recyclerView = itemView.findViewById(R.id.recyclerViewItemQLGH);
            txtTThai = itemView.findViewById(R.id.txtTThai_GLGH);
            txtTT_title= itemView.findViewById(R.id.txtTongTien_QLGH_title);
            txtTT_content = itemView.findViewById(R.id.txtTT_Item_LSMH);
        }
    }
}
