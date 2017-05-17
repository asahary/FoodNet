package com.asahary.foodnet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText txtNick,txtPass,txtPass2,txtEmail,txtNombre,txtApellidos;
    Button btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initVistas();
    }

    private void initVistas() {
        txtNick= (EditText) findViewById(R.id.txtNick);
        txtPass= (EditText) findViewById(R.id.txtPass);
        txtPass2= (EditText) findViewById(R.id.txtPass2);
        txtEmail= (EditText) findViewById(R.id.txtEmail);
        txtNombre= (EditText) findViewById(R.id.txtNombre);
        txtApellidos= (EditText) findViewById(R.id.txtApellidos);

        btnRegistrar= (Button) findViewById(R.id.btnRegister);
    }

    private boolean comprobarPass(){
        if(txtPass.getText().toString().equals(txtPass2.getText().toString())){
            return true;
        }
        else{
            Toast.makeText(RegisterActivity.this,"Las contraseñas no coinciden, reviselas",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
