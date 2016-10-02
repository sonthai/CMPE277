package servicesapp.android.cmpe277.servicesapp;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService {
		
	public MyIntentService() {
        super("MyIntentServiceName");
    }	
	
    @Override 
	protected void onHandleIntent(Intent intent) {
			Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
			URL[] urls = new URL[objUrls.length];
			for (int i = 0; i < objUrls.length - 1; i++) {
				int result = DownloadFile((URL) objUrls[i]);
				Log.d("IntentService", "Downloaded " + result + " bytes");
				//---send a broadcast to inform the activity
				// that the file has been downloaded---
				Intent broadcastIntent = new Intent();
				broadcastIntent.setAction("FILE_DOWNLOADED_ACTION");
				getBaseContext().sendBroadcast(broadcastIntent);
			}

		stopSelf();
	}

	private int DownloadFile(URL url) {		
		try {
			//---simulate taking some time to download a file---
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 100;
	}	
}