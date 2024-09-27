package getAPITest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

public class contactListAPI {
	
	
	@Test
	public void contactListTest()
	{
		
		//URL = "https://thinking-tester-contact-list-herokuapp.com/contacts"
		RestAssured.baseURI ="https://thinking-tester-contact-list.herokuapp.com";
		
	}

}
