package com.asahary.foodnet;

import com.asahary.foodnet.POJO.Usuario;

import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by omniumlab on 15/05/2017.
 */

public interface CookNetService {

    public static String URL_BASE="http://asahary.esy.es/";

    @GET("usuarios")
    Call<List<Usuario>> listUsers();

    @FormUrlEncoded
    @POST("usuarios/login")
    Call<Usuario> login(@Field("user")String user,@Field("pass")String pass);

    @FormUrlEncoded
    @POST("comprobaciones/nick")
    Call<Boolean> comprobarNick(@Field("nick")String nick);

    @FormUrlEncoded
    @POST("comprobaciones/email")
    Call<Boolean> comprobarEmail(@Field("email")String email);

}
