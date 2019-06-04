package com.example.application30;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Info;
    private Button Login;
    private int Counter = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.emailText);
        Password = findViewById(R.id.mdpText);
        Info = findViewById(R.id.btnOublie);
        Login = findViewById(R.id.loginBtn);



        /*Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onLogin(view);




            }
        });*/


            this.Login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    validate();
                }
            });
        }





    private void validate(){
        final String username = Email.getText().toString();
        final String password = Password.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if(success){
                        /*String name = jsonObject.getString("name");
                        int age = jsonObject.getInt("age");
                        String nom = jsonObject.getString("nom");
                        String email = jsonObject.getString("email");*/

                        Toast.makeText(MainActivity.this,"Login success ! ", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, InterfaceNFCQR.class);
                        /*intent.putExtra("name", name);
                        intent.putExtra("username", username);
                        intent.putExtra("age", age + "");
                        intent.putExtra("nom", nom);
                        intent.putExtra("email ", email);*/
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this,"Login error ! ", Toast.LENGTH_SHORT).show();
                        Counter--;
                        if (Counter == 0) {

                            Login.setEnabled(false);
                            Info.setText("Compte bloqué, demandez un nouveau mot de passe");
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"Login error ! " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        try {
            LoginRequest loginRequest = new LoginRequest(username, password, InfoServer.getIp(), responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /*private void validate(String userName, String userPassword) {
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
    }*/


    /*public void onLogin(View view) {
        String username = Email.getText().toString();
        String mdp = Password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, mdp);

        if ((Email.equals(username)) && (Password.equals(mdp))) {
            Intent intent = new Intent(MainActivity.this, InterfaceNFCQR.class);
            startActivity(intent);
        }else {

            Counter--;

            Info.setText("Nb d'essais " + String.valueOf(Counter));


            if (Counter == 0) {

                Login.setEnabled(false);
                Info.setText("Nb d'essais " + String.valueOf(Counter) + ". Compte bloqué, demandez un nouveau mot de passe");
            }


        }
    }*/


}




