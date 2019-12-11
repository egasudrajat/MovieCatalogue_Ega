package com.example.moviecatalogueega.Crud_SQLite;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviecatalogueega.R;
import java.util.List;

public class AdapterSQLite extends RecyclerView.Adapter<AdapterSQLite.MyViewHolder> {
    private List<DataModel> listItem;


    public AdapterSQLite(List<DataModel> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_listview_film, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final DataModel dataModel = listItem.get(position);
        holder.tv_nama.setText(dataModel.getName());
        holder.tv_address.setText(dataModel.getAddress());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama, tv_address;
        RelativeLayout relativeLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_itemname);
            tv_address = itemView.findViewById(R.id.tv_item_detail);
            relativeLayout = itemView.findViewById(R.id.relatiflayout);
        }
    }
}
