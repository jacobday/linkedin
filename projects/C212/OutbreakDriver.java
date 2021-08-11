import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

////////////////////////////////////////////////////////////////////////////////////
//	
//  C212
//
//	Outbreak Simulator
//	@Author  Jacob Day day6
//	Last Edited: 04/03/2020 
//
//	Midterm Exam Part 2
//	
//////////////////////////////////////////////////////////////////////////////////

public class OutbreakDriver extends JPanel implements ActionListener {
	
	public final static int FRAME_WIDTH = 1000;
    public final static int FRAME_HEIGHT = 800;
    public static int population;
    int time = 0;
    
    ArrayList<Person> people = new ArrayList<Person>();
    ArrayList<Graph> graph = new ArrayList<Graph>();
    
    public static void main(String[] args) {

    	Scanner sc = new Scanner(System.in);
		
		System.out.print("Population: ");
		population = sc.nextInt();
		
		sc.close();
		
    	OutbreakDriver simulation = new OutbreakDriver();
    }
    
    public void paint(Graphics g) {
    	super.paintComponent(g);
    	
    	
    	for (Person p : people) {
    		p.paint(g);
    	}
    	
    	for(Graph x : graph) {
    		x.paint(g);
    	}
    	
    	g.setColor(Color.black);
    	g.drawString("Healthy: " + Person.totalHealthy, 20, 20);
    	g.drawString("Sick: " + Person.totalSick, 20, 40);
    	g.drawString("Recovered: " + Person.totalRecovered, 20, 60);
    }
    
    public void collide() {
    	for (int i = 0; i < people.size(); i++) {
    		for (int j = i+1; j < people.size(); j++) {
    			people.get(i).collision(people.get(j));
    		}
    	}
    }
    
    public void graph() {
    	
    	int graphX = 20;
    	int graphY = 80;
    	time += 10;
    	
    		if (Person.totalHealthy > 0 && (Person.totalHealthy + Person.totalRecovered) < population) {
    			graph.add(new Graph(time/100 + graphX, graphY, time/100 + graphX, graphY+Person.totalHealthy, 0)); // Healthy Graph
    		}
    		if (Person.totalSick > 0) {
    			graph.add(new Graph(time/100 + graphX, graphY+population, time/100 + graphX, (graphY+population)-Person.totalSick, 1)); // Sick Graph
    		}
    		if (Person.totalRecovered > 0 && (Person.totalRecovered + Person.totalHealthy) < population) {
    			graph.add(new Graph(time/100 + graphX, graphY, time/100 + graphX, graphY+Person.totalRecovered, 2)); // Recovered Graph
    		}
    }
    
    public OutbreakDriver() {
    	
    	JFrame frame = new JFrame();
    	frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	for (int i = 0; i < population; i++) {
    		people.add(new Person());
    	}
    	
    	Timer t = new Timer(10,this);
    	t.start();
    	
    	frame.add(this);
    	frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		collide();
		graph();
	}
}
