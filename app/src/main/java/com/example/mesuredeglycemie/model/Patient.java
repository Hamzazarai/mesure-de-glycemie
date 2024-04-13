package com.example.mesuredeglycemie.model;

import android.util.Log;
import android.widget.Toast;

import com.example.mesuredeglycemie.view.MainActivity;

public class Patient {
    private int age ;
    private float valeur;
    private boolean isFasting;
    private String result;

    // update : controller --> model :
    public Patient(int age, float valeur, boolean isFasting) {
        this.age = age;
        this.valeur = valeur;
        this.isFasting = isFasting;
        setResult();
    }
    private void setResult() {

            if (isFasting) {
                if (age >= 13) {
                    if (valeur < 5.0)
                        result="Le niveau de glycémie est trop bas.";
                    else if (valeur <= 7.2) //(valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                        result="Le niveau de glycémie est normal.";
                    else //valeurMesuree > 7.2
                        result="Le niveau de glycémie est trop élevé.";

                } else if (age >= 6)  //(age >= 6 && age <= 12)
                {
                    if (valeur < 5.0)
                        result="Le niveau de glycémie est trop bas.";
                    else if (valeur <= 10.0)  //(valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                        result="Le niveau de glycémie est normal.";
                    else  //valeurMesuree > 10.0
                        result="Le niveau de glycémie est trop élevé.";


                } else // age < 6
                {
                    if (valeur > 10.5)
                        result="Niveau de glycémie est trop élevé";
                    else
                        result="Niveau de glycémie est normale";
                }

            }

        }

        // notify : model --> controller :
    public String getResult() {
        return result;
    }
}


