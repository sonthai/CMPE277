package datastorageapp.android.cmpe277.datastorageapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            InputStream in = openFileInput(PreferencesActivity.STORE_PREFERENCES);
            if (in !=null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader =  new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n");
                }
                in.close();
                TextView savePref = (TextView) findViewById(R.id.save_data);
                savePref.setText(buf.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPreferences(View v) {
        Intent preferencesIntent = new Intent(MainActivity.this, PreferencesActivity.class);
        startActivity(preferencesIntent);
    }

    public void createSQLiteDB(View v) {
        Intent sqliteIntent = new Intent(this, SQLiteActivity.class);
        startActivity(sqliteIntent);

    }

    public void close(View view) {
        this.finish();
        System.exit(0);
    }
}
