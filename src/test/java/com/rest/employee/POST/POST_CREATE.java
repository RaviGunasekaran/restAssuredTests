package com.rest.employee.POST;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_CREATE {

	// Refer - README.md in "https://github.com/RaviGunasekaran/ravi-rest-service" to setup your own REST SERVICE
	
	/**
	 * Set up URI / PATH / PORT for your tests
	 */
	@BeforeTest
	public void setDataForTest() {
		RestAssured.baseURI = "http://localhost:8085/";
		RestAssured.basePath = "/rest";
		RestAssured.port = 8085;
	}

	/**
	 * This method will create Dummy Employee Using GET
	 */
	@Test
	public void createDummyWithGET() {
		// Create Dummy Employee using GET
		Response dummyGET = 
		RestAssured
		.given()
		.when()
		.get("employee/dummy");
		
		System.out.println("Dummy  Created = " + dummyGET.prettyPeek());
		
		// Status Code Validation
				RestAssured
				.given()
				.when()
				.get("employee/dummy")
				.then()
				.statusCode(200);
		System.out.println("Status Code == 200 == GET == Dummy Employee Created == SUCCESS");		
	}
	
	//Generate String from Given JSON File
	public String generateStringFromJSON(String path) throws IOException {
	    return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	// Create New Employee using JSON Payload from file & POST
	
	@Test
	public void createNewEmployee() throws IOException{
		// Prepare path of the file
		String postJsonFilePath;
		String json;
		postJsonFilePath= System.getProperty("user.dir")+"/src/test/java/com/rest/employee/POST/employee1.json";
		// Get Employee1 content into JSON
		json = generateStringFromJSON(postJsonFilePath);
		// Use Employee1-JSON for POST Request
		Response newEmployee = RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.body(json)
		.post("/employee/create");
		System.out.println("Status Code == 200 == POST == Employee1 Created == SUCCESS"+newEmployee.prettyPrint());		

		//EMPLOYEE2 creation with Status Code Verification
		
		// Prepare path of the file
		postJsonFilePath= System.getProperty("user.dir")+"/src/test/java/com/rest/employee/POST/employee2.json";
		
		// Get Employee2 content into JSON
		json = generateStringFromJSON(postJsonFilePath);

		// Use Employee1-JSON for POST Request & Get status
		int status = RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.body(json)
		.post("/employee/create")
		.statusCode();
		
		// Print status Code 
		System.out.println("POST Request statusCode === "+status);
		
		// Validate Status Code after POST
		Assert.assertEquals(status, 200);
		System.out.println("Status Code == 200 == POST == Employee2 Created == SUCCESS");		
		
	}
	

}
