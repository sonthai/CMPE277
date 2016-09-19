package farmiotapp.cmpe277.sdthai.farmiotapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class TempHumidityActivity extends AppCompatActivity {
    private EditText tempText = null;
    private EditText humidText = null;

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
        } else if (temp.equals("")) {

        } else if (humid.equals("")) {

        }
    }

    public void cancelTempHumid(View v) {
        tempText.setText("");
        humidText.setText("");

    }



}
