package com.example.deneme;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String jsonCevabi;
    private String url = "https://free-nba.p.rapidapi.com/players?page=0&per_page=25";

    private ListView listView;
    private OzelAdaptor adapter;
    private List<Oyuncular> oyuncularListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oyuncularListesi = new ArrayList<>();
        adapter = new OzelAdaptor(this, R.layout.item_player, oyuncularListesi);
        listView.setAdapter(adapter);

        apiTalep(url);
    }

    private void apiTalep(String url) {

        RequestQueue talepSirasi = Volley.newRequestQueue(this);
        StringRequest talep = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("test", "Tüm cevap :" + response);
                jsonCevabi = response;
                try {
                    jsonAyikla(); // jsonCevabi elde edildikten sonra json ayıklama işlemi(json parsing)
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("test", "Hata mesajı :" + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "417c6ac604msh2359943fcdbe616p18ee75jsn65db57ecf01d");
                params.put("X-RapidAPI-Host", "free-nba.p.rapidapi.com");
                return params;
            }
        };
        talepSirasi.add(talep);
    }

    private void jsonAyikla() throws JSONException {
        try {
            JSONObject kokJo = new JSONObject(jsonCevabi);
            JSONArray listJA = kokJo.getJSONArray("data");

            for (int i = 0; i < listJA.length(); i++) {
                JSONObject playerObj = listJA.getJSONObject(i);
                String ad = playerObj.getString("first_name");
                String soyad = playerObj.getString("last_name");
                String pozisyon = playerObj.getString("position");

                Oyuncular oyuncu = new Oyuncular(ad, soyad, pozisyon);
                oyuncularListesi.add(oyuncu);
            }

            adapter.notifyDataSetChanged(); // Listeyi güncelle

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
