package com.petstore.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.github.javafaker.Faker;
import com.petstore.api.endpoints.UserEndPoints;
import com.petstore.api.payload.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class UserTest {
//post user, get user, update user, delete user
	public static Faker faker;
	public static User user;
	public static Response res;
	public static SoftAssert softassert;
		
	@Test(priority = 1)
	public void testPostUser() {
		softassert = new SoftAssert();
		faker = new Faker();
		user = new User();
		user.setId(faker.idNumber().hashCode());
		user.setUsername(faker.name().username());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password(5, 10));
		user.setPhone(faker.phoneNumber().cellPhone());
		
		 res = UserEndPoints.createUser(user);
		System.out.println(user.getUsername());
		res.then().log().all();
		
	
	}
	
	
	@Test(priority = 2)
	public void testGetUser() {
		
		 res = UserEndPoints.getUser(user.getUsername());
		res.then().log().all();
	}
	
	@Test(priority = 3)
	public void testPutUser() {
		Faker faker1 = new Faker();
		user.setFirstName(faker1.name().firstName());
		user.setLastName(faker1.name().lastName());
		res = UserEndPoints.updateUser(user, user.getUsername());
		res.then().log().all();
		
				
	}
	
	@Test(priority = 4)
	public void testGetUserAfterUpdate() {
		
		 res = UserEndPoints.getUser(user.getUsername());
		res.then().log().all();
	}
	
	@Test(priority = 5)
	public void testDeleteUser() {
		res = UserEndPoints.deletUser(user.getUsername());
		res.then().log().all();
	}
	
	@Test(priority = 6)
	public void testGetUserAfterDelete() {
		
		 res = UserEndPoints.getUser(user.getUsername());
		res.then().statusCode(404);
	}
	
	
}
