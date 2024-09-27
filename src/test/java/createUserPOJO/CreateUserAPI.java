package createUserPOJO;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPI {
	
	@Test
	public void createUser()
	{
		RestAssured.baseURI= "https://gorest.co.in";
		Integer Id =given().log().all()
		.header("Authorization" ,"Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		  .contentType(ContentType.JSON)
		    .body(new File (".\\src\\test\\resource\\json\\user.json"))
		     .post("/public/v2/users")
		       .then()
		         .assertThat()
		           .statusCode(201)
		             .extract()
		               .path("id");
		
		System.out.println(Id);
		       
	}

}
