package com.example.moviecatalogueega.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.moviecatalogueega.R;

public class MainActivity_Fragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homefragment = new HomeFragment();

        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if(!(fragment instanceof HomeFragment)){
            fragmentTransaction.add(R.id.frame_container, homefragment, HomeFragment.class.getSimpleName());
            fragmentTransaction.commit();

            Button button = findViewById(R.id.btn_dialog);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    OptionDialogFragment optionDialogFragment = new OptionDialogFragment();
//
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    optionDialogFragment.show(fragmentManager, OptionDialogFragment.class.getSimpleName());
//                }
//            });
        }
    }}