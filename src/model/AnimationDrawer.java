package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author นายจอมพล	เสริมสุข   	5610450063
 * 
 * @author Jompol Sermsook  5610450063 
 *
 */

public class AnimationDrawer implements ActionListener{
	
	RectComponent r ;
	private ArrayList<DataObject> dataObjectsList = new ArrayList<>();
	private JFrame aniFrame = null;
	private ArrayList<RectComponent> BoxList = new ArrayList<>();
	private ArrayList<String> BoxNameList = new ArrayList<>();
	private boolean stateLock = false;
	private Timer timer;

	public AnimationDrawer(ArrayList<DataObject> data, JFrame ani) {
		dataObjectsList = data;
		aniFrame = ani;
		timer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
//			//...Update the progress bar...
//		    	try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		    	timer.stop();
//		        if (/* thread is done */) {
//		            timer.stop();
//		      
//		        }
		    }    
		});
		for (int i = 0; i < dataObjectsList.size(); i++) {
			DataObject temp = dataObjectsList.get(i);
			if (temp.getType().equals("s")) {
				System.out.println("Size Action");
				i = sizeAct(i);
			}else if (temp.getType().equals("r")) {
				System.out.println("Rect Action");
				i = rectAct(i);
			}else if (temp.getType().equals("m")) {
				System.out.println("Move Action");
				i = moveAct(i);
			}
			aniFrame.setVisible(true);
		}
	}
	
	public int moveAct(int in){
		if (!stateLock){
			System.out.println("Start "+stateLock);
			int data = 3;
			int out = in+data;
			ArrayList<DataObject> temp = new ArrayList<>();
			stateLock = true;
			for (int i = in + 1; i <= out; i++) {
				temp.add(dataObjectsList.get(i));
			}
			String name = temp.get(0).getStrVal();
			int x1 = Integer.parseInt(temp.get(1).getIntVal());
			int y1 = Integer.parseInt(temp.get(2).getIntVal());
			RectComponent moveItem = BoxList.get(BoxNameList.indexOf(name));
			moveItem.moveBy(x1+50, y1+30);
			stateLock = false;
			System.out.println("End "+stateLock);
			return out;
		}
		return in;
	}
//	public int moveAct(int in){
//		if (!stateLock){
//			System.out.println("Start "+stateLock);
//			int data = 6;
//			int out = in+data;
//			ArrayList<DataObject> temp = new ArrayList<>();
//			stateLock = true;
//			for (int i = in + 1; i <= out; i++) {
//				temp.add(dataObjectsList.get(i));
//			}
//			stateLock = false;
//			System.out.println("End "+stateLock);
//			return out;
//		}
//		return in;
//	}

	public int rectAct(int in){
		if (!stateLock){
			System.out.println("Start "+stateLock);
			int data = 6;
			int out = in+data;
			ArrayList<DataObject> temp = new ArrayList<>();
			stateLock = true;
			for (int i = in + 1; i <= out; i++) {
				temp.add(dataObjectsList.get(i));
			}
			String name = temp.get(0).getStrVal();
			int x1 = Integer.parseInt(temp.get(1).getIntVal());
			int y1 = Integer.parseInt(temp.get(2).getIntVal());
			int x2 = x1+Integer.parseInt(temp.get(3).getIntVal());
			int y2 = y1+Integer.parseInt(temp.get(4).getIntVal());
			String color = temp.get(5).getStrVal();
			RectComponent rect = new RectComponent(name, x1, y1, x2, y2, color);
			BoxList.add(rect);
			BoxNameList.add(rect.getName());
			aniFrame.add(rect);
			stateLock = false;
			System.out.println("End "+stateLock);
			return out;
		}
		return in;
	}
	
	public int sizeAct(int in) {
		if (!stateLock) {
			System.out.println("Start "+stateLock);
			int data = 2;
			int out = in + data;
			ArrayList<DataObject> temp = new ArrayList<>();
			stateLock = true;
			for (int i = in + 1; i <= out; i++) {
				temp.add(dataObjectsList.get(i));
			}
			int size1 = Integer.parseInt(temp.get(0).getIntVal());
			int size2 = Integer.parseInt(temp.get(1).getIntVal());
			aniFrame.setSize(size1, size2);
			stateLock = false;
			System.out.println("End "+stateLock);
			return out;
		}
		return in;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
