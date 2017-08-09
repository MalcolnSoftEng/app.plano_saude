package com.example.malcoln.prototipoinpal.model;

/**
 * Created by Malcoln on 09/10/2016.
 */

public class Tarefas {
    private String linha, trajeto;
    public int candidato;
    private Integer id;

    public Tarefas() {
    }

    public Tarefas(String linha, String trajeto, int candidato, Integer id) {
        this.linha = linha;
        this.trajeto = trajeto;
        this.candidato = candidato;
        this.id = id;
    }
    public Integer getId(){ return id;}
    public void setId(Integer id){ this.id = id;}

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(String trajeto) {
        this.trajeto = trajeto;
    }

    public int getCandidato() {
        return candidato;
    }

    public void setCandidato(int trajeto) {
        this.candidato = candidato;
    }


}
