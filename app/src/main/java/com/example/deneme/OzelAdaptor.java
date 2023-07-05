package com.example.deneme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OzelAdaptor extends ArrayAdapter<Oyuncular> {

    private Context context;
    private int resource;
    private List<Oyuncular> oyuncularListesi;

    public OzelAdaptor(@NonNull Context context, int resource, @NonNull List<Oyuncular> oyuncularListesi) {
        super(context, resource, oyuncularListesi);
        this.context = context;
        this.resource = resource;
        this.oyuncularListesi = oyuncularListesi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView txtAd = convertView.findViewById(R.id.txtAd);


        Oyuncular oyuncu = oyuncularListesi.get(position);

        txtAd.setText(oyuncu.getAd());


        return convertView;
    }
}
