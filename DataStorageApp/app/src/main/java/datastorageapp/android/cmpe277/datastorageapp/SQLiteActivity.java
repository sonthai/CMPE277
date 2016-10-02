package datastorageapp.android.cmpe277.datastorageapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteActivity extends AppCompatActivity {
    public int counter=0;
    private SimpleDateFormat s=new SimpleDateFormat("MM/dd/yyyy-hh:mm a");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        counter=sharedPrefs.getInt("SQL_COUNTER", 0);

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        counter=sharedPrefs.getInt("SQL_COUNTER", 0);

    }

    public void save(View v) {
        EditText editText = (EditText) findViewById(R.id.blog_msg_id);
        String message = editText.getText().toString();
        DataController dataController=new DataController(getBaseContext());
        dataController.open();
        long retValue= dataController.insert(message);
        dataController.close();

        if (retValue != -1) {
            Context context = getApplicationContext();
            CharSequence text=getString(R.string.save_success_msg);
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();

            try
            {
                counter+=1;

                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
                Editor editor=sharedPreferences.edit();
                editor.putInt("SQL_COUNTER", counter);
                editor.commit();

                OutputStreamWriter out=new OutputStreamWriter(openFileOutput(PreferencesActivity.STORE_PREFERENCES,MODE_APPEND));
                out.write("\nSQLite "+counter+", "+s.format(new Date()));
                out.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void cancel(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
