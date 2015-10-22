package bin;


import java.awt.Color;
import java.awt.Graphics;

public class Render {

	
	/*  game field varriables    */
	public int field_width = 10;
	public int field_height = 20;
	
	public int cell_width = 20;
	public int cell_height = 20;
	
	public int field_start_x = 300;
	public int field_start_y = 100;
	
	
	//-----------------------------------------------------------------------
	
	public void drawField(Graphics g){
		
		for(int i = 0; i < field_width; i++){
			for(int j = 0; j < field_height; j++){
				g.setColor(Color.BLUE);
				g.drawRect(field_start_x + i * cell_width, field_start_y + j * cell_height , cell_width, cell_height);	
			}
		}
	}
	
	
	//-----------------------------------------------------------------------
	
	
	public void drawAllBlocks(Graphics g , int tetris_table[][] ){
		
		for(int i = 0; i < field_width; i++){
			for(int j = 0; j < field_height; j++){
				
				//block color
				switch(tetris_table[i][j] ){
				   		
				   		case 0:  break;
				   		case 1:  g.setColor( new Color(0x00FFFF)); break;
				   		case 2:  g.setColor( new Color(0x0000B0)); break;
				   		case 3:  g.setColor(new Color(0xFF8106) ); break; 
				   		case 4:  g.setColor( new Color(0xFFFF00)); break;
				   		case 5:  g.setColor( new Color(0x006400));break;
				   		case 6:  g.setColor( new Color(0x8B008B)); break;
				   		case 7:  g.setColor( new Color(0x8B0000));break;
				   }
				
				   //draw block on screen if there is a block -> "0" value means that there is no block
				   if(tetris_table[i][j] != 0) g.fillRect( field_start_x + i * cell_width,  field_start_y +  j * cell_height, cell_width, cell_height);
				
			}
		}
	
	}
	
	
	//-----------------------------------------------------------------------
	//draw all currently falling blocks -> copy random_bircks , rotation_number, block_fall_x and block_fall_y
	//from Action-class via Main-Class
	public void drawFallingBlocks(Graphics g , int random_bricks, int rotation_number, int block_fall_x, int block_fall_y){
		
		g.setColor(Color.GREEN);
	
		switch(random_bricks){
		
			case 0:  g.setColor( new Color(0x00FFFF)); break;
			
			case 1:  g.setColor( new Color(0x0000B0)); break;
			
			case 2:  g.setColor(new Color(0xFF8106) ); break; 
			
			case 3:  g.setColor( new Color(0xFFFF00)); break;
			
			case 4:  g.setColor( new Color(0x006400));break;
			
			case 5:  g.setColor( new Color(0x8B008B)); break;
			
			case 6:  g.setColor( new Color(0x8B0000));break;
			
		}
		
		for(int i = 0; i < 4; i++){
			
			int x = Bricks.tetris_bricks[random_bricks][rotation_number][i][0];
			int y = Bricks.tetris_bricks[random_bricks][rotation_number][i][1];
			
			g.fillRect(field_start_x + block_fall_x * cell_width + x * cell_width, 
					   field_start_y + block_fall_y * cell_height + y * cell_height, 
					   cell_width, cell_height
					   );
		}
			
	}
	
	
	
	
	
	//-----------------------------------------------------------------------
	
	public void drawScoreText(Graphics g, int scores, boolean can_rotate, boolean game_over ){
		
		//just text on screen
		
		g.setColor(Color.BLUE);
		
		g.drawString("Line burn scores : "+ scores, 100, 100);
		
		if(can_rotate == true){
			g.drawString("rotate -> ok !", 100, 130);
		}
		else if(can_rotate == false){
			g.drawString("rotate -> fail !", 100, 130);
		}
		
		if(game_over == true) {
			g.setColor(Color.RED);
			g.drawString("GAME OVER !", 100, 70);
		}
		
		
	}
	
	//-----------------------------------------------------------------------
	
	public void drawGameOverArea(Graphics g){
		 g.setColor(Color.RED);
		 
		 g.drawRect(field_start_x + 4 * cell_width, 
				   field_start_y + 0 * cell_height, 
				   4 * cell_width, 4 * cell_height
				   );
	}
	
}
