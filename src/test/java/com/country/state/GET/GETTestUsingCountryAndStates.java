package com.country.state.GET;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.hamcrest.*;
import com.beust.testng.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GETTestUsingCountryAndStates {
	/*
	 * given()
	 * 
	 * Set Cookies / param / header / auth / etc
	 * 
	 * .when() GET / PUT / POST / DELETE
	 * 
	 * .then() Response / Work with response & use it in next call
	 * 
	 */

	// Sample Test RestAPI
	// http://services.groupkt.com/state/get/{countryCode}/all
	// REST web-service to get a list of all States and territories of a country

	// Before test, Let's setup BaseURI.
	// If RestAPI is running on you local, you can use "port" option to set the
	// port

	@BeforeTest
	public void before() {
		RestAssured.baseURI = "http://services.groupkt.com/";
		// RestAssured.port=8085;
		// RestAssured.basePath = "get";
		System.out.println("*****************       your test started 		***************");
	}

	@Test(description = "This is Example for Get Call without Parameters")
	public void getAllStates() {
		System.out.println(" %%%%%%%    Get All Availabe States in INDIA  %%%%%%%%%%");
		Response response = RestAssured.given().when().get("state/get/IND/all");
		System.out.println("getAllDataSuccess : " + response.prettyPrint());

		// Status Validation
		RestAssured.given().when().get("state/get/IND/all").then().statusCode(200);
		System.out.println("getAllDataSuccess ==== Status - Success - 200");
		System.out.println("TEST - %%%%%%%    getAllStates - COMPLETED %%%%%%%%%%");
	}

	@Test
	public void getOneState() {
		System.out.println(" %%%%%%%    Get ONE  in INDIA  %%%%%%%%%%");
		Response respone = RestAssured.given().when().get("state/get/IND/TN");
		System.out.println("getOneState === response : " + respone.prettyPrint());
		// Status Validation
		RestAssured.given().when().get("state/get/IND/TN").then().statusCode(200);
		System.out.println("getOneState ==== Status - Success - 200");
		System.out.println("TEST - %%%%%%%    getOneState - COMPLETED %%%%%%%%%%");
	}

	// http://services.groupkt.com/state/search/IND?text=pradesh
	@Test
	public void searchOneState() {
		System.out.println(" %%%%%%%    searchOneState  in INDIA  %%%%%%%%%%");
		Response respone = RestAssured.given().when().get("state/search/IND?text=pradesh");
		System.out.println("searchOneState === response : " + respone.prettyPrint());
		// Status Validation
		RestAssured.given().when().get("country/search?text=un").then().statusCode(200);
		System.out.println("searchOneState ==== Status - Success - 200");
		System.out.println("TEST - %%%%%%%    searchOneState - COMPLETED %%%%%%%%%%");
	}

	@Test(description = "This is Example for Get Call with Parameters using param()")
	public void searchUsingParamOption() {
		System.out.println(" %%%%%%%    searchUsingParamOption Example  %%%%%%%%%%");
		Response respone = RestAssured.given().param("text", "kerala").when().get("state/search/IND");
		System.out.println("searchUsingParamOption === response : " + respone.prettyPrint());
		// Status Validation
		int statuscode = RestAssured.given().param("text", "kerala").when().get("state/search/IND").statusCode();
		Assert.assertEquals(statuscode, 200);
		System.out.println("searchUsingParamOption ==== Status - Success - 200");
		System.out.println("TEST - %%%%%%%    searchUsingParamOption - COMPLETED %%%%%%%%%%");
	}

}
