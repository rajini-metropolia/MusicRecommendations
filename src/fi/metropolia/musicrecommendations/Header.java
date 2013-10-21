package fi.metropolia.musicrecommendations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public class Header extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
	public void setHeader(String title, boolean homeBtnVisible, boolean searchBtnVisible)
		    {
		      ViewStub stub = (ViewStub) findViewById(R.id.vsHeader);
		      View inflated = stub.inflate();
		 
		      TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
		      txtTitle.setText(title);
		 
		      Button btnHome = (Button) inflated.findViewById(R.id.homeBtn);
		      if(!homeBtnVisible)
		       btnHome.setVisibility(View.INVISIBLE);
		 
		      Button btnFeedback = (Button) inflated.findViewById(R.id.searchBtn);
		      if(!searchBtnVisible)
		       btnFeedback.setVisibility(View.INVISIBLE);
		 
		    }
	
	public void homeBtnClick(View v)
	{
	     Intent intent = new Intent(getApplicationContext(), MusicRecommendations.class);
	     intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
	     startActivity(intent);
		 
	}
	
	
	

}
