package com.rest.employee.PUT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PUT_MULTIPLE_UPDATE {
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
	
	//	Generate String from Given JSON File
	public String generateStringFromJSON(String path) throws IOException {
	    return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	// Delete Employee2 By ID using JSON Payload from file & DELETE
	@Test
	public void putByID() throws IOException{
		// Prepare path of the file
		String postJsonFilePath;
		String json;
		postJsonFilePath= System.getProperty("user.dir")+"/src/test/java/com/rest/employee/PUT/put_employee2.json";
		// Get Employee1 content into JSON
		json = generateStringFromJSON(postJsonFilePath);
		
		// Use Employee2-JSON for PUT Request
		Response response = RestAssured
		.given()
		.contentType(ContentType.JSON)
		.when()
		.body(json)
		.put("employee/put/2");
		System.out.println("Status Code == 200 == PUT == Employee2 UPDATED BY ID == SUCCESS" + response.prettyPrint());	
	}

}
