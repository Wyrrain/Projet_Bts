package fr.thomashourdin.www.ihmrfidcostumes;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcV;
import android.os.Parcelable;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.widget.ViewSwitcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    private boolean tagReaded;

    private final int nAcces = 8;
    private  final String charSet = "ISO-8859-1";

    private NfcAdapter nfcAdapter;

    private Vibrator vibe;
    private int vibrationDuration;

    private Toolbar toolbar;

    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;

    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String MY_UNIQUE_PREFERENCE_FILE = "RFIDCostumesPreferences";

    private RelativeLayout layoutReadWrite;
    private ToggleButton tglReadWrite;
    private TextView txtReadWriteMode;
    private ImageButton btnClearFields;

    private TextView txtLabelLigne1;
    private TextView txtLabelLigne2;
    private TextView txtLabelLigne3;
    private TextView txtLabelLigne4;
    private TextView txtLabelLigne5;

    private EditText editTagLigne1;
    private EditText editTagLigne2;
    private EditText editTagLigne3;
    private EditText editTagLigne4;
    private EditText editTagLigne5;
    private ToggleButton tglAcces1;
    private ToggleButton tglAcces2;
    private ToggleButton tglAcces3;
    private ToggleButton tglAcces4;
    private ToggleButton tglAcces5;
    private ToggleButton tglAcces6;
    private ToggleButton tglAcces7;
    private ToggleButton tglAcces8;

    private ToggleButton[] tglAcces; //tableau de références aux 8 ToggleButton précédentes

    private TextView txtTagLigne1;
    private TextView txtTagLigne2;
    private TextView txtTagLigne3;
    private TextView txtTagLigne4;
    private TextView txtTagLigne5;
    private ImageView imgAcces1;
    private ImageView imgAcces2;
    private ImageView imgAcces3;
    private ImageView imgAcces4;
    private ImageView imgAcces5;
    private ImageView imgAcces6;
    private ImageView imgAcces7;
    private ImageView imgAcces8;

    private ImageView[] imgAcces; //tableau de références aux 8 ImageView précédentes
    private boolean[] accesState;

    private TextView txtAcces1;
    private TextView txtAcces2;
    private TextView txtAcces3;
    private TextView txtAcces4;
    private TextView txtAcces5;
    private TextView txtAcces6;
    private TextView txtAcces7;
    private TextView txtAcces8;
    private TextView txtTglAcces1;
    private TextView txtTglAcces2;
    private TextView txtTglAcces3;
    private TextView txtTglAcces4;
    private TextView txtTglAcces5;
    private TextView txtTglAcces6;
    private TextView txtTglAcces7;
    private TextView txtTglAcces8;

    private ViewSwitcher viewSwitcherLigne1;
    private ViewSwitcher viewSwitcherLigne2;
    private ViewSwitcher viewSwitcherLigne3;
    private ViewSwitcher viewSwitcherLigne4;
    private ViewSwitcher viewSwitcherLigne5;

    private ViewSwitcher viewSwitcherAcces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagReaded = false;

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter!=null) {
            //Toast.makeText(this, "NFC disponible sur ce Smartphone !", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "NFC indisponible sur ce Smartphone", Toast.LENGTH_LONG).show();
            //finish();
        }

        vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrationDuration = 20;

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        //toolbar.setNavigationIcon(R.drawable.locked_off);
        toolbar.setLogo(R.drawable.logo_pdf);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        preferences = getSharedPreferences(MY_UNIQUE_PREFERENCE_FILE, PREFERENCE_MODE_PRIVATE);
        preferencesEditor = preferences.edit();

        layoutReadWrite = (RelativeLayout)findViewById(R.id.layoutReadWrite);
        tglReadWrite = (ToggleButton)findViewById(R.id.tglReadWrite);
        txtReadWriteMode = (TextView)findViewById(R.id.txtReadWriteMode);
        btnClearFields = (ImageButton)findViewById(R.id.btnClearFields);

        txtLabelLigne1 = (TextView)findViewById(R.id.txtLabelLigne1);
        txtLabelLigne2 = (TextView)findViewById(R.id.txtLabelLigne2);
        txtLabelLigne3 = (TextView)findViewById(R.id.txtLabelLigne3);
        txtLabelLigne4 = (TextView)findViewById(R.id.txtLabelLigne4);
        txtLabelLigne5 = (TextView)findViewById(R.id.txtLabelLigne5);

        editTagLigne1 = (EditText)findViewById(R.id.editTagLigne1);
        editTagLigne2 = (EditText)findViewById(R.id.editTagLigne2);
        editTagLigne3 = (EditText)findViewById(R.id.editTagLigne3);
        editTagLigne4 = (EditText)findViewById(R.id.editTagLigne4);
        editTagLigne5 = (EditText)findViewById(R.id.editTagLigne5);
        tglAcces1 = (ToggleButton)findViewById(R.id.tglAcces1);
        tglAcces2 = (ToggleButton)findViewById(R.id.tglAcces2);
        tglAcces3 = (ToggleButton)findViewById(R.id.tglAcces3);
        tglAcces4 = (ToggleButton)findViewById(R.id.tglAcces4);
        tglAcces5 = (ToggleButton)findViewById(R.id.tglAcces5);
        tglAcces6 = (ToggleButton)findViewById(R.id.tglAcces6);
        tglAcces7 = (ToggleButton)findViewById(R.id.tglAcces7);
        tglAcces8 = (ToggleButton)findViewById(R.id.tglAcces8);

        tglAcces = new ToggleButton[nAcces];
        tglAcces[0] = tglAcces1;
        tglAcces[1] = tglAcces2;
        tglAcces[2] = tglAcces3;
        tglAcces[3] = tglAcces4;
        tglAcces[4] = tglAcces5;
        tglAcces[5] = tglAcces6;
        tglAcces[6] = tglAcces7;
        tglAcces[7] = tglAcces8;

        txtTagLigne1 = (TextView)findViewById(R.id.txtTagLigne1);
        txtTagLigne2 = (TextView)findViewById(R.id.txtTagLigne2);
        txtTagLigne3 = (TextView)findViewById(R.id.txtTagLigne3);
        txtTagLigne4 = (TextView)findViewById(R.id.txtTagLigne4);
        txtTagLigne5 = (TextView)findViewById(R.id.txtTagLigne5);
        imgAcces1 = (ImageView)findViewById(R.id.imgAcces1);
        imgAcces2 = (ImageView)findViewById(R.id.imgAcces2);
        imgAcces3 = (ImageView)findViewById(R.id.imgAcces3);
        imgAcces4 = (ImageView)findViewById(R.id.imgAcces4);
        imgAcces5 = (ImageView)findViewById(R.id.imgAcces5);
        imgAcces6 = (ImageView)findViewById(R.id.imgAcces6);
        imgAcces7 = (ImageView)findViewById(R.id.imgAcces7);
        imgAcces8 = (ImageView)findViewById(R.id.imgAcces8);

        imgAcces = new ImageView[nAcces];
        imgAcces[0] = imgAcces1;
        imgAcces[1] = imgAcces2;
        imgAcces[2] = imgAcces3;
        imgAcces[3] = imgAcces4;
        imgAcces[4] = imgAcces5;
        imgAcces[5] = imgAcces6;
        imgAcces[6] = imgAcces7;
        imgAcces[7] = imgAcces8;

        accesState = new boolean[nAcces];

        txtAcces1 = (TextView)findViewById(R.id.txtAcces1);
        txtAcces2 = (TextView)findViewById(R.id.txtAcces2);
        txtAcces3 = (TextView)findViewById(R.id.txtAcces3);
        txtAcces4 = (TextView)findViewById(R.id.txtAcces4);
        txtAcces5 = (TextView)findViewById(R.id.txtAcces5);
        txtAcces6 = (TextView)findViewById(R.id.txtAcces6);
        txtAcces7 = (TextView)findViewById(R.id.txtAcces7);
        txtAcces8 = (TextView)findViewById(R.id.txtAcces8);
        txtTglAcces1 = (TextView)findViewById(R.id.txtTglAcces1);
        txtTglAcces2 = (TextView)findViewById(R.id.txtTglAcces2);
        txtTglAcces3 = (TextView)findViewById(R.id.txtTglAcces3);
        txtTglAcces4 = (TextView)findViewById(R.id.txtTglAcces4);
        txtTglAcces5 = (TextView)findViewById(R.id.txtTglAcces5);
        txtTglAcces6 = (TextView)findViewById(R.id.txtTglAcces6);
        txtTglAcces7 = (TextView)findViewById(R.id.txtTglAcces7);
        txtTglAcces8 = (TextView)findViewById(R.id.txtTglAcces8);

        viewSwitcherLigne1 = (ViewSwitcher)findViewById(R.id.viewSwitcherLigne1);
        viewSwitcherLigne2 = (ViewSwitcher)findViewById(R.id.viewSwitcherLigne2);
        viewSwitcherLigne3 = (ViewSwitcher)findViewById(R.id.viewSwitcherLigne3);
        viewSwitcherLigne4 = (ViewSwitcher)findViewById(R.id.viewSwitcherLigne4);
        viewSwitcherLigne5 = (ViewSwitcher)findViewById(R.id.viewSwitcherLigne5);

        viewSwitcherAcces = (ViewSwitcher)findViewById(R.id.viewSwitcherAcces);

        initActivity();
    }

    private void initActivity()
    {
        RefreshModeReadWrite();
        for(int i=0; i<nAcces; i++){
            accesState[i] = true;
            tglAcces[i].setChecked(accesState[i]);
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

        txtLabelLigne1.setText(preferences.getString("role1", getResources().getString(R.string.label_ligne_1)));
        txtLabelLigne2.setText(preferences.getString("role2", getResources().getString(R.string.label_ligne_2)));
        txtLabelLigne3.setText(preferences.getString("role3", getResources().getString(R.string.label_ligne_3)));
        txtLabelLigne4.setText(preferences.getString("role4", getResources().getString(R.string.label_ligne_4)));
        txtLabelLigne5.setText(preferences.getString("role5", getResources().getString(R.string.label_ligne_5)));

        txtAcces1.setText(preferences.getString("acces1", getResources().getString(R.string.label_acces_1)));
        txtAcces2.setText(preferences.getString("acces2", getResources().getString(R.string.label_acces_2)));
        txtAcces3.setText(preferences.getString("acces3", getResources().getString(R.string.label_acces_3)));
        txtAcces4.setText(preferences.getString("acces4", getResources().getString(R.string.label_acces_4)));
        txtAcces5.setText(preferences.getString("acces5", getResources().getString(R.string.label_acces_5)));
        txtAcces6.setText(preferences.getString("acces6", getResources().getString(R.string.label_acces_6)));
        txtAcces7.setText(preferences.getString("acces7", getResources().getString(R.string.label_acces_7)));
        txtAcces8.setText(preferences.getString("acces8", getResources().getString(R.string.label_acces_8)));

        txtTglAcces1.setText(preferences.getString("acces1", getResources().getString(R.string.label_acces_1)));
        txtTglAcces2.setText(preferences.getString("acces2", getResources().getString(R.string.label_acces_2)));
        txtTglAcces3.setText(preferences.getString("acces3", getResources().getString(R.string.label_acces_3)));
        txtTglAcces4.setText(preferences.getString("acces4", getResources().getString(R.string.label_acces_4)));
        txtTglAcces5.setText(preferences.getString("acces5", getResources().getString(R.string.label_acces_5)));
        txtTglAcces6.setText(preferences.getString("acces6", getResources().getString(R.string.label_acces_6)));
        txtTglAcces7.setText(preferences.getString("acces7", getResources().getString(R.string.label_acces_7)));
        txtTglAcces8.setText(preferences.getString("acces8", getResources().getString(R.string.label_acces_8)));

        // Gestion du NFC

        if (!nfcAdapter.isEnabled()) {
            //Toast.makeText(getApplicationContext(), "Vous devez activer la fonction NFC de votre smartphone pour utiliser cette application.", Toast.LENGTH_LONG).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("NFC désactivé");
            builder.setMessage("Vous devez activer la fonction NFC de votre smartphone pour lire ou écrire des TAG.");
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

    private Tag currentTag;

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);

        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            currentTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if(!tglReadWrite.isChecked()) {
                readTagData(currentTag);
            }
            else {
                writeTagData(currentTag);
            }
        }
        else {

            Toast.makeText(this, "Tag illisible", Toast.LENGTH_LONG).show();
            tagReaded = false;
        }
    }

    private void enableForegroundDispatcher() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    private void disableForegroundDispatcher() {

        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent intent;

        int id = item.getItemId();

        switch (id) {
            case R.id.action_labels:

                intent = new Intent(this, LabelActivity.class);
                startActivity(intent);

                break;
            case R.id.action_acces:

                intent = new Intent(this, AccesActivity.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tglReadWriteOnClick(View view) {

        viewSwitcherLigne1.showNext();
        viewSwitcherLigne2.showNext();
        viewSwitcherLigne3.showNext();
        viewSwitcherLigne4.showNext();
        viewSwitcherLigne5.showNext();

        viewSwitcherAcces.showNext();

        RefreshModeReadWrite();
    }

    private void RefreshModeReadWrite() {

        if (!tglReadWrite.isChecked()) { // LIRE TAG

            layoutReadWrite.setBackgroundColor(Color.parseColor("#33ffffff"));
            txtReadWriteMode.setText("Lecture");
            txtReadWriteMode.setTextColor(Color.parseColor("#ffffffff"));
            btnClearFields.setVisibility(View.INVISIBLE);
        }
        else { // ECRIRE TAG

            layoutReadWrite.setBackgroundColor(Color.parseColor("#ffff0000"));
            txtReadWriteMode.setText("Ecriture");
            txtReadWriteMode.setTextColor(Color.parseColor("#ffffffff"));
            btnClearFields.setVisibility(View.VISIBLE);

            if(tagReaded) {

                String l1 = ((String)txtTagLigne1.getText()).replaceAll("\\u0000", "");
                String l2 = ((String)txtTagLigne2.getText()).replaceAll("\\u0000", "");
                String l3 = ((String)txtTagLigne3.getText()).replaceAll("\\u0000", "");
                String l4 = ((String)txtTagLigne4.getText()).replaceAll("\\u0000", "");
                String l5 = ((String)txtTagLigne5.getText()).replaceAll("\\u0000", "");

                editTagLigne1.setText(l1);
                editTagLigne2.setText(l2);
                editTagLigne3.setText(l3);
                editTagLigne4.setText(l4);
                editTagLigne5.setText(l5);

                for(int i=0; i<nAcces; i++) tglAcces[i].setChecked(accesState[i]);
            }
        }
    }

    public void tglAcces1_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }
    public void tglAcces2_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }
    public void tglAcces3_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }
    public void tglAcces4_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }
    public void tglAcces5_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }
    public void tglAcces6_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }
    public void tglAcces7_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }
    public void tglAcces8_OnClick(View view) {
        vibe.vibrate(vibrationDuration);
    }

    public void btnClearFields_OnClick(View view) {

        vibe.vibrate(vibrationDuration);

        editTagLigne1.setText("");
        editTagLigne2.setText("");
        editTagLigne3.setText("");
        editTagLigne4.setText("");
        editTagLigne5.setText("");

        for(int i=0; i<nAcces; i++) tglAcces[i].setChecked(true);
    }


    private void readTagData(Tag tag) {

        byte[] id = tag.getId();

        for (String tech : tag.getTechList()) {

            if (tech.equals(NfcV.class.getName())) {

                // Get an instance of NfcV for the given tag:
                NfcV nfcvTag = NfcV.get(tag);

                try {
                    nfcvTag.connect();
                } catch (IOException e) {
                    return;
                }

                try {
                    /*
                    // Get system information (0x2B)
                    byte[] cmd = new byte[] {
                            (byte)0x00, // Flags
                            (byte)0x2B // Command: Get system information
                    };
                    byte[] systeminfo = nfcvTag.transceive(cmd);

                    // Chop off the initial 0x00 byte:
                    systeminfo = Arrays.copyOfRange(systeminfo, 1, 15);
                    */

                    // Read first 8 blocks containing the 32 byte of userdata defined in the Danish model
                    byte[] cmd = new byte[] {
                            (byte)0x00, // Flags
                            (byte)0x23, // Command: Read multiple blocks
                            (byte)0x00, // First block (offset)
                            (byte)0x1C  // Number of blocks
                    };
                    byte[] userdata = nfcvTag.transceive(cmd);

                    // Chop off the initial 0x00 byte:
                    userdata = Arrays.copyOfRange(userdata, 1, 112);

                    /*** DECODAGE DE LA TRAME ***/


                    int i;

                    /*** autorisations d'accès ***/

                    i = 2;

                    byte[] bufferAcces = new byte[4];

                    int bit = 1;
                    int codageAcces = 0;

                    for (int j = 0; j < 4; j++)
                    {
                        int byteVal = userdata[j + i];
                        if(byteVal < 0) byteVal = 256 + byteVal;
                        codageAcces += bit * byteVal;
                        bit *= 2;
                    }

                    bit = 1;

                    for (i = 0; i < nAcces; i++)
                    {
                        accesState[i] = ((codageAcces & bit) != 0);
                        setAcces(i, accesState[i]);
                        bit *= 2;
                    }

                    /*** rôles 1 à 5 ***/

                    i = 6;

                    byte[] buffer = new byte[18];
                    int iBuffer = 0;

                    while (userdata[i] != 0x00)
                    {
                        buffer[iBuffer] = userdata[i];
                        i++;
                        iBuffer++;
                    }
                    String tagL1 = new String(buffer, charSet);
                    txtTagLigne1.setText(tagL1);

                    i = 24;

                    buffer = new byte[18];
                    iBuffer = 0;

                    while (userdata[i] != 0x00)
                    {
                        buffer[iBuffer] = userdata[i];
                        i++;
                        iBuffer++;
                    }
                    String tagL2 = new String(buffer, charSet);
                    txtTagLigne2.setText(tagL2);

                    i = 42;

                    buffer = new byte[18];
                    iBuffer = 0;

                    while (userdata[i] != 0x00)
                    {
                        buffer[iBuffer] = userdata[i];
                        i++;
                        iBuffer++;
                    }
                    String tagL3 = new String(buffer, charSet);
                    txtTagLigne3.setText(tagL3);

                    i = 60;

                    buffer = new byte[18];
                    iBuffer = 0;

                    while (userdata[i] != 0x00)
                    {
                        buffer[iBuffer] = userdata[i];
                        i++;
                        iBuffer++;
                    }
                    String tagL4 = new String(buffer, charSet);
                    txtTagLigne4.setText(tagL4);

                    i = 78;

                    buffer = new byte[18];
                    iBuffer = 0;

                    while (userdata[i] != 0x00)
                    {
                        buffer[iBuffer] = userdata[i];
                        i++;
                        iBuffer++;
                    }
                    String tagL5 = new String(buffer, charSet);
                    txtTagLigne5.setText(tagL5);

                    /*** FIN - DECORTICAGE DE LA TRAME ***/

                    tagReaded = true;

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Tag illisible", Toast.LENGTH_SHORT).show();
                    tagReaded = false;
                    return;
                }

                try {
                    nfcvTag.close();
                } catch (IOException e) {
                    return;
                }
            }
        }
    }

    private void writeTagData(Tag tag) {

        int i = 0;
        int indexBuffer = 0;

        byte[] message = new byte[100];

        int codageAcces = 0;

        if (tglAcces1.isChecked()) codageAcces += 1;
        if (tglAcces2.isChecked()) codageAcces += 2;
        if (tglAcces3.isChecked()) codageAcces += 4;
        if (tglAcces4.isChecked()) codageAcces += 8;
        if (tglAcces5.isChecked()) codageAcces += 16;
        if (tglAcces6.isChecked()) codageAcces += 32;
        if (tglAcces7.isChecked()) codageAcces += 64;
        if (tglAcces8.isChecked()) codageAcces += 128;

        message[2] = (byte)codageAcces;

        byte[] bTagL1 = (editTagLigne1.getText().toString() + "\0").getBytes(StandardCharsets.ISO_8859_1);
        byte[] bTagL2 = (editTagLigne2.getText().toString() + "\0").getBytes(StandardCharsets.ISO_8859_1);
        byte[] bTagL3 = (editTagLigne3.getText().toString() + "\0").getBytes(StandardCharsets.ISO_8859_1);
        byte[] bTagL4 = (editTagLigne4.getText().toString() + "\0").getBytes(StandardCharsets.ISO_8859_1);
        byte[] bTagL5 = (editTagLigne5.getText().toString() + "\0").getBytes(StandardCharsets.ISO_8859_1);

        indexBuffer = 6;
        for (i = 0; i < bTagL1.length; i++)
        {
            message[indexBuffer + i] = bTagL1[i];
            if (i == 16) break;
        }

        indexBuffer = 24;
        for (i = 0; i < bTagL2.length; i++)
        {
            message[indexBuffer + i] = bTagL2[i];
            if (i == 16) break;
        }

        indexBuffer = 42;
        for (i = 0; i < bTagL3.length; i++)
        {
            message[indexBuffer + i] = bTagL3[i];
            if (i == 16) break;
        }

        indexBuffer = 60;
        for (i = 0; i < bTagL4.length; i++)
        {
            message[indexBuffer + i] = bTagL4[i];
            if (i == 16) break;
        }

        indexBuffer = 78;
        for (i = 0; i < bTagL5.length; i++)
        {
            message[indexBuffer + i] = bTagL5[i];
            if (i == 16) break;
        }

        /*** CRC16 ***/

        byte[] CRC = CRC16.ComputeChecksumBytes(message, 96);

        for (i = 0; i < CRC.length; i++)
        {
            message[96 + i] = CRC[i];
        }

        for (String tech : tag.getTechList()) {

            if (tech.equals(NfcV.class.getName())) {

                // Get an instance of NfcV for the given tag:
                NfcV nfcvTag = NfcV.get(tag);

                try {
                    nfcvTag.connect();
                }
                catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Echec de connexion au Tag.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    byte[] cmd;
                    byte firstBlockAddr;

                    for(i=0; i<25; i++) // 25 blocks de 4 pour aller jusqu'au 100ème octet
                    {
                        firstBlockAddr = (byte)i;

                        // Write Single Block (0x21)
                        cmd = new byte[] {
                                (byte)0x00,        // Flags
                                (byte)0x21,        // Command: write single block
                                firstBlockAddr,    // First block (offset)
                                message[(i*4)],    // trame >> octet 0
                                message[(i*4)+1],  // trame >> octet 1
                                message[(i*4)+2],  // trame >> octet 2
                                message[(i*4)+3]   // trame >> octet 3
                        };

                        byte[] response = nfcvTag.transceive(cmd);
                    }

                    Toast.makeText(getApplicationContext(), "Tag enregistré avec succès", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "L'enregistrement du Tag a échoué", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    nfcvTag.close();
                }
                catch (IOException e) {
                    return;
                }
            }
        }


    }

    private void setAcces(int i, boolean authorise) {

        if(authorise) imgAcces[i].setBackgroundResource(R.drawable.locked_off);
        else  imgAcces[i].setBackgroundResource(R.drawable.locked_on_circle);
    }

    private void setTglAcces(int i, boolean authorise) {

        tglAcces[i].setChecked(authorise);
    }




}
