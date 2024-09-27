package getAPITest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
public class ProductAPIs {
	
	@Test
	public void productGetTest_1()
	{
		
		//URL = "https://fakeapistore.com/products"
		
		RestAssured.baseURI="https://fakestoreapi.com";
		given().log().all()
		   .get("/products")
		       .then().log().all()
		          .assertThat()
		              .statusCode(200);
	}
	
	@Test
	public void productGetTest_2()
	{
		
		//URL = "https://fakeapistore.com/products"
		
		RestAssured.baseURI="https://fakestoreapi.com";
		given().log().all()
		  .when().log().all()
		     .get("/products")
		        .then().log().all()
		           .assertThat()
		              .statusCode(200)
		                   .and()
		                      .statusLine("HTTP/1.1 200 OK")
		                           .body("$.size()", equalTo(20));
		                              
	}

}
