package com.example.surat;

public class ModalRiwayat {
    private String ID_SURAT;
    private String TANGGAL;
    private String ID_JENIS_SURAT;
    private String NAMA_MITRA;
    private String STATUS_SURAT;

    public String getID_SURAT() {
        return ID_SURAT;
    }

    public void setID_SURAT(String ID_SURAT) { this.ID_SURAT = ID_SURAT;
    }

    public String getTanggal() {
        return TANGGAL;
    }

    public void setTanggal(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getJenis_surat() {
        return ID_JENIS_SURAT;
    }

    public void setJenis_surat(String ID_JENIS_SURAT) {
        this.ID_JENIS_SURAT = ID_JENIS_SURAT;
    }

    public String getNama_mitra() {
        return NAMA_MITRA;
    }

    public void setNama_mitra(String NAMA_MITRA) {
        this.NAMA_MITRA = NAMA_MITRA;
    }

    public String getKeterangan() {
        return STATUS_SURAT;
    }

    public void setKeterangan(String STATUS_SURAT) {
        this.STATUS_SURAT = STATUS_SURAT;
    }
}
