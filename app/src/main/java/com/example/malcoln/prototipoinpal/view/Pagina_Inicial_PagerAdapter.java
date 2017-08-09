package com.example.malcoln.prototipoinpal.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.malcoln.prototipoinpal.controller.Inpao_Pagina_Usuario_tab_frag;


public class Pagina_Inicial_PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Pagina_Inicial_PagerAdapter(FragmentManager fragment, int NumOfTabs) {
        super(fragment);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: // Chama TAB pagina de entrada
                Inpao_BoasVindas_tab_frag tab1 = new Inpao_BoasVindas_tab_frag();
                //Inpao_Pagina_Usuario_tab_frag tab1 = new Inpao_Pagina_Usuario_tab_frag();
                return tab1;
            case 1: // chama tAB de e.mail para contato ou outra coisa
                Inpao_Pagina_Usuario_tab_frag tab2 = new Inpao_Pagina_Usuario_tab_frag();

                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}



/*Stat {
    int mNumOfTabs;

    public refeicoes_PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Produtos_Refeicoes_tabelaList_frag tab1 = new Produtos_Refeicoes_tabelaList_frag();
                return tab1;
            case 1:
                cand_pref1_tab2_frag tab2 = new cand_pref1_tab2_frag();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    } */

