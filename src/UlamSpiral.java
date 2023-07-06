import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class UlamSpiral{

 	private static boolean isPrime(long number) {
		if (number == 2)
			return true;
	    if (number%2==0) return false;

		for (int i = 3; i*i <= number; i+=2) {
			if ((number % i) == 0  )
				return false;
		}
		return true;
	}	
 


private static ArrayList<Integer> fillPrimesArray(int max) {
	ArrayList<Integer> primes = new ArrayList<Integer>();
	int counter = 2;
	while (counter <= max){
		if(isPrime(counter)){
			primes.add(counter);
		}		
		if(counter == 2){
		counter++;
		}else{
		counter+=2;
		}

	}
	return primes;
}	

public static void main(String[] args) {
	int max = 0;
	System.out.println("Enter an integer less than 250000 ");
	Scanner scanner = new Scanner(System.in);
	int input =0;
	if(scanner.hasNextInt()){
		input = scanner.nextInt();
		if(input>0 && input <= 250000){
			max = input;
	scanner.close();
	ArrayList<Integer> primes = fillPrimesArray(max);
	//System.out.println(primes);
    JPanel container = new SpiralPanel(1000,1000,max,primes);
    container.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    JScrollPane scroll = new JScrollPane(container);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(scroll);
    f.pack();
    f.setSize(1000, 1000);
    f.setVisible(true);
	NumberFormat myFormat = NumberFormat.getInstance();
	f.setTitle("This spiral expands to "+myFormat.format(max)+" and contains "+myFormat.format(primes.size())+" primes");
	
		}else{
		System.out.println("Please Enter Positive Integer Less than Max");
		}
  		
		}else{
			System.out.println("Enter Valid Input");
  		}
	}
}

class SpiralPanel extends JPanel implements Scrollable{

  public Dimension getPreferredSize() {
    return getPreferredScrollableViewportSize();
  }
  public Dimension getPreferredScrollableViewportSize() {
    if (getParent() == null)
      return getSize();
    Dimension d = getParent().getSize();
    return new Dimension(d.width*2 , d.height*2);
  }
  public int getScrollableBlockIncrement(Rectangle visibleRect,
      int orientation, int direction) {
    return 50;
  }
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
      int direction) {
    return 10;
  }
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }
  public boolean getScrollableTracksViewportWidth() {
    return getParent() != null ? getParent().getSize().width > getPreferredSize().width
        : true;
  }	

	int height, width, diameter = 2, plotDistance = 1;
	int max;
  	ArrayList<Integer> primesList;

 	public SpiralPanel(int frameHeight, int frameWidth, int maxNum, ArrayList<Integer> primes) {
		height = frameHeight;
		width = frameWidth;
		max = maxNum;
		primesList = primes;
	
	} 
	

	
	private void plot(String s, int x, int y, Graphics2D g2d) {
		int Xcoor = ((height / 2) + x) + (x * plotDistance);
		int Ycoor = ((width / 2) + y) + (y * plotDistance);
		g2d.fillOval(Xcoor, Ycoor, diameter, diameter);		
	}



	private void drawSpiral(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//int primecount = 0;
		int counter = 1;
		int directcount = 0; // 0 = right or up,   1 = left or down;
		int X = 0, Y = 0;
		int currentNum = 1;
		setBackground(Color.black);
		g.setColor(Color.white);
		//white marks 1, the center of the spiral
		plot(Integer.toString(currentNum), X, Y, g2d);
		currentNum++;
		while (currentNum <= max) {
			if(currentNum < (max/3)){
				g.setColor(Color.red);
			}else if((currentNum > max/3) && (currentNum < ((max*2)/3))){
				g.setColor(Color.blue);
			}else if(currentNum > ((max*2)/3)){
				g.setColor(Color.yellow);
			}
			if (directcount == 0) {
				// move right
				for (int i = 0; i < counter; i++) {
					if (primesList.contains(currentNum)){
						plot(Integer.toString(currentNum), ++X, Y, g2d);
						//primecount++;
						//System.out.println(currentNum);
				}else
						++X;
					currentNum++;
					if(currentNum >= max){
						break;
					}
				}
				// move Up
				for (int i = 0; i < counter; i++) {
					if (primesList.contains(currentNum)){
						plot(Integer.toString(currentNum), X, --Y, g2d);
						//primecount++;
						//System.out.println(currentNum);
					}else
						--Y;
					currentNum++;
					if(currentNum >= max){
						break;
					}					
				}
				directcount = 1;
				counter++;
			} else {
				// move left
				for (int i = 0; i < counter; i++) {
					if (primesList.contains(currentNum)){
						plot(Integer.toString(currentNum), --X, Y, g2d);
						//primecount++;
						//System.out.println(currentNum);
					}else
						--X;
					currentNum++;
					if(currentNum >= max){
						break;
					}					
				}
				// move down
				for (int i = 0; i < counter; i++) {
					if (primesList.contains(currentNum)){
						plot(Integer.toString(currentNum), X, ++Y, g2d);
						//primecount++;
						//System.out.println(currentNum);
					}else
						++Y;
					currentNum++;
					if(currentNum >= max){
						break;
					}					
				}
				directcount = 0;
				counter++;
			}
		}
		//System.out.println("This spiral has: "+primecount+" prime numbers");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSpiral(g);
	}


}