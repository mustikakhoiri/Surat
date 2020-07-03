package com.example.surat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surat.configfile.ServerApi;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HistoryDetailActivity extends AppCompatActivity {
    TextView textId, textDosen, textJenis, textMitra, textAnggota;
    String ID_SURAT;

    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        progressDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);

        textId = findViewById(R.id.text_id_status);
        textDosen = findViewById(R.id.text_dosen);
        textJenis = findViewById(R.id.text_jenis_surat);
        textMitra = findViewById(R.id.text_nama_mitra);
        textAnggota = findViewById(R.id.text_anggota);

        Intent intent = getIntent();
        ID_SURAT = intent.getStringExtra("ID_SURAT");

        Toast.makeText(this, ID_SURAT, Toast.LENGTH_LONG).show();

        loadDetail();
    }

    private void loadDetail(){
        progressDialog.show();

        String url = ServerApi.URL_DETAIL_RIWAYAT;
        StringRequest detailRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject hasilRequest = new JSONObject(response);
                    String status = hasilRequest.getString("status");

                    if (status.equals("true")){
//                        mengambil array data pada json
                        JSONArray data = hasilRequest.getJSONArray("data");

//                        mengambil objek indeks pertama sebagai data utama
//                        indeks 0
                        JSONObject utama = data.getJSONObject(0);
                        String id_surat = utama.getString("ID_SURAT");
                        String dosen = utama.getString("NAMA_DOSEN");
                        String jenis = utama.getString("JENIS_SURAT");
                        String mitra = utama.getString("NAMA_MITRA");
                        String anggota = utama.getString("ANGGOTA_MHS");

//                        mengisi variabel nama mahasiswa dari semua objek yang terdeteksi
//                        indeks 1 keatas
                        for (int i = 1; i < data.length(); i++){
                            JSONObject objekAnggota = data.getJSONObject(i);

//                            penambahan anggota sesua jumlah anggota tiap surat
                            anggota += ","+ objekAnggota.getString("ANGGOTA_MHS") + " (" + objekAnggota.getString("NIM_ANGGOTA") + ")";
                        }

                        textId.setText(id_surat);
                        textDosen.setText(dosen);
                        textJenis.setText(jenis);
                        textMitra.setText(mitra);
                        textAnggota.setText(anggota);
                    } else {
                        Toast.makeText(HistoryDetailActivity.this, "gagal!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(HistoryDetailActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(HistoryDetailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_surat", ID_SURAT);

                return params;
            }
        };

        requestQueue.add(detailRequest);
    }
}
