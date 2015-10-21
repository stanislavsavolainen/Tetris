

#ifndef _RENDER_H_
    #define _RENDER_H_


class Render{
      
      public:
             //varriables
             int map_width;
             int map_height;
             
             int field_start_x;
             int field_start_y;
             
             int cell_width;
             int cell_height;
             
             //functions
             void drawField();
             void drawAllBlocks(int field[]);
             void drawFallingBlock(int x, int y, int type);
      
             void drawGameOverArea(int fall_start_x, int fall_start_y);
      
      
      };


#endif
