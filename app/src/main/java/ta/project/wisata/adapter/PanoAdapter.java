package ta.project.wisata.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ta.project.wisata.R;
import ta.project.wisata.panorama.PanoActivity;

public class PanoAdapter extends RecyclerView.Adapter<PanoAdapter.ImageViewHolder> {

    String[] pano;
    Context context;

    public PanoAdapter(Context context,String[] pano) {
        this.pano = pano;
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pano, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        String pano_id =  pano[position];
        Picasso.get().load("http://panoramawisata.000webhostapp.com/android/gambar/"+pano_id).resize(250,250).into(holder.pano);
        holder.pano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PanoActivity.class);
                intent.putExtra("gambar", pano_id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pano.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView pano;

        public ImageViewHolder(View itemView) {
            super(itemView);
            pano = (ImageView) itemView.findViewById(R.id.pano);
        }

    }
}
