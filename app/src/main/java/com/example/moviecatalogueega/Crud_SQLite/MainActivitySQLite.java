package com.example.moviecatalogueega.Crud_SQLite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.moviecatalogueega.Adapter.AdapterCardViewFilm;
import com.example.moviecatalogueega.Helper.RecyclerItemClickListener;
import com.example.moviecatalogueega.MainActivity;
import com.example.moviecatalogueega.Model.ModelFilm;
import com.example.moviecatalogueega.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivitySQLite extends AppCompatActivity {
    Context mcontext;
    private RecyclerView recyclerView;
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "address";


    AlertDialog.Builder dialog;
    List<DataModel> itemList = new ArrayList<>();
    DbHelper dbHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);
        mcontext = this;
        recyclerView = findViewById(R.id.recycleviewsql);
        final FloatingActionButton floatButton = findViewById(R.id.btnfloat);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySQLite.this, ActivityAddEdit.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DataModel dataModel = itemList.get(position);
                final DbHelper dbHelper = new DbHelper(mcontext);
                final String idx = dataModel.getId();
                final String name = dataModel.getName();
                final String address = dataModel.getAddress();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(mcontext);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(mcontext, ActivityAddEdit.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_ADDRESS, address);
                                mcontext.startActivity(intent);
                                break;
                            case 1:
                                dbHelper.delete(Integer.parseInt(idx));
                                onResume();
                                break;
                        }
                    }
                }).show();

            }

            @Override
            public void onLongItemClick(View view, int position) {
        Toast.makeText(mcontext,"oke",Toast.LENGTH_SHORT).show();


            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
        showRecyclerview();
    }


    public void showRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterSQLite adapterSQLite = new AdapterSQLite(itemList);
        recyclerView.setAdapter(adapterSQLite);
    }

    private void getAllData() {
        List<DataModel> row = dbHelper.getAll();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).getId();
            String poster = row.get(i).getName();
            String title = row.get(i).getAddress();

            DataModel data = new DataModel();
            data.setId(id);
            data.setName(poster);
            data.setAddress(title);

            itemList.add(data);
        }

    }


}
