package jsonArrayAPI;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonArrayPoJo {
	
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private List<UserData> UserData;
	private Support support;
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class UserData{
		
		private int id;
		private String email;
		private String firstname;
		private String lastname;
		private String avatar;
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Support{
		private String url;
		private String text;
		
	}

}
