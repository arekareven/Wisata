package ta.project.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ta.project.wisata.wisata.HomeActivity;
import ta.project.wisata.wisata.WisataSejarah;

public class MainActivity extends AppCompatActivity {

    CardView wisata,berita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wisata = findViewById(R.id.cardView);
        berita = findViewById(R.id.cardView2);

        wisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });

        berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WisataSejarah.class));
            }
        });
    }
}