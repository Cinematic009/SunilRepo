package bookingApisFlow;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookingApiFeatureTest {
	
	@BeforeMethod
	public void getToken()
	{
       RestAssured.baseURI= "https://restful-booker.herokuapp.com";
		
		String tokenId = given().log().all()
		 .contentType(ContentType.JSON)
		    .body(new File(".\\src\\test\\resource\\json\\Auth.json"))
		       .when()
		          .post("/Auth")
		             .then()
		               .assertThat()
		                 .statusCode(200)
		                    .extract()
		                     .path("token");
		
		System.out.println("Token id is this " + tokenId);
	}
	
	@Test
	public void getBookingIdTest()
	{
		RestAssured.baseURI= "https://restful-booker.herokuapp.com";
		Response resp =given().log().all()
		 .when()		
		  .get("/booking")
		    .then()
		     .assertThat()
		       .statusCode(200)
		      .extract()
		         .response();
		
		JsonPath js = resp.jsonPath();
		List<Integer> bokkingIdList = js.getList("bookingid");
		System.out.println("Number of booking ids are : " +bokkingIdList.size());
//		System.out.println(bokkingIdList);
//		
//		for (Integer e : bokkingIdList)
//		{
//			Assert.assertNotNull(e);
//		}	
	}	
	
	
	public int createBooking()
	{
		RestAssured.baseURI= "https://restful-booker.herokuapp.com";
		int bookingId =given().log().all()
	    .contentType(ContentType.JSON)
	     .body(new File(".\\src\\test\\resource\\json\\booking.json"))
		  .when()		
		   .post("/booking")
		    .then().log().all()
		     .assertThat()
		       .statusCode(200)
		      .extract()
		         .path("bookingid");
		
		System.out.println(bookingId);
		return bookingId;
	}
		@Test
		public void getBookingTest()
		{
			int BookingId = createBooking();
			 given()
			  .pathParam("BookingId",BookingId)
			    .get("/booking/{BookingId}")
			       .then()
			         .assertThat()
			           .statusCode(200)  
			             .and()
			               .body("firstname", equalTo("Jim"))
			                 .and()
			                   .body("additionalneeds",  equalTo("Breakfast"));
		}
		
		@Test
		public void CreateBookingTest()
		{
			Assert.assertNotNull(createBooking());
		}
	
		@Test
		public void updateBooking()
		{
			RestAssured.baseURI= "https://restful-booker.herokuapp.com";
			int BookingId = createBooking();
			//get the Booking id
			given().log().all()
			 .when()		
			  .get("/booking")
			    .then().log().all()
			     .assertThat()
			       .statusCode(200);
			      
			
			
		//Update The Booking	
			given()
			 .contentType(ContentType.JSON)
			 .pathParam("bookingid", BookingId)
			  .body("{\n"
			  		+ "    \"firstname\" : \"Jack\",\n"
			  		+ "    \"lastname\" : \"Shukla\",\n"
			  		+ "    \"totalprice\" : 111,\n"
			  		+ "    \"depositpaid\" : true,\n"
			  		+ "    \"bookingdates\" : {\n"
			  		+ "        \"checkin\" : \"2018-01-01\",\n"
			  		+ "        \"checkout\" : \"2019-01-01\"\n"
			  		+ "    },\r\n"
			  		+ "    \"additionalneeds\" : \"Lunch\"\n"
			  		+ "}")
			   .when()
			     .put("/booking/{bookingid}")
			       .then()
			         .assertThat()
			           .statusCode(200)
			             .and()
			               .body("firstname", equalTo("Jack"))
			                 .and()
			                   .body("lastname", equalTo("Shukla"));
			
			
			                   
			       
		}
	

	


}
