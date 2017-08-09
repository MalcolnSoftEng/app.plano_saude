package com.example.malcoln.prototipoinpal.model;

/**
 * Created by Malcoln on 07/08/2017.
 */

public class Dentistas {
    private Long id;
    private String nome;
    private String cep;
    private String endereco;



    public Dentistas(String nome, String endereco) {
        this.nome = nome;

        this.endereco = endereco;
    }


    @Override
    public String toString() {
        return "Dentistas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
