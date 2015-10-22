package bin;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame {

	private int FRAME_WIDTH = 800;
	private int FRAME_HEIGHT = 600;
	
	public Main(){
		
	
		GameApplet screen = new GameApplet();
		add(screen, BorderLayout.CENTER);
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tetris public version 1 by Stanislav Savolainen");
	
	}
	
    //Launch applet in main class	
	public static void main(String args[]){
		new Main();
	}
	
}
