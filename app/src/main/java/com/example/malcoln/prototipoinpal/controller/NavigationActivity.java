package com.example.malcoln.prototipoinpal.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.malcoln.prototipoinpal.FormContato;
import com.example.malcoln.prototipoinpal.R;
import com.example.malcoln.prototipoinpal.model.Usuario;
import com.example.malcoln.prototipoinpal.dao.UsuarioHelper;
import com.example.malcoln.prototipoinpal.view.Pagina_Inicial_Fragment;



public class NavigationActivity extends AppCompatActivity {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mOpcaoSelecionada;

    private UsuarioHelper usuarioHelper;
    private String usuarioEmail;
    TextView setEmailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //LayoutInflater inflater = LayoutInflater.from(NavigationActivity.this);
        //View navView = inflater.inflate(R.layout.nav_header_navigation,null);

        Intent it = getIntent();
        Usuario usuario = (Usuario) it.getSerializableExtra("emailUsuario");
        usuarioEmail = usuario.getEmail();
        Toast.makeText(getApplicationContext(),"Usuario Teste:"+usuarioEmail,Toast.LENGTH_LONG).show();



        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        selecionarOpcaoMenu(menuItem);
                        return true;
                    }
                });

        String teste = String.valueOf(mNavigationView.findViewById(R.id.textEmailUser));
        Toast.makeText(getApplicationContext(), "Teste Nac:"+teste,Toast.LENGTH_LONG).show();
        //setEmailUser = (TextView) navigationView.findViewById(R.id.textEmailUser);
        //setEmailUser.setText(); // SETAR E.MAIL DO USUÁRIO INPAO

        if (savedInstanceState == null){
            mOpcaoSelecionada = R.id.nav_intro;
        } else {
            mOpcaoSelecionada = savedInstanceState.getInt("menuItem");
        }
        selecionarOpcaoMenu(mNavigationView.getMenu().findItem(mOpcaoSelecionada));
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("menuItem", mOpcaoSelecionada);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        //if (item.getItemId()==R.id.nav_share){
            //CHAMAR AÇÃO PARA COMPARTILHAR WhatsAppContact();
        //}
        if (item.getItemId()==R.id.action_exit){
            Sair();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void selecionarOpcaoMenu(MenuItem menuItem){

        Fragment fragment = null;
        mDrawerLayout.closeDrawers();
        int id = menuItem.getItemId();
        if (id == R.id.nav_intro) {

            fragment = new Pagina_Inicial_Fragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();

        } else if (id == R.id.nav_1) {
            //startActivity(new Intent(getBaseContext(), Produtos_RefeicoesList_ViewPager_Activity.class));

        } else if (id == R.id.nav_2) {
            //startActivity(new Intent(getBaseContext(), Produtos_LanchesList_ViewPager_Activity.class));


        } else if (id == R.id.nav_3) {
            //WhatsAppContact();

        } else if (id == R.id.nav_4_contato) {
            startActivity(new Intent(getBaseContext(), FormContato.class));

        } else if (id==R.id.action_exit){
            Sair();
        }
    }
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate menu resource file.
                getMenuInflater().inflate(R.menu.menu_compartilhar, menu);
                MenuItem shareMenuItem = menu.findItem(R.id.action_share);

                if (shareMenuItem!=null) {
                    ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenuItem);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "LINK APP INPAO GOOGLEPLAY");
                    shareActionProvider.setShareIntent(shareIntent);
                    //shareMenuItem.setIcon(R.drawable.ic_share_black_24dp);
                }

                return super.onCreateOptionsMenu(menu);
            }
    public void Sair() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Sair do app?");
        alertDialogBuilder
                .setMessage("Click SIM para Sair!")
                .setCancelable(false)
                .setPositiveButton("SIM",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })
                .setNeutralButton("Site-", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       //Avisofinal();
                    }
                })
                .setNegativeButton("VOLTAR AO APLICATIVO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Colocar -> Refactor -> Extract -> Method
    }
}
