package servicesapp.android.cmpe277.servicesapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText pdf1, pdf2, pdf3, pdf4, pdf5;
    IntentFilter intentFilter;
    private MyService serviceBinder;
    private URL[] urls;


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

        pdf1 = (EditText) findViewById(R.id.pdf1_id);
        pdf2 = (EditText) findViewById(R.id.pdf2_id);
        pdf3 = (EditText) findViewById(R.id.pdf3_id);
        pdf4 = (EditText) findViewById(R.id.pdf4_id);
        pdf5 = (EditText) findViewById(R.id.pdf5_id);

        // Set up for testing only
        pdf1.setText("https://www.cisco.com/web/about/ac79/docs/innov/IoE.pdf");
        pdf2.setText("http://www.cisco.com/web/about/ac79/docs/innov/IoE_Economy.pdf");
        pdf3.setText("http://www.cisco.com/web/strategy/docs/gov/everything-for-cities.pdf");
        pdf4.setText("http://www.cisco.com/web/offer/gist_ty2_asset/Cisco_2014_ASR.pdf");
        pdf5.setText("http://www.cisco.com/web/offer/emear/38586/images/Presentations/P3.pdf");


        // This intent filter is used to get the broadcase sent back from MyIntentService bounded services
        intentFilter = new IntentFilter();
        intentFilter.addAction("FILE_DOWNLOADED_ACTION");
        registerReceiver(intentReceiver, intentFilter);

    }

    public void startDownload(View v) {
        String url1 = pdf1.getText().toString();
        String url2 = pdf2.getText().toString();
        String url3 = pdf3.getText().toString();
        String url4 = pdf4.getText().toString();
        String url5 = pdf5.getText().toString();

        Intent intent = new Intent(getBaseContext(), MyService.class);

        // This is use for IntentService
        //Intent intent = new Intent(getBaseContext(), MyIntentService.class);
        try {
            urls = new URL[] {new URL(url1), new URL(url2), new URL(url3), new URL(url4), new URL(url5)};
            intent.putExtra("URLs", urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // This is unbounded service
         startService(intent);

    }

    private BroadcastReceiver  intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getBaseContext(), "File downloaded!",
                    Toast.LENGTH_LONG).show();
        }
    };
}
