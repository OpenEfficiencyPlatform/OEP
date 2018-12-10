package tecintegration.transformers;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

// Base REST caller used for web service interactions

public class RESTCaller {
	
	public static String send(String fullUrl, String method, byte[] data, HashMap<String, String> additionalHeaders)
		throws MalformedURLException, IOException
	{
		 
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
		
		Properties prop = new Properties();
    	try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			prop.load(cl.getResourceAsStream("tecintegration.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			Logger l = Logger.getRootLogger();
			
			URL url = null;
			url = new URL(fullUrl);
			
			
	        // Install the all-trusting trust manager
	        SSLContext sc = null;
			try {
				sc = SSLContext.getInstance("SSL");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	 
	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	           	@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
	        };
	 
	        // Install the all-trusting host verifier
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

	        HttpsURLConnection req = null;
			
			req = (HttpsURLConnection)url.openConnection();
						
				l.debug("Sending " + method + " to " + fullUrl + " with data: " + data);
				req.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				req.setRequestMethod(method);
				
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


