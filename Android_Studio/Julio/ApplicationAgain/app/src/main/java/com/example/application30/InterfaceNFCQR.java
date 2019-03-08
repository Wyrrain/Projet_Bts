package com.example.application30;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InterfaceNFCQR extends AppCompatActivity {


    private ImageButton NFC;
    /*private ImageButton QRCode;
    private Button PasTAG;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_nfcqr);

        NFC = (ImageButton) findViewById(R.id.NFCbtn);
        /*QRCode = (ImageButton) findViewById(R.id.QRCodebtn);
        PasTAG = (Button) findViewById(R.id.PasTAGbtn);*/


        NFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ouvrirInterfaceNFCQR();

            }
        });
        /*QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        PasTAG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


    }

    public void ouvrirInterfaceNFCQR() {
        Intent intent = new Intent(this, NFCScan.class);
        startActivity(intent);


    }
}

