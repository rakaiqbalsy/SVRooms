package com.bso.informatika.svrooms.Dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bso.informatika.svrooms.Detail.DetailActivity;
import com.bso.informatika.svrooms.R;

public class AdminActivity extends AppCompatActivity {

    private Button uploadKamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        uploadKamar = findViewById(R.id.button_upload);
        uploadKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(i);
            }
        });

    }
}
