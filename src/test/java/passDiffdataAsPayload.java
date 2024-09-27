import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.io.File;

public class passDiffdataAsPayload {
	
	
	@Test
	public void fileUpload()
	{
		RestAssured.baseURI = "http://httpbin.org";
		given().log().all()
		  .contentType(ContentType.MULTIPART)		    
		      .multiPart("Sunil's File" , new File("C:\\Users\\Sunil Ghatul\\Downloads\\1726055900487-image.png"))
		      .multiPart("Sunil", "API")
		       .when()
		        .post("/post")
		          .then().log().all()
		            .statusCode(200);
		       		         
		         
	}
	
	@Test
	public void htmlData ()
	{
		
		String htmlpayload = "My API's can automate";
		RestAssured.baseURI = "http://httpbin.org";
		given().log().all()
		 .contentType(ContentType.HTML)
		   .body(htmlpayload)
		     .when()
		       .post("/post")
		         .then().log().all()
		           .statusCode(200);
		   
	}
	
	@Test
	public void xmlData ()
	{
		
		String Xmlpayload = "<paymentRequest>\n"
				+ "    <PAY_ID>12345</PAY_ID>\n"
				+ "    <CUST_NAME>John Doe</CUST_NAME>\n"
				+ "    <ORDER_ID>ORD98765</ORDER_ID>\n"
				+ "    <AMOUNT>1500</AMOUNT>\n"
				+ "    <CURRENCY>INR</CURRENCY>\n"
				+ "    <EMAIL>johndoe@example.com</EMAIL>\n"
				+ "    <MOBILE_NO>9876543210</MOBILE_NO>\n"
				+ "    <RETURN_URL>https://example.com/callback</RETURN_URL>\n"
				+ "    <PRODUCT_DESC>Electronics Purchase</PRODUCT_DESC>\n"
				+ "</paymentRequest>`)";
		
		
		RestAssured.baseURI = "http://httpbin.org";
		given().log().all()
		 .contentType("application/xml;charset=utf-8")
		   .body(Xmlpayload)
		     .when()
		       .post("/post")
		         .then().log().all()
		           .statusCode(200);
	
	}
	
	@Test
	public void textData ()
	{
		RestAssured.baseURI = "http://httpbin.org";
		String textPayload = "My name is My name";
		given().log().all()
		 .contentType(ContentType.TEXT)
		   .body(textPayload)
		     .when()
		       .post("/post")
		         .then().log().all()
		           .statusCode(200);
		
	}

}
