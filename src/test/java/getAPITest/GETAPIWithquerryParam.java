package getAPITest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

public class GETAPIWithquerryParam {
	
	
	@Test(enabled=false)
	public void GetAPIwithQuerryparam()
	{
		
		RestAssured.baseURI = "https://www.gorest.co.in";
		
		given().log().all()
		  .header("Authorization" , "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		   .queryParam("name", "Patel")
		   .queryParam("status", "inactive")
		     .when().log().all()
		       .get("/public/v2/users")
		         .then().log().all()
		           .assertThat()
		              .statusCode(200)
		                .and()
		                  .contentType(ContentType.JSON)
		                     .body("$.size()", equalTo(10));
		
	}
	
	@Test(enabled=false)
	public void GetAPIwithQuerryparamWithHashMap()
	{
		Map <String, String> querymap = new HashMap<>();
		querymap.put("name", "shiv");
		querymap.put("status", "inactive");
		RestAssured.baseURI = "https://www.gorest.co.in";
		
		given().log().all()
		  .header("Authorization" , "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		     .queryParams(querymap)
		       .when().log().all()
		       .get("/public/v2/users")
		         .then().log().all()
		           .assertThat()
		              .statusCode(200)
		                .and()
		                  .contentType(ContentType.JSON)
		                     .body("$.size()", equalTo(2));
		
	}
	
	@DataProvider
	public Object [] [] getUserData()
	{
		return new Object[][]
				{
			{"7384604","male"},
			{"7384603", "female"}
			
				};
		
	}
	
	@Test(dataProvider="getUserData")
	public void GetAPIwithPathParameter(String userId, String gender)
	{
		
		RestAssured.baseURI = "https://www.gorest.co.in";
		
		given().log().all()
		  .header("Authorization" , "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		     .pathParam("userId", userId)
		     .pathParam("gender", gender)
		       .when().log().all()
		       .get("/public/v2/users/{userId}/gender/{gender}")
		         .then().log().all();
		          
		                     
		
	}

	
	

}
