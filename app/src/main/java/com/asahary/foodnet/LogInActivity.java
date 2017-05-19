package com.asahary.foodnet;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asahary.foodnet.POJO.Usuario;

import java.io.IOException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class LogInActivity extends AppCompatActivity {



    EditText txtNick,txtPass;
    Button btnAccess;
    TextView lblRegistrar;

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
        lblRegistrar= (TextView) findViewById(R.id.lblRegistro);

        //Link al formulario de registro
        lblRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        //Boton de LogIn
        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creamos el retrofit poniendole la url base y el convertidor de Gson
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CookNetService.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Creamos la interfaz que contiene los metodos que llaman a la api
                CookNetService apiLogin= retrofit.create(CookNetService.class);

                //Ejecutamos el metodo que hace log in de la interfaz
                Call<Usuario> call = apiLogin.login(txtNick.getText().toString(),txtPass.getText().toString());

                //Hacemos una llamada asincrona
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        if(response.body()!=null){
                            Toast.makeText(LogInActivity.this, response.body().getNombre(), Toast.LENGTH_SHORT).show();
                        }else{
                            //Login fallido
                            Toast.makeText(LogInActivity.this, "Cuerpo nullo", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                    }
                });
            }
        });
    }

}
