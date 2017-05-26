package com.github.easai.text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
			TrustManager[] tm = { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] xc, String type) {
				}

				public void checkServerTrusted(X509Certificate[] xc, String type) {
				}
			} };

			SSLContext ctx = SSLContext.getInstance("SSL");
			ctx.init(null, tm, new SecureRandom());

			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setSSLSocketFactory(ctx.getSocketFactory());
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			JSONObject obj = (JSONObject) parser.parse(reader);
			JSONArray article = (JSONArray) obj.get(key);
			for (int i = 0; i < article.size(); i++) {
				JSONObject item = (JSONObject) article.get(i);
				sourceList.add(item);
			}
		} catch (Exception e) {
			throw e;
		}
		return sourceList;
	}

}
