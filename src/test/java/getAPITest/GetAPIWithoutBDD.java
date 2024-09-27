package getAPITest;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import io.restassured.specification.RequestSpecification;

public class GetAPIWithoutBDD {

	@Test
	public void withoutBDD() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		RequestSpecification ReqSpec = RestAssured.given();
		Response resp = ReqSpec.get("/contacts");
		int RespCode = resp.statusCode();
		String statusLine = resp.statusLine();
		System.out.println(RespCode + "Response Code");
		System.out.println(statusLine + "Response Status Line");
		
		Assert.assertEquals(RespCode, 404);
		//Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		
	}
	
}
