package view;


import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class CustomTextArea extends JScrollPane{
	
	public JTextArea textArea = new JTextArea();
	
	public CustomTextArea() {
		final JTextArea lineNo = new JTextArea("1    ");
		lineNo.setBackground(Color.LIGHT_GRAY);
		lineNo.setEditable(false);
//		lineNo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		textArea.getDocument().addDocumentListener(new DocumentListener() {
			public String getText(){
				int caretPosition = textArea.getDocument().getLength();
				Element root = textArea.getDocument().getDefaultRootElement();
				String text = "1    " + "\n";//System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					String space = " ";
					if (i < 10){
						space = "    ";
					}else if (i < 100){
						space = "   ";
					}else if (i < 1000){
						space = "  ";
					}
					text += i + space + "\n";//System.getProperty("line.separator");
				}
				return text;
			}
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNo.setText(getText());
			}
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNo.setText(getText());
			}
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNo.setText(getText());
			}
		});
		getViewport().add(textArea);
		setRowHeaderView(lineNo);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
}
