package com.asahary.foodnet;

import com.asahary.foodnet.POJO.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by omniumlab on 15/05/2017.
 */

public interface CookNetService {

    @GET("usuarios")
    Call<List<Usuario>> listUsers();
}
