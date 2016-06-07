package model;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class DataObject {
	
	private String name = "";
	private String type = "";
	private String strVal = "";
	private String intVal = "";
	private String lineNumber = "";
	
	public DataObject(String name, String type, String strVal, String intVal, String line){
		this.setName(name);
		this.setType(type);
		this.setStrVal(strVal);
		this.setIntVal(intVal);
		this.setLineNumber(line);
	}
	
	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String toString(){
		return getName()+" | "+getType()+" | "+getStrVal()+" | "+getIntVal()+" | "+getLineNumber();
	}
	public String toString_2(){
		return "Data:\"" + getName()+"\" |Type:\""+getType()+"\" |Line: "+getLineNumber()+".";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStrVal() {
		return strVal;
	}
	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}
	public String getIntVal() {
		return intVal;
	}
	public void setIntVal(String intVal) {
		this.intVal = intVal;
	}
	
	
	

}
