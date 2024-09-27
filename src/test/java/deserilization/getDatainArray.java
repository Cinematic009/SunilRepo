package deserilization;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getDatainArray {

	
	@Test
	public void getDataFromArray()
	{
	
	RestAssured.baseURI = "https://gorest.co.in";
	Response res1 =given().log().all()
			  .contentType(ContentType.JSON)
			    .header("Authorization" , "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
			      .when()
	                .get("/public/v2/users");
	
	res1.prettyPrint();
	
	
	ObjectMapper mapper  = new ObjectMapper();
	try {
		User [] user =mapper.readValue(res1.getBody().asString(), User[].class);
		
		for (User e : user)
		{
			System.out.println(e.getId());
			System.out.println(e.getName());
			System.out.println(e.getEmail());
			System.out.println(e.getGender());
			System.out.println(e.getStatus());
			
			System.out.println("---------------------------------");
		}
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
}
}
