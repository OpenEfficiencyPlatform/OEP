package com.psdconsulting;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.mule.util.Base64;



// Base REST caller used for web service interactions

public class RESTCaller {
	
	public static String Credentials()
         {
        	
        	Properties prop = new Properties();
        	try {
    			ClassLoader cl = Thread.currentThread().getContextClassLoader();
    			prop.load(cl.getResourceAsStream("warmfmintegration.properties"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	String user = prop.getProperty("fm.username").toString();
        	String password = prop.getProperty("fm.password").toString();
        	
        	String authString = user + ":" + password;
		    String authStringEnc = null;
			try {
				authStringEnc = Base64.encodeBytes(authString.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return authStringEnc;
      }

	public static String send(String fullUrl, String method, byte[] data, HashMap<String, String> additionalHeaders)
		throws MalformedURLException, IOException
	{
			Logger l = Logger.getRootLogger();
			
			URL url = null;
			url = new URL(fullUrl);

			HttpURLConnection req = null;
			
			req = (HttpURLConnection)url.openConnection();
						
				l.debug("Sending " + method + " to " + fullUrl + " with data: " + data);
				req.setRequestProperty("Content-Type", "application/json");
				req.setRequestMethod(method);
				
				req.setRequestProperty("Authorization", "Basic " + Credentials());
				
				if (null != additionalHeaders)
		        {
		        	for (String key : additionalHeaders.keySet())
		        		req.setRequestProperty(key, additionalHeaders.get(key));
		        }
				
		        if (method == "POST" || method == "PUT") {
					req.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(req.getOutputStream());
					if (data != null && data.length > 0)
						wr.write(data);
				    wr.flush();
				    wr.close();
		        }
		                
				String response = req.getResponseMessage();
				l.debug("Response from " + fullUrl + ": " + response);
				if (response.equals("OK"))
				{
					String xmlStringResults = "";
					BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) 
						xmlStringResults = xmlStringResults + inputLine; 
					in.close();
					l.debug("Received data: " + xmlStringResults);
				    return xmlStringResults;
				}
				
				if (response.equals("Created"))
				{
					String xmlStringResults = "";
					BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) 
						xmlStringResults = xmlStringResults + inputLine; 
					in.close();
					l.debug("Received data: " + xmlStringResults);
				    return xmlStringResults;
				}
				
				req.disconnect();
				return response;
			
	}

}


