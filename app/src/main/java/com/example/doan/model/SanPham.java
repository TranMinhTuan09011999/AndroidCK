package com.example.doan.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPham implements Serializable,Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ten")
    @Expose
    private String tensp;
    @SerializedName("gia")
    @Expose
    private String gia;
    @SerializedName("km")
    @Expose
    private int khuyenmai;
    @SerializedName("mota")
    @Expose
    private String mota;
    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("idloaisp")
    @Expose
    private int idloaisp;
    @SerializedName("ttx")
    @Expose
    private int ttx;


    public SanPham(int id, String tensp, String gia, int khuyenmai, String mota, String hinhanh, int idloaisp, int ttx) {
        this.id = id;
        this.tensp = tensp;
        this.gia = gia;
        this.khuyenmai = khuyenmai;
        this.mota = mota;
        this.hinhanh = hinhanh;
        this.idloaisp = idloaisp;
        this.ttx = ttx;
    }

    protected SanPham(Parcel in) {
        id = in.readInt();
        tensp = in.readString();
        gia = in.readString();
        khuyenmai = in.readInt();
        mota = in.readString();
        hinhanh = in.readString();
        idloaisp = in.readInt();
        ttx = in.readInt();
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(int khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getIdloaisp() {
        return idloaisp;
    }

    public void setIdloaisp(int idloaisp) {
        this.idloaisp = idloaisp;
    }

    public int getTtx() {
        return ttx;
    }

    public void setTtx(int ttx) {
        this.ttx = ttx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tensp);
        dest.writeString(gia);
        dest.writeInt(khuyenmai);
        dest.writeString(mota);
        dest.writeString(hinhanh);
        dest.writeInt(idloaisp);
        dest.writeInt(ttx);
    }
}
