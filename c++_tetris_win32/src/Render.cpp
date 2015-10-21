
#include "Render.h"

#include <GL/glut.h>



//------------------------------------------------------------------------------

void Render::drawField(){
            
            /*
            glColor3f(1 , 0, 0);
            
            glBegin(GL_LINE_LOOP);
            glVertex2f(200,100);
            glVertex2f(200,200);
            glEnd();
            */
            
            for(int i = 0; i < map_width; i++){
                    for(int j = 0; j < map_height; j++){
                            
                             glColor3f(1 , 0, 0);
                            
                            //north line
                            glBegin(GL_LINE_LOOP);
                            glVertex2f( field_start_x +  i * cell_width , field_start_y + j * cell_height);
                            glVertex2f( field_start_x +  i * cell_width + cell_width , field_start_y + j * cell_height);
                            glEnd();
                            
                            
                            //east line
                            glBegin(GL_LINE_LOOP);
                            glVertex2f( field_start_x +  i * cell_width + cell_width , field_start_y + j * cell_height);
                            glVertex2f( field_start_x +  i * cell_width + cell_width , field_start_y + j * cell_height + cell_height);
                            glEnd();
                            
                            
                            //west line
                             glBegin(GL_LINE_LOOP);
                            glVertex2f( field_start_x +  i * cell_width , field_start_y + j * cell_height);
                            glVertex2f( field_start_x +  i * cell_width , field_start_y + j * cell_height + cell_height);
                            glEnd();
                            
                            //south line
                            glBegin(GL_LINE_LOOP);
                            glVertex2f( field_start_x +  i * cell_width , field_start_y + j * cell_height + cell_height);
                            glVertex2f( field_start_x +  i * cell_width + cell_width , field_start_y + j * cell_height + cell_height);
                            glEnd();
                            
                            
                  }
            }

     }
//------------------------------------------------------------------------------


void Render::drawAllBlocks(int field[]){
     
         for(int j = 0; j < map_height; j++){
                    for(int i = 0; i < map_width; i++){
                            if(field[ j * map_width + i] != 0){
                                      
                                      int block_color = field[ j * map_width + i];
                                      
            //block_color = block id
            //block_color += 1; // for color balance
            switch(block_color){
                 case 0:
                      glColor3f( ( 1.0f / 256) * 0x00  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0x00 ); // empty
                 break;
                 
                 case 1:
                      glColor3f( ( 1.0f / 256) * 0x00  , (1.0f /  256) * 0xFF , (1.0f / 256) * 0xFF ); // I-block
                 break;
                 
                 case 2:
                      glColor3f( ( 1.0f / 256) * 0x00  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0xB0 ); // J - block
                 break;
                 
                 case 3:
                      glColor3f( ( 1.0f / 256) * 0xFF  , (1.0f /  256) * 0x81 , (1.0f / 256) * 0x06 ); // L - block
                 break;
                 
                 case 4:
                      glColor3f( ( 1.0f / 256) * 0xFF  , (1.0f /  256) * 0xFF , (1.0f / 256) * 0x00 ); // O - block
                 break;
                 
                 case 5:
                      glColor3f( ( 1.0f / 256) * 0x00  , (1.0f /  256) * 0x64 , (1.0f / 256) * 0x00 ); // S - block
                 break;
                 
                 case 6:
                      glColor3f( ( 1.0f / 256) * 0x8B  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0x8B ); //T - block
                 break;
                 
                 case 7:
                      glColor3f( ( 1.0f / 256) * 0x8B  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0x00 ); // Z - block
                 break;                    
                                                  
            }
                                      
                                      
                                      //glColor3f(0, 1, 0);
                                      glBegin(GL_QUADS);
                                      glVertex2f(field_start_x + i * cell_width, field_start_y + j * cell_height );
                                      glVertex2f(field_start_x + i * cell_width + cell_width , field_start_y + j * cell_height );
                                      glVertex2f(field_start_x + i * cell_width + cell_width, field_start_y + j * cell_height + cell_height);
                                      glVertex2f(field_start_x + i * cell_width, field_start_y + j * cell_height + cell_height );
                                      glEnd();          
                            }
                    }      
         }
     
     
}

//------------------------------------------------------------------------------


void Render::drawFallingBlock(int x, int y, int type){
            
            //type = block id
            type += 1; //for color balance
            switch(type){
                 case 0:
                      glColor3f( ( 1.0f / 256) * 0x00  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0x00 ); // empty
                 break;
                 
                 case 1:
                      glColor3f(  ( 1.0f / 0xFF ) * 0x00  , (1.0f /  0xFF ) * 0xFF , ( 1.0f / 0xFF) * 0xFF ); // I-block
                 break;
                 
                 case 2:
                      glColor3f( ( 1.0f / 256) * 0x00  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0xB0 ); // J - block
                 break;
                 
                 case 3:
                      glColor3f( ( 1.0f / 256) * 0xFF  , (1.0f /  256) * 0x81 , (1.0f / 256) * 0x06 ); // L - block
                 break;
                 
                 case 4:
                      glColor3f( ( 1.0f / 256) * 0xFF  , (1.0f /  256) * 0xFF , (1.0f / 256) * 0x00 ); // O - block
                 break;
                 
                 case 5:
                      glColor3f( ( 1.0f / 256) * 0x00  , (1.0f /  256) * 0x64 , (1.0f / 256) * 0x00 ); // S - block
                 break;
                 
                 case 6:
                      glColor3f( ( 1.0f / 256) * 0x8B  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0x8B ); //T - block
                 break;
                 
                 case 7:
                      glColor3f( ( 1.0f / 256) * 0x8B  , (1.0f /  256) * 0x00 , (1.0f / 256) * 0x00 ); // Z - block
                 break;                    
                                                  
            }
            
           // glColor3f(0, 1, 0);
            //glColor3f( ( 1.0f / 256) * 0x8B  , ( 1.0f /  256) * 0x00 , ( 1.0f / 256) * 0x00 ); // Z - block
            
            
            glBegin(GL_QUADS);
            glVertex2f( field_start_x + x * cell_width, field_start_y + y * cell_height );
            glVertex2f(field_start_x + x * cell_width + cell_width , field_start_y + y * cell_height );
            glVertex2f(field_start_x + x * cell_width + cell_width, field_start_y + y * cell_height + cell_height);
            glVertex2f(field_start_x + x * cell_width, field_start_y + y * cell_height + cell_height );
            glEnd();
     
     
     }

//------------------------------------------------------------------------------

void Render::drawGameOverArea( int fall_start_x, int fall_start_y){
     
     glColor3f( 1.0f, 1.0f, 1.0f);
     
     int x =  field_start_x + fall_start_x * cell_width; 
     int y = field_start_y + fall_start_y * cell_height;
     int xx =  field_start_x + fall_start_x * cell_width + 4 * cell_width;
     int yy = field_start_y + fall_start_y * cell_height + 4 * cell_height;
     
     
     glBegin(GL_LINE_LOOP);
     glVertex2f( x , y );
     glVertex2f( xx , y );
     glEnd();
     
     glBegin(GL_LINE_LOOP);
     glVertex2f( xx , y );
     glVertex2f( xx, yy);
     glEnd();
     
     glBegin(GL_LINE_LOOP);
     glVertex2f( xx ,  yy );
     glVertex2f( x , yy );
     glEnd();
     
      glBegin(GL_LINE_LOOP);
     glVertex2f( x , yy);
     glVertex2f( x, y );
     glEnd();
     
     
     
     }





