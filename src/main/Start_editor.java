package main;

import java.awt.EventQueue;

import view.Main_frame;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class Start_editor {
	

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main_frame();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
