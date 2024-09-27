package fakeStoreAPIWitLomBok;

import org.testng.annotations.Test;

import fakeStoreAPIWitLomBok.UserLombok.Address.GeoLocation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserLomBokTest {
	
	
	
	@Test
	public void PostAndUpdateAPI() {
		UserLombok.Name name = new UserLombok.Name("Sunil", "Patil");
		UserLombok.Address.GeoLocation geolocation = new UserLombok.Address.GeoLocation("-78.567", "99.67");
		UserLombok.Address address = new UserLombok.Address("Delhi", "Rajiv Chowk", 12345678, "110011", geolocation);
		
		
		UserLombok user=new UserLombok("sunil09@gmail.com", "Sunil123","Sunil@123", 888888888, name, address);
		
		RestAssured.baseURI= "https://fakestoreapi.com";
	Integer Id = given().log().all()
		.contentType(ContentType.JSON)
		  .body(user)
		   .when()
		     .post("/users")
		       .then().log().all()
		         .assertThat()
                   .statusCode(200)
		             .extract()
		               .path("id");
	
		
	System.out.println(Id);
		
		name.setFirstname("Puneet");
		name.setLastname("Sharma");
		geolocation.setLat("88.99");
		geolocation.setLongitude("55.77");
		address.setCity("Mumbai");
		user.setPhone(99999999);	
		
		  given().log().all()
		  .contentType(ContentType.JSON)
		   .body(user)
		    .when()
		     .put("/users/" + Id)
		       .then().log().all()
		        .assertThat()
		         .statusCode(200)
		          .and()
		            .body("name.firstname", equalTo(name.getFirstname()));
		             
		  
		  System.out.println("after put request firstname is " + name.getFirstname());
	  	  
		
	}
	
	@Test
	public void createAndUpdateWithBuilder()
	{
		UserLombok.Name name = new UserLombok.Name.NameBuilder()
				               .firstname("Ajay")
				                 .lastname("Deshmukh")
				                   .build();
		
		UserLombok.Address.GeoLocation geolocation = new UserLombok.Address.GeoLocation.GeoLocationBuilder()
				                                     .lat("-22.11")
				                                       .longitude("44.89")
				                                         .build();
		
		UserLombok.Address address = new UserLombok.Address.AddressBuilder()
				                     .city("Pune")
				                       .street("Hinjewadi")
				                        .number(101010)
				                         .zipcode("111111")
				                          .geolocation(geolocation)
				                           .build();
		
		UserLombok user = new UserLombok.UserLombokBuilder()
				          .email("sg@gmail.com")
				            .username("sg12345")
				             .password("sg@123")
				              .phone(99889988)
				               .address(address)
				                .name(name)
				                 .build();
		
		RestAssured.baseURI= "https://fakestoreapi.com";
		
		//Create user
	Integer Id =given().log().all()
		  .contentType(ContentType.JSON)
		   .body(user)
		     .when().log().all()
		       .post("/users")
		         .then().log().all()
		           .extract()
		             .path("id");
	
	//Update the Data for Put Request
	 
	name.setFirstname("Rajpal");
	 geolocation.setLat("18.20");
	   user.setPhone(333333);
	
	
	//Update User
	
	 given().log().all()
	  .contentType(ContentType.JSON)
	   .body(user)
	    .when()
	     .put("/users/" + Id)
	       .then().log().all()
	        .assertThat()
	         .statusCode(200)
	          .and()
	            .body("name.firstname", equalTo(name.getFirstname()))
	              .and()
	                .body("address.geolocation.lat" , equalTo(address.getGeolocation().getLat()))
	                 .and()
	                   .body("phone", equalTo(user.getPhone()));
	              
	             
	  
	  System.out.println("after put request firstname is " + name.getFirstname());
	
	
		        
				          
		
				                   
	}

}
