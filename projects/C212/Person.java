import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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

public class Person {

	int x;
	int y;
	int vx;
	int vy;
	int condition; // Healthy = 0 , Sick = 1, Recovered = 2
	static int initialSick = 1;  // When program starts, 1 person is sick
	static int totalSick = 0;
	static int totalRecovered = 0;
	static int totalHealthy = OutbreakDriver.population;
	int time = 0;
	int recoveryTime = (int) (Math.random() * 20000 + 15000); // Recover after 15-20 seconds
	
	public Person() {
		
		this.x = (int) (Math.random() * OutbreakDriver.FRAME_WIDTH);
		this.y = (int) (Math.random() * OutbreakDriver.FRAME_HEIGHT);
		
		if (initialSick > 0) {  // When program starts, 1 person is sick
			condition = 1;
			initialSick -= 1;
			totalSick++;
			totalHealthy--;
		}
		else {
			condition = 0;
		}
		
		int direction = (int) Math.round(Math.random());
		
		if (direction == 1) { // random x direction
			vx = 1;
		}
		else {
			vx = -1;
		}
		
		direction = (int) Math.round(Math.random());
		
		if (direction == 1) { // random y direction
			vy = -1;
		}
		else {
			vy = 1;
		}
		
	}
	
	public void paint(Graphics g) {
		
		Color healthy = Color.blue;
		Color sick = new Color(139,69,19); // Brown
		Color recovered = new Color(255,20,147); // Pink
		
		if (condition == 0) {
			g.setColor(healthy);
		}
		else if (condition == 1) {
			g.setColor(sick);
		}
		else if (condition == 2) {
			g.setColor(recovered);
		}
		
		g.fillOval(x, y, 10, 10);
		
		x += vx;
		y += vy;
		
		if (x <= 0 || x >= OutbreakDriver.FRAME_WIDTH) {
			vx *= -1;
		}
		
		if (y <= 0 || y >= OutbreakDriver.FRAME_HEIGHT) {
			vy *= -1;
		}
		
		time += 10;
		if (time >= recoveryTime && condition == 1) {
			condition = 2;  // recover after 15-20 seconds
			totalRecovered += 1;
			totalSick -= 1;
		}
	}
	
	public void collision(Person p) {
		Rectangle p1 = new Rectangle (x, y, 10, 10);
		Rectangle p2 = new Rectangle (p.x, p.y, 10 ,10);
		
		if (p1.intersects(p2)) {
			if (this.condition == 1 && p.condition == 0) {
				p.condition = 1;
				p.vx *= -1;
				p.vy *= -1;
				this.vx *= -1;
				this.vy *= -1;
				totalSick++;
				totalHealthy -= 1;
			}
			else if (this.condition == 0 && p.condition == 1) {
				this.condition = 1;
				p.vx *= -1;
				p.vy *= -1;
				this.vx *= -1;
				this.vy *= -1;
				totalSick++;
				totalHealthy -= 1;
			}
			else {
				p.vx *= -1;
				p.vy *= -1;
				this.vx *= -1;
				this.vy *= -1;
			}
		}
	}
}
