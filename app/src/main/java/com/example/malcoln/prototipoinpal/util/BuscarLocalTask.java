package com.example.malcoln.prototipoinpal.util;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Malcoln on 09/08/2017.
 */

public class BuscarLocalTask extends AsyncTaskLoader{

    Context mContext;
    String mLocal;
    List<Address> mEnderecosEncontrados;

    public BuscarLocalTask(Context activity, String local) {
        super(activity);
        this.mContext = activity;
        this.mLocal = local;
    }
    public void onStartLoading(){
        if (mEnderecosEncontrados == null){
            forceLoad();
        }else{
            deliverResult(mEnderecosEncontrados);
        }
    }

    @Override
    public List<Address> loadInBackground() {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            mEnderecosEncontrados = geocoder.getFromLocationName(mLocal,10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mEnderecosEncontrados;
    }
}
