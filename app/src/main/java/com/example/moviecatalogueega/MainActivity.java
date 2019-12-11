package com.example.moviecatalogueega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.moviecatalogueega.Adapter.AdapterCardViewFilm;
import com.example.moviecatalogueega.Adapter.AdapterGridFilm;
import com.example.moviecatalogueega.Adapter.AdapterListViewFilm;
import com.example.moviecatalogueega.Fragment.MainActivity_Fragment;
import com.example.moviecatalogueega.Helper.DrawableCounter;
import com.example.moviecatalogueega.Intent_Bundle_parcelable.ActivityMainIntentBundle;
import com.example.moviecatalogueega.Lainnya.PreferenceLastPosition;
import com.example.moviecatalogueega.Model.ModelFilm;
import com.example.moviecatalogueega.ViewAndViews.ViewAndViews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private PreferenceLastPosition prefs;
    AdapterCardViewFilm adapterCardViewFilm;
    private RecyclerView recyclerViewFilm;

    private ArrayList<ModelFilm> List = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewFilm = findViewById(R.id.Rv_mainactivity);
        recyclerViewFilm.setHasFixedSize(true);
        prefs = new PreferenceLastPosition(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Daftar Film");

            LoadDataAsync loadData = new LoadDataAsync();
            loadData.execute();
            showRecyclerview();

        }
    }

    @Override
    public void onBackPressed() {
        autoScroll();  //auto scroll

    }


    public void autoScroll() {
        final int speedScroll = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {

                while (count < adapterCardViewFilm.getItemCount()){
                    recyclerViewFilm.smoothScrollToPosition(count++);
                    handler.postDelayed(this, speedScroll);
                }
//                if (count < adapterCardViewFilm.getItemCount()) {
//                    recyclerViewFilm.smoothScrollToPosition(count++);
//                    handler.postDelayed(this, speedScroll);
//                }
            }
        };
        handler.postDelayed(runnable, speedScroll);
    }

    private void showRecyclerview() {
        // Speed SmothScroll Controll
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                try {
                    LinearSmoothScroller smoothScroller = new LinearSmoothScroller(Objects.requireNonNull(MainActivity.this)) {
                        private static final float SPEED = 1000f;// Change this value (default=25f)

                        @Override
                        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                            return SPEED / displayMetrics.densityDpi;
                        }
                    };
                    smoothScroller.setTargetPosition(position);
                    startSmoothScroll(smoothScroller);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        recyclerViewFilm.setLayoutManager(manager);
        adapterCardViewFilm = new AdapterCardViewFilm(List);
        recyclerViewFilm.setAdapter(adapterCardViewFilm);



        //==== manggil fragmen dari aktivity
//        DialogPoster dialogPoster = new DialogPoster();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        dialogPoster.show(fragmentManager, DialogPoster.class.getSimpleName());
    }

    private void showRecycleViewGrid() {
        recyclerViewFilm.setLayoutManager(new GridLayoutManager(this, 2));
        AdapterGridFilm adapterGridFilm = new AdapterGridFilm(List);
        recyclerViewFilm.setAdapter(adapterGridFilm);
    }

    private void showRecycleviewList() {
        recyclerViewFilm.setLayoutManager(new LinearLayoutManager(this));
        AdapterListViewFilm adapterListViewFilm = new AdapterListViewFilm(List);
        recyclerViewFilm.setAdapter(adapterListViewFilm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //======= inisiasi menu yg ingin di ubah (visible true false =======
        MenuItem refres = menu.findItem(R.id.refresh);
        MenuItem menucart = menu.findItem(R.id.cart);
        menucart.setVisible(true);
        refres.setVisible(false);
        //=========== penampil  badge count ============
        LayerDrawable icon = (LayerDrawable) menucart.getIcon();
        DrawableCounter badge;
        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_group_count);
        if (reuse != null && reuse instanceof DrawableCounter) {
            badge = (DrawableCounter) reuse;
        } else {
            badge = new DrawableCounter(this);
        }
        int getcount = List.size();
        badge.setCount(String.valueOf(getcount));
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_group_count, badge);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.List_cardview:
                showRecyclerview();
                break;
            case R.id.List_ListView:
                showRecycleviewList();
                break;
            case R.id.List_GridView:
                showRecycleViewGrid();
                break;
            case R.id.cart:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getData() {
        //koneksi ke file read_all.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.get("http://192.168.43.30/api_siswa/read.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: " + response);
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject data = response.getJSONObject(i);

                                List.add(new ModelFilm(
                                        data.getString("id_siswa"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("nama"),
                                        data.getString("kelas"),
                                        data.getString("alamat")
                                ));
                            }

                            showRecyclerview();  // adapter

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "onError: " + error); //untuk log pada onerror
                        // handle error
                    }
                });
    }

    private class LoadDataAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Tunggu");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List.addAll(DataFilm.GetListData());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }
}
