package com.example.moviecatalogueega.Lainnya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.moviecatalogueega.Helper.DatePickerView;
import com.example.moviecatalogueega.Helper.SelisihWaktu;
import com.example.moviecatalogueega.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityKalenderWaktu extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btkalender, bttime, jam1, tgl1;
    private TextInputEditText ethasil, hsil2, hsil3, hsil4, hsil5;
    DatePickerView datePickerView;
    private String waktu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender_waktu);
        btkalender = findViewById(R.id.bt_calender);
        bttime = findViewById(R.id.bt_time);
        jam1 = findViewById(R.id.jam1);

        tgl1 = findViewById(R.id.tgl1);

        ethasil = findViewById(R.id.ethasil);
        hsil2 = findViewById(R.id.ethasil2);
        hsil3 = findViewById(R.id.ethasil3);
        hsil4 = findViewById(R.id.ethasil4);
        hsil5 = findViewById(R.id.ethasil5);
        datePickerView = findViewById(R.id.bt_datepick);
        btkalender.setOnClickListener(this);
        bttime.setOnClickListener(this);
        jam1.setOnClickListener(this);
        tgl1.setOnClickListener(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerView.setVisibility(View.GONE);
        } else {
            btkalender.setVisibility(View.GONE);
            ethasil.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.bt_calender:
                showDialogDatepicker(ethasil);
                break;
            case R.id.bt_time:
                showTimeDialog(ethasil);
                break;
            case R.id.jam1:
                showTimeDialog(hsil2);
                break;
            case R.id.tgl1:
                showDialogDatepicker(hsil4);
                break;

        }

    }

    private void showDialogDatepicker(final TextInputEditText et) {
        final Calendar tampungWaktu = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Minimum API mesti level 24 Nougat
            DatePickerDialog datePickerDialog = new DatePickerDialog(this);
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {

                String Cek = et.getText().toString();

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tampungWaktu.set(year, month, dayOfMonth);
                    String hsilwaktu = dateFormat.format(tampungWaktu.getTime());
                    String isiEt = et.getText().toString();
                    if (Cek.isEmpty()) {
                        et.setText(hsilwaktu);
                    } else {
                        if (Cek.length() > 12) {
                            et.setText(hsilwaktu);
                            hsil5.setText("");
                        } else {
                            et.setText(isiEt + " - " + hsilwaktu);
                            long hsl = SelisihWaktu.tanggal(isiEt, hsilwaktu);
                            if (hsl <= 0) {
                                hsil5.setText("Masukan Tgl yang benar");
                            } else {
                                hsil5.setText(String.valueOf(hsl));
                            }
                        }
                    }

                }
            });
            datePickerDialog.show();
        }
    }

    private void showTimeDialog(final TextInputEditText et) {
        TimePickerDialog timePickerDialog;
        final Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            String jam = "";
            String Cek = et.getText().toString();

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                jam = hourOfDay + ":" + minute + ":00";
                if (Cek.isEmpty()) {
                    et.setText(jam);
                } else {
                    if (Cek.length() > 8) {
                        et.setText(jam);
                        hsil3.setText("");
                    } else {
                        et.setText(et.getText().toString() + " - " + jam);
                        long hsl = SelisihWaktu.jam(et.getText().toString(), jam);
                        if (hsl <= 0) {
                            hsil3.setText("Masukan jam yang benar");
                        } else {
                            hsil3.setText(String.valueOf(hsl));
                        }
                    }

                }
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

}
