package com.example.databaselokal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselokal.R;
import com.example.databaselokal.DataPerpustakaan;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataPerpustakaan> list;
    com.example.databaselokal.view.MainContact.view aView;

    public MainAdapter(Context context, List<DataPerpustakaan> list, com.example.databaselokal.view.MainContact.view aView) {
        this.context = context;
        this.list = list;
        this.aView = aView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_perpustakaan,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.viewHolder holder, int position) {
        final DataPerpustakaan item = list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvLahir.setText(item.getLahir());
        holder.tvAngkatan.setText(item.getAngkatan());
        holder.tvHp.setText(item.getHp());
        holder.tvJurusan.setText(item.getJurusan());
        holder.id.setText(Integer.toString(item.getId()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                aView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvNama,tvLahir,tvAngkatan,tvHp,tvJurusan,id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama  =   itemView.findViewById(R.id.tv_item_nama);
            tvLahir  =   itemView.findViewById(R.id.tv_item_lahir);
            tvAngkatan  =   itemView.findViewById(R.id.tv_item_angkatan);
            tvHp  =   itemView.findViewById(R.id.tv_item_hp);
            tvJurusan  =   itemView.findViewById(R.id.tv_item_jurusan);
            id  =   itemView.findViewById(R.id.tv_item_id);
            cardView  =   itemView.findViewById(R.id.cv);
        }
    }
}
