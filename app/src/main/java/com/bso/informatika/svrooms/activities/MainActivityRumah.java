package com.bso.informatika.svrooms.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bso.informatika.svrooms.R;
import com.bso.informatika.svrooms.adapter.RecyclerViewAdapter;
import com.bso.informatika.svrooms.model.rumah;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRumah extends AppCompatActivity {

    private final String JSON_URL = "https://gist.githubusercontent.com/rakaiqbalsy/7915044d261588653b46bfe051fcfcf7/raw/bf80032b70c5bd4dcf0d9518774f56a16c70c98c/Pilihan.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<rumah>  firstRumah;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rumah);

        firstRumah = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewRumah);
        jsonrequest();
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i=0; i< response.length(); i++) {

                    try{
                        jsonObject = response.getJSONObject(i);
                        rumah Rumah = new rumah();
                        Rumah.setName(jsonObject.getString("name"));
                        Rumah.setDescription(jsonObject.getString("description"));
                        Rumah.setRating(jsonObject.getString("Rating"));
                        Rumah.setHarga(jsonObject.getString("harga"));
                        Rumah.setAlamat(jsonObject.getString("alamat"));
                        Rumah.setPerusahaan(jsonObject.getString("perusahaan"));
                        Rumah.setCategory(jsonObject.getString("category"));
                        Rumah.setImg_url(jsonObject.getString("img"));
                        firstRumah.add(Rumah);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                    setuprecyclerview(firstRumah);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivityRumah.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<rumah> firstRumah) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, firstRumah);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
