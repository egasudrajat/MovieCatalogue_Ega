package com.example.moviecatalogueega.Crud_FAN;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.widget.ANImageView;
import com.example.moviecatalogueega.R;

import java.util.List;

public class ListSiswaAdapter extends RecyclerView.Adapter<ListSiswaAdapter.MyViewHolder> {
    private List<ModelCrud> dataSiswa;

    public ListSiswaAdapter(List<ModelCrud> dataSiswa) {
        this.dataSiswa = dataSiswa;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crud_list_siswa, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelCrud data = dataSiswa.get(position);
        holder.tvnama.setText(data.getNama());
        holder.tvkelas.setText(data.getKelas());
        holder.tvalamat.setText(data.getAlamat());
        holder.imageView.setImageUrl(data.getImage());

    }

    @Override
    public int getItemCount() {
        return dataSiswa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvnama, tvkelas, tvalamat;
        ANImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvalamat = itemView.findViewById(R.id.textListMahasiswaalamat);
            tvkelas = itemView.findViewById(R.id.textListMahasiswakelas);
            tvnama = itemView.findViewById(R.id.textListMahasiswaNama);
            imageView = itemView.findViewById(R.id.imagevw);
        }
    }
}
