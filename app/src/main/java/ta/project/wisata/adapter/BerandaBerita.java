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

import java.util.ArrayList;
import java.util.List;

import ta.project.wisata.R;
import ta.project.wisata.db.Berita;
import ta.project.wisata.detail.DetailBerita;

public class BerandaBerita extends RecyclerView.Adapter<BerandaBerita.BeritaViewHolder> {

    List<Berita> beritas;
    Context mContext;
    private static final String IMG_URL = "http://panoramawisata.000webhostapp.com/android/gambar/";

    public BerandaBerita(Context ctx, List<Berita> beritas) {
        this.beritas = beritas;
        this.mContext = ctx;
    }

    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaBerita.BeritaViewHolder holder, int position) {
        holder.judul.setText(beritas.get(position).getJudul());
        holder.desc.setText(beritas.get(position).getIsi());
        Picasso.get().load(IMG_URL+beritas.get(position).getGambar())
                .resize(400,250).into(holder.gambar);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailBerita.class);

                intent.putExtra("penulis",beritas.get(position).getPenulis());
                intent.putExtra("gambar",beritas.get(position).getGambar());
                intent.putExtra("judul",beritas.get(position).getJudul());
                intent.putExtra("isi",beritas.get(position).getIsi());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }

    public static class BeritaViewHolder extends RecyclerView.ViewHolder{

        ImageView gambar;
        TextView judul,desc;
        CardView cardView;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            gambar = itemView.findViewById(R.id.gBerita);
            judul = itemView.findViewById(R.id.jBerita);
            desc = itemView.findViewById(R.id.dBerita);
        }
    }
}
