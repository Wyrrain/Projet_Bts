package fr.thomashourdin.www.ihmrfidcostumes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AccesActivity extends ActionBarActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;

    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String MY_UNIQUE_PREFERENCE_FILE = "RFIDCostumesPreferences";

    private EditText editAcces1;
    private EditText editAcces2;
    private EditText editAcces3;
    private EditText editAcces4;
    private EditText editAcces5;
    private EditText editAcces6;
    private EditText editAcces7;
    private EditText editAcces8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acces);

        preferences = getSharedPreferences(MY_UNIQUE_PREFERENCE_FILE, PREFERENCE_MODE_PRIVATE);
        preferencesEditor = preferences.edit();

        editAcces1 = (EditText)findViewById(R.id.editAcces1);
        editAcces2 = (EditText)findViewById(R.id.editAcces2);
        editAcces3 = (EditText)findViewById(R.id.editAcces3);
        editAcces4 = (EditText)findViewById(R.id.editAcces4);
        editAcces5 = (EditText)findViewById(R.id.editAcces5);
        editAcces6 = (EditText)findViewById(R.id.editAcces6);
        editAcces7 = (EditText)findViewById(R.id.editAcces7);
        editAcces8 = (EditText)findViewById(R.id.editAcces8);

        editAcces1.setText(preferences.getString("acces1", getResources().getString(R.string.label_acces_1)));
        editAcces2.setText(preferences.getString("acces2", getResources().getString(R.string.label_acces_2)));
        editAcces3.setText(preferences.getString("acces3", getResources().getString(R.string.label_acces_3)));
        editAcces4.setText(preferences.getString("acces4", getResources().getString(R.string.label_acces_4)));
        editAcces5.setText(preferences.getString("acces5", getResources().getString(R.string.label_acces_5)));
        editAcces6.setText(preferences.getString("acces6", getResources().getString(R.string.label_acces_6)));
        editAcces7.setText(preferences.getString("acces7", getResources().getString(R.string.label_acces_7)));
        editAcces8.setText(preferences.getString("acces8", getResources().getString(R.string.label_acces_8)));
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

    public void btnValider_OnClick(View view) {

        preferencesEditor.putString("acces1", editAcces1.getText().toString());
        preferencesEditor.putString("acces2", editAcces2.getText().toString());
        preferencesEditor.putString("acces3", editAcces3.getText().toString());
        preferencesEditor.putString("acces4", editAcces4.getText().toString());
        preferencesEditor.putString("acces5", editAcces5.getText().toString());
        preferencesEditor.putString("acces6", editAcces6.getText().toString());
        preferencesEditor.putString("acces7", editAcces7.getText().toString());
        preferencesEditor.putString("acces8", editAcces8.getText().toString());

        preferencesEditor.commit();

        this.finish();
    }

}
