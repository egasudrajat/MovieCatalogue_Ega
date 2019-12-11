package com.example.moviecatalogueega;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogueega.Model.ModelFilm;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogPoster extends DialogFragment {
    public static final String IMAGE = "image";
    public static final String EXTRA_NAME = "extra";
    public static final String PARCEL_TEST = "extra";
    private ImageView imgposter;
    private TextView tvnmfilm, tvega, tvega1, tvega2, tvega3, tvega4;


    public DialogPoster() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_poster, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgposter = view.findViewById(R.id.imgdialog);
        tvnmfilm = view.findViewById(R.id.tv_nmfilm);
        tvega = view.findViewById(R.id.tv_ega);
        tvega1 = view.findViewById(R.id.tv_ega1);
        tvega2 = view.findViewById(R.id.tv_ega2);
        tvega3 = view.findViewById(R.id.tv_ega3);
        tvega4 = view.findViewById(R.id.tv_ega4);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //========== Cara Via Bundle ========= //
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String mybundle = bundle.getString(EXTRA_NAME);
            tvnmfilm.setText(mybundle);
        } else {
            tvnmfilm.setText("gg");
        }
        //=========== via Parcelable ========
        Bundle parcel = this.getArguments();
        ModelFilm modelFilm = parcel.getParcelable(IMAGE);

        if (modelFilm != null) {
            Glide.with(this)
                    .load(modelFilm.getPoster())
                    .apply(new RequestOptions()).override(550, 550)
                    .into(imgposter);
        }

        //=========== via Parcelable yg di isi ulang di Adapterfilm========
        Bundle parcel2 = this.getArguments();
        ModelFilm modelFilm2 = parcel2.getParcelable(PARCEL_TEST);
        if (modelFilm2 != null) {
            tvega.setText(modelFilm2.getNama());
            tvega1.setText(modelFilm2.getTanggal());
            tvega2.setText(modelFilm2.getDurasi());
            tvega3.setText(modelFilm2.getDeskripsi());
            imgposter.setImageResource(modelFilm2.getPoster());
        }
    }

}

