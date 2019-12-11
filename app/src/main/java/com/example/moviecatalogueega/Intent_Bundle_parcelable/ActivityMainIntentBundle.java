package com.example.moviecatalogueega.Intent_Bundle_parcelable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviecatalogueega.Helper.KumpulanFunction;
import com.example.moviecatalogueega.Model.Person;
import com.example.moviecatalogueega.R;

public class ActivityMainIntentBundle extends AppCompatActivity implements View.OnClickListener {
    private int REQUEST_CODE = 10;
    private TextView tvresult;
    EditText etpanjang, etlebar, ettinggi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent_bundle);
        tvresult = findViewById(R.id.tv_result);
        Button moveaktiviti = findViewById(R.id.btn_pindah);
        Button moveaktivitidata = findViewById(R.id.btn_pindah_data);
        Button dialnumber = findViewById(R.id.btn_dial_number);
        Button movewithobject = findViewById(R.id.bt_movewithobject);
        Button movewithresult = findViewById(R.id.bt_movewithresult);
        Button dialoginterface = findViewById(R.id.bt_dialoginterface);
        Button btnhasil = findViewById(R.id.btn_hasil);
        etpanjang = findViewById(R.id.et_panjang);
        etlebar = findViewById(R.id.et_lebar);
        ettinggi = findViewById(R.id.et_tinggi);
        moveaktiviti.setOnClickListener(this);
        moveaktivitidata.setOnClickListener(this);
        dialnumber.setOnClickListener(this);
        movewithobject.setOnClickListener(this);
        movewithresult.setOnClickListener(this);
        dialoginterface.setOnClickListener(this);
        btnhasil.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pindah:
                Intent pindah = new Intent(this, ActivityMove.class);
                startActivity(pindah);
                break;
            case R.id.btn_pindah_data:
                Intent pindah_data = new Intent(this, MoveActivityWithData.class);
                pindah_data.putExtra(MoveActivityWithData.EXTRA_NAME, "Ega Sudrajat");
                pindah_data.putExtra(MoveActivityWithData.EXTRA_AGE, 24);
                startActivity(pindah_data);
                break;
            case R.id.btn_dial_number:
                String phonenumber = "089678435044";
                Intent dialintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phonenumber));
                startActivity(dialintent);
                break;
            case R.id.bt_movewithobject:
                Person person = new Person();
                person.setName("Ega Sudrajat");
                person.setAge(5);
                person.setEmail("egasudrajat@gmail.com");
                person.setCity("Majalengka");

                Intent intent = new Intent(this, MoveActivityWithObject.class);
                intent.putExtra(MoveActivityWithObject.EXTRA_PERSON, person);
                startActivity(intent);
                break;
            case R.id.bt_movewithresult:
                Intent moveResult = new Intent(this, MoveActivityWithResult.class);
                startActivityForResult(moveResult, REQUEST_CODE);
                break;
            case R.id.bt_dialoginterface:
                KumpulanFunction.PindahIntent(this, ActivityDialogInterface.class);
                break;
            case R.id.btn_hasil:
                String pjg = etpanjang.getText().toString();
                String lbr = etlebar.getText().toString();
                String tnggi = ettinggi.getText().toString();
                Boolean isEmptyFields = false;
                Boolean isInvalidDouble = false;

                if (TextUtils.isEmpty(pjg)) {
                    isEmptyFields = true;
                    etpanjang.setError("Inputan harus di isi");
                }
                if (TextUtils.isEmpty(lbr)) {
                    isEmptyFields = true;
                    etlebar.setError("Inputan harus di isi");
                }
                if (TextUtils.isEmpty(tnggi)) {
                    isEmptyFields = true;
                    ettinggi.setError("Inputan harus di isi");
                }

                Double length = toDouble(pjg);
                Double width = toDouble(lbr);
                Double height = toDouble(tnggi);

                if (length == null) {
                    isInvalidDouble = true;
                    etpanjang.setError("inputan harus angka yang benar");
                }
                if (width == null) {
                    isInvalidDouble = true;
                    etlebar.setError("inputan harus angka yang benar");
                }
                if (height == null) {
                    isInvalidDouble = true;
                    ettinggi.setError("inputan harus angka yang benar");
                }

                if (!isEmptyFields && !isInvalidDouble) {
                    double hasil = KumpulanFunction.hitungvolume(length, width, height);
                    tvresult.setText(String.valueOf(hasil));

                    break;

                }


        }
    }


    //    =========== Fungsi untuk menangkap Activity Result ======
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resultCode = MoveActivityWithResult.RESULT_CODE;
        if (requestCode == REQUEST_CODE) {
            if (resultCode == 10) {
                int selectedvalue = data.getIntExtra(MoveActivityWithResult.EXTRA_SELECTED_VALUE, 0);
                tvresult.setText("Hasil : " + selectedvalue);
            } else if (resultCode == 20) {
                tvresult.setText("Empat Ratus");
            } else if (resultCode == RESULT_CANCELED) {
                tvresult.setText("Cancel");
            }
        }

    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
