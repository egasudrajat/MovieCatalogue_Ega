package com.example.moviecatalogueega;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogueega.Model.ModelFilm;

public class ActivityDetilFilm extends AppCompatActivity {
    private TextView tvnamafilm, tvtglrilis, tvdurasi, tvdeskripsi;
    private ImageView imageViewdetil;

    public static final String datafilm = "datafilm";
    ModelFilm modelFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_film);


        tvnamafilm = findViewById(R.id.tv_namadetil);
        tvdurasi = findViewById(R.id.tv_durasifilm);
        tvtglrilis = findViewById(R.id.tv_detilrilis);
        tvdeskripsi = findViewById(R.id.tv_Deskripsidetil);
        imageViewdetil = findViewById(R.id.imgdetil);

        modelFilm = getIntent().getParcelableExtra(datafilm);
        tvnamafilm.setText(modelFilm.getNama());
        tvtglrilis.setText("Tanggal Rilis " + modelFilm.getTanggal());
        tvdurasi.setText("Durasi Film " + modelFilm.getDurasi());
        tvdeskripsi.setText(modelFilm.getDeskripsi());
        Glide.with(this)
                .load(modelFilm.getPoster())
                .apply(new RequestOptions().override(450, 550))
                .into(imageViewdetil);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detil "+modelFilm.getNama());
        }

    }
}
