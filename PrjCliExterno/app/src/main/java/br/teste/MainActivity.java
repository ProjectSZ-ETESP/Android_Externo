package br.teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnEntrar, btnMove, btnShow, btnForgetSenha;
    EditText txtEmail, txtSenha;
    TextView lblLog;
    LinearLayout lnlPanel;
    ConstraintLayout clLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clLayout = findViewById(R.id.clLayout);
        btnForgetSenha = findViewById(R.id.btnForgetSenha);
        btnEntrar = findViewById(R.id.BtnEntrar);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        lnlPanel = findViewById(R.id.lnlPanel);
        lblLog = findViewById(R.id.lblLog);
        btnMove = findViewById(R.id.btnMove);
        btnShow = findViewById(R.id.btnShow);

        lblLog.setPadding((int)(lnlPanel.getWidth()*0.095),0,0,0);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSenha.getInputType()==129){
                    txtSenha.setInputType(10);

                    btnShow.setBackgroundResource(R.drawable.eyeopen);
                }
                else{
                    txtSenha.setInputType(129);
                    btnShow.setBackgroundResource(R.drawable.eyeclosed);

                }

            }
        });

        btnForgetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://allensart.netlify.app"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
               // startActivity(intent);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), menuPrincipal.class);
                startActivity(it);
            }
        });

        btnMove.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float xAtual = 0, yAtual = 0;

                switch(event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        yAtual = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float yMove = event.getY();
                        float ydesl =  yMove - yAtual;


                        if(Math.round(lnlPanel.getY()+ydesl)>clLayout.getHeight()-lnlPanel.getY()*1/4){
                            lnlPanel.setY(clLayout.getHeight()-lnlPanel.getY()*1/4);
                        }else if(Math.round(lnlPanel.getY()+ydesl+lnlPanel.getHeight()) < clLayout.getHeight()){
                            lnlPanel.setY(clLayout.getHeight()-lnlPanel.getHeight());
                        }else{
                            lnlPanel.setY(lnlPanel.getY()+ydesl);
                        }
                        double padd;

                        padd = Float.parseFloat(Math.floor(lnlPanel.getY()-(clLayout.getHeight()-lnlPanel.getHeight()))/(lnlPanel.getHeight()-lnlPanel.getY()*1/4)+"");
                        padd = padd* (float)(lblLog.getWidth()*(0.35))+lblLog.getWidth()*0.23-(lnlPanel.getWidth()*0.05);

                        lblLog.setPadding((int)padd,0,0,0);

                        break;

                }

                return true;

            }
        });

        lnlPanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float xAtual = 0, yAtual = 0;

                switch(event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        yAtual = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float yMove = event.getY();
                        float ydesl =  yMove - yAtual;


                        if(Math.round(lnlPanel.getY()+ydesl)>clLayout.getHeight()-lnlPanel.getY()*1/4){
                            lnlPanel.setY(clLayout.getHeight()-lnlPanel.getY()*1/4);
                        }else if(Math.round(lnlPanel.getY()+ydesl+lnlPanel.getHeight()) < clLayout.getHeight()){
                            lnlPanel.setY(clLayout.getHeight()-lnlPanel.getHeight());
                        }else{
                            lnlPanel.setY(lnlPanel.getY()+ydesl);
                        }
                        double padd;

                        padd = Float.parseFloat(Math.floor(lnlPanel.getY()-(clLayout.getHeight()-lnlPanel.getHeight()))/(lnlPanel.getHeight()-lnlPanel.getY()*1/4)+"");
                        padd = padd* (float)(lblLog.getWidth()*(0.35))+lblLog.getWidth()*0.23-(lnlPanel.getWidth()*0.05);

                        lblLog.setPadding((int)padd,0,0,0);

                        break;

                }

                return true;

            }
        });



    }
}