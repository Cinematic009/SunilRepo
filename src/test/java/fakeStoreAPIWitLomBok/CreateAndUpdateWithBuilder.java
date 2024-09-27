package fakeStoreAPIWitLomBok;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateAndUpdateWithBuilder {
	
	private UserLombok.Address.GeoLocation geolocation;
	private UserLombok.Address address;
	private UserLombok user;
	private UserLombok.Name name;
	private int Id;
	
	@Test
	public void CreateUserWithBuilder()
	{
		 name = new UserLombok.Name.NameBuilder()
	               .firstname("Ajay")
	                 .lastname("Deshmukh")
	                   .build();

   geolocation = new UserLombok.Address.GeoLocation.GeoLocationBuilder()
	                                     .lat("-22.11")
	                                       .longitude("44.89")
	                                         .build();

   address = new UserLombok.Address.AddressBuilder()
	                     .city("Pune")
	                       .street("Hinjewadi")
	                        .number(101010)
	                         .zipcode("111111")
	                          .geolocation(geolocation)
	                           .build();

   user = new UserLombok.UserLombokBuilder()
	          .email("sg@gmail.com")
	            .username("sg12345")
	             .password("sg@123")
	              .phone(99889988)
	               .address(address)
	                .name(name)
	                 .build();
  
  RestAssured.baseURI= "https://fakestoreapi.com";
	
	//Create user
   Id =given().log().all()
	  .contentType(ContentType.JSON)
	   .body(user)
	     .when().log().all()
	       .post("/users")
	         .then().log().all()
	           .extract()
	             .path("id");
  
	}
	
	@Test
	public void UpdateUserWithBuilder()
	{
		CreateAndUpdateWithBuilder builder = new CreateAndUpdateWithBuilder();
		builder.CreateUserWithBuilder();
		
		 
		
		
		
		name.setFirstname("Ankur");
		 geolocation.setLat("22.22");
		   user.setPhone(666333);
		
		
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
		              
		             
		  
		  System.out.println("After put request firstname is " + name.getFirstname());
		  System.out.println("After put request lat is " +address.getGeolocation().getLat());
		  System.out.println("After put request Phone is " +user.getPhone());
	}

}
