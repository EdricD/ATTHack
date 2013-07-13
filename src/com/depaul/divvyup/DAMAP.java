package com.depaul.divvyup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;


import com.google.android.gms.internal.d;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 * <p>
 * Notice how we deal with the possibility that the Google Play services APK is not
 * installed/enabled/updated on a user's device.
 */
public class DAMAP extends FragmentActivity {
    /**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;
    private ArrayList<Attraction> attractions;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damap);
        
        setUpMapIfNeeded();
        
        CameraUpdate center=
        		CameraUpdateFactory.newLatLng(new LatLng(41.883112, -87.621845));
        		CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        		mMap.moveCamera(center);
        		mMap.animateCamera(zoom);
        
        InputStream inputStream = this.getResources().openRawResource(R.raw.data_attractions);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

         try {
           while (( line = buffreader.readLine()) != null) {
               text.append(line);
               text.append('\n');
             }
       } catch (IOException e) {
           Log.d("DOES THIS WORK?", "NO");
       }
         Log.d("DOES THIS WORK?", text.toString());
         
         attractions = new ArrayList<Attraction>();
         String[] dataLst = text.toString().split("\n");
         
         Geocoder g = new Geocoder(this, Locale.getDefault());
         
 		for(int i = 0; i < 75; i++){
 			System.out.println(i);
 			String[] OneAttraction = dataLst[i].split(";");
 			//attractions.add(new Attraction(OneAttraction[1],OneAttraction[2],OneAttraction[3],OneAttraction[4],OneAttraction[5],OneAttraction[0]));
 			
 			try {
				List<Address> addrs = g.getFromLocationName(OneAttraction[3], 5);
				if (addrs.size() > 0) {
				Log.d("FUCK", "ONE");
				Double currentLat = addrs.get(0).getLatitude();
                Double currentLon = addrs.get(0).getLongitude();
                mMap.addMarker(new MarkerOptions().position(new LatLng(currentLat, currentLon)).title(OneAttraction[1]).snippet(OneAttraction[2]));
                Log.d("OH SHIT MAN ITS A LAT", ""+currentLat);
                Log.d("OH SHIT MAN ITS A LON", ""+currentLon);
				}
			} catch (IOException e) {
				Log.d("WHAT THE FUCK IS HAPPENING", e.toString());
				// TODO Auto-generated catch block
				Log.d("FUCKING", "BROKEN" + OneAttraction[3]);
			}
 		
 		}
 		
 		
 // TEM'S CODE
 		
 		
        InputStream inputStreamBIKES= this.getResources().openRawResource(R.raw.stations);

        InputStreamReader inputreaderBIKES = new InputStreamReader(inputStreamBIKES);
        BufferedReader buffreaderBIKES= new BufferedReader(inputreaderBIKES);
        String lineBIKES;
        StringBuilder textBIKES = new StringBuilder();

        try {
	        while (( line = buffreaderBIKES.readLine()) != null) {
	            String[] temp = line.split(", ");
	            Log.d("Station: ", temp[0] + " " + temp[1] + " " + temp[2]);
	            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(temp[1]), Double.parseDouble(temp[2]))).title(temp[0]).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	            
	        }
        } catch (IOException e) {
            Log.d("DOES THIS WORK?", e.toString());
        }
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and 
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not have been
     * completely destroyed during this process (it is likely that it would only be stopped or
     * paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in
     * {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
