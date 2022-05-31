package ta.project.wisata.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import ta.project.wisata.R;
import ta.project.wisata.adapter.PanoAdapter;
import ta.project.wisata.panorama.PanoActivity;

public class DetailWisata extends AppCompatActivity implements OnMapReadyCallback {

    private ImageView mGambar, mWifi, mResto,mHotel,mMasjid;
    private TextView mNamaWisata,mDeskripsi,mAlamat,mJamBuka,mJamTutup,txtWifi,txtHotel,txtMasjid,txtResto;
    GoogleMap googleMaps;
    String namaWisata, gambar, deskripsi, alamat, fasilitas, jamBuka, jamTutup;


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager  layoutManager;
    PanoAdapter panoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        mGambar = findViewById(R.id.gambar);
        mNamaWisata = findViewById(R.id.namaWisata);
        mDeskripsi = findViewById(R.id.deskripsi);
        mAlamat = findViewById(R.id.alamat);
        mJamBuka = findViewById(R.id.jamBuka);
        mJamTutup = findViewById(R.id.jamTutup);
        mWifi = findViewById(R.id.wifi);
        mResto = findViewById(R.id.resto);
        mHotel = findViewById(R.id.hotel);
        mMasjid = findViewById(R.id.masjid);
        txtHotel = findViewById(R.id.txtHotel);
        txtWifi = findViewById(R.id.txtWifi);
        txtMasjid = findViewById(R.id.txtMasjid);
        txtResto = findViewById(R.id.txtResto);

 //       mFasilitas = findViewById(R.id.fasilitas);

/*        mGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailWisata.this, PanoActivity.class);
                intent.putExtra("gambar", gambar);
                startActivity(intent);
            }
        });
 */
        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Catching incoming intent
        Intent intent = getIntent();
        namaWisata = intent.getStringExtra("namaWisata");
        gambar = intent.getStringExtra("gambar");
        deskripsi = intent.getStringExtra("deskripsi");
        alamat = intent.getStringExtra("alamat");
        fasilitas = intent.getStringExtra("fasilitas");
        jamBuka = intent.getStringExtra("jamBuka");
        jamTutup = intent.getStringExtra("jamTutup");

        Log.e("CEK", fasilitas);

        if(fasilitas.toLowerCase().contains("resto")){
            mResto.setVisibility(View.VISIBLE) ;
            txtResto.setVisibility(View.VISIBLE);
        }if(fasilitas.toLowerCase().contains("wifi")){
            mWifi.setVisibility(View.VISIBLE);
            txtWifi.setVisibility(View.VISIBLE);
        }if(fasilitas.toLowerCase().contains("hotel")){
            mHotel.setVisibility(View.VISIBLE);
            txtHotel.setVisibility(View.VISIBLE);
        }if(fasilitas.toLowerCase().contains("masjid")){
            mMasjid.setVisibility(View.VISIBLE);
            txtMasjid.setVisibility(View.VISIBLE);
        }

        //RecycleView
        String[] pano = gambar.split(",");
        String gambarp = pano[0];
        recyclerView = findViewById(R.id.recycleview);
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        panoAdapter = new PanoAdapter(getApplicationContext(),pano);
        recyclerView.setAdapter(panoAdapter);

        if (intent != null){

            mNamaWisata.setText(namaWisata);
            mDeskripsi.setText(deskripsi);
            mAlamat.setText(alamat);
            Picasso.get().load("http://panoramawisata.000webhostapp.com/android/gambar/"+gambarp).
                    resize(400,280).into(mGambar);
            mJamBuka.setText(jamBuka);
            mJamTutup.setText(jamTutup);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //get LatLong
        Intent intent = getIntent();
        String koordinat = intent.getStringExtra("koordinat");
        String[] latlong =  koordinat.split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title("Wisatanya"));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);

    }
}