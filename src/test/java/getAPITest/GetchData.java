package getAPITest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;


public class GetchData {

	@Test
	public void fetchdata()
	{
		RestAssured.baseURI= "https://fakestoreapi.com";
		
		Response resp = given()
		  .get("/products");
		
		resp.prettyPrint();
		JsonPath jsonpath = resp.jsonPath();
		//List <Integer> = jsonpath.getList();
		  
	}
	
}
