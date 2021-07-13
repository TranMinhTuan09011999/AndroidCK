package com.example.doan.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Parcelable, Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("idUser")
    @Expose
    private int idUser;
    @SerializedName("Ngaymua")
    @Expose
    private String ngayMua;
    @SerializedName("Tongtien")
    @Expose
    private String tongtien;

    @SerializedName("Trangthai")
    @Expose
    private String trangthai;

    public Cart(int id,int idUser, String ngayMua,String tongtien,String trangthai) {
        this.id = id;
        this.idUser = idUser;
        this.ngayMua = ngayMua;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    protected Cart(Parcel in) {
        id = in.readInt();
        idUser = in.readInt();
        ngayMua = in.readString();
        tongtien = in.readString();
        trangthai = in.readString();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idUser);
        dest.writeString(ngayMua);
        dest.writeString(tongtien);
        dest.writeString(trangthai);
    }
}
