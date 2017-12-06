package com.country.employee.POST;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

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
		System.out.println("Status Code == 200");		
	}
	
	//Generate String from Given Json File
	public String generateStringFromJSON(String path) throws IOException {
	    return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	@Test
	public void createNewEmployee() throws IOException{
		// Prepare path of the file
		String postJsonFilePath = System.getProperty("user.dir")+"/src/test/java/com/country/employee/POST/employee1.json";
		// Get file content into JSON
		String json = generateStringFromJSON(postJsonFilePath);
		// Use JSON for POST Request
		Response newEmployee = RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.body(json)
		.post("/employee/create");
		System.out.println("New Employee Created : "+newEmployee.prettyPrint());
	}

}
