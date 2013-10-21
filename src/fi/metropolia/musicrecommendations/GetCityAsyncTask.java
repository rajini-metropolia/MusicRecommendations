package fi.metropolia.musicrecommendations;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

public class GetCityAsyncTask extends AsyncTask<String, String, String> {

	 private TopTracksActivity activity;
	 private double latitude;
	 private double longitude;

	 public GetCityAsyncTask(TopTracksActivity activity, double latitude, double longitude) {
	     // TODO Auto-generated constructor stub
	     this.activity = activity;
	        this.latitude = latitude;
	        this.longitude = longitude;
	    }
	
	
	@Override
	protected String doInBackground(String... params) {
		String result = null;
        Geocoder geocoder = new Geocoder(activity, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if(addresses.size() > 0)
            	result = addresses.get(0).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
	}


	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
	
	

}
