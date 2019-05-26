/**
 * This class belongs to project "Redonioncrawler".
 * 
 * Main task in this implemented class is get webpage source code as output from given URL.
 * 
 * @author Jere Sumell
 * 
 * Date created: 26.5.2019.
 */

import java.io.*;
import java.net.*;
import java.util.zip.GZIPInputStream;

public class UrlIO {

	//Attributes
	private String url;
	private String content;
	
	//Constructor with parameter
	public UrlIO(String url) {
		this.url = url;
		try {
			this.content = connect(this.url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Setters and Getters
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	//Other methods
	
	//Next method returns source code as String from given URL

	public static String connect(String ur) throws Exception{
			
		StringBuffer sb = new StringBuffer("");
		 URL url = new URL(ur);
		 URLConnection spoof = url.openConnection();
	        BufferedReader in = null;

	        if (spoof.getHeaderField("Content-Encoding") != null
	                && spoof.getHeaderField("Content-Encoding").equals("gzip")) {
	            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(
	                    spoof.getInputStream())));
	        } else {
	            in = new BufferedReader(new InputStreamReader(
	                    spoof.getInputStream()));
	        }

		  String strLine = "";


		  while ((strLine = in.readLine()) != null){


		   sb.append(in.readLine());
		  }
		  return sb.toString();
		 }

}
