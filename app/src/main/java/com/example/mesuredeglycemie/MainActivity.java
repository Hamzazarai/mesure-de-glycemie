package com.example.mesuredeglycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge,tvReponse;
    private EditText etValeur;
    private SeekBar sbAge;
    private RadioButton rbIsFasting,rbIsNotFasting;
    private Button btnConsulter;
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
                    calculer();
                }
            }
        });
    }
    private void calculer()
    {
    Log.i("Information :","onclick sur le bouton consulter ");
    int age;float valeur;boolean verifAge =false,verifValeur=false;
    if(sbAge.getProgress()>0)
        verifAge=true;
    else
        Toast.makeText(MainActivity.this,"Veuillez saisir votre age .",Toast.LENGTH_SHORT).show();
    if(etValeur.getText().toString().isEmpty())
        Toast.makeText(MainActivity.this,"Veuillez saisir votre valeur mesurée.",Toast.LENGTH_LONG).show();
    else
        verifValeur=true;
    if(verifValeur&&verifValeur)
    {
        age=sbAge.getProgress();
        valeur=Float.parseFloat(etValeur.getText().toString());
        if(rbIsFasting.isChecked())
        {
            if(age>=13)
            {
                if(valeur<5.0)
                    tvReponse.setText("Le niveau de glycémie est trop bas.");
                else if (valeur<=7.2) //(valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                    tvReponse.setText("Le niveau de glycémie est normal.");
                else //valeurMesuree > 7.2
                    tvReponse.setText("Le niveau de glycémie est trop élevé.");

            }
            else if(age>=6)  //(age >= 6 && age <= 12)
            {
                if(valeur<5.0)
                    tvReponse.setText("Le niveau de glycémie est trop bas.");
                else if (valeur<=10.0)  //(valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                    tvReponse.setText("Le niveau de glycémie est normal.");
                else  //valeurMesuree > 10.0
                    tvReponse.setText("Le niveau de glycémie est trop élevé.");


            }
            else // age < 6
            {
                if (valeur > 10.5)
                    tvReponse.setText("Niveau de glycémie est trop élevé");
                else
                    tvReponse.setText("Niveau de glycémie est normale");
            }

        }

    }
    }

    private void init()
    {
        tvAge=findViewById(R.id.tvAge);
        tvReponse=findViewById(R.id.tvReponse);
        etValeur=findViewById(R.id.etVal);
        sbAge=findViewById(R.id.sbAge);
        rbIsFasting=findViewById(R.id.rbtOui);
        rbIsNotFasting=findViewById(R.id.rbtNon);
        btnConsulter=findViewById(R.id.btnConsulter);
    }
}