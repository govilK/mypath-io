package com.gps.maps;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class GeoPointsList {

	List<GeoPoint> geoList = new ArrayList<GeoPoint>();

	public GeoPointsList(List<GeoPoint> g)
	{
		geoList =g;
	}
	
	public List<GeoPoint> getGeoList() {
		return geoList;
	}

	public void setGeoList(List<GeoPoint> geoList) {
		this.geoList = geoList;
	}
	
	
}
