package com.gps;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class mypathIO extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypath_io);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mypath_io, menu);
        return true;
    }

    

    public void onLoginClick(View view) throws Exception
    {
    	Intent i = new Intent(this, Home.class);
	
    	EditText email = (EditText)findViewById(R.id.userid);
    	//EditText password = (EditText)findViewById(R.id.password);
	
    	i.putExtra("email", email.getText().toString());
    	//i.putExtra("password", password.getText());
	
	startActivity(i);
   }
    
}
