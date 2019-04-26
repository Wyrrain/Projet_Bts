package com.example.application30;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.provider.Settings;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class NFCScan extends AppCompatActivity {


    private boolean tagReaded;
    NfcAdapter nfcAdapter;
    ToggleButton tglReadWrite;
    private TextView txtTag;
    EditText txtTagContent;

    public static final String TAG = "NfcDemo";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcscan);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        tglReadWrite = (ToggleButton)findViewById(R.id.tglReadWrite);
        txtTagContent = (EditText)findViewById(R.id.txtTagContent);

        if(nfcAdapter!=null) {
            //Toast.makeText(this, "NFC disponible sur ce Smartphone !", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "NFC indisponible sur ce Smartphone...", Toast.LENGTH_LONG).show();
            //finish();
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (!nfcAdapter.isEnabled()) {
            //Toast.makeText(getApplicationContext(), "Vous devez activer la fonction NFC de votre smartphone pour utiliser cette application.", Toast.LENGTH_LONG).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(NFCScan.this);
            builder.setTitle("NFC est désactivé");
            builder.setMessage("Vous devez activer la fonction NFC de votre smartphone pour lire ou écrire des TAG RFID.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                }
            });
            builder.show();
        }

        enableForegroundDispatcher();
    }

    @Override
    protected  void onPause() {

        super.onPause();

        disableForegroundDispatcher();
    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);

        if(intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Toast.makeText(this, "NFC intent!", Toast.LENGTH_LONG).show();

            if(tglReadWrite.isChecked())
            {
                Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

                if(parcelables != null && parcelables.length > 0)
                {
                    readTextFromMessage((NdefMessage)parcelables[0]);

                }
                else {

                    Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void readTextFromMessage(NdefMessage ndefMessage) {

        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if(ndefRecords != null && ndefRecords.length > 0) {

            NdefRecord ndefRecord = ndefRecords[0];

            String tagContent = getTextFromNdefRecord(ndefRecord);

            txtTagContent.setText(tagContent);
        }
        else {

            Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_LONG).show();
        }
    }



    private void enableForegroundDispatcher() {

        Intent intent = new Intent(this, NFCScan.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    private void disableForegroundDispatcher() {

        nfcAdapter.disableForegroundDispatch(this);
    }



    public String getTextFromNdefRecord(NdefRecord ndefRecord) {

        String tagContent = null;

        try {

            byte[] payload = ndefRecord.getPayload();

            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";

            int languageSize = payload[0] & 0063;

            tagContent = new String(payload, languageSize + 1, payload.length - languageSize - 1, textEncoding);

        }catch (UnsupportedEncodingException e) {
            Log.e("getTextFromNdef", e.getMessage(), e);
        }

        return tagContent;
    }





}







