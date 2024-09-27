package jayWayLibraries;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class FakeStoreAPI {

	
	@Test
	public void JsonPathtest()
	{
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response =given()
		  .when()
		    .get("/products");
		
		response.prettyPrint();
		
		String jsonResponse =response.asString();
		//System.out.println(jsonResponse);
		
		DocumentContext	ctx =JsonPath.parse(jsonResponse);
		
	List<Number> PriceList	=ctx.read("$[?(@.price>50)].price");
	System.out.println(PriceList);
	System.out.println("----------------------------------------------------");
	
	List<Integer> Idlist	=ctx.read("$[?(@.price>50)].id");
	System.out.println(Idlist);
	System.out.println("----------------------------------------------------");
	
	List<String> titleList	=ctx.read("$[?(@.price>50)].title");
	System.out.println(titleList);
	System.out.println("----------------------------------------------------");
	
	List<String> titleList1	=ctx.read("$.[*].title");
	System.out.println(titleList1);
	System.out.println("----------------------------------------------------");
	
	List<Double> Pricelist1	=ctx.read("$.[*].price");
	System.out.println(Pricelist1);
	System.out.println("----------------------------------------------------");
	
	List <Double> RateList = ctx.read("$.[?(@.rating.rate<4.0)].rating.rate");
	System.out.println(RateList);
	System.out.println("----------------------------------------------------");
	
	List<Map<String, Object>> twoAttributes = ctx.read("$.[?(@.rating.rate<4.0)].['title' , 'price']");
	System.out.println(twoAttributes);
	
	for(Map<String, Object> attriList : twoAttributes)
	{
		String title = (String)attriList.get("title");
		Number price = (Number)attriList.get("price");
		System.out.println(title);
		System.out.println(price);
		System.out.println("--------------------------------------------------");
	}
	
	
	List <Map<String, Object>> AndOperators = ctx.read("$[?((@.category == 'jewelery') && (@.price>50))].['id', 'price', 'title']");
	System.out.println(AndOperators);
	}
}
