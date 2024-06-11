package br.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class agendamento extends AppCompatActivity {


    static int year, month;
    TextView lblMesAgendamento;
    EditText txtData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);



        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        month = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));

        dateFormat = new SimpleDateFormat("yyyy");
        year = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));

        txtData = findViewById(R.id.txtData);

        lblMesAgendamento = findViewById(R.id.lblMesAgendamento);

        findViewById(R.id.imgX).setOnClickListener((v)->{
            finish();
        });
        buildCalendar();

        findViewById(R.id.imgNextMonth).setOnClickListener((v)->{
            month++;
            if(month > 12){
                month = 1;
                year++;
            }
            buildCalendar();
        });

        findViewById(R.id.imgPreviousMonth).setOnClickListener((v)->{
            month--;
            if(month < 1){
                month = 12;
                year--;
            }
            buildCalendar();
        });
    }

    int getDeterminateDate(int year, int month, int day) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LocalDate a = LocalDate.of(year, month, day);
                int dayWeek = a.getDayOfWeek().getValue();
                return dayWeek;
            }
            return 0;
        }catch(Exception err){
            return -1;
        }
    }

    void clearBackground(){
        LinearLayout tabela =  findViewById(R.id.llDias);
        for(int i = 1; i < tabela.getChildCount(); i++) {
            LinearLayout semana = (LinearLayout) tabela.getChildAt(i);
            for (int j = 0; j < semana.getChildCount(); j++) {
                semana.getChildAt(j).setBackgroundResource(0);
            }
        }
    }

    String dayName(int idWeek){
        String[] week = {"Domingo","Segunda-Feira","Terça-Feira","Quarta-Feira","Quinta-Feira","Sexta-Feira","Sábado"};
        return week[idWeek];
    }
    String monthName(int idMonth){
        String[] months = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return months[idMonth];
    }

    void buildCalendar(){
        clearBackground();
        LinearLayout tabela =  findViewById(R.id.llDias);
        lblMesAgendamento.setText(monthName(month-1));

        int numeroDia = 0;
        int diaSemana = getDeterminateDate(year,month,1)%7;
        int ultimoDia = 31;

        while (getDeterminateDate(year, month, ultimoDia) == -1) {
            ultimoDia--;
        }

        for(int i = 1; i < tabela.getChildCount(); i++){
            LinearLayout semana = (LinearLayout) tabela.getChildAt(i);
            for(int j = 0; j < semana.getChildCount(); j++){
                TextView lblDia = (TextView) semana.getChildAt(j);
                lblDia.setWidth((int)Math.floor(semana.getWidth()/7));
                lblDia.setHeight((int)Math.floor(semana.getWidth()/7));

                if(((diaSemana > j) && (i < 2))||(numeroDia >= ultimoDia)){
                    lblDia.setText("");
                    lblDia.setOnClickListener(null);

                }else{
                    numeroDia++;
                    lblDia.setText(numeroDia+"");

                    if(numeroDia == 1) {
                        lblDia.setBackgroundResource(R.drawable.roundy);
                        lblDia.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lime)));
                        int idDayWeek = (int) ((Integer.parseInt(lblDia.getText().toString()) % 7 ) +diaSemana-1) %7;
                        txtData.setText(dayName(idDayWeek)+", "+year);
                    }

                    lblDia.setOnClickListener((v)->{
                        clearBackground();

                        lblDia.setBackgroundResource(R.drawable.roundy);
                        lblDia.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lime)));

                        int idDayWeek = (int) ((Integer.parseInt(lblDia.getText().toString()) % 7 ) +diaSemana-1) %7;
                        txtData.setText(dayName(idDayWeek)+", "+year);
                    });
                }
            }
        }
    }
}