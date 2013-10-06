package com.gps.maps;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MyMapOverlay1 extends Overlay{
	
	GeoPoint gp1;
	GeoPoint gp2;
	private Context mContext ;

	public MyMapOverlay1(GeoPoint g1, GeoPoint g2)
	{
		gp1 = g1;
		gp2 = g2;
	}
	
	public MyMapOverlay1(Context ctx)
	{
		mContext = ctx;
	}
@Override
	public boolean draw (Canvas canvas, MapView mapView, boolean shadow, long when)
	{
	    Projection projection = mapView.getProjection();
	   
	        Paint paint = new Paint();
	        paint.setAntiAlias(true);
	        paint.setDither(true);
	        Point point = new Point();
	        projection.toPixels(gp1, point);

	        	paint.setColor(Color.RED);
	        	paint.setStrokeWidth(2);
//	            paint.setARGB(255, 255, 255, 255);
	            paint.setStyle(Paint.Style.FILL);
//	        	paint.setStrokeWidth(1);
//	            paint.setARGB(255, 255, 255, 255);
//	            paint.setStyle(Paint.Style.STROKE);
	            Point point2 = new Point();
	            projection.toPixels(gp2, point2);
	            paint.setStrokeWidth(5);
	            paint.setAlpha(120);
	            canvas.drawLine(point.x, point.y, point2.x,point2.y, paint);
	    
	    return super.draw(canvas, mapView, shadow, when);
	}

	public boolean onTouchEvent(MotionEvent event, MapView mapView) 
	{   
	    //---when user lifts his finger---
	    if (event.getAction() == 1) {                
	        GeoPoint pp = mapView.getProjection().fromPixels(
	            (int) event.getX(),
	            (int) event.getY());
	            Toast.makeText(mContext, 
	                pp.getLatitudeE6() / 1E6 + "," + 
	                pp.getLongitudeE6() /1E6 , 
	                Toast.LENGTH_SHORT).show();
	    }                            
	    return false;
	} 


}
