package bin;


import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;



public class GameApplet extends JPanel implements KeyListener {

	/* double-buffer */
	Image pic;
	Graphics buff;
	
	
	
	/* applet screen size */
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	
	
	//tetris game field
	public int tetris_table[][];
	
	
	//Render object
	Render render1;
	
	//Action object
	Action action1;
	
	
	//keyboard listener varriables -> required to keyboard take controll of applet
	Button key_b;
	
	//----------------------------------------------------------------------
	
	public GameApplet(){
		
		init();
	}
	
	
	public void init(){
		
		//init screen
		//setSize(WIDTH, HEIGHT);
		
		//render graphics
		render1 = new Render();
		
		//action
		action1 = new Action();
		
		//init game field
		tetris_table = new int[render1.field_width][render1.field_height];
		for(int j= 0; j < render1.field_height; j++){
			for(int i = 0 ; i < render1.field_width; i++){
				tetris_table[i][j] = 0;
			}
		}
		
		
		//init keyboard listener -> for keyboard control
		setLayout(null);
		key_b = new Button("");
		key_b.setBounds(3000, 3000, 1, 1);
		add(key_b);
		key_b.addKeyListener(this);
		
	
	}
	
	//----------------------------------------------------------------------
	
	//paint() -> replaced to paintComponent() method to make JAR-file from applet
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		action1.updateActionData(tetris_table, render1.field_width, render1.field_height);
		action1.gameOver(); //check if game over
		action1.rotate_exception(); //check block rotation state -> player know when rotate is imposible
		action1.lines_burn();
		
		render1.drawField(g);
		//render1.drawAllBlocks(g, action1.copy_of_tetris_table);
		render1.drawAllBlocks(g, tetris_table);
		render1.drawGameOverArea(g);
		
		
		//make block falling with timer -> stop block falling if game over happens
		if(action1.game_over == false){
			if(action1.block_fall_tick == action1.block_fall_delay) action1.block_fall();
			render1.drawFallingBlocks(g, action1.random_bricks, action1.rotation_number, action1.block_fall_x, action1.block_fall_y );
		}
		
		action1.tick();
		
		render1.drawScoreText(g, action1.scores, action1.can_rotate, action1.game_over );
		
		repaint();
	}
	
	//----------------------------------------------------------------------
	
	@Override
	public void update(Graphics g){
		
		//Double-buffer -> avoid screen blinking
		if(pic == null){
			pic = createImage(this.getSize().width, this.getSize().height);
			buff = pic.getGraphics();
		}
		
		buff.setColor(getBackground());
		buff.fillRect(0, 0, this.getSize().width, this.getSize().height);
		buff.setColor(getForeground());
		paint(buff);
		g.drawImage(pic, 0,0,this);
	}

	
	//========================================================================
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		//keyboard control here
		
		int key = arg0.getKeyCode();
		
		switch(key){
		
			//--------------------------------------------------------------
			case KeyEvent.VK_LEFT:
				action1.can_move_left = true;
				if(action1.block_fall_x > 0 && action1.can_move_left == true){ action1.block_fall_x -= 1 ; }
			break;
		
			//---------------------------------------------------------------
			case KeyEvent.VK_RIGHT:
				action1.can_move_right = true;
				
				for(int i = 0; i < 4; i++){
					int x = Bricks.tetris_bricks[action1.random_bricks][action1.rotation_number][i][0];
					if( (action1.block_fall_x + x) < render1.field_width - 1){
						
					}else{
						action1.can_move_right = false;
					}
				
				}
				
				if(action1.block_fall_x < (render1.field_width - 1) && action1.can_move_right == true){ action1.block_fall_x+=1; }
			
			break;
			//----------------------------------------------------------------
			
			case KeyEvent.VK_DOWN:
				action1.block_fall_tick = action1.block_fall_delay; //instant brick falling speed , if key pressed
			break;
			
			//-----------------------------------------------------------------
			
			case KeyEvent.VK_UP:
				
				action1.rotate_exception();
				
				  if(action1.can_rotate == true){
					  action1.rotation_number += 1;
					  if(action1.rotation_number == 4)action1.rotation_number = 0;
				  }
					
			break;
		//---------------------------------------------------------------------
			
		}
		
		
		
	}

	//---------------------------------------------------------------------------
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
