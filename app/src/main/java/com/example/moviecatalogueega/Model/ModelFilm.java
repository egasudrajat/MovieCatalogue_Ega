package com.example.moviecatalogueega.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelFilm implements Parcelable {
    private String nama, deskripsi, tanggal, durasi;
    private int poster;

    public ModelFilm(String nama, String deskripsi, String tanggal, String durasi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.durasi = durasi;

    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public ModelFilm() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.deskripsi);
        dest.writeString(this.tanggal);
        dest.writeString(this.durasi);
        dest.writeInt(this.poster);
    }

    protected ModelFilm(Parcel in) {
        this.nama = in.readString();
        this.deskripsi = in.readString();
        this.tanggal = in.readString();
        this.durasi = in.readString();
        this.poster = in.readInt();
    }

    public static final Creator<ModelFilm> CREATOR = new Creator<ModelFilm>() {
        @Override
        public ModelFilm createFromParcel(Parcel source) {
            return new ModelFilm(source);
        }

        @Override
        public ModelFilm[] newArray(int size) {
            return new ModelFilm[size];
        }
    };
}
