package jsonArrayAPI;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jsonArrayAPI.JsonArrayPoJo.UserData;
import static io.restassured.RestAssured.given;

public class JsonArrayTest {
	
	
	@Test
	public void ReqresjSONArrayAPI()
	{
		
		JsonArrayPoJo.UserData userdata1 = new JsonArrayPoJo.UserData(1, "sunil@gmail.com", "Sunil", "Patil", "https://reqres.in/img/faces/5-image.jpg");
		JsonArrayPoJo.UserData userdata2 = new JsonArrayPoJo.UserData(2, "anil@gmail.com", "Anil", "Patil", "https://reqres.in/img/faces/6-image.jpg");
		JsonArrayPoJo.UserData userdata3 = new JsonArrayPoJo.UserData(3, "nitish@gmail.com", "Nitish", "Verma", "https://reqres.in/img/faces/7-image.jpg");
		JsonArrayPoJo.UserData userdata4 = new JsonArrayPoJo.UserData(4, "kartik@gmail.com", "Kartik", "Mishra", "https://reqres.in/img/faces/8-image.jpg");
		JsonArrayPoJo.UserData userdata5 = new JsonArrayPoJo.UserData(5, "bhavna@gmail.com", "Bhavna", "Shinde", "https://reqres.in/img/faces/9-image.jpg");
		JsonArrayPoJo.UserData userdata6 = new JsonArrayPoJo.UserData(6, "glenn@gmail.com", "Glenn", "Maxwell", "https://reqres.in/img/faces/10-image.jpg");
	
	
	    List<UserData> userdata = Arrays.asList(userdata1, userdata2, userdata3, userdata4, userdata5, userdata6);
	    
	    JsonArrayPoJo.Support support = new JsonArrayPoJo.Support("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
	    
	    JsonArrayPoJo user = new JsonArrayPoJo.JsonArrayPoJoBuilder()
	    		             .page(1)
	    		             .total_pages(2)
	    		             .total(12)
	    		             .per_page(6)
	    		             .UserData(userdata)
	    		             .support(support)
	    		             .build();
	    
	    RestAssured.baseURI = "https://reqres.in";
	     given().log().all()
	     .contentType(ContentType.JSON)
	        .queryParam("page", 1)
	         .body(user)
	          .when()
	           .post("/api/users")
	             .then().log().all()
	               .assertThat() 
	                 .statusCode(201);
	    
	     given()
	     .contentType(ContentType.JSON)
	        .queryParam("page", 1)
	          .body(user)
               .when()
                 .get("/api/users")
                   .then()
                     .assertThat() 
                       .statusCode(200);
	                   
	                     
	                     
	    
	    
	}

}
