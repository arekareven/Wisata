package ta.project.wisata.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

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

import ta.project.wisata.R;
import ta.project.wisata.adapter.Adapter;
import ta.project.wisata.db.Wisata;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Wisata> wisatas;
    private static final String JSON_URL = "http://panoramawisata.000webhostapp.com/api_wisata";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.listWisata);
        wisatas = new ArrayList<>();
        extractWisata();
    }

    private void extractWisata() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONArray>() {
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

                adapter = new Adapter(getApplicationContext(),wisatas);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1,
                        RecyclerView.VERTICAL,false);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
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