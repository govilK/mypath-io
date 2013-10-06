package com.gps;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Home extends Activity {

	TextView messageTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public void showMyPath(View view) {
		Intent i = new Intent(this, Mypaths.class);
		startActivity(i);
	}

	public void startTracing(View view) {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

}
