package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import model.DataObject;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class CustomTokenizer {
	
	private ArrayList<DataObject> DataObjectList;
	private ArrayList<String> alreadyAdd = new ArrayList<String>();
	private JTable symbolTable;
	private JTable outputTable;
	private HashMap<String, String> typeMap = new CustomDriver().getTypeMap();

	public CustomTokenizer(){
		
	}
	
	public CustomTokenizer(ArrayList<DataObject> data){
		this.DataObjectList = data;
	}
	public JTable initOutputTable(){
		outputTable = new JTable();
		DefaultTableModel outTableModel = new DefaultTableModel(0, 0);
		String[] headerOutput = new String[] {"Type", "STR Value", "INT Value"};
		outTableModel.setColumnIdentifiers(headerOutput);
		outputTable.setModel(outTableModel);
		outTableModel.addRow(new Object[] {"Type", "STR Value", "INT Value"});
		outTableModel.addRow(new Object[] {"====","=========","========="});
		for (int i = 0; i < DataObjectList.size(); i++){
			DataObject tempData = DataObjectList.get(i);
			outTableModel.addRow(new Object[] {tempData.getType(),tempData.getStrVal(),tempData.getIntVal()});
		}
		outputTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		return outputTable;
	}
	public JTable initSymbolTable(){
		symbolTable = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);
		String[] header = new String[] {"Token", "Type", "STR Value", "INT Value"};
		tableModel.setColumnIdentifiers(header);
		symbolTable.setModel(tableModel);
		tableModel.addRow(new Object[] {"Token", "Type", "STR Value", "INT Value"});
		tableModel.addRow(new Object[] {"     ","====","=========","========="});
		for (int i = 0; i < DataObjectList.size(); i++){
			DataObject tempData = DataObjectList.get(i);
			if ((!typeMap.containsKey(tempData.getName())&&(!alreadyAdd.contains(tempData.getName())))){
				tableModel.addRow(new Object[] {tempData.getName(), tempData.getType(), tempData.getStrVal(),tempData.getIntVal()});
				alreadyAdd.add(tempData.getName());
			}
		}
		symbolTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		return symbolTable;
	}

}
