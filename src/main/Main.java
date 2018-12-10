package main;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	private String[] args;

	private int width, height;

	private BufferedImage img;

	private JFrame frame;
	private JPanel imageHolder;

	static boolean NOGUI = false;

	private Main(String[] args) {

		//go through the launch options
		this.args = args;
		for(String i : args) {
			if(i.equals("-nogui")) {
				NOGUI = true;
			}
		}

		//just because why not :D
		System.out.println("© 2018 Jompda. All rights reserved.");
		System.out.println("---------------------");

		//capture the screen
		try {
			//get the screen resolution
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			width = (int)screenSize.getWidth();
			height = (int)screenSize.getHeight();

			//take the screenshot
			img = new Robot().createScreenCapture(new Rectangle(width, height));
		} catch (AWTException e) {
			Output.printInfo("SOMETHING WENT WRONG! EXITING..");
			System.exit(0);
		}

		//print info
		Output.printInfo("Opening the input window..");

		//initialize the JFrame
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//render the image and add a mouse listener
		imageHolder = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
			}
		};
		frame.add(imageHolder);
		imageHolder.addMouseListener(new mi());

		frame.setVisible(true);

		//print info
		Output.printInfo("Waiting for input..");

	}

	class mi extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {

			//close the input window
			Output.printInfo("Closing input window..");
			imageHolder.removeMouseListener(this);
			frame.setVisible(false);
			frame.dispose();


			int x = e.getX(); int y = e.getY();
			int integerPixel = img.getRGB(x, y);
			int red = (integerPixel & 0x00ff0000) >> 16;
			int green = (integerPixel & 0x0000ff00) >> 8;
			int blue = integerPixel & 0x000000ff;
			String hexadecimal = Integer.toHexString(integerPixel).substring(2);

			//print information to console
			System.out.println("-------------------");
			Output.printInfo("Mouse Position (x, y): " + x + " " + y);
			Output.printInfo("Integer Pixel: " + integerPixel);
			Output.printInfo("RGB Values: " + red + " " + green + " " + blue);
			Output.printInfo("Hexadecimal(HEX): " + hexadecimal);
			System.out.println("-------------------");

			//open the output window if NOGUI is false
			if(!NOGUI) {
				//set the outputs
				Output.x = x; Output.y = y;
				Output.integerPixel = integerPixel;
				Output.red = red;
				Output.green = green;
				Output.blue = blue;
				Output.hexadecimal = hexadecimal;

				//print info
				Output.printInfo("Opening output window..");

				Output.init(args);
			}
			else {
				Output.printInfo("Exiting..");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Output.dateFormat = new SimpleDateFormat("HH:mm:ss");
		new Main(args);
	}

}
