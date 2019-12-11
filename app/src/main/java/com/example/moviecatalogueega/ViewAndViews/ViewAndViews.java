package com.example.moviecatalogueega.ViewAndViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.moviecatalogueega.R;

public class ViewAndViews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_views);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Google Pixel");
        }
    }
}
