package com.example.moviecatalogueega.Crud_FAN;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.moviecatalogueega.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityLihatData extends AppCompatActivity {
    private static final String TAG = "ReadAllActivity";
    private String Base_url = "http://192.168.43.30/jmsport-api/api/pesanan/all_lapang";
    private String MOVIE_URL= "https://api.themoviedb.org/3/discover/movie?api_key=dea18540a759525c735f78de8820a2d7&language=id";
    private List<ModelCrud> dataMahasiswa;
    private RecyclerView recyclerView;
    private ModelResponse modelResponse;
    private DataHistory dataHistory;
    private List<DataHistory> listHistory = new ArrayList<>();
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_lihatdata);
        recyclerView = findViewById(R.id.recyclerReadAllData); //findId recyclerView yg ada pada activity_read_all.xml
        recyclerView.setHasFixedSize(true);
        //menset layout manager sebagai LinearLayout(scroll kebawah)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mcontext = this;

        dataMahasiswa = new ArrayList<>(); //arraylist untuk menyimpan data mahasiswa
        AndroidNetworking.initialize(this); //inisialisasi FAN
        getData();

    }

    // Hit APi yg di mulai Json object

    public void getData() {
        //koneksi ke file read_all.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.get(MOVIE_URL)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        final ArrayList<Bundle> List = new ArrayList<>();
                        try {
                            Bundle modelFilm = new Bundle();

                            JSONArray results = response.getJSONArray("results");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                        Gson gson = new Gson();
//                        modelResponse = gson.fromJson(response.toString(), ModelResponse.class);
//                        listHistory.addAll(modelResponse.getData());
//
//                        HistoryAdapter adapter = new HistoryAdapter(listHistory);
//                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "onResponse: " + anError);
                    }
                });
    }

    // Hit APi yg di mulai Json array

//    public void getData() {
//        //koneksi ke file read_all.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
//        AndroidNetworking.get(Base_url)
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
//                        // do anything with response
//                        {
//                            //mengambil data dari JSON array pada read_all.php
//                            try {
//                                for (int i = 0; i < response.length(); i++) {
//                                    JSONObject data = response.getJSONObject(i);
//                                    //adding the product to product list
//                                    dataMahasiswa.add(new ModelCrud(
//                                            data.getString("id"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
//                                            data.getString("nama"),
//                                            data.getString("juara"),
//                                            data.getString("sekolah"),
//                                            data.getString("jenis")
//                                    ));
//                                }
//                                //men inisialisasi adapter RecyclerView yang sudah kita buat sebelumnya
//                                //menset adapter yang akan digunakan pada recyclerView
//                                ListSiswaAdapter adapter = new ListSiswaAdapter(dataMahasiswa);
//                                recyclerView.setAdapter(adapter);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        Log.d(TAG, "onError: " + error); //untuk log pada onerror
//                        // handle error
//                    }
//                });
//    }


}