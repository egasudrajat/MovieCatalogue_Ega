package com.example.moviecatalogueega.ViewPagerTablayout;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogueega.Adapter.AdapterCardViewFilm;
import com.example.moviecatalogueega.DataFilm;
import com.example.moviecatalogueega.Model.ModelFilm;
import com.example.moviecatalogueega.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ModelFilm> List = new ArrayList<>();

    public Tab1_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.RecycleCard);
        recyclerView.setHasFixedSize(true);
    }

    private void showrecyclerview(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterCardViewFilm adapterCardViewFilm = new AdapterCardViewFilm(List);
        recyclerView.setAdapter(adapterCardViewFilm);
    }

    @Override
    public void onResume() {
        super.onResume();
        List.clear();
        asyncData asin = new asyncData();
        asin.execute();
        showrecyclerview();
    }

    private class asyncData extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            List.addAll(DataFilm.GetListData());
            return null;
        }
    }
}
