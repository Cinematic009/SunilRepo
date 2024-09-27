package deserilization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MapperClass {
	
	
	String randomMail =  "sg" + System.currentTimeMillis() + "@gmail.com";
	
	User user = new User.UserBuilder()
			    .name("SunilG")
			    .gender("male")
			    .status("active")
			    .email(randomMail)
			    .build();
	
	
	@Test
	public void getResponse()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response resp=given().log().all()
		 .contentType(ContentType.JSON)
		    .header("Authorization" , "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		      .body(user)
		        .when()
		          .post("/public/v2/users");
		
		resp.prettyPrint();
		
		Integer userId =resp.jsonPath().get("id");
		
		Response res1 =given().log().all()
		  .contentType(ContentType.JSON)
		    .header("Authorization" , "Bearer 2aa25560fdbfd3f7c1ffca5173463668d04580568905392b3dd3e80b5a241e7d")
		      .when()
                .get("/public/v2/users/" + userId);
		       
		
		ObjectMapper mapper = new ObjectMapper();    //de serialization
		 
		try {
		User  userMapper =	mapper.readValue(res1.getBody().asString(), User.class);
		
		System.out.println(userMapper.getEmail());
		System.out.println(userMapper.getGender());
		System.out.println(userMapper.getId());
		System.out.println(userMapper.getName());
		System.out.println(userMapper.getStatus());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		
		
		
		
		 
		
	}

}
