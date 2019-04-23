package com.example.application30;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class NFCScan extends AppCompatActivity {

    public static final String TAG = "NfcDemo";

    private TextView mTextView;
    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcscan);


        mTextView = findViewById(R.id.textView_explanation);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;

        }

        if (!mNfcAdapter.isEnabled()) {
            mTextView.setText("NFC is disabled.");
        } else {
            mTextView.setText("NFC is enable");

        }

        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        // TODO: handle Intent
    }
    private void processNFCData( Intent inputIntent ) {

        Parcelable[] rawMessages =
                inputIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        if (rawMessages != null && rawMessages.length > 0) {

            NdefMessage[] messages = new NdefMessage[rawMessages.length];

            for (int i = 0; i < rawMessages.length; i++) {

                messages[i] = (NdefMessage) rawMessages[i];

            }
            Log.i(TAG, "message size = " + messages.length);


            // so you can just grab the first record.
            NdefMessage msg = (NdefMessage) rawMessages[0];

            // record 0 contains the MIME type, record 1 is the AAR, if present
            String payloadStringData = new String(msg.getRecords()[0].getPayload());
            mTextView.setText(payloadStringData);

            // now do something with your payload payloadStringData


        }

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals( intent.getAction() )) {
            // We scanned an NFC Tag.
            processNFCData( intent );
        }

    }



}



