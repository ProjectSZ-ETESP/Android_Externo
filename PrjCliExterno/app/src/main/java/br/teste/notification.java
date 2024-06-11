package br.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        findViewById(R.id.btnSettings).setOnClickListener((v)->{
            Intent it = new Intent(getBaseContext(), settings.class);
            startActivity(it);
        });
        findViewById(R.id.imgBack).setOnClickListener((v)->{
            finish();
        });
        findViewById(R.id.btnHome).setOnClickListener((v)->{
            Intent it = new Intent(getBaseContext(), menuPrincipal.class);
            startActivity(it);
        });



    }
}