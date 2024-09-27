package deserilization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import deserilization.Product.UserData;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPIdeserilization {
	
	@Test
	 public void getMapperforProductAPI() {
        // Set base URI
        RestAssured.baseURI = "https://reqres.in";
        
        // Send GET request
        Response res = given()
            .contentType(ContentType.JSON)
            .get("/api/users?page=1");
        
        // Print response for debugging
        res.prettyPrint();
        
        // Create ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            // Deserialize response body to Product object
            Product product = mapper.readValue(res.getBody().asString(), Product.class);
            
            // Access and print the deserialized values
            System.out.println("Page: " + product.getPage());
            System.out.println("Per Page: " + product.getPer_page());
            System.out.println("Total: " + product.getTotal());
            System.out.println("Total Pages: " + product.getTotal_pages());
            System.out.println("Url : " + product.getSupport().getUrl());
            System.out.println("Text : " + product.getSupport().getText());

            // Loop through userData list
            for (UserData userData : product.getData()) {
                System.out.println("ID: " + userData.getId());
                System.out.println("Email: " + userData.getEmail());
                System.out.println("First Name: " + userData.getFirst_name());
                System.out.println("Last Name: " + userData.getLast_name());
                System.out.println("Avatar: " + userData.getAvatar());
                System.out.println("========================================");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}   

}
