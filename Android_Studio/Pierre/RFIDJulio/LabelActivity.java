package fr.thomashourdin.www.ihmrfidcostumes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class LabelActivity extends ActionBarActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;

    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String MY_UNIQUE_PREFERENCE_FILE = "RFIDCostumesPreferences";

    private EditText editRole1;
    private EditText editRole2;
    private EditText editRole3;
    private EditText editRole4;
    private EditText editRole5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        preferences = getSharedPreferences(MY_UNIQUE_PREFERENCE_FILE, PREFERENCE_MODE_PRIVATE);
        preferencesEditor = preferences.edit();

        editRole1 = (EditText)findViewById(R.id.editRole1);
        editRole2 = (EditText)findViewById(R.id.editRole2);
        editRole3 = (EditText)findViewById(R.id.editRole3);
        editRole4 = (EditText)findViewById(R.id.editRole4);
        editRole5 = (EditText)findViewById(R.id.editRole5);

        editRole1.setText(preferences.getString("role1", getResources().getString(R.string.label_ligne_1)));
        editRole2.setText(preferences.getString("role2", getResources().getString(R.string.label_ligne_2)));
        editRole3.setText(preferences.getString("role3", getResources().getString(R.string.label_ligne_3)));
        editRole4.setText(preferences.getString("role4", getResources().getString(R.string.label_ligne_4)));
        editRole5.setText(preferences.getString("role5", getResources().getString(R.string.label_ligne_5)));
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

        preferencesEditor.putString("role1", editRole1.getText().toString());
        preferencesEditor.putString("role2", editRole2.getText().toString());
        preferencesEditor.putString("role3", editRole3.getText().toString());
        preferencesEditor.putString("role4", editRole4.getText().toString());
        preferencesEditor.putString("role5", editRole5.getText().toString());

        preferencesEditor.commit();

        this.finish();
    }
}
