package fi.metropolia.musicrecommendations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MusicRecommendations extends Header {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setHeader(getString(R.string.app_name), true, true);
	}
	
	
	public void onFeatureBtnClick(View v) {
		Intent intent;			 
		switch (v.getId()) {
		case R.id.top_tracks_btn:
			System.out.println("I came here boss");
			intent = new Intent(this, TopTracksActivity.class);
			startActivity(intent);
			break;
		default:
			break;
			
		}
	}

	
}
