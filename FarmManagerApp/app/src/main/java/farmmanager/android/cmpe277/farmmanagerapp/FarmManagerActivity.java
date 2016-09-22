package farmmanager.android.cmpe277.farmmanagerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FarmManagerActivity extends AppCompatActivity {
    private String TEMPERATURE = "Temperature";
    private String HUMIDITY = "Humidity";
    private Receiver receiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_manager);
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

        IntentFilter intentFilter = new IntentFilter("android.intent.action.TempHumidity");
        receiver = new Receiver();
        this.registerReceiver(receiver, intentFilter);
    }

    public void turnFan(View v) {
        Intent broadcastIntent = new Intent("android.intent.action.FANONOFF");
        sendBroadcast(broadcastIntent);
    }

    public void turnFanSprinkler(View v) {
        Intent broadcastIntent = new Intent("android.intent.action.FANSPRINKLERONOFF");
        sendBroadcast(broadcastIntent);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int tempVal = intent.getExtras().getInt(TEMPERATURE);
            int humidVal = intent.getExtras().getInt(HUMIDITY);
            Intent broadcastIntent = null;
            if (tempVal > 70 && tempVal < 90) {
                broadcastIntent = new Intent("android.intent.action.FANON");
            } else if (tempVal > 90 && tempVal < 125) {
                broadcastIntent = new Intent("android.intent.action.FANSPRINKLERON");
            }
            sendBroadcast(broadcastIntent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}


