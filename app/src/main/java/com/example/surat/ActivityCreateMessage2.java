package com.example.surat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityCreateMessage2 extends AppCompatActivity {
    Button bsimpan;
    EditText instansi, alamat, tanggal_survei, keterangan, nim_pengaju, nimanggota1, namaanggota1, nimanggota2, namaanggota2, nimanggota3, namaanggota3, nimanggota4, namaanggota4;
    TextView thasil;
    RadioGroup rgjk;
    Spinner jenis_surat, nama_dosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        // inisialisasi
        bsimpan = (Button) findViewById(R.id.simpan);
        jenis_surat = (Spinner) findViewById(R.id.jenis_surat);
        nama_dosen  = (Spinner) findViewById(R.id.nama_dosen);
        instansi = (EditText) findViewById(R.id.instansi);
        tanggal_survei = (EditText) findViewById(R.id.tanggalsurvei);
        alamat = (EditText) findViewById(R.id.isialamat);
        keterangan = (EditText) findViewById(R.id.isiketerangan);
        nim_pengaju = (EditText) findViewById(R.id.nimpengaju);
        nimanggota1 = (EditText) findViewById(R.id.nimanggota1);
        namaanggota1 = (EditText) findViewById(R.id.namaanggota1);
        nimanggota2 = (EditText) findViewById(R.id.nimanggota2);
        namaanggota2 = (EditText) findViewById(R.id.namaanggota2);
        nimanggota3 = (EditText) findViewById(R.id.nimanggota3);
        namaanggota3 = (EditText) findViewById(R.id.namaanggota3);
        nimanggota4 = (EditText) findViewById(R.id.nimanggota4);
        namaanggota4 = (EditText) findViewById(R.id.namaanggota4);
        thasil = (TextView) findViewById(R.id.hasil);

        bsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputinstansi = String.valueOf(instansi.getText().toString());
                String inputtanggalsurvei = String.valueOf(tanggal_survei.getText().toString());
                String inputalamat = String.valueOf(alamat.getText().toString());
                String inputketerangan = String.valueOf(keterangan.getText().toString());
                String inputnimpengaju = String.valueOf(nim_pengaju.getText().toString());
                String inputnimanggota1 = String.valueOf(nimanggota1.getText().toString());
                String inputnamaanggota1 = String.valueOf(namaanggota1.getText().toString());
                String inputnimanggota2 = String.valueOf(nimanggota2.getText().toString());
                String inputnamaanggota2 = String.valueOf(namaanggota2.getText().toString());
                String inputnimanggota3 = String.valueOf(nimanggota3.getText().toString());
                String inputnamaanggota3 = String.valueOf(namaanggota3.getText().toString());
                String inputnimanggota4 = String.valueOf(nimanggota4.getText().toString());
                String inputnamaanggota4 = String.valueOf(namaanggota4.getText().toString());

                thasil.setText("Jenis Surat\t\t\t\t\t\t\t: " + jenis_surat.getSelectedItem().toString() + "\n" +
                        "Nama Dosen\t\t\t\t\t\t: " + nama_dosen.getSelectedItem().toString() + "\n" +
                        "Instansi\t\t\t\t\t\t\t\t\t\t: " + inputinstansi + "\n" +
                        "Tanggal Survei\t\t\t\t: " + inputtanggalsurvei + "\n" +
                        "Alamat\t\t\t\t\t\t\t\t\t\t\t: " + inputalamat + "\n" +
                        "NIM Pengaju\t\t\t\t\t\t: " + inputnimpengaju + "\n" +
                        "NIM Anggota 1\t\t\t\t: " + inputnimanggota1 + "\n" +
                        "Nama Anggota 1\t\t\t: " + inputnamaanggota1 + "\n" +
                        "NIM Anggota 2\t\t\t\t: " + inputnimanggota2 + "\n" +
                        "Nama Anggota 2\t\t\t: " + inputnamaanggota2 + "\n" +
                        "NIM Anggota 3\t\t\t\t: " + inputnimanggota3 + "\n" +
                        "Nama Anggota 3\t\t\t: " + inputnamaanggota3 + "\n" +
                        "NIM Anggota 4\t\t\t\t: " + inputnimanggota4 + "\n" +
                        "Nama Anggota 4\t\t\t: " + inputnamaanggota4 + "\n");
            }
        });

    }
}
