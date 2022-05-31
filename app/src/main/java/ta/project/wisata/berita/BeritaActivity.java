package ta.project.wisata.berita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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
import ta.project.wisata.adapter.AdapterBerita;
import ta.project.wisata.db.Berita;

public class BeritaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Berita> beritas;
    private static final String JSON_URL = "http://panoramawisata.000webhostapp.com/api_berita";
    AdapterBerita adapterBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        recyclerView = findViewById(R.id.listBerita);
        beritas = new ArrayList<>();
        extractBerita();
    }

    private void extractBerita() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
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

                adapterBerita = new AdapterBerita(getApplicationContext(),beritas);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1,
                        RecyclerView.VERTICAL,false);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapterBerita);
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