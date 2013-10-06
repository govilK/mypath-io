package com.gps;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.gps.maps.CustomItemizedOverlay;
import com.gps.utils.Settings;

public class CopyOfDrawOverlayActivity extends MapActivity {

	private MapView mapView;
	public List mapOverlays = null;
	MapController mapController;
	String pathName = "";

	private static final double latitudeE6 = 12.92992478;
	private static final double longitudeE6 = 77.62401319;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpsmap);
		List<GeoPoint> geoList = new ArrayList<GeoPoint>();
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);

		mapOverlays = mapView.getOverlays();

		 Drawable drawable =
		 this.getResources().getDrawable(R.drawable.ic_launcher);
		 CustomItemizedOverlay itemizedOverlay =
		 new CustomItemizedOverlay(drawable, this);
		//
		// create geolist
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		pathName = extras.getString("pathName");
		String points = Settings.getStringProperty(pathName, "",
				getApplicationContext());
		System.out.println("pathName in drawoverlay: " + pathName + " points: " + points);
		String pointsList[] = points.split("\\|");
		ArrayList<GeoPoint> locObj = new ArrayList<GeoPoint>();
		for (int j = 0; j < pointsList.length; j++) {
			String[] latLong = pointsList[j].split(",");
			String lat = latLong[0];
			String lont = latLong[1];
			locObj.add(new GeoPoint((int) (Double.parseDouble(lat) * 1E6),
					(int) (Double.parseDouble(lont) * 1E6)));

		}
		int j = 0;
		for (int i = 1; i < locObj.size(); i++) {
			mapOverlays.add(new MyMapOverlay(locObj.get(j), locObj.get(i)));
			j = i;
		}

		/*
		 * final int lat = (int) (latitudeE6 * 1E6); final int lot = (int)
		 * (longitudeE6 * 1E6); GeoPoint point = new GeoPoint(lat, lot);
		 * geoList.add(point);
		 * 
		 * final int lat1 = (int) (12.92999289*1E6); final int lot1 = (int)
		 * (77.62394153*1E6); GeoPoint point1 = new GeoPoint(lat1, lot1);
		 * geoList.add(point);
		 * 
		 * final int lat2 = (int) (12.9301252*1E6); final int lot2 = (int)
		 * (77.62362553*1E6); GeoPoint point2 = new GeoPoint(lat2, lot2);
		 * geoList.add(point2);
		 * 
		 * final int lat3 = (int) (12.93042286*1E6); final int lot3 = (int)
		 * (77.62293666*1E6); GeoPoint point3 = new GeoPoint(lat3, lot3);
		 * geoList.ad	d(point3);
		 *  
		 * final int lat4 = (int) (12.93043827*1E6); final int lot4 = (int)
		 * (77.62282671*1E6); GeoPoint point4 = new GeoPoint(lat4, lot4);
		 * geoList.add(point4);
		 * 
		 * final int lat5 = (int) (12.93080925*1E6); final int lot5 = (int)
		 * (77.62300282*1E6); GeoPoint point5 = new GeoPoint(lat5, lot5);
		 * geoList.add(point5);
		 * 
		 * 
		 * //add into mapOverlays mapOverlays.add(new MyMapOverlay(point,
		 * point1)); mapOverlays.add(new MyMapOverlay(point1, point2));
		 * mapOverlays.add(new MyMapOverlay(point2, point3));
		 * mapOverlays.add(new MyMapOverlay(point3, point4));
		 * mapOverlays.add(new MyMapOverlay(point4, point5));
		 */
		// plot that one by one.

		mapController = mapView.getController();

		// if(locObj.size()>0)
		mapController.animateTo(locObj.get(0));
		// mapController.animateTo(point);

		mapController.setZoom(18);

		 OverlayItem overlayitem =
		 new OverlayItem(locObj.get(0), "Hello", "I'm in Athens, Greece!");
		
		 itemizedOverlay.addOverlay(overlayitem);

		 mapOverlays.add(itemizedOverlay);
		 mapView.invalidate();

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public class MyMapOverlay extends Overlay implements OnGestureListener {

		GeoPoint gp1;
		GeoPoint gp2;
		private Context mContext;
		private GestureDetector gestureDetector = new GestureDetector(
				getApplicationContext(), this);
		private OnGestureListener onGestureListener;
		
		 public MyMapOverlay() {
			  gestureDetector = new GestureDetector(this);
			 }

		public MyMapOverlay(OnGestureListener onGestureListener) {
		  this();
		  setOnGestureListener(onGestureListener);
		}

		public MyMapOverlay(GeoPoint g1, GeoPoint g2) {
			gp1 = g1;
			gp2 = g2;
		}

		public MyMapOverlay(Context ctx) {
			mContext = ctx;
		}

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,	long when) {
			Projection projection = mapView.getProjection();

			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setDither(true);
			Point point = new Point();
			projection.toPixels(gp1, point);

			paint.setColor(Color.RED);
			paint.setStrokeWidth(2);
			// paint.setARGB(255, 255, 255, 255);
			paint.setStyle(Paint.Style.FILL);
			// paint.setStrokeWidth(1);
			// paint.setARGB(255, 255, 255, 255);
			// paint.setStyle(Paint.Style.STROKE);
			Point point2 = new Point();
			projection.toPixels(gp2, point2);
			paint.setStrokeWidth(5);
			paint.setAlpha(120);
			canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);

			return super.draw(canvas, mapView, shadow, when);
		}
		
	/*	@Override
		 public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		  if (gestureDetector.onTouchEvent(event)) {
		   return true;
		  }
		  return false;
		 }*/

		 @Override
		 public boolean onDown(MotionEvent e) {
		  if (onGestureListener != null) {
		   return onGestureListener.onDown(e);
		  }
		  return false;
		 }

		 @Override
		 public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		   float velocityY) {
		  if (onGestureListener != null) {
		   return onGestureListener.onFling(e1, e2, velocityX, velocityY);
		  }
		  return false;
		 }


		 @Override
		 public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
		   float distanceY) {
		  if (onGestureListener != null) {
		   onGestureListener.onScroll(e1, e2, distanceX, distanceY);
		  }
		  return false;
		 }

		 @Override
		 public void onShowPress(MotionEvent e) {
		  if (onGestureListener != null) {
		   onGestureListener.onShowPress(e);
			Toast.makeText(
					getApplicationContext(),
					"show", Toast.LENGTH_SHORT)
					.show();
		  }
		 }

		 @Override
		 public boolean onSingleTapUp(MotionEvent e) {
		  if (onGestureListener != null) {
		   onGestureListener.onSingleTapUp(e);
		  }
		  return false;
		 }

		 public boolean isLongpressEnabled() {
		  return gestureDetector.isLongpressEnabled();
		 }

		 public void setIsLongpressEnabled(boolean isLongpressEnabled) {
		  gestureDetector.setIsLongpressEnabled(isLongpressEnabled);
		 }

		 public OnGestureListener getOnGestureListener() {
		  return onGestureListener;
		 }

		 public void setOnGestureListener(OnGestureListener onGestureListener) {
		  this.onGestureListener = onGestureListener;
		 }

		public boolean onTouchEvent(MotionEvent me, MapView mapView) {
			// //---when user lifts his finger---
			// float pressure = event.getPressure();
			// // if(pressure > 1)
			// {
			// if (event.getAction() == 1) {
			// GeoPoint pp = mapView.getProjection().fromPixels(
			// (int) event.getX(),
			// (int) event.getY());
			// Toast.makeText(getBaseContext(),
			// pp.getLatitudeE6() / 1E6 + "," +
			// pp.getLongitudeE6() /1E6 + "," + pressure ,
			// Toast.LENGTH_SHORT).show();
			// }
			// }
			// return false;
			this.gestureDetector.onTouchEvent(me);
			Toast.makeText(
					getApplicationContext(),
					"touch", Toast.LENGTH_SHORT)
					.show();
			return super.onTouchEvent(me, mapView);
		}

		// public boolean onTap(GeoPoint p, MapView mapView)
		// {
		// mapOverlays = mapView.getOverlays();
		// // drawable =getResources().getDrawable(R.drawable.ic_launcher);
		// // itemizedOverlay = new SitesOverlay(drawable);
		// int lat=(int)p.getLatitudeE6();
		// int lng=(int)p.getLongitudeE6();
		//
		//
		// GeoPoint point = new GeoPoint(lat,lng);
		// // Log.d("tap event ", "tapcalled"+lat+""+lng);
		// OverlayItem overlayitem = new OverlayItem(point, "", "");
		// Toast.makeText(getBaseContext(),
		// p.getLatitudeE6() / 1E6 + "," +
		// p.getLongitudeE6() /1E6 ,
		// Toast.LENGTH_SHORT).show();
		// // items.add(overlayitem);
		// // populate();
		// // Log.d("tap event ", "populated");
		// // mapOverlays.add(itemizedOverlay);
		//
		//
		// return true;
		// }

		 @Override
		 public void onLongPress(MotionEvent event) {
		  if (onGestureListener != null) {
		   onGestureListener.onLongPress(event);

			// TODO Auto-generated method stub

			// ---when user lifts his finger---
			float pressure = event.getPressure();
			// if(pressure > 1)
			{
				// if (event.getAction() == 1) {
				GeoPoint pp = mapView.getProjection().fromPixels(
						(int) event.getX(), (int) event.getY());
				Toast.makeText(
						getApplicationContext(),
						pp.getLatitudeE6() / 1E6 + "," + pp.getLongitudeE6()
								/ 1E6 + "," + pressure, Toast.LENGTH_SHORT)
						.show();
				mapController.animateTo(pp);
				// }
			}
		
		  }
		 }

	}

}
