package com.example.moviecatalogueega.ViewPagerTablayout;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogueega.Adapter.AdapterGridFilm;
import com.example.moviecatalogueega.Crud_FAN.ModelCrud;
import com.example.moviecatalogueega.DataFilm;
import com.example.moviecatalogueega.Model.ModelFilm;
import com.example.moviecatalogueega.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3_Fragment extends Fragment {
    RecyclerView recyclerView;
    private ArrayList<ModelFilm> List = new ArrayList<>();


    public Tab3_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab3_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.RecycleGrid);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        List.clear();
        asyncdata asyncdata = new asyncdata();
        asyncdata.execute();
        showGrid();
    }

    private void showGrid() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AdapterGridFilm adapterGridFilm = new AdapterGridFilm(List);
        recyclerView.setAdapter(adapterGridFilm);
    }

    private class asyncdata extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            List.addAll(DataFilm.GetListData());
            return null;
        }
    }
}
