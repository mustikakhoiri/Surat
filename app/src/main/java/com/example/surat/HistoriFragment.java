package com.example.surat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surat.R;
import com.example.surat.configfile.AppController;
import com.example.surat.configfile.ServerApi;
import com.example.surat.configfile.authdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.surat.R.layout.fragment_histori;
import static java.security.AccessController.getContext;

public class HistoriFragment extends Fragment{
    RecyclerView recyclerView;
    List<ModalRiwayat> modelDataList;
    ListRiwayat listRiwayat;
    authdata authdataa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_histori, container, false);
        recyclerView = v.findViewById(R.id.rv);
        authdataa = new authdata(getContext());
        loaddetail();
        return v;
    }
    public void loaddetail()
    {
        StringRequest senddata = new StringRequest(Request.Method.GET, ServerApi.IPServer + "api/riwayat?NIM="
                +authdataa.getKodeUser(), new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject res = null;
                try {
                    JSONObject obj  = new JSONObject(response);

                    modelDataList = new ArrayList<>();
                    JSONArray data = obj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++)
                    {
                        ModalRiwayat playerModel = new ModalRiwayat();
                        JSONObject dataobj = data.getJSONObject(i);
                        playerModel.setID_SURAT(dataobj.getString("ID_SURAT"));
                        playerModel.setTanggal(dataobj.getString("TANGGAL"));
                        playerModel.setJenis_surat(dataobj.getString("ID_JENIS_SURAT"));
                        playerModel.setNama_mitra(dataobj.getString("NAMA_MITRA"));
                        playerModel.setKeterangan(dataobj.getString("STATUS_SURAT"));


                        modelDataList.add(playerModel);
                    }
                    setupListView();

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley", "errornya : " + error.getMessage());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(senddata);
    }

    private void setupListView()
    {
        listRiwayat = new ListRiwayat(getActivity().getApplicationContext(), modelDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listRiwayat);

        listRiwayat.setListener(new ListRiwayat.OnHistoryClickListener() {
            @Override
            public void onClick(int position) {
                ModalRiwayat modalRiwayat = modelDataList.get(position);
                Toast.makeText(getActivity(), modalRiwayat.getID_SURAT(), Toast.LENGTH_LONG).show();

                Intent detail = new Intent(getActivity(), HistoryDetailActivity.class);
                detail.putExtra("ID_SURAT", modalRiwayat.getID_SURAT());
                startActivity(detail);
            }
        });
    }
}