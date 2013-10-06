package com.gps.service;

import com.gps.network.ConnectionClient;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;

public class SendLocationUpdatesService  extends IntentService
{
	 private int result = Activity.RESULT_CANCELED;
	  
	public SendLocationUpdatesService() {
		super("SendLocationUpdatesService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String data= "";
		Bundle extras = intent.getExtras();
		if (extras == null) {
			return;
		}
		String sessioncode= extras.getString("sessioncode");
		String userid = extras.getString("userid");
		String points = extras.getString("points");
		if (userid == null  || userid== "") {
			return; 
		}
		String url = "http://184.173.92.102:8200/MyPathDotIO/mypath/service" + "?sessioncode=" + sessioncode + "&userid=" + userid + "&points=" + points + "&action=savepathinfo";
		ConnectionClient conn = new ConnectionClient();
		try {
			data = conn.getJsonData(url);
			 result = Activity.RESULT_OK;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    if (extras != null) {
	      Messenger messenger = (Messenger) extras.get("MESSENGER");
	      Message msg = Message.obtain();
	      msg.arg1 = result;
	      msg.obj = (Object)data;
	      try {
	        messenger.send(msg);
	      } catch (android.os.RemoteException e1) {e1.printStackTrace();
	      }

	    }
	}

}
