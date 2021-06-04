package com.example.databaselokal.view;

import android.view.View;

import com.example.databaselokal.AppDatabase;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<com.example.databaselokal.DataPerpustakaan> list);
        void editData(com.example.databaselokal.DataPerpustakaan item);
        void deleteData(com.example.databaselokal.DataPerpustakaan item);
    }

    interface presenter{
        void insertData(String nama, String lahir, String angkatan, String hp, String jurusan, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String nama, String lahir, String angkatan, String hp, String jurusan, int id, AppDatabase database);
        void deleteData(com.example.databaselokal.DataPerpustakaan dataPerpustakaan, AppDatabase database);
    }
}
