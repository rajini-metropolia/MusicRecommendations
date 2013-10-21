package fi.metropolia.musicrecommendations;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TopTracksAsyncTask  extends AsyncTask<String, Integer, String>{

	private TopTracksActivity activity;
	private Context context;
	private ProgressDialog prog;
	static final String lastFMgetTopTracksUrl = 
			"http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&api_key=f01fc875eedb2b6d87573722109ce5b6&format=json";
	
	public TopTracksAsyncTask(TopTracksActivity activity){
		super();
		this.activity = activity;
		this.context = this.activity.getApplicationContext();
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		prog = ProgressDialog.show(this.activity, "Wait", this.context.getResources().getString(R.string.get_tracks), true, false);
	}
	
	@Override
	protected String doInBackground(String... params) {
		String result = GetDataFromLastFm.downloadFromServer(lastFMgetTopTracksUrl + "&country=" + params[0]);
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		ArrayList<Track> trackData = new ArrayList<Track>();
		prog.dismiss();
		
		if(result.length() == 0){
			Log.i("WebApiTask", "Unable to find tracks" );
			return;
		}
		
	try {
			JSONObject resObject = new JSONObject(result);
			JSONObject topTracksObj = resObject.getJSONObject("toptracks");
			JSONArray tracks = topTracksObj.getJSONArray("track");
			for(int i = 0; i < tracks.length(); ++i){
				JSONObject track = tracks.getJSONObject(i);
				String trackName = track.getString("name");
				trackData.add(new Track(trackName));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		this.activity.setTracks(trackData);
	}

}
