package updateUserPOJO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class UpdateUserWithPOJO {
	
	//Create user: POST
	//Get User : GET
	//Update the User : PUT/PATCH
	
	public String randomEmailId()
	{
		return "sunil" + System.currentTimeMillis() + "@gmail.com";
	}
	
	@Test
	public void createUserwith_POJO()
	{
		RestAssured.baseURI="https://gorest.co.in";
		User user = new User("Sunil", randomEmailId(), "male", "active");
		
	   Integer Id = given().log().all()
		 .contentType(ContentType.JSON)
		  .header("Authorization", "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		    .body(user)
		       .when()
		         .post("/public/v2/users")
		           .then().log().all()
		             .extract()
		               .path("id");
	   
	   System.out.println(Id);
	   
	   user.setName("sunita");
	   user.setGender("female");
	   
	   given().log().all()
		 .contentType(ContentType.JSON)
		   .header("Authorization", "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		     .body(user)
		       .when()
		         .put("/public/v2/users/" + Id)
		           .then().log().all()
		             .assertThat()
		               .statusCode(200)
	                     .and()
	                       .body("name", equalTo(user.getName()))
	                       .body("gender", equalTo(user.getGender()));
	   
	   System.out.println("Updated Name is : " + user.getName());
	   Assert.assertEquals("sunita", user.getName());
	                       
	   
	   
	   
	  
	   
	   
	   
	   
	   
	   
	}
	
	
	
	

}
