package assignment.cmpe277.activitylifecycle;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int threadCounter = 0;
    private TextView threadCounterView;
    private String THREAD_COUNTER_VALUE = "thread_counter_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        threadCounterView = (TextView) findViewById(R.id.threadCounter);

        Log.d("onCreate() ", String.valueOf(threadCounter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startActivityB(View v) {
        Intent intent = new Intent(MainActivity.this, ActivityB.class);
        startActivity(intent);
    }

    public void popDialog(View v) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.content_dialog);
        Button closeDialog = (Button) dialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        //Intent intent = new Intent(MainActivity.this, DialogActivity.class);
        //startActivity(intent);
    }

    public void closeApp(View v) {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart() ", String.valueOf(threadCounter));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Before in OnResume()", String.valueOf(threadCounter));
        threadCounter++;
        threadCounterView.setText(String.valueOf(threadCounter));
        Log.d("After inc OnResume()", String.valueOf(threadCounter));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause() ", String.valueOf(threadCounter));
        //threadCounter++;
        //Log.d("After inc onPause() ", String.valueOf(threadCounter));
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("onStop() ", String.valueOf(threadCounter));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroy()", String.valueOf(threadCounter));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(THREAD_COUNTER_VALUE, threadCounter);
        Log.d("OnSaveInstanceState()", String.valueOf(threadCounter));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        threadCounter = savedInstanceState.getInt(THREAD_COUNTER_VALUE);
        Log.d("OnRestoreInstance()", String.valueOf(threadCounter));
    }

}
