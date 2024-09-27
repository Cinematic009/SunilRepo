package deserilization;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
	    private int page;
	    private int per_page;
	    private int total;
	    private int total_pages;
	    private List<UserData> data;
	    private Support support;


 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
 @Data
 public static class Support
 {
	 private String url;
	 private String text;
 }
 
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
 @Data
 public static class UserData
 {
	 private int id;
	 private String email;
	
	 private String first_name;
	
	 private String last_name;
	 private String avatar;
 }
	

}
