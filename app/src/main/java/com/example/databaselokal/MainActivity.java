package com.example.databaselokal.view;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselokal.R;
import com.example.databaselokal.AppDatabase;
import com.example.databaselokal.DataPerpustakaan;

import java.util.List;

public class MainActivity extends AppCompatActivity implements com.example.databaselokal.view.MainContact.view{
    private AppDatabase appDatabase;
    private com.example.databaselokal.view.MainPresenter mainPresenter;
    private com.example.databaselokal.view.MainAdapter mainAdapter;

    private Button btnSubmit;
    private RecyclerView recyclerView;
    private EditText etNama, etLahir, etAngkatan, etHp, etJurusan;

    private int id = 0;
    boolean edit = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        etNama = findViewById(R.id.data_nama);
        etLahir = findViewById(R.id.data_lahir);
        etAngkatan = findViewById(R.id.data_angkatan);
        etHp = findViewById(R.id.data_hp);
        etJurusan = findViewById(R.id.data_email);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new com.example.databaselokal.view.MainPresenter(this);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil Menambahkan Data", Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil Menghapus Data", Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etNama.setText("");
        etLahir.setText("");
        etAngkatan.setText("");
        etHp.setText("");
        etJurusan.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void getData(List<DataPerpustakaan> list) {
        mainAdapter = new com.example.databaselokal.view.MainAdapter(this,list, this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataPerpustakaan item) {
        etNama.setText(item.getNama());
        etLahir.setText(item.getLahir());
        etAngkatan.setText(item.getAngkatan());
        etHp.setText(item.getHp());
        etJurusan.setText(item.getJurusan());
        id = item.getId();
        edit = true;
        btnSubmit.setText("Edit Data");
    }

    @Override
    public void deleteData(DataPerpustakaan item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Apakah Anda Yakin Ingin Menghapus Data Ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view==btnSubmit){
            if(etNama.getText().toString().equals("")||etLahir.getText().toString().equals("")||etAngkatan.getText().toString().equals("")||etHp.getText().toString().equals("")||etJurusan.getText().toString().equals("")){
                Toast.makeText(this, "Harap Isi Semua Data!", Toast.LENGTH_SHORT).show();
            }else{
                if(!edit){
                    mainPresenter.insertData(etNama.getText().toString(),etLahir.getText().toString(),etAngkatan.getText().toString(),etHp.getText().toString(),etJurusan.getText().toString(),appDatabase);
                }else{
                    mainPresenter.editData(etNama.getText().toString(),etLahir.getText().toString(),etAngkatan.getText().toString(),etHp.getText().toString(),etJurusan.getText().toString(),id,appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}
