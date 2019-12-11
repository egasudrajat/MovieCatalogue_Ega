package com.example.moviecatalogueega.Lainnya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.moviecatalogueega.R;

public class ActivityKalkulator extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton btplus,btequal, bt1,bt2,bt3;
    AppCompatEditText ethasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        btplus = findViewById(R.id.btplus);
        btequal = findViewById(R.id.btequal);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt1.setOnClickListener(this);
        btequal.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        btplus.setOnClickListener(this);
        ethasil = findViewById(R.id.et_hasil);
        ethasil.setText("");
    }

    @Override
    public void onClick(View v) {
       String getEthasil = ethasil.getText().toString();
      switch (v.getId()){
          case R.id.bt1:
              ethasil.setText(getEthasil+""+1);
              break;
          case R.id.bt2:
              ethasil.setText(getEthasil+""+2);
              break;
          case R.id.bt3:
              ethasil.setText(getEthasil+""+3);
              break;
          case R.id.btplus:
              ethasil.setText(getEthasil+""+"+");
              break;
          case R.id.btequal:

              int hasil = Integer.parseInt("11+11");
              ethasil.setText(String.valueOf(hasil));
              break;
      }
    }
}
