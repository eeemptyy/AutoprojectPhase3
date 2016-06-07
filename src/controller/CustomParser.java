package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.naming.spi.DirStateFactory.Result;

import model.DataObject;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class CustomParser {

	private ArrayList<DataObject> tokenData;
	private int nLine = 0;
	private Stack stack = new Stack();
	private String[] StateHeader = { ";", "=", "[", "]", "b", "c", "d", "e", "f", "i", "l", "m", "r", "s", "t", "v",
			"$" };
	private String[] ActionHeader = { "L", "P", "S", "T", "Z" };
	private String[] rulesReduce = { "P'", "P", "Z", "L", "L", "S", "S", "S", "S", "S", "T", "T" };
	private List<String> rulesHeader = new ArrayList<String>(Arrays.asList(rulesReduce));
	private String[][] rules = { { "P" }, { "]", "L", "[", "Z" }, { "i", "i", "s" }, { ";", "S" }, { ";", "S", "L" },
			{ "c", "T", "T", "T", "T", "v", "l" }, { "c", "T", "T", "T", "T", "v", "r" }, { "T", "T", "v", "m" },
			{ "e", "L", "d", "T", "b", "T", "t", "T", "=", "v", "f" }, { "T", "=", "v" }, { "v" }, { "i" } };
	private String[][] parserTableAct = { { " ", "2", " ", " ", "1" }, { " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { "12", " ", "7", " ", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", "14", " ", " " }, { " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", "25", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", "26", " " }, { " ", " ", " ", "28", " " },
			{ " ", " ", " ", "23", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " },
			{ " ", " ", " ", "30", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", "31", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", "32", " " }, { " ", " ", " ", "33", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", "36", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", "34", " " }, { " ", " ", " ", "38", " " },
			{ " ", " ", " ", "37", " " }, { " ", " ", " ", "39", " " }, { " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " " }, { " ", " ", " ", "43", " " }, { " ", " ", " ", " ", " " },
			{ "45", " ", "7", " ", " " }, { " ", " ", "14", " ", " " }, { " ", " ", " ", " ", " " } };
	private String[][] parserTableState = { // [state][header]
			// 0    1    2    3    4    5    6    7    8    9    10   11   12   13    14   15   16
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "s3", " ", " ", " " },
			{ " ", " ", "s4", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "acc" },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s5", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", "s10", " ", "s11", "s8", "s9", " ", " ", "s13", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s6", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", "r2", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ "s21", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "s20", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "s16", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "s17", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "s19", " " },
			{ " ", " ", " ", "s15", " ", " ", " ", " ", "s10", " ", "s11", "s8", "s9", " ", " ", "s13", " " },
			{ " ", "s18", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ "s29", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "r1" },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", "s27", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", "r3", " ", " ", " ", "r3", "r3", " ", "r3", "r3", "r3", " ", " ", "r3", " " },
			{ "r11", " ", " ", " ", "r11", "r11", "r11", " ", " ", "r11", " ", " ", " ", " ", "r11", "r11", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ "r10", " ", " ", " ", "r10", "r10", "r10", " ", " ", "r10", " ", " ", " ", " ", "r10", "r10", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ "r9", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", "r4", " ", " ", " ", "r4", "r4", " ", "r4", "r4", "r4", " ", " ", "r4", " " },
			{ "r7", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "s35", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", "s42", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", "s40", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", "s41", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ "r5", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ "r6", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "s22", " ", " ", " ", " ", " ", "s24", " " },
			{ " ", " ", " ", " ", " ", " ", "s44", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", "s10", " ", "s11", "s8", "s9", " ", " ", "s13", " " },
			{ " ", " ", " ", " ", " ", " ", " ", "s46", "s10", " ", "s11", "s8", "s9", " ", " ", "s13", " " },
			{ "r8", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " } };

	List<String> ActionList;
	List<String> StateList;
	private int row, column;
	private String status = "Compiling";

	public String getStatus() {
		return status;
	}

	public CustomParser(ArrayList<DataObject> token) {
		this.tokenData = token;
		this.tokenData.add(new DataObject("$", "$", "", "", ""));
		StateList = new ArrayList<String>(Arrays.asList(StateHeader));
		ActionList = new ArrayList<String>(Arrays.asList(ActionHeader));
	}

	public void checkSyntax() {
		System.out.println("Starting parser.");
		try {
			stack.push(0);
			for (int i = 0; i < tokenData.size(); i++) {
				nLine = i;
				DataObject temp = tokenData.get(i);
				row = (int) stack.peek();
				column = StateList.indexOf(temp.getType());
				String datafromPaserTable = parserTableState[row][column];
				if (temp.getType().equals("$") && datafromPaserTable.equals("acc")) {
					System.out.println("ACCEPTED");
					status = "ACCEPTED";
					break;
				}
				
				String deri = datafromPaserTable.substring(0, 1);
				int stateNo = Integer.parseInt(datafromPaserTable.substring(1, datafromPaserTable.length()));
				if (deri.equals("s")) {
					System.out.println("Pushing");
					stack.push(temp.getType());
					stack.push(stateNo);
				} else {
					System.out.println("Reducing");
					reduce(stateNo);
					i = i - 1;
				}
				System.out.println("Stack After push > " + stack.toString());
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
//			status = "Syntax  Error at "+tokenData.get(nLine).toString_2()+" :Line= "+tokenData.get(nLine).getLineNumber();
			status = "Syntax Error.";
			System.out.println(status);
		}
	}

	public void reduce(int rulesNo) {
		boolean error = false;
		try {
			String[] checker = rules[rulesNo];
			for (int i = 0; i < checker.length; i++) {
				stack.pop();
				String fromStack = (String) stack.pop();
				if (!fromStack.equals(checker[i])) {
					System.out.println("Syntax Error. ");
					status = "Syntax Error.";
					error = true;
					break;
				}
			}
			if (!error) {
				String result = rulesHeader.get(rulesNo);
				row = (int) stack.peek();
				column = ActionList.indexOf(result);
				int state = Integer.parseInt(parserTableAct[row][column]);
				// System.out.println("X>" + result + "<X");
				// System.out.println("XX-" + state + "-XX");
				stack.push(result);
				stack.push(state);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
			status = "Syntax  Error.2";
			System.out.println(status);
		}
	}

}
