import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.psdconsulting.RESTCaller;


public class SEEDRestCallerTests {
	
	
	
	@Test
	public void httpsShouldWork()throws Exception {

		String urlOverHttps = "https://seedtest.lbl.gov/app/create_dataset/";
		String payload = "{\"organization_id\":\"33\", \"name\":\"NewSetWhichIsNew\"}";
		HashMap<String, String> Header = new HashMap<String, String>();
		Header.put("authorization", "jasbrand@psdconsulting.com:c0aac5e04c1cb731746d72bf162c290ea3812360");
		Header.put("Content-Type", "text/plain");
		
		String response = RESTCaller.send(urlOverHttps, "PUT", payload.getBytes(), Header);

		Assert.assertTrue(response.contains("success"));
	}

	

}
