package com.test.read.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.path.json.JsonPath;

/**
 * 
 * @author rgunasekaran Read JSON Values using Simple JSON Parser.
 */
public class ReadJson {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// Create JSONParser Object
		JSONParser jsonParser = new JSONParser();

		// Get JSON File Path
		String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\test\\read\\json\\data.json";
		System.out.println("JSON File - Path : " + jsonFilePath);
		//
		// Get JSON Object from JSON File
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonFilePath));

		// Convert JSON Object to String & Print
		String out = jsonObject.toString();
		System.out.println("\nConvert JSON Object to String & Print : " + out);

		// Print All Values in JSON OBJECT
		System.out.println("\nPrint All Values in JSON OBJECT : " + jsonObject);

		// Print Value Of "Type" JSON OBJECT
		Object name = jsonObject.get("Type");
		System.out.println("\nPrint Value Of \"Type\" JSON OBJECT : " + name);

		// Print Value Of "items" JSON OBJECT
		Object items = jsonObject.get("items");
		System.out.println("\nPrint Value Of \"items\" JSON OBJECT : " + items);

		// Create JSON Array and Store "items" json array
		JSONArray jsonArray = (JSONArray) jsonObject.get("items");

		// Get 0th Element of items Array
		System.out.println("\nGet 0th Element of items Array = " + jsonArray.get(0));
		// Get 0th Element "Price" from "items" Array & Print
		JSONObject object = (JSONObject) jsonArray.get(0);
		System.out.println("\nGet 0th Element \"Price\" from \"items\" Array & Print = " + object.get("Price"));
		// Get "Price" value and Check It is equal to "1001"
		System.out.println(
				"\nGet \"Price\" value and Check It is equal to \"1001\" = " + object.get("Price").equals("1001"));

		// Verify how many "category" is "Jacket"
		int jCount = 0, nonJCount = 0;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			if (obj.get("category").equals("Jacket")) {
				jCount++;
				System.out.println("Object is " + obj + " AND Your category is Jacket");
			} else {
				nonJCount++;
				System.out.println("Object is " + obj + " AND Your category is NOT Jacket");
			}
		}
		System.out.println("Jacket Count is : " + jCount);
		System.out.println("Non Jacket Count is : " + nonJCount);
	}

}
