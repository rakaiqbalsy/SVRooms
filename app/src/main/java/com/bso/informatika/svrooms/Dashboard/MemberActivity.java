package com.bso.informatika.svrooms.Dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bso.informatika.svrooms.R;
import com.bso.informatika.svrooms.activities.MainActivityRumah;

public class MemberActivity extends AppCompatActivity {

    private Button carirumah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        carirumah = findViewById(R.id.buttoncarirumah);
        carirumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityRumah.class);
                startActivity(i);
            }
        });

    }
}
