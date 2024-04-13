package com.example.mesuredeglycemie.controller;
import com.example.mesuredeglycemie.model.Patient;

public class Controller {
    private static Patient patient;
    private static Controller instance =null;

    private Controller()
    {
        super();
    }

    public static final Controller getInstance()
    {
        if(instance==null)
        {
            instance=new Controller();
        }
        return instance;
    }
    //user action ; view --> controller :
    public void createPatient(int age , float valeur ,boolean isFasting)
    {
        // update : controller --> model :
        patient=new Patient(age,valeur,isFasting);
    }

    // notify controller --> view :
    public String getResult()
    {
        return patient.getResult();
    }
}









