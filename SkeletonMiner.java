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
	private StringBuilder sBuildEnd;
	private StringBuilder sBuildStart;
	private StringBuffer reversed;
	private String cont;
	
	//ArrayLists
	
	private ArrayList outcomingLinks;
	private ArrayList para;
	//Constructor to init variables (new memory references for objects.)
	
	public SkeletonMiner(String cont) {
		this.sBuildEnd = new StringBuilder();
		this.sBuildStart = new StringBuilder();
		this.reversed = new StringBuffer("");
		this.cont = cont;
		this.outcomingLinks = new ArrayList();
		this.para = new ArrayList();
	}
	
	//Setters and Getters
	
	
	public ArrayList getOutcomingLinks() {
		return outcomingLinks;
	}
	
	public ArrayList getParas() {
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


	//Next method is used to tokenize String
	public ArrayList tokenizeString(String orig, String prefix, String whatToLook) {
		StringTokenizer temp = new StringTokenizer(orig, prefix);
		String tempString = temp.nextToken();
		ArrayList tempStringArray = new ArrayList();
	
		while (temp.hasMoreElements()) {
			if (tempString.contains(new StringBuilder(whatToLook))) {
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
		sBuildEnd.append(new StringBuilder("/a>"));
		sBuildStart.append(new StringBuilder("<a href="));
		int tempLoppu = cont.indexOf(sBuildEnd.toString());
		String orig="";
		String backComingString="";
		
		
			for (int x = tempLoppu-1;x>=sBuildStart.length();x--) {
				reversed.append(cont.charAt(x));
		
			}
			orig = reverseString(reversed.toString());
			ArrayList backComingStringArray = tokenizeString(cont, ">","https://");
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
		sBuildEnd.append(new StringBuilder(">"));
		sBuildStart.append(new StringBuilder("<"));
		int tempLoppu = cont.indexOf(sBuildEnd.toString());
		String orig="";
		String backComingString="";
		
		
			for (int x = tempLoppu-1;x>=sBuildStart.length();x--) {
				reversed.append(cont.charAt(x));
		
			}
			orig = reverseString(reversed.toString());
			ArrayList backComingStringArray = tokenizeString(cont, ">","");
			String del = null;
			for (int y = 0;y<backComingStringArray.size();y++) {
				del = (String) backComingStringArray.get(y);
				clearRef(del,"<");
				para.add(del);
			}
			
			clearBuffer();
			
			
		
	}
}
	

