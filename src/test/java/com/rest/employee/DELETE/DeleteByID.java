package com.rest.employee.DELETE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteByID {
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
	
	//Generate String from Given JSON File
	public String generateStringFromJSON(String path) throws IOException {
	    return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	// Delete Employee2 By ID using JSON Payload from file & DELETE
	@Test
	public void deleteEmployeeByID() throws IOException{
		// Use Employee2-JSON for DELETE Request
		Response response = RestAssured
		.given()
		.when()
		.delete("employee/delete/id/2");
		System.out.println("Status Code == 200 == DELETE == Employee2 Deleted BY ID == SUCCESS" + response.prettyPrint());	
	}
	
	// Delete Employee2 By Employee LastName using JSON Payload from file & DELETE
	@Test
	public void deleteEmployeeByLastName() throws IOException{
		Response response = RestAssured
		.given()
		.when()
		.delete("employee/delete/name/API_LASTNAME");
		System.out.println("Status Code == 200 == DELETE == Employee2 Deleted BY LASTNAME== SUCCESS" + response.prettyPrint());	
	}

}
