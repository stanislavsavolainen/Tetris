#ifndef _ACTION_H_
    #define _ACTION_H_
    
    class Action{
          
          public:
                 //constructor
                 Action();
                 
                 
                 //varriables
                 int delay_counter;
                 int delay;
                 
                 
                 int block_start_fall_x; //where new begin to fall cordinates x
                 int block_start_fall_y; //where new begin to fall cordinates y
                 int block_fall_x;
                 int block_fall_y;
                 bool block_is_faling;
                 
                 int map_width;
                 int map_height;
                 
                 int field[20*10];
                 
                 int falling_block_index;
                 
                 int line_burn_scores;


                 //function
                 void tick();
                 void block_fall(int block_x[], int block_y[] , bool can_rotate);
                 void line_burn(int row);
                 void fall_after_burn(int removed_row);
                 void lines_burn(); // use line_burn() function * amount of row times
          
                 void move_right();
                 void move_left();
                 
                 int randomize_new_block();
                 
                 void check_game_over();
                 bool game_over;
          
          
          };
    
    
    
#endif
