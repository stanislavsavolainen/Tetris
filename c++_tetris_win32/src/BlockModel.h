#ifndef _BLOCKMODEL_H_
    #define _BLOCKMODEL_H_
    
    class BlockModel{
          
          public:
                 
                
                 //int tetris_bricks[][][][]; // 1 value -> index, 2 value -> rotation, 2 value ->  small block index 
                 int tetris_bricks[7][4][4][2];
              
                 
                 void init_block_values();
          
          
          
          };
    

#endif
