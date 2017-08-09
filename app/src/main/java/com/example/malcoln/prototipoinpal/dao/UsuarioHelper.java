package com.example.malcoln.prototipoinpal.dao;

import android.widget.EditText;


import com.example.malcoln.prototipoinpal.R;
import com.example.malcoln.prototipoinpal.controller.LoginActivity;
import com.example.malcoln.prototipoinpal.model.Usuario;

/**
 * Created by Malcoln on 04/08/2017.
 */

public class UsuarioHelper {
    private EditText campoEmail, campoSenha;
    private Usuario usuario;

    public UsuarioHelper(LoginActivity activity) {

            campoEmail = (EditText) activity.findViewById(R.id.userEmailId);
            campoSenha = (EditText)activity.findViewById(R.id.passwordId);
            usuario = new Usuario();
    }
    public Usuario getUsuario() {
        usuario.setEmail(campoEmail.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        campoEmail.setText(usuario.getEmail());
        this.usuario = usuario;
    }

}
