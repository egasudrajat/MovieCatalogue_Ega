package com.example.moviecatalogueega.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogueega.ActivityDetilFilm;
import com.example.moviecatalogueega.DialogPoster;
import com.example.moviecatalogueega.MainActivity;
import com.example.moviecatalogueega.Model.ModelFilm;
import com.example.moviecatalogueega.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdapterCardViewFilm extends RecyclerView.Adapter<AdapterCardViewFilm.MyViewHolder> {
    private Context mcontext;
    private onPositionCallback positionCallback;

    public void setPositionCallback(onPositionCallback positionCallback) {
        this.positionCallback = positionCallback;
    }

    private ArrayList<ModelFilm> Listfilm = new ArrayList<>();

    public AdapterCardViewFilm(ArrayList<ModelFilm> listfilm) {
        Listfilm.clear();
        Listfilm.addAll(listfilm);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_film_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        mcontext = holder.itemView.getContext();


        Log.d("coba", "onBindViewHolder: "+position);

        final ModelFilm modelFilm = Listfilm.get(position);
        Glide.with(mcontext)
                .load(modelFilm.getPoster())
                .apply(new RequestOptions().override(170, 250))
                .into(holder.imageView);
        holder.tvnamafilm.setText(modelFilm.getNama());
        holder.tvtglrilis.setText(modelFilm.getTanggal());
        holder.tvdeskripsi.setText(modelFilm.getDeskripsi());

        holder.btndetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFilm data = Listfilm.get(holder.getAdapterPosition());
                Intent intent = new Intent(v.getContext(), ActivityDetilFilm.class);
                intent.putExtra("datafilm", data);
                v.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogPoster dialogPoster = new DialogPoster();

                //======= via Bundle ==========
                String nama = "EGA SUDRAJAT";
                Bundle bundle1 = new Bundle();
                bundle1.putString(DialogPoster.EXTRA_NAME, nama);
                dialogPoster.setArguments(bundle1);
                //======= akses Fragmen dari adapter====
                FragmentManager fragmentManager = ((AppCompatActivity) mcontext).getSupportFragmentManager();
                dialogPoster.show(fragmentManager, DialogPoster.class.getSimpleName());

            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPoster dialogPoster = new DialogPoster();

                //========= Via Parcelable (bisa kirim apapun trmasuk arraylist)========
                ModelFilm datalist = Listfilm.get(holder.getAdapterPosition());
                Bundle bundle = new Bundle();
                bundle.putParcelable(DialogPoster.IMAGE, datalist);
                dialogPoster.setArguments(bundle);
                //======= akses Fragmen dari adapter======
                FragmentManager fragmentManager = ((AppCompatActivity) mcontext).getSupportFragmentManager();
                dialogPoster.show(fragmentManager, DialogPoster.class.getSimpleName());
            }
        });

        holder.btndetil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPoster dialogPoster = new DialogPoster();

                //========= Via Parcelable (di isi ulang)========
                ModelFilm data = new ModelFilm();
                data.setNama("Ega Sudrajat");
                data.setDurasi("Durasi 24thn wkwk");
                data.setPoster(R.drawable.poster_venom);
                data.setTanggal("30-09-2019");
                data.setDeskripsi("Contoh Parcel");

                Bundle bundle = new Bundle();
                bundle.putParcelable(DialogPoster.PARCEL_TEST, data);
                dialogPoster.setArguments(bundle);
                //======= akses Fragmen dari adapter======
                FragmentManager fragmentManager = ((AppCompatActivity) mcontext).getSupportFragmentManager();
                dialogPoster.show(fragmentManager, DialogPoster.class.getSimpleName());
            }
        });

    }



    @Override
    public int getItemCount() {
        return Listfilm.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvnamafilm, tvdeskripsi, tvtglrilis;
        Button btndetil, btndetil2;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_listfilm);
            tvnamafilm = itemView.findViewById(R.id.tv_namafilm);
            tvdeskripsi = itemView.findViewById(R.id.tv_deskripsifilm);
            tvtglrilis = itemView.findViewById(R.id.tv_tglrilis);
            btndetil = itemView.findViewById(R.id.btn_detil);
            btndetil2 = itemView.findViewById(R.id.btn_detil2);


        }
    }

    public interface onPositionCallback {
        void positionCallback(int position);
    }
}
