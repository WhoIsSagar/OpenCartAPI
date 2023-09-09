package com.petstore.api.endpoints;

import com.petstore.api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class UserEndPoints {
	
		


	public static Response createUser(User payload){
	
		
		Response res = 	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		
		.when()
		.post(Routes.postUrl);
		
		return res;
	}
	
	public static Response getUser(String username){
		
	Response	res = 	given()
				
				
				.pathParam("username",username)
				.when()
				.get(Routes.getUrl);
				
				return res;
			}
	
	public static Response updateUser(User payload,String username){
	
		Response res = 	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username",username)
				.body(payload)
				.when()
				.put(Routes.updateUrl);
				
				return res;
			}
	
	public static Response deletUser(String username){
	
		Response res = 	given()
				
				.accept(ContentType.JSON)
				.pathParam("username",username)
				.when()
				.delete(Routes.deleteUrl);
				
				return res;
			}
	
}
