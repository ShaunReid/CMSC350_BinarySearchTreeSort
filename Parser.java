/*
 CMSC350 
 23 Feb 2020
 Shaun Reid
 
 Parser class parses all the input data from the GUI
 */

public class Parser {

	private String[] tokens = null;
	
	public String[] parser(String s) {
		tokens = s.split("\\s");
		
		return tokens;
	}
	
}
