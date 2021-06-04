package com.example.databaselokal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataPerpustakaanDAO {
    @Insert
    Long insertData(com.example.databaselokal.DataPerpustakaan dataPerpustakaan);

    @Query("Select * from Perpustakaan_db")
    List<com.example.databaselokal.DataPerpustakaan> getData();

    @Update
    int updateData(com.example.databaselokal.DataPerpustakaan item);

    @Delete
    void deleteData(com.example.databaselokal.DataPerpustakaan item);
}
