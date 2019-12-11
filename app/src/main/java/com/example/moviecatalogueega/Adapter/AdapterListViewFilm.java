package com.example.moviecatalogueega.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogueega.Model.ModelFilm;
import com.example.moviecatalogueega.R;

import java.util.ArrayList;

public class AdapterListViewFilm extends RecyclerView.Adapter<AdapterListViewFilm.MyViewHolder> {
    private ArrayList<ModelFilm> ListFilm;

    public AdapterListViewFilm(ArrayList<ModelFilm> list) {
        ListFilm = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_listview_film, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelFilm modelFilm = ListFilm.get(position);

        holder.tvname.setText(modelFilm.getNama());
        holder.tvdetil.setText(modelFilm.getDeskripsi());

        Glide.with(holder.itemView.getContext())
                .load(modelFilm.getPoster())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ListFilm.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvname, tvdetil;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_photo);
            tvname = itemView.findViewById(R.id.tv_itemname);
            tvdetil = itemView.findViewById(R.id.tv_item_detail);
        }
    }
}
