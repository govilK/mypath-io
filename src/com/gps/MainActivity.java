package com.gps;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gps.utils.Settings;

public class MainActivity extends Activity {

	TextView latLong;
	public List<LocationObject> latLongList = new ArrayList<LocationObject>();
	public static int count = 0;
	public boolean gps_enabled = false;
	public boolean nw_enabled = false;
	LocationManager locManager;
	LocationManager locationManager;
	LocationListener locationListener ;

	public void intializeListner()
	{
	 locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {

			if (location != null) {
				System.out.println("updates location: "
						+ location.getLatitude() + " : "
						+ location.getLongitude());
				latLongList.add(new LocationObject(location));
			} else {
				System.out.println("updates location: is null ");
			}

			Toast.makeText(
					MainActivity.this,
					"data: " + location.getLatitude() + " : "
							+ location.getLongitude(), Toast.LENGTH_LONG)
					.show();
		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(MainActivity.this,"Your provider sucks. Try Again !!", Toast.LENGTH_LONG).show();
			stopTrace(null);
			saveTempLocationUpdates(latLongList);
		}

		public void onProviderEnabled(String provider) {
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}
	};
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		count = 0;
		latLongList.clear();
	}

	// @Override
	// protected void onResume() {
	// super.onResume();
	// locManager.requestLocationUpdates(provider, 5000, 10, this);
	// }


	private void saveTempLocationUpdates(List<LocationObject> tempLocationList) {
		StringBuilder str = new StringBuilder();
		int i = tempLocationList.size() - 1;
		while (i >= 0) {
			str.append(tempLocationList.get(i).getLoc().getLatitude() + ","
					+ tempLocationList.get(i).getLoc().getLongitude() + "|");
			i--;
		}
		String pathList = Settings.getStringProperty(Settings.PATH_NAME_LIST, "", getApplicationContext());
		String pathName= "";
		if(pathList != "")
		{
			String[] params = pathList.split(",");
			int size=  params.length;
			pathName = "path-"+size++;
			pathList = pathList + "," + pathName;
			Settings.setProperty(Settings.PATH_NAME_LIST, pathList, getApplicationContext());
			Settings.setProperty(pathName, str.toString(), getApplicationContext());
		}
		else
		{
			pathName = "path-1";
			pathList = pathName;
			Settings.setProperty(Settings.PATH_NAME_LIST, pathList, getApplicationContext());
			Settings.setProperty(pathName, str.toString(), getApplicationContext());
		}
		Toast.makeText(MainActivity.this,"Saved !!", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String context = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(context);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
//		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			System.out.println("location: " + location.getLatitude() + " : "
					+ location.getLongitude());
			latLongList.add(new LocationObject(location));
		} else {
			System.out.println("location: is null ");
		}
		System.out.println("provider: " + provider);
		if(locationListener != null)
		{
			locationListener.toString();
		}
		else
		{
			System.out.println("locationListener is null so initializing...");
			intializeListner();
		}
		locationManager.requestLocationUpdates(provider, 10000, 1,
				locationListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void stopTrace(View view) {
		locationManager.removeUpdates(locationListener);
		locationManager  = null;
		saveTempLocationUpdates(latLongList);
	}

}

class LocationObject {

	Location loc;

	public LocationObject(Location l) {
		// TODO Auto-generated constructor stub
		loc = l;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	@Override
	public boolean equals(Object o) {
		LocationObject l = (LocationObject) o;
		if (this.loc.getLatitude() == l.getLoc().getLatitude()
				&& this.loc.getLongitude() == l.getLoc().getLongitude())
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + 9;
		hash = 31
				* hash
				+ (int) (this.loc.getLatitude() * 11 + this.loc.getLongitude() * 31);
		return hash;
	}
}
