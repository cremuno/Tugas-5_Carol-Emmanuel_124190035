package com.example.databaselokal.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.databaselokal.AppDatabase;
import com.example.databaselokal.DataPerpustakaan;

import java.util.List;

public class MainPresenter implements com.example.databaselokal.view.MainContact.presenter {
    private com.example.databaselokal.view.MainContact.view view;

    public MainPresenter(com.example.databaselokal.view.MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long> {
        private AppDatabase appDatabase;
        private com.example.databaselokal.DataPerpustakaan dataPerpustakaan;

        public InsertData(AppDatabase appDatabase, DataPerpustakaan dataPerpustakaan) {
            this.appDatabase = appDatabase;
            this.dataPerpustakaan = dataPerpustakaan;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataPerpustakaan);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }
    @Override
    public void insertData(String nama, String lahir, String Angkatan, String hp, String Jurusan, AppDatabase database) {
        final com.example.databaselokal.DataPerpustakaan dataPerpustakaan = new DataPerpustakaan();
        dataPerpustakaan.setNama(nama);
        dataPerpustakaan.setLahir(lahir);
        dataPerpustakaan.setAngkatan(Angkatan);
        dataPerpustakaan.setHp(hp);
        dataPerpustakaan.setJurusan(Jurusan);
        new InsertData(database,dataPerpustakaan).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<com.example.databaselokal.DataPerpustakaan> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void,Void,Integer>{
        private AppDatabase appDatabase;
        private DataPerpustakaan dataPerpustakaan;

        public EditData(AppDatabase appDatabase, DataPerpustakaan dataPerpustakaan) {
            this.appDatabase = appDatabase;
            this.dataPerpustakaan = dataPerpustakaan;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataPerpustakaan);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute : "+integer);
            view.successAdd();
        }
    }
    @Override
    public void editData(String nama, String lahir, String Angkatan, String hp, String Jurusan, int id, AppDatabase database) {
        final com.example.databaselokal.DataPerpustakaan dataPerpustakaan = new DataPerpustakaan();
        dataPerpustakaan.setNama(nama);
        dataPerpustakaan.setLahir(lahir);
        dataPerpustakaan.setAngkatan(Angkatan);
        dataPerpustakaan.setHp(hp);
        dataPerpustakaan.setJurusan(Jurusan);
        dataPerpustakaan.setId(id);
        new EditData(database,dataPerpustakaan).execute();
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataPerpustakaan dataPerpustakaan;

        public DeleteData(AppDatabase appDatabase, DataPerpustakaan dataPerpustakaan) {
            this.appDatabase = appDatabase;
            this.dataPerpustakaan = dataPerpustakaan;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataPerpustakaan);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }
    @Override
    public void deleteData(com.example.databaselokal.DataPerpustakaan dataPerpustakaan, AppDatabase database) {
        new DeleteData(database, dataPerpustakaan).execute();
    }
}