package com.gps.maps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class CustomItemizedOverlay  extends ItemizedOverlay<OverlayItem>{

	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	private Context context;
	
	public CustomItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	public CustomItemizedOverlay(Drawable defaultMarker, Context context) {
		        this(defaultMarker);
		        this.context = context;
	}

	
	@Override
	protected OverlayItem createItem(int i) {
		return mapOverlays.get(i);
	}

	@Override
	public int size() {
		return mapOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
	      OverlayItem item = mapOverlays.get(index);
	      AlertDialog.Builder dialog = new AlertDialog.Builder(context);
	      dialog.setTitle(item.getTitle());
	      dialog.setMessage(item.getSnippet());
	      dialog.show();
	      return true;
	}
	
	public void addOverlay(OverlayItem overlay) {
		  mapOverlays.add(overlay);
		  this.populate();
	}



}
