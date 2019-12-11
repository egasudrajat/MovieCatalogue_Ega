package com.example.moviecatalogueega.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogueega.Model.ModelFilm;
import com.example.moviecatalogueega.R;

import java.util.ArrayList;

public class AdapterGridFilm extends RecyclerView.Adapter<AdapterGridFilm.MyViewHolder> {
    private ArrayList<ModelFilm> ListFilm;

    public AdapterGridFilm(ArrayList<ModelFilm> listfilm) {
        ListFilm = listfilm;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grid_film, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelFilm modelFilm = ListFilm.get(position);
        Glide.with(holder.itemView.getContext())
                .load(modelFilm.getPoster())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ListFilm.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item_photo);
        }
    }
}
