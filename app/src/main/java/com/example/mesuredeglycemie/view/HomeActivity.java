package com.example.mesuredeglycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mesuredeglycemie.R;
import com.example.mesuredeglycemie.controller.HomeController;

public class HomeActivity extends AppCompatActivity {

    private Button btnConsultation ;
    private EditText etUserEmail,etUserPassword;
    private HomeController controller ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        etUserEmail.setText(controller.getUserEmail());
        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("information","button cliqué");
                String userEmail, password;
                boolean verifUserEmail = false, verifPassword = false;

                if(!etUserEmail.getText().toString().isEmpty())
                    if(!controller.getUserEmail().equals("") && !etUserEmail.getText().toString().equals(controller.getUserEmail()))
                        Toast.makeText(HomeActivity.this, "Email incorrect", Toast.LENGTH_LONG).show();
                    else
                        verifUserEmail = true;
                else
                    Toast.makeText(HomeActivity.this, "Veuillez saisir votre nom d'utilisateur !", Toast.LENGTH_SHORT).show();

                if(!etUserPassword.getText().toString().isEmpty())
                    if(!controller.getUserEmail().equals("") && !etUserPassword.getText().toString().equals(controller.getUserPassword()))
                        Toast.makeText(HomeActivity.this, "Mot de passe incorrect", Toast.LENGTH_LONG).show();
                    else
                        verifPassword = true;
                else
                    Toast.makeText(HomeActivity.this, "Veuillez saisir votre mot de passe !", Toast.LENGTH_LONG).show();

                if(verifPassword && verifUserEmail) {
                    userEmail = etUserEmail.getText().toString();
                    password = etUserPassword.getText().toString();
                    controller.createUser(userEmail, password, HomeActivity.this);
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void init()
    {  controller=HomeController.getInstance(this);
        etUserEmail=findViewById(R.id.etUserEmail);
        etUserPassword=findViewById(R.id.etUserPassword);
        btnConsultation=findViewById(R.id.btnConsultation);
    }
}