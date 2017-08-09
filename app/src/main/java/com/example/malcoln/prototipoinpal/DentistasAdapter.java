package com.example.malcoln.prototipoinpal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.malcoln.prototipoinpal.model.Dentistas;


import java.util.List;

/**
 * Created by Malcoln on 07/08/2017.
 */

public class DentistasAdapter extends RecyclerView.Adapter<DentistasAdapter.MyViewHolder>{

    private Context ctx;
    private List<Dentistas> lista_dentistas;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nome_dentista, endereco;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome_dentista = itemView.findViewById(R.id.txtNomeDentista);
            endereco = itemView.findViewById(R.id.txtEndereco);
        }
    }
    public DentistasAdapter(List<Dentistas> lista_dentistas) {

        this.lista_dentistas = lista_dentistas;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_dentistas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Dentistas dentistas = lista_dentistas.get(position);
        holder.nome_dentista.setText(dentistas.getNome());
        holder.endereco.setText(dentistas.getEndereco());
    }



    @Override
    public int getItemCount() {
        return lista_dentistas.size();
    }




}
