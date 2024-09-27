package createUserPOJO;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.PoJoPojO;

public class GetAuthTokenAPI {

	@Test()
	public void withHardcodedata() {
		
		RestAssured.baseURI= "https://restful-booker.herokuapp.com";
		
		String tokenId = given().log().all()
		 .contentType(ContentType.JSON)
		    .body("{\n"
		    		+ "    \"username\" : \"admin\",\n"
		    		+ "    \"password\" : \"password123\"\n"
		    		+ "}")
		       .when()
		          .post("/Auth")
		             .then()
		               .assertThat()
		                 .statusCode(200)
		                    .extract()
		                     .path("token");
		
		System.out.println("Token id is this " + tokenId);
		Assert.assertNotNull(tokenId);	
		
		 		 
	}
	
@Test
public void getAuthToken_withJsonFile() {
		
		RestAssured.baseURI= "https://restful-booker.herokuapp.com";
		
		String tokenId = given().log().all()
		 .contentType(ContentType.JSON)
		    .body(new File(".\\src\\test\\resource\\pojo\\Auth.json"))
		       .when()
		          .post("/Auth")
		             .then()
		               .assertThat()
		                 .statusCode(200)
		                    .extract()
		                     .path("token");
		
		System.out.println("Token id is this " + tokenId);
		Assert.assertNotNull(tokenId);
		
}

@Test(enabled=true)
public void getAuthToken_withPOJO() {
	
	PoJoPojO credentials = new PoJoPojO("admin", "password123");
		
		RestAssured.baseURI= "https://restful-booker.herokuapp.com";
		
		String tokenId = given().log().all()
		 .contentType(ContentType.JSON)
		    .body(credentials)  //here need to do serialization pojo to json ...ObjectMapper
		       .when()
		          .post("/Auth")
		             .then()
		               .assertThat()
		                 .statusCode(200)
		                    .extract()
		                     .path("token");
		
		System.out.println("Token id is this " + tokenId);
		Assert.assertNotNull(tokenId);
		
}
}
