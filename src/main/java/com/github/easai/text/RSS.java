package com.github.easai.text;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSS {

	/**
	 * Reads RSS and returns the first node whose tag name is "channel". 
	 * 
	 * @param rss the RSS adderss 
	 * @return the top Node
	 */
	public static Node readRSS(String rss) throws Exception{
		Node node=null;
		try {
			node=XML.getFirstNode(rss, "channel");
		} catch (Exception e) {
			throw e;
		}
		return node;
	}

	/**
	 * Gets the list of list of Node's.
	 * 
	 * @param rss the URL of the RSS
	 * @return ArrayList of ArrayList of Node
	 */
	public static ArrayList<ArrayList<Node>> getList(String rss) throws Exception{
		ArrayList<ArrayList<Node>> list = new ArrayList<>();
		try {
			Node top = readRSS(rss);
			if(top!=null){
				NodeList nodeList = top.getChildNodes();
				for (int index = 0; index < nodeList.getLength(); index++) {
					Node item = nodeList.item(index);
					if (item.getNodeName().equals("item")) {
						list.add(getItemList(item));
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	/**
	 *　Gets the list of Node's that is under the Node. 
	 * 
	 * @param top the top Node
	 * @return ArrayList of Node's.
	 */
	public static ArrayList<Node> getItemList(Node top) {
		NodeList nodeList = top.getChildNodes();
		ArrayList<Node> list = new ArrayList<>();
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node item = nodeList.item(index);			
			list.add(item);
		}
		return list;
	}

}
