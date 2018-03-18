package com.example.armin.wave.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Una on 27.10.2017..
 */

public class LoginRegisterService {
    private static String BASE_URL = "https://wave.sdmiransan.com/";

    public interface LoginAPI{

        @FormUrlEncoded
        @POST("/api/login")
        Call<ResponseBody> postLoginSession(@Field("username") String username,
                                     @Field("password") String password,
                                     @Header("Cookie") String token);

        @FormUrlEncoded
        @POST("/api/register")
        Call<ResponseBody> postRegister(@Field("username") String username,
                                        @Field("password") String password,
                                        @Field("email") String email,
                                        @Field("first_name") String firstName,
                                        @Field("last_name") String lastName);

        @GET("/api/me")
        Call<ResponseBody> aboutUser(@Header("Cookie") String token);

        @GET("/api/me")
        Call<ResponseBody> aboutUserSession();

        @GET("/api/logout")
        Call<ResponseBody> logout(@Header("Cookie") String token);
    }

    public LoginAPI GetLoginAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(LoginAPI.class);
    }
}
