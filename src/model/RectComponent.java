package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.JComponent;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class RectComponent extends JComponent {
	private String name;
	private int BOX_X = 100;
	private int BOX_Y = 100;
	private int BOX_WIDTH = 20;
	private int BOX_HEIGHT = 30;
	private Color color = Color.black;
	HashMap<String, Color> colorMap = new HashMap<>();
	private Rectangle box;

	public RectComponent() {
		GenColorMap();
		box = new Rectangle(BOX_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
	}
	public RectComponent(String name, int x1, int y1, int x2, int y2, String c) {
		GenColorMap();
		this.setName(name);
		this.setBOX_X(x1);
		this.setBOX_Y(y1);
		this.setBOX_WIDTH(x2);
		this.setBOX_HEIGHT(y2);
		this.setColor(colorMap.get(c));
		box = new Rectangle(BOX_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(box);
	}

	public void moveBy(int dx, int dy) {
		box.translate(dx, dy);
	}
	
	public void GenColorMap(){
		colorMap.put("red",Color.red);
		colorMap.put("blue",Color.blue);
		colorMap.put("yellow",Color.yellow);
		colorMap.put("green",Color.green);
		colorMap.put("orange",Color.orange);
		colorMap.put("black",Color.black);
		colorMap.put("pink",Color.pink);
		colorMap.put("cyan",Color.cyan);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBOX_X() {
		return BOX_X;
	}

	public void setBOX_X(int bOX_X) {
		BOX_X = bOX_X;
	}

	public int getBOX_Y() {
		return BOX_Y;
	}

	public void setBOX_Y(int bOX_Y) {
		BOX_Y = bOX_Y;
	}

	public int getBOX_WIDTH() {
		return BOX_WIDTH;
	}

	public void setBOX_WIDTH(int bOX_WIDTH) {
		BOX_WIDTH = bOX_WIDTH;
	}

	public int getBOX_HEIGHT() {
		return BOX_HEIGHT;
	}

	public void setBOX_HEIGHT(int bOX_HEIGHT) {
		BOX_HEIGHT = bOX_HEIGHT;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

}
