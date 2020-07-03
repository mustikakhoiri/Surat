package com.example.surat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surat.configfile.ServerApi;
import com.example.surat.configfile.authdata;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText edtNIM, edtPassword;
    Button btnlogin;
    ProgressBar PrgsBar;
    ProgressDialog progressDialog;
    authdata authdataa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        authdataa = new authdata(this);
        edtNIM = findViewById(R.id.EditTextNIM);
        edtPassword = findViewById(R.id.EditTextPassword);
        btnlogin = findViewById(R.id.ButtonLogin);
        progressDialog = new ProgressDialog(this);
        PrgsBar = new ProgressBar(Login.this);
        PrgsBar.setVisibility(View.GONE);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (edtNIM.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }else if (edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }else {
                    aksilogin();
                }
            }
        });
        authdataa = new authdata(this);
        if (authdataa.isLogin() == true){
            Intent main = new Intent(Login.this, MainActivity.class);
            startActivity(main);
            finish();
        }
    }

    public void aksilogin(){
        StringRequest senddata = new StringRequest(Request.Method.POST, ServerApi.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject res = new JSONObject(response);

                    JSONObject respon = res.getJSONObject("data");
                    Toast.makeText(Login.this, respon.getString("pesan"), Toast.LENGTH_SHORT).show();
                    JSONObject datalogin = res.getJSONObject("data");
                    Log.e("ser", datalogin.getString("token"));
                    authdataa.setdatauser(
                            datalogin.getString("status"),
                            datalogin.getString("NIM"),
                            datalogin.getString("NAMA_MHS"),
                            datalogin.getString("token"),
                            datalogin.getString("PRODI"),
                            datalogin.getString("HP")
                    );
                    if (datalogin.getString("status").equals("0")) {
                        Log.e("ser", "sep gan");
                        Intent a = new Intent(Login.this, MainActivity.class);
                        startActivity(a);
                    } else {
                        Toast.makeText(Login.this, "Aplikasi Hanya Untuk Reseller" , Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    progressDialog.dismiss();
                    Log.e("errorgan", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("errornyaa ", "" + error);
                Toast.makeText(Login.this, "Gagal Login, " + error, Toast.LENGTH_SHORT).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("NIM", edtNIM.getText().toString());
                params.put("PASSWORD_MHS", edtPassword.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);

        requestQueue.add(senddata);
    }
}

