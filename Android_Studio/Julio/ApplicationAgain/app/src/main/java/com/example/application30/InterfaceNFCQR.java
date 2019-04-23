package com.example.application30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class InterfaceNFCQR extends AppCompatActivity {


    private ImageButton NFCbtn;
    private ImageButton QRCodebtn;

    private void initViews() {
        QRCodebtn = findViewById(R.id.QRCodebtn);
        NFCbtn = findViewById(R.id.NFCbtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_nfcqr);
        initViews();


        NFCbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ouvrirInterfaceNFC();
            }
        });
        QRCodebtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                ouvrirInterfaceQR();

             }
          });





    }

    public void ouvrirInterfaceNFC() {
        Intent intent = new Intent(this, NFCScan.class);
        startActivity(intent);


    }

    public void ouvrirInterfaceQR() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);

    }

}

