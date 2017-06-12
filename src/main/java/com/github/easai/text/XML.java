package com.github.easai.text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XML {

	/**
	 * Gets the first Node whose tag name is key.
	 * 
	 * @param urlStr the URL
	 * @param key the key
	 * @return the top Node
	 */
	public static Node getFirstNode(String urlStr, String key)throws Exception{
		Node node=null;
		try {
			NodeList list=getNodeList(urlStr, key);		
			if(0<list.getLength()){
				node=list.item(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return node;
	}
	
	/**
	 * Reads the XML from the URL and returns the NodeList of nodes whose tag name is key.
	 * 
	 * @param urlStr the URL 
	 * @param key the key
	 * @return the NodeList
	 */
	public static NodeList getNodeList(String urlStr, String key)throws Exception{
		NodeList list=null;
	     try {
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            URL url=new URL(urlStr);
	            InputStream inputStream=url.openStream();
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");
	            Document doc = dBuilder.parse(is);
	            doc.getDocumentElement().normalize();
	            list=doc.getElementsByTagName(key);
	            if(is!=null){
	            	reader.close();
	            }
	        } catch (Exception e) {
	        	throw e;
	        }
	     return list;
	}
	
}
