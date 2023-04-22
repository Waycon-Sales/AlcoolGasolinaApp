package com.example.alcoolgasolinaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch switch75;
    private EditText txGasolina, txAlcool;
    private Button btnCalcula;
    private double variante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch75 = findViewById(R.id.sw);
        txGasolina = findViewById(R.id.txGasolina);
        txAlcool = findViewById(R.id.txAlcool);
        btnCalcula = findViewById(R.id.btnCalc);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        String baseCalculo = preferences.getString("variavelDeCalculo", "0.70");
        variante = Double.parseDouble(baseCalculo);
        if(variante == 0.75){
             switch75.setChecked(true);
        }
        switch75.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch75.isChecked()){
                    variante = 0.75;
                    editor.putString("variavelDeCalculo", "0.75");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Calculo com base em 75% ativado", Toast.LENGTH_SHORT).show();
                } else {
                    variante = 0.70;
                    editor.putString("variavelDeCalculo", "0.70");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Calculo com base em 70% ativado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCalcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double gasolina = Double.parseDouble(txGasolina.getText().toString().replace(",","."));
                Double alcool = Double.parseDouble(txAlcool.getText().toString().replace(",","."));

                if((alcool/gasolina) <= variante){
                    //alcool
                    Toast.makeText(MainActivity.this, "Álcool é a melhor opção", Toast.LENGTH_LONG).show();

                }else{
                    //gasolina
                    Toast.makeText(MainActivity.this, "Gasolina é a melhor opção", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}