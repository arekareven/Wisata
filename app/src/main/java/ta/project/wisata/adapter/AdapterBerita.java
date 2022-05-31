package ta.project.wisata.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ta.project.wisata.detail.DetailBerita;
import ta.project.wisata.R;
import ta.project.wisata.db.Berita;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder>{
    LayoutInflater inflater;
    List<Berita> beritas;
    Context mContext;
    private static final String IMG_URL = "http://panoramawisata.000webhostapp.com/android/gambar/";

    public AdapterBerita(Context ctx, List<Berita> beritas){
        this.inflater = LayoutInflater.from(ctx);
        this.beritas = beritas;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_berita,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterBerita.ViewHolder holder, int position) {
        holder.judul.setText(beritas.get(position).getJudul());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        ImageView gambar;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul);
            gambar = itemView.findViewById(R.id.gBerita);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
