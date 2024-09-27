package getAPITest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponsedata {
	
	
	@Test(enabled=false)
	public void GetContactAPI_withInvalidToken()
	{
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		  .header("Authorization" , "Bearer ")
		    
		       .when().log().all()
		       .get("/contacts")
		         .then().log().all()
		            .assertThat()
		               .statusCode(401)
		                 .and()
		                   .body("error", equalTo("Please authenticate."));
		                     
		
	}
	
	@Test(enabled=false)
	public void GetContactAPI_withInvalidToken_Withextract()
	{
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		String errMsg =given().log().all()
		  .header("Authorization" , "Bearer ")
		    
		       .when().log().all()
		       .get("/contacts")
		         .then().log().all()
		                   .extract()
		                      .path("error");
		
		System.out.println(errMsg);
		Assert.assertEquals(errMsg, "Please authenticate.");
		                     
		
	}
	
	@Test(enabled=false)
	public void GetSingleUser()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		Response resp =given()
		  .when()
		     .get("/public/v2/users/7384593");
		
		
		System.out.println(resp);
		resp.prettyPrint();
		
		JsonPath js = resp.jsonPath();
		Object userid =js.get("id");
		System.out.println(userid);
		
		int userid1 = js.getInt("id");
		//System.out.println(userid1);
		
		String gender = js.getString("gender");
		//System.out.println(gender);
		
		List<String> dataList = js.getList(gender);
		//System.out.println(dataList);
		
		
		
		        
	}

	
	@Test(enabled=false)
	public void GetUsersData()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		Response resp =given()
		  .when()
		     .get("/public/v2/users");
		
		resp.prettyPrint();
	JsonPath js = resp.jsonPath();
	
	List<Integer> userids =js.getList("id");
	//System.out.println(userids);
	

	List<String> userGenders =js.getList("gender");
	//System.out.println(userGenders);
	
	for (Object e: userids)
	{
		System.out.println(e);
	}
	}
	
	
	@Test
	public void getDetailData ()
	{
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response resp =given()
		  .when()
		     .get("/products");
		resp.prettyPrint();
		
    	JsonPath js1 =resp.jsonPath();
    	
    	List<Integer> ids =js1.getList("id");
		System.out.println(ids);
		
		List<Object> prices =js1.getList("price");
		System.out.println(prices);
		
		List<Integer> Count =js1.getList("rating.count");
		System.out.println("here is the Count : " +Count);
		
		List<Object> rate =js1.getList("rating.rate");
		System.out.println("here is the rate : " +rate);
		
		for (int i=0; i<ids.size(); i++)
		{
			int id =ids.get(i);
			Object price =prices.get(i);
			int counts = Count.get(i);
			Object rates =rate.get(i);
			
			//System.out.println("ID : " + id + "price : " + price + " counts :" +counts + "rates  :" +rates );
		}
		
		
		Assert.assertTrue(rate.contains(4.7f));
		
	}
}
