package com.example.login20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText)findViewById(R.id.emailText);
        Password = (EditText)findViewById(R.id.mdpText);
        Info = (TextView) findViewById(R.id.txtEssais);
        Login = (Button)findViewById(R.id.loginButton);

        Info.setText("Nb d'essais restantes: 5");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());

            }
        });


    }
            private void validate(String userName, String userPassword){
                if((userName.equals("Admin")) && (userPassword.equals("1234"))) {
                    Intent intent = new Intent(MainActivity.this, secondActivity.class);
                    startActivity(intent);
                } else {

                    counter--;

                    Info.setText("Nb d'essais" + String.valueOf (counter));

                    if(counter == 0) {

                        Login.setEnabled(false);
                    }


                }
        }
    }
}
