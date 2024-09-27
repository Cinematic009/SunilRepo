package mylombok;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import updateUserPOJO.User;

public class MyuserTest {


	public String randomEmailId()
	{
		return "sunil" + System.currentTimeMillis() + "@gmail.com";
	}
	
	@Test
	public void cCreateAndUpdateUser_WithPOJO()
	{
		RestAssured.baseURI="https://gorest.co.in";
		Myuser	myUserLombok = new Myuser.MyuserBuilder() 
		          .name("Sanjay")
		          .email(randomEmailId())
		          .gender("male")
		          .status("active")
		          .build();
		
	   Integer Id = given().log().all()
		 .contentType(ContentType.JSON)
		  .header("Authorization", "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		    .body(myUserLombok)
		       .when()
		         .post("/public/v2/users")
		           .then().log().all()
		             .extract()
		               .path("id");
	   
	   System.out.println(Id);
	   
	   myUserLombok.setName("Rohit");
	   myUserLombok.setGender("male");
	   
	   given().log().all()
		 .contentType(ContentType.JSON)
		   .header("Authorization", "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		     .body(myUserLombok)
		       .when()
		         .put("/public/v2/users/" + Id)
		           .then().log().all()
		             .assertThat()
		               .statusCode(200)
	                     .and()
	                       .body("name", equalTo(myUserLombok.getName()))
	                       .body("gender", equalTo(myUserLombok.getGender()));
	   
	   
	   
}
}
