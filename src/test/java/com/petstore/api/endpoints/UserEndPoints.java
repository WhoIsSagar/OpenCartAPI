package com.petstore.api.endpoints;

import com.petstore.api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class UserEndPoints  {
	public static Properties prop;
	public static Properties setProperties() throws IOException {
		prop = new Properties();
		FileInputStream ip = new FileInputStream("/Users/simran/eclipse-workspace/PetStore_Api/src/test/java/com/petsotre/api/utilities/Routes.properties");
		prop.load(ip);
		return prop;
	}
	
	public static Response createUser(User payload) throws IOException{	
		
		prop = setProperties();
		Response res = 	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		
		.when()
		.post(prop.getProperty("postUrl"));
		
		return res;
	}
	
	public static Response getUser(String username) throws IOException{
		prop = setProperties();
	Response	res = 	given()
				
				
				.pathParam("username",username)
				.when()
				.get(prop.getProperty("getUrl"));
				
				return res;
			}
	
	public static Response updateUser(User payload,String username) throws IOException{
		prop = setProperties();
		Response res = 	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username",username)
				.body(payload)
				.when()
				.put(prop.getProperty("updateUrl"));
				
				return res;
			}
	
	public static Response deletUser(String username) throws IOException{
		prop = setProperties();
		Response res = 	given()
				
				.accept(ContentType.JSON)
				.pathParam("username",username)
				.when()
				.delete(prop.getProperty("deleteUrl"));
				
				return res;
			}
	
}
