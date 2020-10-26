import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class BoxMaker extends GraphicsProgram {
	
	//create our variables
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	HashMap<String, GObject> boxes = new HashMap<String, GObject>();
	JTextField tField;
	JButton addB;
	JButton removeB;
	JButton clearB;
	GObject click;
	GPoint last;
	
	public void init() 
	{
		//make our GUI and add mouse/action listeners
		boxes = new HashMap<String, GObject>();
		tField = new JTextField(20);
		tField.addActionListener(this);
		addB = new JButton("Add");
		removeB = new JButton("Remove");
		clearB = new JButton("Clear");
		add(new JLabel("Name"), SOUTH);
		add(tField, SOUTH);
		add(addB, SOUTH);
		add(removeB, SOUTH);
		add(clearB, SOUTH);
		addActionListeners();
		addMouseListeners();
	}
	
	//code for adding a box
	private void add(String name)
	{
		GCompound box = new GCompound();
		GRect outline = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		box.add(outline, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
		box.add(label, -label.getWidth() / 2, label.getAscent() / 2);
		add(box, getWidth()/2, getHeight()/2);
		boxes.put(name,box);
	}
	
	//remove a box
	private void remove(String name)
	{
		GObject o = boxes.get(name);
		if(o != null)
		{
			remove(o);
		}
	}
	
	//calls on actionPerformed to use the add/remove methods
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == addB)
		{
			add(tField.getText());
		}
		if (source == removeB)
		{
			remove(tField.getText());
		}
		if (source == clearB)
		{
			removeAll();
		}
	}
	
	public void mousePressed(MouseEvent e)
	{
		last = new GPoint(e.getPoint());
		click = getElementAt(last);
	}
	
	public void mouseDragged(MouseEvent e)
	{
		if (click != null)
		{
			click.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	

	
	
	

}
