package com.example.malcoln.prototipoinpal.model;

import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by Malcoln on 04/08/2017.
 */

public class Usuario implements Serializable {
    private int id;
    private String Nome;
    private String email;
    private String senha;

    private EditText campoEmail, campoSenha;
    private Usuario usuario;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
