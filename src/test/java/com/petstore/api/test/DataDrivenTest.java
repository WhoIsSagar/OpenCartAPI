package com.petstore.api.test;

import org.testng.annotations.Test;

import com.petstore.api.endpoints.UserEndPoints;
import com.petstore.api.payload.User;
import com.petstore.api.testdata.FetchExcelData;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class DataDrivenTest {

	public static User user;
	public String phone;
	public  int id;
	public static Response res;
	public static UserEndPoints endpoint;
	
	@Test(priority=1, dataProvider = "fetchAllData", dataProviderClass = FetchExcelData.class)
	public void postRequest(double id, String username, String firstName, String lastName, String email, String password, double phone) throws IOException {
		
		this.phone = Double.toString(phone);
		this.id = (int)id;
		user = new User();
		user.setId(this.id);
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(this.phone);		
		res = endpoint.createUser(user);
		res.then().log().body();
		
		
		
	}
	
	@Test(priority = 2, dataProvider = "fetchUserName", dataProviderClass = FetchExcelData.class)
	public void testGetUser(String userName) throws IOException {
		System.out.println(userName);
		res = UserEndPoints.getUser(userName);
		res.then().log().all();
	}
	
	
	@Test(priority = 3, dataProvider = "fetchUserName", dataProviderClass = FetchExcelData.class)
	public void testDeleteUser(String userName) throws IOException {
		System.out.println(userName);
		res = UserEndPoints.deletUser(userName);
		res.then().log().all();
	}
	
}

