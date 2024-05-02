package com.example.mesuredeglycemie.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mesuredeglycemie.R;
import com.example.mesuredeglycemie.controller.Controller;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge;
    private EditText etValeur;
    private SeekBar sbAge;
    private RadioButton rbIsFasting,rbIsNotFasting;
    private Button btnConsulter;
    private Controller controller;
    private final String RESPONSE_KEY="result";
    private final int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Log.i("Information ","on progress change"+progress);
                     tvAge.setText("Votre age :"+progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age;
                float valeur;
                Log.i("Information", "button cliqué");
                boolean verifAge = false, verifValeur = false;
                if(sbAge.getProgress()!=0)
                    verifAge = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre age!", Toast.LENGTH_SHORT).show();

                if(!etValeur.getText().toString().isEmpty())
                    verifValeur = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !", Toast.LENGTH_LONG).show();
                if(verifAge && verifValeur)
                {
                  age=sbAge.getProgress();
                  valeur =Float.valueOf(etValeur.getText().toString());
                  // user action view --> controller :
                    controller.createPatient(age,valeur,rbIsFasting.isChecked());

                    // notify controller --> view :
                    Intent intent =new Intent(getApplicationContext(),ConsultActivity.class);
                    intent.putExtra(RESPONSE_KEY,controller.getResult());
                    startActivityForResult(intent,REQUEST_CODE);
                    //tvReponse.setText(controller.getResult());


                }
            }
        });
    }

    private void init()
    {
        controller=Controller.getInstance();
        tvAge=findViewById(R.id.tvAge);
        //tvReponse=findViewById(R.id.tvReponse);
        etValeur=findViewById(R.id.etVal);
        sbAge=findViewById(R.id.sbAge);
        rbIsFasting=findViewById(R.id.rbtOui);
        rbIsNotFasting=findViewById(R.id.rbtNon);
        btnConsulter=findViewById(R.id.btnConsulter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE)
            if(resultCode==REQUEST_CODE)
                Toast.makeText(MainActivity.this,"ERROR:RESULT_CANCELLED",1);
    }
}