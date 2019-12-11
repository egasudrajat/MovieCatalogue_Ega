package com.example.moviecatalogueega.Intent_Bundle_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.moviecatalogueega.R;

public class MoveActivityWithData extends AppCompatActivity {
    public  static  final String EXTRA_AGE = "extra_age";
    public  static  final String EXTRA_NAME = "extra_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_data);
        TextView txview = findViewById(R.id.tx_receivedata);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        Integer age = getIntent().getIntExtra(EXTRA_AGE,0);

        String text = "nama " +name + "umur "+ age;
        txview.setText(text);

    }
}
