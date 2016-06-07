package controller;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class CustomScanner {
	
	private String data, outData;
	private String[] dataArr;
	
	public String[] initArr(String data){
		this.data = data;
		this.outData = this.data.replaceAll("(?m)%.*$","");
		dataArr = this.outData.split("\n");
		return dataArr;
	}
	
	public String toString(){
		return outData;
	}
}
