/**
 * This class belongs to project "Redonioncrawler".
 * 
 * Main task in this implemented class is get webpage outcoming links and other conent out for next step..
 * 
 * @author Jere Sumell
 * 
 * Date created: 31.5.2019.
 */

import java.util.StringTokenizer;
import java.util.ArrayList;

public class SkeletonMiner {

	//Attributes;
	private String sBuildEnd;
	private String sBuildStart;
	private StringBuffer reversed;
	private String cont;
	
	//ArrayLists
	
	private ArrayList<String> outcomingLinks;
	private ArrayList<String> para;
	//Constructor to init variables (new memory references for objects.)
	
	public SkeletonMiner(String cont) {
		this.sBuildEnd = null;
		this.sBuildStart = null;
		this.reversed = new StringBuffer("");
		this.cont = cont;
		this.outcomingLinks = new ArrayList<String>();
		this.para = new ArrayList<String>();
	}
	
	//Setters and Getters
	
	
	public ArrayList<String> getOutcomingLinks() {
		return outcomingLinks;
	}
	
	public ArrayList<String> getParas() {
		return para;
	}
	
	//Other methods
	
	//Next tmethod reverses the given string.
	
	//Reversing the String
	
	public String reverseString(String str) {
		StringBuffer sbB = new StringBuffer("");
		for (int x=str.length()-1;x>=0;x--) {
			sbB.append(str.charAt(x));
		}
		return sbB.toString();
		
	}
	
	//Next method build String with StringBuffer.
	public String appendChars(String y) {
		StringBuffer temp = new StringBuffer("");
		for (int x=0;x<y.length();x++) {
			temp.append(y.charAt(x));
		}
		return temp.toString();
	}


	//Next method is used to tokenize String
	public ArrayList<String> tokenizeString(String orig, String prefix, String whatToLook) {
		StringTokenizer temp = new StringTokenizer(orig, prefix);
		String tempString = temp.nextToken();
		String whatToLookToo = appendChars(whatToLook);
		ArrayList<String> tempStringArray = new ArrayList<String>();
	
		while (temp.hasMoreElements()) {
			if (tempString.contains(whatToLookToo)) {
				tempStringArray.add(tempString);
				
			}
			tempString = temp.nextToken();
			
		}
		return tempStringArray;
	}
	
	//Next method clears this Data Types StringBuffer
	public void clearBuffer() {
		//Clearing the Data Type StringBuffer first
		reversed.delete(0, reversed.length());
	}
	
	public void clearRef(String ref, String indexOf) {
		clearBuffer();
	    int index = 0;
	    
	    index = ref.indexOf(indexOf);
	    for (int x=index;x<ref.length()-1;x++) {
			reversed.append(ref.charAt(x));
			
	    	}
				
		
		
		 this.outcomingLinks.add(reversed.toString());
	}
	
	//Next method parses the outcoming links
	public void parseLinks() {
		//Clearing the Data Type StringBuffer first
		clearBuffer();
		this.sBuildEnd = appendChars("/a>");
		this.sBuildStart = appendChars("<a href=");
		int tempLoppu = cont.indexOf(sBuildEnd.toString());
		String orig="";
		String backComingString="";
		
		
			for (int x = tempLoppu-1;x>=sBuildStart.length();x--) {
				reversed.append(cont.charAt(x));
		
			}
			orig = reverseString(reversed.toString());
			ArrayList<String> backComingStringArray = tokenizeString(cont, ">","https://");
			String del = null;
			for (int y = 0;y<backComingStringArray.size();y++) {
				del = (String) backComingStringArray.get(y);
				clearRef(del, "https://");
			}
			clearBuffer();
			
			
		
	}
	//Next method is for the rest of the content
	
	public void parseRest() {
		//Clearing the Data Type StringBuffer first
		clearBuffer();
		this.sBuildEnd = appendChars("/>");
		this.sBuildStart = appendChars("<");
		int tempLoppu = cont.indexOf(sBuildEnd.toString());
		String orig="";
		String backComingString="";
		
		
			for (int x = tempLoppu-1;x>=sBuildStart.length();x--) {
				reversed.append(cont.charAt(x));
		
			}
			orig = reverseString(reversed.toString());
			ArrayList<String> backComingStringArray = tokenizeString(cont, ">","");
			String del = null;
			for (int y = 0;y<backComingStringArray.size();y++) {
				del = (String) backComingStringArray.get(y);
				clearRef(del,"<");
				para.add(del);
			}
			
			clearBuffer();
			
			
		
	}
}
	

