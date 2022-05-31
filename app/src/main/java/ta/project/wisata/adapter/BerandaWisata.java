package ta.project.wisata.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ta.project.wisata.R;
import ta.project.wisata.db.Berita;
import ta.project.wisata.db.Wisata;
import ta.project.wisata.detail.DetailBerita;
import ta.project.wisata.detail.DetailWisata;

public class BerandaWisata extends RecyclerView.Adapter<BerandaWisata.WisataViewHolder> {

    List<Wisata> wisatas;
    private static final String IMG_URL = "http://panoramawisata.000webhostapp.com/android/gambar/";
    Context mContext;

    public BerandaWisata(Context ctx, List<Wisata> wisatas ) {
        this.mContext = ctx;
        this.wisatas = wisatas;
    }

    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_wisata, parent, false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        String[] pano = wisatas.get(position).getGambar().split(",");
        String gambarp = pano[0];
        holder.nama.setText(wisatas.get(position).getNamaWisata());
        holder.desc.setText(wisatas.get(position).getDeskripsi());
        Picasso.get().load(IMG_URL+gambarp)
                .resize(400,250).into(holder.gambar);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailWisata.class);

                intent.putExtra("namaWisata",wisatas.get(position).getNamaWisata());
                intent.putExtra("gambar",wisatas.get(position).getGambar());
                intent.putExtra("deskripsi",wisatas.get(position).getDeskripsi());
                intent.putExtra("alamat",wisatas.get(position).getAlamat());
                intent.putExtra("fasilitas",wisatas.get(position).getFasilitas());
                intent.putExtra("koordinat",wisatas.get(position).getKoordinat());
                intent.putExtra("jamBuka",wisatas.get(position).getJamBuka());
                intent.putExtra("jamTutup",wisatas.get(position).getJamTutup());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wisatas.size();
    }

    public static class WisataViewHolder extends RecyclerView.ViewHolder{

        ImageView gambar;
        TextView nama,desc;
        CardView cardView;

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);

            gambar = itemView.findViewById(R.id.gWisata);
            nama = itemView.findViewById(R.id.nWisata);
            desc = itemView.findViewById(R.id.dWisata);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
