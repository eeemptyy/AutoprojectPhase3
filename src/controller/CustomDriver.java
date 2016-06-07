package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.CheckError;
import model.DataObject;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class CustomDriver {

	private String[] dataArr;
	private String patternSize = "(\\bsize\\b)(\\s+)(-?\\d{3,})(\\s+)(-?\\d{3,})(\\s+)([\\[])";// (.*)";//([\\]])";
	private String patternMove = "(\\bmove\\b)(\\s+)(\\w{1,8})(\\s+)(\\w+)(\\s+)(\\w+)(\\s+)([;])";
	private String patternRect = "(\\brect\\b)(\\s+)(\\w{1,8})(\\s+)(-?\\d+)(\\s+)(-?\\d+)(\\s+)(\\d+)"
			+ "(\\s+)(\\d+)(\\s+)(\\w{1,8})(\\s+)([;])";
	private String patternLoop = "(\\bfor\\b)(\\s+)(\\w{1,8})(\\s+)([=])(\\s+)(-?\\d+)(\\s+)(\\bto\\b)(\\s+)"
			+ "(-?\\d+)(\\s+)(\\bby\\b)(\\s+)(\\d+)(\\s+)(\\bdo\\b)";// (\\s+)";//(.*)(\\bend\\b)(\\s+)([;])";
	private String patternLine = "(\\bline\\b)(\\s+)(\\w{1,8})(\\s+)(\\w+)(\\s+)(\\w+)(\\s+)(\\w+)"
			+ "(\\s+)(\\w+)(\\s+)(\\w{1,8})(\\s+)([;])";
	private String patternAssign = "(\\w{1,8})(\\s+)([=])(\\s+)(\\w+)(\\s+)([;])";
	private String pattern;
	private ArrayList<String> outputTable = new ArrayList<String>();
	private ArrayList<String> colorMap = new ArrayList<String>();
	public ArrayList<String> errorLogs = new ArrayList<String>();
	public ArrayList<DataObject> dataObjectList = new ArrayList<>();
	private HashMap<String, String> typeMap = new HashMap<String, String>();

	public HashMap<String, String> getTypeMap() {
		return typeMap;
	}
	private String fullData = "";
	private CheckError checker = new CheckError();

	public CustomDriver() { GenTypeMap_ColorMap(); }

	public CustomDriver(String[] dataArr) {
		this.dataArr = dataArr;
		GenTypeMap_ColorMap();
		pattern = patternSize + "|" + patternMove + "|" + patternRect + "|" + patternLoop + "|" + patternLine + "|"
				+ patternAssign + "|([\\]])|((\\bend\\b)(\\s+)([;]))";
	}
	
	public void GenTypeMap_ColorMap(){
		typeMap.put("size", "s");
		typeMap.put("move", "m");
		typeMap.put("line", "l");
		typeMap.put("rect", "r");
		typeMap.put("for", "f");
		typeMap.put("to", "t");
		typeMap.put("by", "b");
		typeMap.put("do", "d");
		typeMap.put("end", "e");
		typeMap.put("=", "=");
		typeMap.put(";", ";");
		typeMap.put("[", "[");
		typeMap.put("]", "]");
		colorMap.add("red");
		colorMap.add("blue");
		colorMap.add("yellow");
		colorMap.add("green");
		colorMap.add("orange");
		colorMap.add("black");
		colorMap.add("pink");
		colorMap.add("cyan");
	}

	public ArrayList<DataObject> initOutputData() {
		for (int i = 0; i < outputTable.size(); i++) {
			String[] temp = outputTable.get(i).split("\\+");
			String temp0 = temp[0];
			String token = temp0;
			String typee = "v";
			String str_v = token;
			String int_v = "0";
			String line_number = temp[1];
			if (typeMap.containsKey(token)) {
				typee = (String) typeMap.get(token);
				str_v = " ";
				int_v = " ";
			}
			if (colorMap.contains(token)) {
				typee = "c";
			}
			if (isInteger(token)) {
				int_v = "" + token;
				typee = "i";
			}
			fullData += token + "+" + typee + "+" + str_v + "+" + int_v + "+" + line_number +"\n";
		}
		initFullDataObject(fullData);
		return dataObjectList;
	}
	public void initFullDataObject(String fullData){
		String[] tempData = fullData.split("\n");
		for (int i = 0; i<tempData.length; i++){
			String[] temp = tempData[i].split("\\+");
			dataObjectList.add(new DataObject(temp[0], temp[1], temp[2], temp[3], temp[4]));
		}
//		System.out.println("Printing data object.");
//		for (int i = 0; i<dataObjectList.size(); i++){
//			System.out.println(dataObjectList.get(i).toString());
//		}System.out.println("Printing success.");
	}

	public void matchPattern() {
		fullData = "";
		Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
		errorLogs.clear();
		for (int i = 0; i < this.dataArr.length; i++) {
//			System.out.println("This is i from CustomDriver " + i + " got value " + this.dataArr[i]);
			checker.MainChecker(this.dataArr[i], i);
			Matcher m = r.matcher(this.dataArr[i].toLowerCase());
			if (m.find()) {
				for (int j = 1; j <= m.groupCount(); j++) {
					String temp = m.group(j);
					if (temp != null) {
						if (!m.group(j).matches("\\s+")) {
//							System.out.println("Found value at [i=" + i + " j=" + j + "] is " + temp);
							if (!temp.equals("end ;")) {
								outputTable.add(temp+"+"+checker.getLine());
							}
						}
					}
				}
			}
		}
		String[] t = checker.getErrorPharse().split("\n");
		for (int i = 0; i<t.length;i++){
			errorLogs.add(t[i]);			
		}
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
}
