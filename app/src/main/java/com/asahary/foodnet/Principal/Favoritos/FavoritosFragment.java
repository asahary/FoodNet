package com.asahary.foodnet.Principal.Favoritos;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asahary.foodnet.CookNetService;
import com.asahary.foodnet.POJO.Receta;
import com.asahary.foodnet.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Saha on 22/05/2017.
 */

public class FavoritosFragment extends Fragment implements FavoritosAdapter.OnReciclerItemClickListener{
    RecyclerView lista;
    FavoritosAdapter adaptador;


    public FavoritosFragment(){
    }

    public void initVistas(View vista){

        lista= (RecyclerView) vista.findViewById(R.id.rvFavoritos);
        adaptador=new FavoritosAdapter(new ArrayList<Receta>(obtenerRecetas()),this);
        lista.setAdapter(adaptador);
        lista.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private List<Receta> obtenerRecetas() {
        List<Receta> recetas=null;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(CookNetService.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();

        CookNetService servicio =retrofit.create(CookNetService.class);

        Call<List<Receta>> llamada=servicio.listRecetas();

        llamada.enqueue(new Callback<List<Receta>>() {
            @Override
            public void onResponse(Call<List<Receta>> call, Response<List<Receta>> response) {

            }

            @Override
            public void onFailure(Call<List<Receta>> call, Throwable t) {

            }
        });
        return recetas;
    }

    @Override
    public void itemClic(Receta receta) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setRetainInstance(true);
        return inflater.inflate(R.layout.item_receta, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Habilitamos los permisos que nos hacen falta para utilizar el retrofit
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View vista= getView();
        initVistas(vista);
    }

    public static FavoritosFragment newInstance(){
        return new FavoritosFragment();
    }
}
