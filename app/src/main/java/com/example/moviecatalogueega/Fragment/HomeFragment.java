package com.example.moviecatalogueega.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviecatalogueega.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bt_category = view.findViewById(R.id.btn_category);
        bt_category.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_category){
            Toast.makeText(v.getContext(),"coba", Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = getFragmentManager();
            if(fragmentManager !=null){
                CategoryFragment categoryFragment = new CategoryFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, categoryFragment, CategoryFragment.class.getSimpleName());
                fragmentTransaction.addToBackStack(null); //kode agar fragment sebelumnya  tetap ada ketika tombol back di klik
                fragmentTransaction.commit();
            }
        }
    }
}
