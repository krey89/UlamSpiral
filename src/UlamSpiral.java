import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
//import java.awt.event.*;



class SpiralPanel extends JPanel{

	int height, width, diameter = 2, plotDistance = 1;
	double max;


	public SpiralPanel(int frameHeight, int frameWidth, double maxNum) {
		height = frameHeight;
		width = frameWidth;
		max = maxNum;
	
	}
	
	
	
	private void plot(String s, int x, int y, Graphics2D g2d) {
		//the numbers are sent in as strings 
		//potential to store the primes in some data structure
		int Xcoor = ((height / 2) + x) + (x * plotDistance);
		int Ycoor = ((width / 2) + y) + (y * plotDistance);
		// g2d.fillOval(((height/2)+x)+(x*plotDistance),((width/2)+y)+(y*plotDistance),diameter,diameter);
		g2d.fillOval(Xcoor, Ycoor, diameter, diameter);
		
	}

	private boolean isPrime(long number) {
		if (number == 2)
			return true;
	    if (number%2==0) return false;

		for (int i = 3; i*i <= number; i+=2) {

			//if (number % i == 0 && i != number)
			if ((number % i) == 0  )

				return false;
		}
		return true;
	}

	private void drawSpiral(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int primecount = 0;
		int counter = 1;
		int directcount = 0; // 0 = right or up,   1 = left or down;
		int X = 0, Y = 0;
		int currentNum = 1;
		setBackground(Color.black);
		g.setColor(Color.red);
		//red marks 1, the center of the spiral
		plot(Integer.toString(currentNum), X, Y, g2d);
		currentNum++;
		g.setColor(Color.blue);
		while (currentNum <= max) {
			//for my Colombian heritage this code colors
			//the stripe in the Colombian flag colors
			//otherwise it has a black background with blue plots
			/*if(currentNum < (max/3)){
				g.setColor(Color.red);

			}if((currentNum > max/3) && (currentNum < ((max*2)/3))){
				g.setColor(Color.blue);

			}if(currentNum > ((max*2)/3)){
				g.setColor(Color.yellow);

			}*/
			if (directcount == 0) {
				// move right
				for (int i = 0; i < counter; i++) {
					if (isPrime(currentNum)){
						plot(Integer.toString(currentNum), ++X, Y, g2d);
						primecount++;
						System.out.println(currentNum);

				}else
						++X;
					currentNum++;
				}
				// move Up
				for (int i = 0; i < counter; i++) {
					if (isPrime(currentNum)){
						plot(Integer.toString(currentNum), X, --Y, g2d);
						primecount++;
						System.out.println(currentNum);

					}else
						--Y;
					currentNum++;
				}

				directcount = 1;
				counter++;
			} else {
				// move left
				for (int i = 0; i < counter; i++) {
					if (isPrime(currentNum)){
						plot(Integer.toString(currentNum), --X, Y, g2d);
						primecount++;

						System.out.println(currentNum);

					}else
						--X;
					currentNum++;
				}
				// move down
				for (int i = 0; i < counter; i++) {
					if (isPrime(currentNum)){
						plot(Integer.toString(currentNum), X, ++Y, g2d);
						primecount++;

						System.out.println(currentNum);

					}else
						++Y;
					currentNum++;
				}
				directcount = 0;
				counter++;
			}
		}
		System.out.println("This spiral has: "+primecount+" prime numbers");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSpiral(g);
	}


}

@SuppressWarnings("serial")
public class UlamSpiral extends JFrame{
	int height, width;
	 
	public UlamSpiral(int spiralheight, int spiralwidth) {
		height = spiralheight;
		width = spiralwidth;
		initialize();
		setVisible(true);
	}

	private void initialize() {
		setSize(height, width);

		setTitle("Ulam Spiral");
		SpiralPanel bs = new SpiralPanel(height, width, 500000);
		//JScrollPane scp = new JScrollPane(bs,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			     // JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scp.setBounds(5,5,100,100);
		 
		//getContentPane().add(scp,BorderLayout.CENTER);
		
		getContentPane().add(bs,BorderLayout.CENTER);
        bs.setEnabled(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UlamSpiral(1000, 1000);
			}
		});
	}

	
}

