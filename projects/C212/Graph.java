import java.awt.Color;
import java.awt.Graphics;

////////////////////////////////////////////////////////////////////////////////////
//
//	C212
//
//	Outbreak Simulator
//	@Author  Jacob Day day6
//	Last Edited: 04/03/2020 
//
//	Midterm Exam Part 2
//
//////////////////////////////////////////////////////////////////////////////////

public class Graph {
	
	int x1, y1, x2, y2;
	int condition;
	
	public Graph (int x1, int y1, int x2, int y2, int condition) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.condition = condition;
	}

	public void paint(Graphics g) {
		
		if (condition == 0) {
			g.setColor(Color.blue); // Healthy = Blue
		}
		else if (condition == 1) {
			g.setColor(new Color(139,69,19)); // Sick = Brown
		}
		else if (condition == 2) {
			g.setColor(new Color(255,20,147)); // Recovered = Pink
		}
		
		g.drawLine(x1, y1, x2, y2);
	}
}
