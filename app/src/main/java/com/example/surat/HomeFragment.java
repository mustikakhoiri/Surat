package com.example.surat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.surat.R.layout.fragment_home;

public class HomeFragment extends Fragment {
    ImageView msg;
    Button bmsg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_home, container, false);
        msg = v.findViewById(R.id.sendmessage);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), ActivityCreateMessage.class);
                startActivity(a);
            }
        });
        bmsg = v.findViewById(R.id.btnsendmessage);
        bmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getActivity(), ActivityCreateMessage.class);
                startActivity(b);
            }
        });
        return v;
    }
}
