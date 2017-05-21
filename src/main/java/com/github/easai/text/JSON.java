package com.github.easai.text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author easai
 *
 */
@SuppressWarnings("deprecation")
public class JSON {

	/**
	 * Gets an array of JSONObject from the URL whose value is key. 
	 * 
	 * @param urlStr the URL
	 * @param key the key
	 * @return ArrayList of JSONObject
	 * @throws Exception an Exception
	 */
	public static ArrayList<JSONObject> getFirstLevel(String urlStr, String key) throws Exception {
		JSONParser parser = new JSONParser();
		ArrayList<JSONObject> sourceList = new ArrayList<>();
		try {
			URL url = new URL(urlStr);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
				JSONObject obj = (JSONObject) parser.parse(reader);
				JSONArray article = (JSONArray) obj.get(key);
				for (int i = 0; i < article.size(); i++) {
					JSONObject item = (JSONObject) article.get(i);
					sourceList.add(item);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return sourceList;
	}

}
