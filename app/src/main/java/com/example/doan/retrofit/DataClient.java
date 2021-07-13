package com.example.doan.retrofit;

import com.example.doan.model.Cart;
import com.example.doan.model.Cart1;
import com.example.doan.model.CartRequest;
import com.example.doan.model.Category;
import com.example.doan.model.DetailCart;
import com.example.doan.model.ImageDetails;
import com.example.doan.model.LoginRequest;
import com.example.doan.model.MessageRespone;
import com.example.doan.model.Order;
import com.example.doan.model.OrderDetails;
import com.example.doan.model.OrderRequest;
import com.example.doan.model.QuantityRequest;
import com.example.doan.model.SanPham;
import com.example.doan.model.SanPham1;
import com.example.doan.model.SingupRequest;
import com.example.doan.model.StatusRequest;
import com.example.doan.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DataClient {
    @GET("getSPMN.php")
    Call<List<SanPham>> getSanPham();

    @FormUrlEncoded
    @POST("getDSSP.php")
    Call<List<SanPham>> getDSSP(@Field("IDLOAISP") int id);

    @GET("QLSP.php")
    Call<List<SanPham>> QLSP();

    @FormUrlEncoded()
    @POST("getDSHA.php")
    Call<List<String>> getDSHA(@Field("IDSP") int id);

    @FormUrlEncoded()
    @POST("login.php")
    Call<User> login(@Field("Email") String email,@Field("MatKhau") String matkhau);

    @FormUrlEncoded()
    @POST("insertCart.php")
    Call<String> insertCart(@Field("IDUSER") int id, @Field("NGAY") String date, @Field("TONGTIEN") String tongtien,@Field("TRANGTHAI") String tt,@Field("LIST") JSONArray list);

    @FormUrlEncoded()
    @POST("insertDetailCart.php")
    Call<String> insertDetailCart (@Field("LIST") JSONArray list);

    @FormUrlEncoded()
    @POST("getLISTCARTBYID.php")
    Call<List<Cart>> getCartByID (@Field("IDUSER") int id);

    @FormUrlEncoded()
    @POST("getListDetailCart.php")
    Call<List<DetailCart>> getDetailCart(@Field("IDCART") int id);

    @FormUrlEncoded()
    @POST("verifymail.php")
    Call<String> verifyMail(@Field("email") String mail,@Field("content") String content);

    @FormUrlEncoded()
    @POST("getDSDH_T.php")
    Call<List<DetailCart>> hihi(@Field("begin") String begin, @Field("end") String end);

    @GET("getCart.php")
    Call<List<Cart>> getCart();
    ////////////////////////////////////////////////
    //XEM từ đây

    @POST("auth/signin")
    Call<User> login1(@Body LoginRequest loginRequest);

    @GET("user/promotion")
    Call<List<SanPham1>> getSanPhamDiscount();

    @GET("user/category/{categoryName}")
    Call<Category> getListProductByCategoryName(@Path("categoryName") String categoryName);

    @GET("user/product/detail/{imageId}")
    Call<List<ImageDetails>> getListImageByProductId(@Path("imageId") Long imageId);

    @POST("auth/signup")
    Call<MessageRespone> signup(@Body SingupRequest singupRequest);

    @POST("addcart/addProduct")
    Call<List<Cart1>> addCartByUserId(@Header("Authorization") String token, @Body CartRequest cartRequest);

    @GET("addcart/getCartsByUserId/{userId}")
    Call<List<Cart1>> getCartByUser(@Header("Authorization") String token, @Path("userId") Long userId);

    @PUT("addcart/update/{id}")
    Call<List<Cart1>> updateQuantityOfProductInCart(@Header("Authorization") String token, @Path("id") Long id, @Body QuantityRequest quantityRequest);

    @GET("order/listOrder/{userId}")
    Call<List<Order>> getOrderByUserId(@Header("Authorization") String token, @Path("userId") Long userId);

    @GET("order/listOrderDetails/{orderId}")
    Call<List<OrderDetails>> getOrderDetailsByOrderId(@Header("Authorization") String token, @Path("orderId") Long orderId);

    @POST("order/checkout")
    Call<Order> checkout(@Header("Authorization") String token, @Body Order order);

    @PUT("user/product/updateQuantity/{idProduct}")
    Call<SanPham1> updateQuantityOfProduct(@Path("idProduct") Long idProduct, @Body QuantityRequest quantityRequest);

    @PUT("order/listOrder/cancel/{id}")
    Call<Order> cancelOrder(@Header("Authorization") String token, @Path("id") Long id, @Body StatusRequest status);

    @DELETE("addcart/delete/{id}")
    Call<List<Cart1>> deleteCart(@Header("Authorization") String token, @Path("id") Long id);
}
