package ta.project.wisata.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.squareup.picasso.Picasso;

import ta.project.wisata.R;

public class DetailBerita extends AppCompatActivity {
    private ImageView gBerita;
    private TextView gJudul, gIsi, gPenulis;
    String penulis, gambar, judul, isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        gBerita = findViewById(R.id.gambar);
        gJudul = findViewById(R.id.judul);
        gIsi = findViewById(R.id.isi);
        gPenulis = findViewById(R.id.penulis);

        Intent intent = getIntent();
        penulis = intent.getStringExtra("penulis");
        gambar = intent.getStringExtra("gambar");
        isi = intent.getStringExtra("isi");
        judul = intent.getStringExtra("judul");

        if (intent != null) {

            gPenulis.setText(penulis);
            gJudul.setText(judul);
            gIsi.setText(isi);
            Picasso.get().load("http://panoramawisata.000webhostapp.com/android/gambar/" + gambar).resize(400, 280).into(gBerita);
        }
    }
}