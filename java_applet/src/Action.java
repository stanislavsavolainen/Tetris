package bin;


import java.util.Random;

public class Action {

	
	//timer varriable -> for blockfall speed
	public int block_fall_delay = 20; // = 20
	public int block_fall_tick = 0;
	
	//block -> falling varriables
	public int block_start_x = 4;
	public int block_start_y = 0;
	public int block_fall_x = block_start_x;
	public int block_fall_y = block_start_y;
	
	//block -> moving varriables
	public boolean can_move_right = true;
	public boolean can_move_left = true;
	public boolean floor_hit = false;
	public boolean block_hit_block = false;
	
	//can rotate
	public boolean can_rotate = false;
	public int rotation_number = 0;
	
	
	//game over
	public boolean game_over = false;
	
	//Random for bricks
	Random rnd;
	public int random_bricks = 0;
	
	//maintenance general data ( copy from Main-class) -> updateActionData(...) function
	public int copy_of_tetris_table[][];
	public int copy_of_field_height;
	public int copy_of_field_width;
	
	//scores varriable
	public int scores = 0;
	
	
	
	
	//-----------------------------------------------------------------------
	//class constructor -> initialize data when action-object created
	public Action(){
		
		//init random blocks and give first random value between 0 and 6
		rnd = new Random();
		randomize_bricks();
	}
	
	//-----------------------------------------------------------------------
	//update important variables for game "Action"-process from Main-class
	public void updateActionData( int n_action_tetris_table[][], int n_field_width , int n_field_height ){
		copy_of_tetris_table = n_action_tetris_table;
		copy_of_field_width = n_field_width;
		copy_of_field_height = n_field_height;
	}
	
	//-----------------------------------------------------------------------
	//put game to delay
	public void tick(){
		
		try{
			Thread.sleep(10);
			//for blockfall timing
			if(block_fall_tick > block_fall_delay){ block_fall_tick = 0; }
			else{ block_fall_tick += 1; }
			
		}catch(Exception e){}
	
	}
	
	//-----------------------------------------------------------------------
	//check if game over happens
	public void gameOver(){
		
		//check game_over area 
		for(int i = block_start_x; i < (block_start_x + 4); i++){
			for(int j = block_start_y; j < ( block_start_y + 4); j++){
				if(copy_of_tetris_table[i][j] != 0){
					game_over = true;
				}
			}
		
		}
		
	}
	
	//-----------------------------------------------------------------------
	
	//don't allow block rotation over right border
	public boolean rotate_exception(){
		
		//don't allow rotate brick near right border -> if overflow happen
		int block_rotate = 0;
		
		int ch_rotation_number = rotation_number + 1;
		if(ch_rotation_number == 4)ch_rotation_number = 0;
		
		for(int i = 0; i < 4; i++){
			int x = Bricks.tetris_bricks[random_bricks][ch_rotation_number][i][0];
			int y = Bricks.tetris_bricks[random_bricks][ch_rotation_number][i][1];
		
			if( ( block_fall_x + x) > (copy_of_field_width - 1) ){
				//can_rotate = false;
				can_rotate = false;
			}else{ block_rotate++; }
			
			if(block_rotate == 4){ can_rotate = true; }
		
		}
     
		return can_rotate;
	}
	
	//------------------------------------------------------------------------
	
	//burn many lines -> use line_burn() method multiple times
	public void lines_burn(){
		for(int i = 0; i < copy_of_field_height; i++){
			line_burn(i);
		}
	}
	
	//------------------------------------------------------------------------
	
	//burn one line -> when it is full of blocks ( no space)
	public void line_burn(int line_row){
		
		boolean lineburn = false;
		int count_in_line = 0;
		
		//count not empty block in line -> if line_width = not_empty block -> there is no space
		for(int i = 0; i < copy_of_field_width; i++){
			if( copy_of_tetris_table[i][line_row] != 0 ) count_in_line += 1;	
		}
		
		//burn line if no space on line
		if(count_in_line == copy_of_field_width ) lineburn = true;
		
		if(lineburn == true ){
			for(int i = 0; i < copy_of_field_width; i++){
				copy_of_tetris_table[i][line_row] = 0;	
			}
			
			scores += 1;
			
			fall_after_burn(line_row);
		}

	}
	
	//------------------------------------------------------------------------
	//make block fall after line is burned
	public void fall_after_burn(int removed_row){
		
			for(int j = removed_row; j > 0; j--){
				for(int i = 0; i < copy_of_field_width; i++){
					copy_of_tetris_table[i][j] = copy_of_tetris_table[i][ j - 1];
				}
			}
			
		}
	
	//------------------------------------------------------------------------
	
	//block is falling
	public void block_fall(){
		
		floor_hit = false;
		block_hit_block = false;
		
		//check if block hit a floor
		for(int i = 0; i < 4; i++){
			int x = Bricks.tetris_bricks[random_bricks][rotation_number][i][0];
			int y = Bricks.tetris_bricks[random_bricks][rotation_number][i][1];
			if(block_fall_y + y < copy_of_field_height){}
			else{ floor_hit = true; }
		}
		
		
		//check if block hit other block under it 
		if ( ! floor_hit ){
			for(int i = 0; i < 4; i++){
				int x = Bricks.tetris_bricks[random_bricks][rotation_number][i][0];
				int y = Bricks.tetris_bricks[random_bricks][rotation_number][i][1];
				if( copy_of_tetris_table[block_fall_x + x][block_fall_y + y ] != 0 ) block_hit_block = true;
			}
		}
		
		
		//stop block fall
		if(floor_hit || block_hit_block ){
	
			for(int i = 0; i < 4; i++){
				
				int x = Bricks.tetris_bricks[random_bricks][rotation_number][i][0];
				int y = Bricks.tetris_bricks[random_bricks][rotation_number][i][1];

				copy_of_tetris_table[block_fall_x + x][block_fall_y + y - 1 ] = random_bricks + 1;
				
			}
		    
			//next block begin falling position
			block_fall_x = block_start_x;
			block_fall_y = block_start_y;
			
			randomize_bricks();
		}else{
			//continue block falling if there is no obstances
			block_fall_y++;	
		}
		
	}//block_fall
	
	//------------------------------------------------------------------------
	//randomize brick -> select brick wich is falling currently ( L, Z, S, T ...)
	public void randomize_bricks(){
		random_bricks = rnd.nextInt(7) + 0;
	}
	
	
	
	
}
