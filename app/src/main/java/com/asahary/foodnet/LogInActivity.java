package com.asahary.foodnet;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asahary.foodnet.POJO.Usuario;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity {

    EditText txtNick,txtPass;
    Button btnAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initVistas();
    }

    public void initVistas(){
        txtNick= (EditText) findViewById(R.id.txtNick);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnAccess = (Button) findViewById(R.id.btnAccess);

        //Creamos el objeto de retrofit
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://asahary.esy.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Creamos la interfaz que llamara a los metodos de la api
        final CookNetService service = retrofit.create(CookNetService.class);




        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Usuario>> call =service.listUsers();

                //Hacemos una llamada asincrona para obtener una respuesta
                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        txtNick.setText(response.body().get(0).getNombre());
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        // en caso de fallo
                    }
                });
            }
        });
    }

}
