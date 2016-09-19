package assignment.cmpe277.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launch(View v) {
        EditText urlEditText = (EditText) findViewById(R.id.url);
        String url = urlEditText.getText().toString();
        if (!url.equals("")) {
            if (!url.startsWith("https://") && !url.startsWith("http://")){
                url = "http://" + url;
            }
            Intent urlIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            urlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(urlIntent);
            finish();
        }
    }

    public void ring(View v) {
        EditText phone = (EditText) findViewById(R.id.phone);
        if (!phone.getText().toString().equals("")) {
           Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.getText().toString()));
            startActivity(phoneIntent);
        }

    }

    public void closeApp(View v) {
        finish();
    }
}
