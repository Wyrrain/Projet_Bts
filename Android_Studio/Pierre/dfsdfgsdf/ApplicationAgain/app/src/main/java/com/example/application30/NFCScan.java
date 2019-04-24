package com.example.application30;
import android.content.Intent;
import android.nfc.NfcAdapter;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class NFCScan extends AppCompatActivity {


    private boolean tagReaded;
    private ToggleButton tglReadWrite;
    private TextView txtTag;

    public static final String TAG = "NfcDemo";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcscan);
        tagReaded = false;

        tglReadWrite = findViewById(R.id.tglReadWrite);
        mTextView = findViewById(R.id.textView_explanation);
        txtTag = findViewById(R.id.txtTag);

        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;

        }

        if (!mNfcAdapter.isEnabled()) {
            mTextView.setText("NFC is disabled.");
        } else {
            mTextView.setText("NFC is enable");


        }

        //handleIntent(getIntent());
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i("NfcDemo", "entered");
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(getIntent().getAction())) {
            Log.i("NfcDemo", "enter here also");
            Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //Toast.makeText(this, "TAG: " + tag.getId().toString(), Toast.LENGTH_LONG).show();
            txtTag.setText("TAG:" + tag.getId().toString());

        }
    }

    /*private void handleIntent(Intent intent) {
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

    }*/

    private Tag currentTag;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            currentTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

                //readTagData(currentTag);


        } else {

            Toast.makeText(this, "Tag illisible", Toast.LENGTH_LONG).show();
            tagReaded = false;
        }
    }


   /*private String readTagData(Tag tag) {

        byte[] id = tag.getId();
        //txtTag.setText(Arrays.toString(id));
        private String bytesToHexString(byte[] id) {
            StringBuilder stringBuilder = new StringBuilder("0x");
            if (id == null || id.length <= 0) {
                return null;
            }

            char[] buffer = new char[2];
            for (int i = 0; i < id.length; i++) {
                buffer[0] = Character.forDigit((id[i] >>> 4) & 0x0F, 16);
                buffer[1] = Character.forDigit(id[i] & 0x0F, 16);
                System.out.println(buffer);
                stringBuilder.append(buffer);
            }

            return stringBuilder.toString();
        }

    }*/
}







