package com.github.easai.text;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSS {

	/**
	 * Reads RSS and returns the first node whose tag name is "channel". 
	 * 
	 * @param rss
	 * @return
	 */
	public static Node readRSS(String rss) {
		return XML.getFirstNode(rss, "channel");
	}

	/**
	 * Gets the list of list of Node's.
	 * 
	 * @param rss the URL of the RSS
	 * @return ArrayList of ArrayList<Node>
	 */
	public static ArrayList<ArrayList<Node>> getList(String rss) {
		Node top = readRSS(rss);
		ArrayList<ArrayList<Node>> list = new ArrayList<>();
		NodeList nodeList = top.getChildNodes();
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node item = nodeList.item(index);
			if (item.getNodeName().equals("item")) {
				list.add(getItemList(item));
			}
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
