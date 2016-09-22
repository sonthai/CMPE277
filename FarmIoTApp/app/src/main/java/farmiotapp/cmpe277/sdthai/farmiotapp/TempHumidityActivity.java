package farmiotapp.cmpe277.sdthai.farmiotapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TempHumidityActivity extends AppCompatActivity {
    private EditText tempText = null;
    private EditText humidText = null;
    private String TEMPERATURE = "Temperature";
    private String HUMIDITY = "Humidity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_humidity);

        tempText = (EditText) findViewById(R.id.tempEditText);
        humidText = (EditText) findViewById(R.id.HumidityEditText);
    }

    public void setTempHumid(View v) {
        String temp =  tempText.getText().toString();
        String humid = humidText.getText().toString();

        if (!(temp.equals("") || humid.equals(""))) {
            // Send Broadcast
            Intent broadcastIntent = new Intent("android.intent.action.TempHumidity");
            broadcastIntent.putExtra(TEMPERATURE, Integer.parseInt(temp));
            broadcastIntent.putExtra(HUMIDITY, Integer.parseInt(humid));
            sendBroadcast(broadcastIntent);
        } else if (temp.equals("")) {
            Toast.makeText(this, "Please enter temperature value", Toast.LENGTH_SHORT).show();
        } else if (humid.equals("")) {
            Toast.makeText(this, "Please enter humidity value", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelTempHumid(View v) {
        tempText.setText("");
        humidText.setText("");

    }



}
