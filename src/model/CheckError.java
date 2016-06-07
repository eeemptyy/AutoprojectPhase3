package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class CheckError {
	String testData;
	private String errorPharse = "";
	String[] testSet;
	int counter = 0, line;
	ArrayList<Character> specialChar = new ArrayList<>();
	ArrayList<String> typeMap = new ArrayList<String>();
	
	List<Character> special = new ArrayList<>(Arrays.asList('*', '!', '@', '#','&','$','!','-'));
	
	public void MainChecker(String inn, int n){
		testData = inn;
		line = n + 1;
		testSet = testData.trim().split("\\s+");
		counter = 0;
		for (int i = 0; i < testSet.length; i++){
			String t = testSet[i].trim();
			if (t.length() > 8){
				setErrorPharse(getErrorPharse() + "In line " + line + " : " + t + " variable is too long.\n");
			}
			if (!t.equals(";")){
				for (int j = 0; j<t.length(); j++){
					if (t.charAt(j) == ';'){
						setErrorPharse(getErrorPharse() + "In line " + line + " : " + t + ""
								+ "There is no separator between "+ t +"and ;"
										+ " and treat as a token which is invalid.\n");
					}
					if (!isInteger(t)){
						if (special.contains(t.charAt(j))){
							setErrorPharse(getErrorPharse() + "In line " + line + " : " +t  + " is invalid token.\n");
						}
					}
				}
			}
		}	
	}
	public String getLine(){
		return line+"";
	}
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}
	public String getErrorPharse() {
		return errorPharse;
	}
	public void setErrorPharse(String errorPharse) {
		this.errorPharse = errorPharse;
	}
}
