package br.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class menuPrincipal extends AppCompatActivity {

    LinearLayout btnForum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        findViewById(R.id.btnCalendario).setOnClickListener((v)->{
            Intent it = new Intent(getBaseContext(), agendamento.class);
            startActivity(it);
        });

        findViewById(R.id.btnSettings).setOnClickListener((v)->{
            Intent it = new Intent(getBaseContext(), settings.class);
            startActivity(it);
        });

        findViewById(R.id.btnNotification).setOnClickListener((v)->{
            Intent it = new Intent(getBaseContext(), notification.class);
            startActivity(it);
        });
    }
}