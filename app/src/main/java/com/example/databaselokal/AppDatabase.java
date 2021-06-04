package com.example.databaselokal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.databaselokal.DataPerpustakaan.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataPerpustakaanDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if(appDatabase==null)
            appDatabase= Room.databaseBuilder(context,AppDatabase.class, "dbPerpustakaan").allowMainThreadQueries().build();
        return appDatabase;
    }
}
