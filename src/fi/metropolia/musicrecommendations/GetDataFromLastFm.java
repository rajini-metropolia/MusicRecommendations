package fi.metropolia.musicrecommendations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetDataFromLastFm {

	static final int HTTP_STATUS_OK = 200;
	static byte[] buff = new byte[1024];
	
	public GetDataFromLastFm(){		
	}
	
	 protected static synchronized String downloadFromServer(String url){
			String retval = null;
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url); 
			
			try {
				HttpResponse response = httpClient.execute(request);
				StatusLine status  = response.getStatusLine();
				if(status.getStatusCode() != HTTP_STATUS_OK) {
					System.out.println("reslut form LastFM: " + status.toString());
				}
				
				HttpEntity entity = response.getEntity();
				InputStream ist = entity.getContent();
				ByteArrayOutputStream content = new ByteArrayOutputStream();
				
				int readCount  = 0;
				while((readCount = ist.read(buff) ) != -1){
					content.write(buff, 0, readCount);
				}

				retval = new String(content.toByteArray());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	    	return retval;
	    }
	
}
