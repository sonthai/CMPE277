package servicesapp.android.cmpe277.servicesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText pdf1, pdf2, pdf3, pdf4, pdf5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        pdf1 = (EditText) findViewById(R.id.pdf1_id);
        pdf2 = (EditText) findViewById(R.id.pdf2_id);
        pdf3 = (EditText) findViewById(R.id.pdf3_id);
        pdf4 = (EditText) findViewById(R.id.pdf4_id);
        pdf5 = (EditText) findViewById(R.id.pdf5_id);
    }

    public void startDownload(View v) {
        String url1 = pdf1.getText().toString();
        String url2 = pdf2.getText().toString();
        String url3 = pdf3.getText().toString();
        String url4 = pdf4.getText().toString();
        String url5 = pdf5.getText().toString();
    }
}
