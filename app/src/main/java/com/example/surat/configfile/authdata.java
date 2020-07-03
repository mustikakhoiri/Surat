package com.example.surat.configfile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.surat.Login;
import com.example.surat.MainActivity;

public class authdata {
 //   private static authdata mInstance;
    SharedPreferences sharedPreferences;
    public Context mCtx;

    public static final String SHARED_PREF_NAME = "surat";
    private static final String sudahlogin = "n";
    public SharedPreferences.Editor editor;

    private static final String nama_mhs = "NAMA_MHS";
    private static final String nim_mhs = "NIM";
    private static final String prodi = "PRODI";
    private static final String hp = "HP";
    private static final String akses_data = "akses_data";
    private static final String status_user = "status";
    private static final String token = "token";
    public static final String LOGIN_STATUS = "LOGIN_STATUS";



    public authdata(Context context){
        this.mCtx = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setdatauser(String xstatus, String xkode_user, String xnama_user, String tokennya, String xprodi, String xhp){


        editor.putString(nama_mhs, xnama_user);
        editor.putString(nim_mhs, xkode_user);
        editor.putString(prodi, xprodi);
        editor.putString(hp, xhp);
        editor.putString(status_user, xstatus);
        editor.putString(sudahlogin, "y");
        editor.putString(token, tokennya);
        editor.apply();
    }




    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN_STATUS, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();

        Intent login = new Intent(mCtx, Login.class);
        mCtx.startActivity(login);
        ((MainActivity)mCtx).finish();
    }

    public String getToken() {
        return sharedPreferences.getString(token, null);
    }
    public String getAksesData() {
        return sharedPreferences.getString(akses_data, null);
    }

    public String getKodeUser() {
        return sharedPreferences.getString(nim_mhs, null);
    }
    public String getNamaUser() {
        return sharedPreferences.getString(nama_mhs, null);
    }
    public String getNamaProdi() {
        return sharedPreferences.getString(prodi, null);
    }
    public String getHP() {
        return sharedPreferences.getString(hp, null);
    }
}
