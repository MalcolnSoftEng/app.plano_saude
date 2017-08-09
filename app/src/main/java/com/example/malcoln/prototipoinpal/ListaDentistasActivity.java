package com.example.malcoln.prototipoinpal;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.malcoln.prototipoinpal.model.Dentistas;
import com.example.malcoln.prototipoinpal.util.BuscarLocalTask;

import java.util.ArrayList;
import java.util.List;

public class ListaDentistasActivity extends AppCompatActivity {

    String[] soma = new String[10];

    List<Dentistas> list;

    DentistasAdapter dentistasAdapter;
    private RecyclerView recyclerView;
    private LayoutInflater inflater;
    private BuscarLocalTask buscarGeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dentistas);

        //String[] dentistas = {"Joao","Zeca","Martinho"};


        list = new ArrayList<>();


        // recuperar GEOLOCALIZAÇÃO DO cLIENTE E COMPARAR COM GEOLOCALIZAÇÃO DOS DENTISTAS

        recyclerView = (RecyclerView) findViewById(R.id.listDentitas);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());

        recyclerView.setLayoutManager(llm);


        prepareLista();
        recyclerView.setAdapter(dentistasAdapter);


        Dentistas endDentitas = new Dentistas("Gweriuioj","Rua Nepal 20");
        endDentitas.setEndereco("Rua Nepal 20");

        Uri recuperarLatLong = Uri.parse("geo:0,0?q="+endDentitas.getEndereco());
        Toast.makeText(this, "Latitude e Longitude :" + recuperarLatLong, Toast.LENGTH_SHORT).show();
    }

    private List<Dentistas> prepareLista() {
        buscarGeo = new BuscarLocalTask(ListaDentistasActivity.this,"Rua Nepal 20");
        buscarGeo.loadInBackground();
        for (int i=0; i< 10; i++ ){



            list.add(new Dentistas("Jose Carlos",soma[i]));

            dentistasAdapter = new DentistasAdapter(list);
        }


        dentistasAdapter.notifyDataSetChanged();
        return  list;
    }
}
