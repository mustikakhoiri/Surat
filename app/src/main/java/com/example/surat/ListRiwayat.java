package com.example.surat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surat.configfile.Util;

import java.util.List;

public class ListRiwayat extends RecyclerView.Adapter<ListRiwayat.HolderDataRiwayat> {
    private List<ModalRiwayat> mItems;
    private Context context;
    private OnHistoryClickListener listener;

    public interface OnHistoryClickListener{
        public void onClick(int position);
    }

    public void setListener(OnHistoryClickListener listener) {
        this.listener = listener;
    }

    public ListRiwayat(Context context, List<ModalRiwayat> mItems)
    {
        this.context = context;
        this.mItems = mItems;
    }
    @NonNull
    @Override
    public HolderDataRiwayat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        HolderDataRiwayat holderDataRiwayat = new ListRiwayat.HolderDataRiwayat(layout, listener);
        return holderDataRiwayat;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderDataRiwayat holder, int position) {
        ModalRiwayat me = mItems.get(position);
        holder.TANGGAL.setText(Util.settanggal(me.getTanggal()));
        holder.ID_JENIS_SURAT.setText(me.getJenis_surat());
        holder.NAMA_MITRA.setText(me.getNama_mitra());
        holder.STATUS_SURAT.setText(me.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class HolderDataRiwayat extends RecyclerView.ViewHolder {
        TextView ID_JENIS_SURAT,TANGGAL, NAMA_MITRA, STATUS_SURAT;
        public HolderDataRiwayat(@NonNull View itemView, final OnHistoryClickListener listener) {
            super(itemView);
            TANGGAL = itemView.findViewById(R.id.tanggale);
            ID_JENIS_SURAT = itemView.findViewById(R.id.idjenissurat);
            NAMA_MITRA = itemView.findViewById(R.id.mitra);
            STATUS_SURAT = itemView.findViewById(R.id.keterangane);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onClick(position);
                        }
                    }
                }
            });
        }
    }
}