package com.bso.informatika.svrooms.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bso.informatika.svrooms.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

public class RumahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumah);

        getSupportActionBar().hide();

        //receive data

        String name = getIntent().getExtras().getString("home_name");
        String description = getIntent().getExtras().getString("home_description");
        String perusahaan = getIntent().getExtras().getString("home_perusahaan");
        String category = getIntent().getExtras().getString("home_category");
        String Rating = getIntent().getExtras().getString("home_rating");
        String img_url = getIntent().getExtras().getString("home_img");
        String harga = getIntent().getExtras().getString("home_harga");
        String alamat = getIntent().getExtras().getString("home_alamat");


        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarRumah);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_idRumah);
        TextView tv_perusahaan = findViewById(R.id.aa_idperusahaan);
        TextView tv_category = findViewById(R.id.aa_idcategory);
        TextView tv_rating = findViewById(R.id.aa_rating);
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_harga = findViewById(R.id.aa_harga);
        TextView tv_alamat = findViewById(R.id.aa_alamat);
        ImageView img = findViewById(R.id.aa_thubnail);

        //setting values
        tv_name.setText(name);
        tv_category.setText(category);
        tv_description.setText(description);
        tv_perusahaan.setText(perusahaan);
        tv_rating.setText(Rating);
        tv_alamat.setText(alamat);
        tv_harga.setText(harga);

        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions =  new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        //set Image with Glide
        Glide.with(this).load(img_url).apply(requestOptions).into(img);

    }
}