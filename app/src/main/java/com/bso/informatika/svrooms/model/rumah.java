package com.bso.informatika.svrooms.model;

/**
 * Created by rakaiqbalsy on 5/12/18.
 */

public class rumah {
    private String name;
    private String Description;
    private String harga;
    private String category;
    private String rating;
    private String alamat;
    private String perusahaan;
    private String img_url;

    public rumah() {
    }

    public rumah(String name, String description, String harga, String rating, String alamat, String perusahaan, String img_url) {
        this.name = name;
        Description = description;
        this.harga = harga;
        this.category = category;
        this.rating = rating;
        this.alamat = alamat;
        this.perusahaan = perusahaan;
        this.img_url = img_url;
    }

//getter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategory() {return category;}

    public String getHarga() { return harga; }

    public String getRating() {
        return rating;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public String getImg_url() {
        return img_url;
    }

    //setter


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCategory(String category) { this.category = category; }

    public void setHarga(String harga) { this.harga = harga; }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
