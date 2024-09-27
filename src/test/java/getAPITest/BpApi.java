package getAPITest;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;;

public class BpApi {

	
	  int numberOfRequests = 100;
      long totalResponseTime = 0;
     // long averageResponseTime=0;
      
	@Test()
	public void statusEnq() {
		
		for (int i=1; i<=numberOfRequests; i++) {
			long startTime = System.currentTimeMillis();
		    RestAssured.baseURI = "https://transact.avantipay.in";
		    JSONObject params = new JSONObject();
	        params.put("PAY_ID", "1004540725104403");
	        params.put("ORDER_ID", "DB1725859864518989");
	        params.put("AMOUNT", "100");
	        params.put("TXNTYPE", "STATUS");
	        params.put("CURRENCY_CODE", "356");
	        params.put("HASH", "5541EDCABD9FD7198E85F4000877A7A093B1B97A98D26AAD839CAFFBC4A3CA74");

	        // Sending the POST request
	        Response resp = given()
	        	.header("Content-type", "application/json")	
	        	 .body(params.toString())
	              .when()
	               .post("/pgws/transact");
	               //.post("/pgws/transact/new");

	        // Printing the response body in pretty format
	        //resp.prettyPrint();
	        
	        
	        
	     // Extract the response time in milliseconds
	        long responseTimeInMillis = resp.time();
	        long endTime = System.currentTimeMillis();
	        totalResponseTime += responseTimeInMillis;
	        
	        
	        System.out.println("Response time for request " + i + ": " + (endTime-startTime) + " ms");
	        // Calculate the average response time
	       // long averageResponseTime = totalResponseTime / numberOfRequests;
	               		
		}  
		//System.out.println("Final average response time after " + numberOfRequests + " requests: " + (totalResponseTime / numberOfRequests) + " ms");
		

	}

}
