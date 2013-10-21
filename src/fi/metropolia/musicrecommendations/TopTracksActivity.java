package fi.metropolia.musicrecommendations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TopTracksActivity extends Activity  implements LocationListener{

	private ListView trackList;
	private String provider;
	private LocationManager manager;
	
	public static class MyViewHolder{
    	public TextView trackName;
    	public Track track;
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_tracks);
        trackList = (ListView)findViewById(R.id.trackListView);
        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = manager.getBestProvider(criteria, false);
		manager.getLastKnownLocation(provider);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);         
    }
    
    
    @Override
   	protected void onResume() {
   		super.onResume();
   		manager.requestLocationUpdates(provider, 20000, 1, (LocationListener) this);
   	}

   	@Override
   	protected void onPause() {
   		super.onPause();
   		manager.removeUpdates(this);
   	}
	
    public void setTracks(ArrayList<Track> tracks){
    	String [] track_name_list = new String[tracks.size()];
    	for(int i = 0 ; i< tracks.size(); i++){
    		track_name_list[i] = tracks.get(i).getName();
    		System.out.println("track_name_list[" + i + "] : "+ tracks.get(i).getName() );
    	}
    	
    	System.out.println("track size: " + track_name_list.length);
    	trackList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, track_name_list));
    	//trackList.setAdapter(new TracksAdapter(this, tracks));
   } 
        
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }	
	
	@Override
	public void onLocationChanged(Location location) {
       String cityName = null;
       
       double longitude = location.getLongitude();
       double latitude = location.getLatitude();
       
       Geocoder gcd = new Geocoder(getApplicationContext(), Locale.ENGLISH);
       System.out.println("gcd: " + gcd);
       List<Address> addresses;
       try {
           addresses = gcd.getFromLocation(latitude, longitude, 1);
           System.out.println("addresses: " + addresses);
           if (addresses.size() > 0) {
           	 cityName = addresses.get(0).getLocality();
           	 System.out.println("CityName: " + cityName);
           }	            
           
       } catch (IOException e) {
           e.printStackTrace();
       }      

		TopTracksAsyncTask lastfm = new TopTracksAsyncTask(TopTracksActivity.this);
		lastfm.execute("finland");
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

}
