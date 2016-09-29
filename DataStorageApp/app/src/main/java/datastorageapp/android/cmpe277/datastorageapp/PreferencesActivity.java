package datastorageapp.android.cmpe277.datastorageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreferencesActivity extends AppCompatActivity {
    public final static String STORE_PREFERENCES="storePrefFinal.txt";
    public int counter=0;
    private SimpleDateFormat s=new SimpleDateFormat("MM/dd/yyyy-hh:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        counter=sharedPrefs.getInt("COUNTER", 0);

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        counter=sharedPrefs.getInt("COUNTER", 0);

    }

    public void save(View v) {
        EditText name_text=(EditText)findViewById(R.id.book_name_id);
        String name=name_text.getText().toString();
        EditText author_text=(EditText)findViewById(R.id.book_author_id);
        String author=author_text.getText().toString();
        EditText des_text=(EditText)findViewById(R.id.description);
        String description=des_text.getText().toString();

        if(name!=null && author!=null && description!=null)
        {
            try
            {
                counter+=1;

                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
                Editor editor=sharedPreferences.edit();
                editor.putInt("COUNTER", counter);
                editor.commit();

                OutputStreamWriter out=new OutputStreamWriter(openFileOutput(STORE_PREFERENCES,MODE_APPEND));
                String message="\nSaved Preference "+counter+", "+s.format(new Date());
                out.write(message);
                out.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void cancel(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
