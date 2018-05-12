package com.bso.informatika.svrooms.uploadActivity;

/**
 * Created by rakaiqbalsy on 4/30/18.
 */

public class upload {
    private String mName;
    private String mImageUrl;

    public upload() {
        //Konstruktor Kosong
    }

    public upload(String name, String ImageUrl) {
        if(name.trim().equals("")){
            name = "no name";
        }

        mName = name;
        mImageUrl = ImageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        mImageUrl = ImageUrl;
    }
}
