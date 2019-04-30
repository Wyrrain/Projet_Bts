package com.example.application30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Info;
    private Button Login;
    private int Counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.emailText);
        Password = findViewById(R.id.mdpText);
        Info = findViewById(R.id.btnOublie);
        Login = findViewById(R.id.loginBtn);

        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());

            }
        });


    }

    private void validate(String userName, String userPassword) {
        if ((userName.equals("Julio")) && (userPassword.equals("12345"))) {
            Intent intent = new Intent(MainActivity.this, InterfaceNFCQR.class);
            startActivity(intent);
        } else {

            Counter--;

            Info.setText("Nb d'essais " + String.valueOf(Counter));


            if (Counter == 0) {

                Login.setEnabled(false);
                Info.setText("Nb d'essais " + String.valueOf(Counter) + ". Compte bloqué, demandez un nouveau mot de passe");
            }


        }
    }


}







