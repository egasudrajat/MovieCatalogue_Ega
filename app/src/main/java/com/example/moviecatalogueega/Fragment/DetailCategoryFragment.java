package com.example.moviecatalogueega.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviecatalogueega.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailCategoryFragment extends Fragment implements View.OnClickListener {
    TextView tvnamakategori, tvdeskripsikategori;
    Button btkeprofil, btshowdialog;

    public static String EXTRA_NAME= "extra name";
    public  static String EXTRA_DESCRIPTION = "extra description";
    private String description;
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDescription() { return description;}
    public void setDescription(String description) { this.description = description; }

    OptionDialogFragment.OnOptionDialogListener optionDialogListener = new OptionDialogFragment.OnOptionDialogListener() {
        @Override
        public void OnOptionChosen(String text) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            tvnamakategori.setText(text);
        }
    };

    public DetailCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvnamakategori= view.findViewById(R.id.tv_namakategori);
        tvdeskripsikategori=view.findViewById(R.id.tv_deskripsikateori);
        btkeprofil=view.findViewById(R.id.btn_profil);
        btshowdialog=view.findViewById(R.id.btn_showdialog);
        btshowdialog.setOnClickListener(this);
        btkeprofil.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String categoryname = getArguments().getString(EXTRA_NAME);
        tvnamakategori.setText(categoryname);
        tvdeskripsikategori.setText(getDescription()+" "+getNama());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_profil:
                Intent intent = new Intent(getActivity(), ProfilActivity.class);
                startActivity(intent);
                break;
            case  R.id.btn_showdialog:
                OptionDialogFragment optionDialogFragment = new OptionDialogFragment();

                FragmentManager fragmentManager = getChildFragmentManager();
                optionDialogFragment.show(fragmentManager, OptionDialogFragment.class.getSimpleName());
                break;
        }

    }
}
