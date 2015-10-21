
#include "Action.h"

#include <windows.h>

//for random
#include <stdlib.h>
#include <time.h>

//------------------------------------------------------------------------------


Action::Action(){
      
      srand( time(NULL) ); // for random function           

}


//------------------------------------------------------------------------------

void Action::tick(){
         Sleep(10); // windows.h -> Sleep(...) function call
         delay_counter++;
                    
     }
     
//------------------------------------------------------------------------------     

void Action::block_fall(int block_x[], int block_y[], bool can_rotate ){
     
     bool floor_hit = false;
	 bool block_hit_block = false;
    
      //check if block is falling -> if not reset block_fall_y variable for new falling block
      
      
       //block hit a floor
        for(int i = 0; i < 4; i++){
         if(block_fall_y + block_y[i] < map_height){}
			else{ floor_hit = true; }     
              
        }
      
      
      //block hit block under it
      if( ! floor_hit ){
          for(int i = 0; i < 4; i++){
                  if( field[ ( block_fall_y + block_y[i] ) * map_width + (block_fall_x + block_x[i] ) ] != 0 ) block_hit_block = true;
          }
      }
      
      
      
      if( floor_hit || block_hit_block ){
          //save old block and begin new block fall
          for(int i = 0; i < 4; i++)
          field [ (block_fall_y + block_y[i] - 1) * map_width  + (block_fall_x + block_x[i]) ] = falling_block_index + 1;
          
          block_fall_x = block_start_fall_x;
          block_fall_y = block_start_fall_y;
          
          falling_block_index = randomize_new_block(); // generate new falling block
          
      
      }else{
      
          block_fall_y += 1;  
      }
    
}
     
//------------------------------------------------------------------------------

void Action::move_right(){
     if(block_fall_x < map_width - 1) block_fall_x++;      
}

//------------------------------------------------------------------------------

void Action::move_left(){
     if(block_fall_x > 0) block_fall_x--;     
}     
     
//------------------------------------------------------------------------------

void Action::line_burn(int row){
     
     bool line_burn = false;
     int count_in_line = 0;
     //---------------
     for(int i = 0; i < map_width; i++){
             if(field[row * map_width + i] != 0) count_in_line++; //check for no space
     }
     //----------------
     if(count_in_line == map_width) line_burn = true; //no space at line
     else line_burn = false;
     //----------------
     if(line_burn){
          for(int i = 0; i < map_width; i++){
            field[ row * map_width + i] = 0; //burn current line 
          }
          
          line_burn_scores += 1;
          
          fall_after_burn(row); //burn this line -> if no space
     
     }    
              
}

//------------------------------------------------------------------------------

void Action::fall_after_burn(int removed_row){
     
     //make previous line next line -> burn it -> until ...
     for(int j = removed_row; j > 0; j--){
			for(int i = 0; i < map_width; i++){
                    field[j * map_width + i ] = field[ (j - 1) * map_width + i ];
			}
		}

}

//------------------------------------------------------------------------------

void Action::lines_burn(){
     
     for(int j = 0; j < map_height; j++){
      line_burn(j);
     }     
     
}

//------------------------------------------------------------------------------

int Action::randomize_new_block(){
    
    int random_block = rand() % 7; // random values from 0 to 6
    
    return random_block;
    }

//------------------------------------------------------------------------------

void Action::check_game_over(){
     
     for(int j = block_start_fall_y; j < (block_start_fall_y + 4); j++ ){
      for(int i = block_start_fall_x; i < (block_start_fall_x + 4); i++ ){
              if(field[j * map_width + i] != 0){
					game_over = true;
				}      
        }        
     }

}


