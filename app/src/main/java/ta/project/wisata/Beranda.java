package ta.project.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ta.project.wisata.adapter.Adapter;
import ta.project.wisata.adapter.AdapterBerita;
import ta.project.wisata.adapter.BerandaBerita;
import ta.project.wisata.adapter.BerandaWisata;
import ta.project.wisata.berita.BeritaActivity;
import ta.project.wisata.db.Berita;
import ta.project.wisata.db.Wisata;
import ta.project.wisata.wisata.HomeActivity;
import ta.project.wisata.wisata.WisataSejarah;
import ta.project.wisata.wisata.WisataTaman;
import ta.project.wisata.wisata.WisataWahana;

public class Beranda extends AppCompatActivity {

    RecyclerView recyclerViewWisata;
    RecyclerView recyclerViewBerita;
    List<Wisata> wisatas;
    List<Berita> beritas;
    private static final String JSON_URL = "http://panoramawisata.000webhostapp.com/api_wisata";
    private static final String JSON_URL2 = "http://panoramawisata.000webhostapp.com/api_berita";
    BerandaWisata adapter;
    BerandaBerita adapter2;
    TextView viewAll;
    LinearLayout alam,taman,wahana,sejarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        recyclerViewWisata = findViewById(R.id.recycleviewWisata);
        wisatas = new ArrayList<>();
        extractWisata();
        recyclerViewBerita = findViewById(R.id.recycleviewBerita);
        beritas = new ArrayList<>();
        extractBerita();

        viewAll = findViewById(R.id.viewAll);
        alam = findViewById(R.id.alam);
        taman = findViewById(R.id.taman);
        wahana = findViewById(R.id.wahana);
        sejarah = findViewById(R.id.sejarah);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beranda.this, BeritaActivity.class);
                startActivity(intent);
            }
        });
        alam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beranda.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        taman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beranda.this, WisataTaman.class);
                startActivity(intent);
            }
        });
        wahana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beranda.this, WisataWahana.class);
                startActivity(intent);
            }
        });
        sejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beranda.this, WisataSejarah.class);
                startActivity(intent);
            }
        });


    }

    private void extractWisata() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject wisataObject = response.getJSONObject(i);

                        Wisata wisata = new Wisata();
                        wisata.setNamaWisata(wisataObject.getString("namaWisata").toString());
                        wisata.setGambar(wisataObject.getString("gambar").toString());
                        wisata.setAlamat(wisataObject.getString("alamat").toString());
                        wisata.setDeskripsi(wisataObject.getString("deskripsi").toString());
                        wisata.setFasilitas(wisataObject.getString("fasilitas").toString());
                        wisata.setKoordinat(wisataObject.getString("koordinat").toString());
                        wisata.setJamBuka(wisataObject.getString("jamBuka").toString());
                        wisata.setJamTutup(wisataObject.getString("jamTutup").toString());
                        wisatas.add(wisata);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                adapter = new BerandaWisata(getApplicationContext(), wisatas);
                recyclerViewWisata.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.HORIZONTAL,false));
                recyclerViewWisata.setAdapter(adapter);
                recyclerViewWisata.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void extractBerita() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject beritaObject = response.getJSONObject(i);

                        Berita berita = new Berita();
                        berita.setPenulis(beritaObject.getString("penulis").toString());
                        berita.setGambar(beritaObject.getString("gambar").toString());
                        berita.setJudul(beritaObject.getString("judul").toString());
                        berita.setIsi(beritaObject.getString("isi").toString());
                        beritas.add(berita);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                adapter2 = new BerandaBerita(getApplicationContext(), beritas);
                recyclerViewBerita.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.HORIZONTAL,false));
                recyclerViewBerita.setAdapter(adapter2);
                recyclerViewBerita.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

}