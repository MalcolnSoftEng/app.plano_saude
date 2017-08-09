package com.example.malcoln.prototipoinpal.controller;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.malcoln.prototipoinpal.R;
import com.example.malcoln.prototipoinpal.model.Usuario;
import com.example.malcoln.prototipoinpal.dao.UsuarioHelper;


/**
 * Created by Malcoln on 04/08/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private UsuarioHelper usuarioHelper;
    private Usuario usuario;
    private Button acesso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        usuarioHelper = new UsuarioHelper(this);
        acesso = (Button) findViewById(R.id.signInId);
        acesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
    }

    private void logIn(){
        usuario = usuarioHelper.getUsuario();

        if (usuario.getEmail().equals("ok")){
            onComplete();
        } else {
            Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show();
        }
    }

    void onComplete(){
        Toast.makeText(getApplication(), "BEM VINDO! .", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
        intent.putExtra("emailUsuario", usuario);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Usuario:"+usuario.getEmail(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signInId:
                logIn();
                break;
        }
    }
}
