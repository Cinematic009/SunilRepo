package authAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BasicAuth {

	
	@Test
	public void AuthBasic()
	{
		RestAssured.baseURI="https://the-internet.herokuapp.com";
		RestAssured.given().log().all()
		   .auth()
		     .basic("admin", "admin")
		       .when()
		          .get("/basic_auth")
		             .then().log().all()
		               .assertThat()
		                 .statusCode(200);
		               
	}
}
