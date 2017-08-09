package com.example.malcoln.prototipoinpal.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.malcoln.prototipoinpal.R;
import com.example.malcoln.prototipoinpal.model.Tarefas;

import java.util.List;

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.MyViewHolder> {
    Context context;
    private List<Tarefas> tarefasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView linha, trajeto;
        public ImageView imgIcones;

        public MyViewHolder(View view) {
            super(view);
            linha = (TextView) view.findViewById(R.id.titulo_tarefa);
            trajeto = (TextView) view.findViewById(R.id.descritivo_tarefa);
            imgIcones = (ImageView) view.findViewById(R.id.imgIconesCep);
        }
    }


    public TarefasAdapter(List<Tarefas> busoesList) {
        this.tarefasList = busoesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarefas_inpao_list_row, parent, false); //VIEW DO GRID DA LISTAGEM DE TAREFAS

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tarefas tarefas = tarefasList.get(position);
        holder.linha.setText(tarefas.getLinha());
        holder.trajeto.setText(tarefas.getTrajeto());
        holder.imgIcones.setImageResource(tarefas.getCandidato());
    }

    @Override
    public int getItemCount() {
        return tarefasList.size();
    }
}
