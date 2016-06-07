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

public class LineComponent extends JComponent {
	private String name;
	private int line_x = 100;
	private int line_y = 100;
	private int line_x2 = 20;
	private int line_y2 = 30;
	private Color color = Color.black;
	HashMap<String, Color> colorMap = new HashMap<>();
	private Rectangle box;
	

	public LineComponent() {
		GenColorMap();
	}
	public LineComponent(String name, int x1, int y1, int x2, int y2, String c) {
		GenColorMap();
		this.setName(name);
		this.setLine_x(x1);
		this.setLine_y(y1);
		this.setLine_x2(x2);
		this.setLine_y2(y2);
		this.setColor(colorMap.get(c));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(box);
		g2.drawLine(line_x, line_y, line_x2, line_y2);
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

	public int getLine_x() {
		return line_x;
	}

	public void setLine_x(int bOX_X) {
		line_x = bOX_X;
	}

	public int getLine_y() {
		return line_y;
	}

	public void setLine_y(int bOX_Y) {
		line_y = bOX_Y;
	}

	public int getLine_x2() {
		return line_x2;
	}

	public void setLine_x2(int bOX_WIDTH) {
		line_x2 = bOX_WIDTH;
	}

	public int getLine_y2T() {
		return line_y2;
	}

	public void setLine_y2(int bOX_HEIGHT) {
		line_y2 = bOX_HEIGHT;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
