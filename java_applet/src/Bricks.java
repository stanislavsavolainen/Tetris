package bin;


public class Bricks {

	
	public static int tetris_bricks[][][][] = {
		
		// I
		{
	    	{ {0, 0}, {1, 0}, {2, 0}, {3, 0}, } ,
	    	{  {0, 0}, {0, 1}, {0, 2}, {0, 3}, },
	    	{  {0, 0}, {1, 0}, {2, 0}, {3, 0}, },
	    	{  {0, 0}, {0, 1}, {0, 2}, {0, 3} }
	    },
		
		// J
	    {
	    	{ {0, 0},  {1, 0} , {2 , 0} , { 2, 1},  }, 
	    	{ {0, 0},  {1, 0} , {0 , 1} , { 0, 2},  },
	    	{ {0, 0},  {0, 1} , {1 , 1} , { 2, 1},  },
	    	{ {1, 0},  {1, 1} , {1 , 2} , { 0, 2}  }
	    },
	    
	    
		// L
	    {
	    	{ {0, 0},  {0, 1} , {1 , 0} , { 2, 0},  }, 
	    	{ {0, 0},  {0, 1} , {0 , 2} , { 1, 2},  },
	    	{ {0, 1},  {1, 1} , {2 , 1} , { 2, 0},  },
	    	{ {0, 0},  {1, 0} , {1 , 1} , { 1, 2}  }
	    	
	    },
	    
		// O
	    {
	    	{ {0, 0},  {1, 0} , {0 , 1} , { 1, 1},  }, 
	    	{ {0, 0},  {1, 0} , {0 , 1} , { 1, 1},  },
	    	{ {0, 0},  {1, 0} , {0 , 1} , { 1, 1},  },
	    	{ {0, 0},  {1, 0} , {0 , 1} , { 1, 1}  }
	    },
		
	    // S
	    {
	    	{ {0, 1},  {1, 0} , {1 , 1} , { 2, 0},  }, 
	    	{ {0, 0},  {0, 1} , {1 , 1} , { 1, 2},  },
	    	{ {0, 1},  {1, 0} , {1 , 1} , { 2, 0},  },
	    	{ {0, 0},  {0, 1} , {1 , 1} , { 1, 2},  },
	    	
	    },
		
	    // T
	    {
	    	{ {0, 1},  {1, 1} , {2 , 1} , { 1, 2},  }, 
	    	{ {0, 0},  {0, 1} , {1 , 1} , { 0, 2},  },
	    	{ {0, 1},  {1, 1} , {1 , 0} , { 2, 1},  },
	    	{ {0, 1},  {1, 1} , {1 , 0} , { 1, 2}  },
	    },
	    
		// Z
	    {
	    	{ {0, 0},  {1, 0} , {1 , 1} , { 2, 1},  }, 
	    	{ {1, 0},  {0, 1} , {1 , 1} , { 0, 2},  },
	    	{ {0, 0},  {1, 0} , {1 , 1} , { 2, 1},  },
	    	{ {1, 0},  {0, 1} , {1 , 1} , { 0, 2},  },
	    }
		
};
	
	
	
}
