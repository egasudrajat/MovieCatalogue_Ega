package com.example.moviecatalogueega.Intent_Bundle_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.moviecatalogueega.R;

import org.w3c.dom.Text;

public class ActivityMove extends AppCompatActivity {
   private String text = "null";

    public ActivityMove(String text) {
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
         TextView txview = findViewById(R.id.textView);


    }
}
