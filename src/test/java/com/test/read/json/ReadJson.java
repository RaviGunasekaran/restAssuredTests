package com.test.read.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author rgunasekaran Read JSON Values using Simple JSON Parser.
 */
public class ReadJson {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// Create JSONParser Object
		JSONParser jsonParser = new JSONParser();
		//
		// Get JSON File Path
		String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\test\\read\\json\\data.json";
		System.out.println("JSON File - Path : " + jsonFilePath);
		//
		// // Get JSON Object from JSON File
		// JSONObject jsonObject = (JSONObject) jsonParser.parse(new
		// FileReader(jsonFilePath));
		//
		// // Convert JSON Object to String & Print
		// String out = jsonObject.toString();
		// System.out.println("JSON Object Converted to String : " + out);
		//
		//
		//
		// Object obj = jsonParser.parse(new FileReader(jsonFilePath));
		// JSONObject jsonObject1 = (JSONObject) obj;
		// //Get Name
		// String Name = (String) jsonObject1.get("menu.header.items[0].Name");
		// System.out.println("Name : " +Name);

		try {

			Object obj = jsonParser.parse(new FileReader(jsonFilePath));

			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);

			Object name = jsonObject.get("Type");
			System.out.println("Type : " + name);

			Object items = jsonObject.get("items");
			System.out.println("items : " + items);

			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("items");
			Iterator<Object> iterator = msg.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
				String string = iterator.next().toString();
				if (string.contains("category:Jacket"))
					System.out.println("True");
				else {
					System.out.println("False");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
