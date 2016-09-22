package farmmaintenanceapp.android.cmpe277.farmmaintenanceapp;

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
import android.widget.TextView;

public class FarmMaintenanceActivity extends AppCompatActivity {
    private TextView fanTextView = null;
    private TextView sprinklerTextView = null;
    private Receiver receiver = null;
    private String fanStatus = "OFF", sprinklerStatus = "OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_maintenance);
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
        fanTextView = (TextView) findViewById(R.id.fanTextId);
        sprinklerTextView = (TextView) findViewById(R.id.sprinklerTextId);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.FANON");
        intentFilter.addAction("android.intent.action.FANONOFF");
        intentFilter.addAction("android.intent.action.FANSPRINKLERON");
        intentFilter.addAction("android.intent.action.FANSPRINKLERONOFF");
        receiver = new Receiver();
        registerReceiver(receiver, intentFilter);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.FANON") ||
                    intent.getAction().equals("android.intent.action.FANSPRINKLERON")) {
                fanTextView.setText("FAN ON");
            }

            if (intent.getAction().equals("android.intent.action.FANSPRINKLERON")) {
                sprinklerTextView.setText("SPRINKLER ON");
            }

            if (intent.getAction().equals("android.intent.action.FANONOFF") ||
                    intent.getAction().equals("android.intent.action.FANSPRINKLERONOFF")) {
                if (fanStatus.equals("ON")) {
                    fanStatus = "OFF";
                } else {
                    fanStatus = "ON";
                }
                fanTextView.setText("FAN " + fanStatus);
            }

            if (intent.getAction().equals("android.intent.action.FANSPRINKLERONOFF")) {
                if (sprinklerStatus.equals("ON")) {
                    sprinklerStatus = "OFF";
                } else {
                    sprinklerStatus = "ON";
                }

                sprinklerTextView.setText("SPRINKLER " + sprinklerStatus);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
