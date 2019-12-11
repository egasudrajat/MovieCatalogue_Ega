package com.example.moviecatalogueega.Intent_Bundle_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.moviecatalogueega.Model.Person;
import com.example.moviecatalogueega.R;

public class MoveActivityWithObject extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);
        TextView tvname = findViewById(R.id.textView2);
        TextView tvage = findViewById(R.id.textView3);
        TextView tvemail= findViewById(R.id.textView4);
        TextView tvcity = findViewById(R.id.textView5);

        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
        tvage.setText(String.valueOf(person.getAge()));
        tvcity.setText(person.getCity());
        tvemail.setText(person.getEmail());
        tvname.setText(person.getName());
    }
}
