package com.example.moviecatalogueega;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.moviecatalogueega.Crud_FAN.CrudMainActivity;
import com.example.moviecatalogueega.Crud_SQLite.ActivityAddEdit;
import com.example.moviecatalogueega.Crud_SQLite.MainActivitySQLite;
import com.example.moviecatalogueega.Fragment.MainActivity_Fragment;
import com.example.moviecatalogueega.Helper.KumpulanFunction;
import com.example.moviecatalogueega.Intent_Bundle_parcelable.ActivityMainIntentBundle;
import com.example.moviecatalogueega.Lainnya.ActivityKalenderWaktu;
import com.example.moviecatalogueega.Lainnya.ActivityKalkulator;
import com.example.moviecatalogueega.Notification.MainActivityNotification;
import com.example.moviecatalogueega.ViewAndViews.ViewAndViews;
import com.example.moviecatalogueega.ViewPagerTablayout.MainActivityViewPager;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {
    private LottieAnimationView kesatu, kedua, act_fragment, act_viewandviews,
            act_intent, act_crud, act_notif, actsql, viewpager, kalender, kalkulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        kesatu = findViewById(R.id.satu);
        kedua = findViewById(R.id.dua);
        viewpager = findViewById(R.id.viewpagertablayout);
        act_fragment = findViewById(R.id.Act_fragmen);
        act_viewandviews = findViewById(R.id.Act_viewandviews);
        act_intent = findViewById(R.id.Act_intent);
        act_crud = findViewById(R.id.Act_crud);
        kalender = findViewById(R.id.kalender);
        act_notif = findViewById(R.id.act_notification);
        actsql = findViewById(R.id.act_sql);
        kalkulator = findViewById(R.id.kalkulator);
        actsql.setOnClickListener(this);
        kalkulator.setOnClickListener(this);
        act_notif.setOnClickListener(this);
        act_crud.setOnClickListener(this);
        act_intent.setOnClickListener(this);
        act_viewandviews.setOnClickListener(this);
        act_fragment.setOnClickListener(this);
        kesatu.setOnClickListener(this);
        kedua.setOnClickListener(this);
        viewpager.setOnClickListener(this);
        kalender.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        showdialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.satu:
                KumpulanFunction.PindahIntent(this, MainActivity.class);
                break;
            case R.id.dua:
                KumpulanFunction.PindahIntent(this, ActivityUpload.class);
                break;
            case R.id.Act_fragmen:
                KumpulanFunction.PindahIntent(this, MainActivity_Fragment.class);
                break;
            case R.id.Act_viewandviews:
                KumpulanFunction.PindahIntent(this, ViewAndViews.class);
                break;
            case R.id.Act_intent:
                KumpulanFunction.PindahIntent(this, ActivityMainIntentBundle.class);
                break;
            case R.id.Act_crud:
                KumpulanFunction.PindahIntent(this, CrudMainActivity.class);
                break;
            case R.id.act_notification:
                KumpulanFunction.PindahIntent(this, MainActivityNotification.class);
                break;
            case R.id.act_sql:
                KumpulanFunction.PindahIntent(this, MainActivitySQLite.class);
                break;
            case R.id.viewpagertablayout:
                KumpulanFunction.PindahIntent(this, MainActivityViewPager.class);
                break;
            case R.id.kalender:
                KumpulanFunction.PindahIntent(this, ActivityKalenderWaktu.class);
                break;
            case R.id.kalkulator:
                KumpulanFunction.PindahIntent(this, ActivityKalkulator.class);
                break;
        }
    }

    private void showdialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Apakah ingin ke toilet dulu?")
                .setCancelable(true)
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("klik Ya untuk keluar aplikasi")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityHome.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
