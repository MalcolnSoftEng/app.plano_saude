package com.example.malcoln.prototipoinpal.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.malcoln.prototipoinpal.FormContato;
import com.example.malcoln.prototipoinpal.ListaDentistasActivity;
import com.example.malcoln.prototipoinpal.R;
import com.example.malcoln.prototipoinpal.model.Tarefas;
import com.example.malcoln.prototipoinpal.view.TarefasAdapter;


import java.util.ArrayList;
import java.util.List;


public class Inpao_Pagina_Usuario_tab_frag extends Fragment {
    List<Tarefas> tarefasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TarefasAdapter mAdapter;

    // variaveis de valor e tipo de produto para futura comunicação com BackEnd
    public static String tipoP0;
    protected static String Dia, Hora;

    // Label dos txt, chamada de itens txtCarQuant=itens add no banco SQLite;
    TextView txtCarQuant, txtValTotal, txtZero, txtItens;

    Button btnFinalizar;
    ImageView imagView;

    LinearLayout carteirinhaView;

    private int quantFinal;
    private double quantValTotal;

    //PedidosRepositorio pedidosRepositorio;

    //CarAdapter adapter;

    public Inpao_Pagina_Usuario_tab_frag(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.recyclerview_tarefasinpao_activity, container, false);

        carteirinhaView = (LinearLayout)layout.findViewById(R.id.carteirinhaView);
        carteirinhaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsuario();
            }
        });

        recyclerView = (RecyclerView) layout.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        android.support.v7.widget.GridLayoutManager llm = new android.support.v7.widget.GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(llm);

        mAdapter = new TarefasAdapter(tarefasList);
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Tarefas tarefas = tarefasList.get(position);

                Fragment fragment = null;
                if (position==0){
                    startActivity(new Intent(getActivity(),FormContato.class));
                }
                if (position==1){

                    View alertView = LayoutInflater.from(getContext()).inflate(R.layout.alert_redecredenciada,null);
                    final AlertDialog.Builder dialogRedeCred = new AlertDialog.Builder(getContext());
                    dialogRedeCred.setView(alertView)
                            .setTitle("Pesquisa Rede Credenciada");
                    CardView pesquisaRua = alertView.findViewById(R.id.pesquisaCep);
                    pesquisaRua.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(), "Teste: " + tarefas.getLinha(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    CardView pesquisaGPS = alertView.findViewById(R.id.pesquisaGPS);
                    pesquisaGPS.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), ListaDentistasActivity.class));
                        }
                    });
                    final CardView btnBack = alertView.findViewById(R.id.btnVoltar);
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogRedeCred.getContext();
                        }
                    });


                    AlertDialog alertDialog = dialogRedeCred.create();
                    alertDialog.show();


                }
                if (position==2){
                    startActivity(new Intent(getActivity(),FormContato.class));

                }
                if (position==3){
                    startActivity(new Intent(getActivity(),FormContato.class));

                }
                if (position==4){
                    startActivity(new Intent(getActivity(),FormContato.class));
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // * OBJETO instanciado em INFLATE 'layout'

        prepareTarefasInpao();

        return layout;
    }

    private List<Tarefas> prepareTarefasInpao() {

        tarefasList.add(new Tarefas("Detalhes", "DO MEU PLANO", R.drawable.ic_meuplano_384px,0));
        tarefasList.add(new Tarefas("Localizar", "REDE CREDENCIADA", R.drawable.pin_redes_white_384px,0));
        tarefasList.add(new Tarefas("Consultar", "REEMBOLSO", R.drawable.ic_reembolso_384px,0));
        tarefasList.add(new Tarefas("Verificar", "HISTÓRICO DE\n TRATAMENTOS", R.drawable.ic_tratamentos_384px,0));

        mAdapter.notifyDataSetChanged();
        return tarefasList;
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                //finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void getUsuario() {

        // get prompts.xml view

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.carteirinha_view, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Carteirinha de :"+"XML do Id");
        /*

        pedidosRepositorio = new PedidosRepositorio(getContext());
        ListView lv = (ListView)promptsView.findViewById(R.id.listCar);
        adapter = new CarAdapter(getContext(), pedidosRepositorio.lista());
        lv.setAdapter(adapter);

           */

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // get user input and set it to result

                                // edit text
                                //startActivity(new Intent(getContext(), NavigationActivity.class));
                                dialog.dismiss();

                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }


}