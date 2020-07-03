package com.example.surat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.surat.R;
import com.example.surat.configfile.authdata;

import static com.example.surat.R.layout.fragment_account;

public class AccountFragment extends Fragment {
    TextView txtnama, txtprodi, txthp, txtnim, txtLogout;
    ImageView edit;
    authdata authdataa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_account, container, false);

        authdataa = new authdata(getContext());

        txtnama = v.findViewById(R.id.tv_nama);
        txtnama.setText(authdataa.getNamaUser());
        txtnim = v.findViewById(R.id.tv_nim);
        txtnim.setText(authdataa.getKodeUser());
        txtprodi = v.findViewById(R.id.tv_prodi);
        txtprodi.setText(authdataa.getNamaProdi());
        txthp = v.findViewById(R.id.tv_nohp);
        txthp.setText(authdataa.getHP());
        txtLogout = v.findViewById(R.id.logout);
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authdataa.logout();
            }
        });
        edit = v.findViewById(R.id.imgedit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), ActivityEditPassword.class);
                startActivity(a);
            }
        });
        return v;


    }

}