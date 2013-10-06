package com.gps;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gps.utils.Settings;

public class Mypaths extends Activity {

	TextView textView;
	ListView list;
	ListView subList;
	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	public ArrayList<String> pathRowList = new ArrayList<String>();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypaths);

		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mainListView);

		String pathList = Settings.getStringProperty(Settings.PATH_NAME_LIST,
				"", getApplicationContext());

		String[] params = pathList.split(",");
		pathRowList.addAll(Arrays.asList(params));

		listAdapter = new ArrayAdapter<String>(this, R.layout.path_row,
				pathRowList);
		mainListView.setAdapter(listAdapter);
		mainListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + position, Toast.LENGTH_LONG)
						.show();
				Intent i = new Intent(getApplicationContext(), CopyOfDrawOverlayActivity.class);
		    	i.putExtra("pathName", pathRowList.get(position));
			startActivity(i);
			}
		});
	}
}
