package com.example.moviecatalogueega.Crud_FAN;

public class ModelCrud {
    String idsiswa;
    String nama, kelas, alamat, image;



    public ModelCrud(String idsiswa, String nama, String kelas, String alamat, String image) {
        this.idsiswa = idsiswa;
        this.nama = nama;
        this.kelas = kelas;
        this.alamat = alamat;
        this.image = image;
    }

    public String getIdsiswa() {
        return idsiswa;
    }

    public void setIdsiswa(String idsiswa) {
        this.idsiswa = idsiswa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
