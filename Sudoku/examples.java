package Sudoku;
class Examples
{
	public static void example1()
 	{
 		Solver s=new Solver();

		int[][] board={{0,0,0,2,6,0,7,0,1},
					   {6,8,0,0,7,0,0,9,0},
					   {1,9,0,0,0,4,5,0,0},
					   {8,2,0,1,0,0,0,4,0},
					   {0,0,4,6,0,2,9,0,0},
					   {0,5,0,0,0,3,0,2,8},
					   {0,0,9,3,0,0,0,7,4},
					   {0,4,0,0,5,0,0,3,6},
					   {7,0,3,0,1,8,0,0,0}};
		
		s.print(board);
    	System.out.println(s.solve(board));
    	s.print(board);
					   
 	}
 	public static void example2()
 	{
 		Solver s=new Solver();
		int[][] board={{0,2,0,0,0,0,0,0,0},
					   {0,0,0,6,0,0,0,0,3},
					   {0,7,4,0,8,0,0,0,0},
					   {0,0,0,0,0,3,0,0,2},
					   {0,8,0,0,4,0,0,1,0},
					   {6,0,0,5,0,0,0,0,0},
					   {0,0,0,0,1,0,7,8,0},
					   {5,0,0,0,0,9,0,0,0},
					   {0,0,0,0,0,0,0,4,0}};
					   
		s.print(board);
    	System.out.println(s.solve(board));
    	s.print(board);
					   
 	}
 	public static void example3()
 	{
 		//Inspired by: https://www.youtube.com/watch?v=yKf9aUIxdb4
 		
 		Solver s=new Solver();
    	Rule knight=(board,r,c)->{
    						int current=board[r][c];
    						
    						if(Solver.inside(r-1,c-2) && board[r-1][c-2]==current)
    								return false;
    						if(Solver.inside(r-1,c+2) && board[r-1][c+2]==current)
    								return false;
    						if(Solver.inside(r-2,c-1) && board[r-2][c-1]==current)
    								return false;
    						if(Solver.inside(r-2,c+1) && board[r-2][c+1]==current)
    								return false;
    						if(Solver.inside(r+1,c-2) && board[r+1][c-2]==current)
    								return false;
    						if(Solver.inside(r+1,c+2) && board[r+1][c+2]==current)
    								return false;
    						if(Solver.inside(r+2,c-1) && board[r+2][c-1]==current)
    								return false;
    						if(Solver.inside(r+2,c+1) && board[r+2][c+1]==current)
    								return false;
    						return true;
    					};
    					
    	Rule king=(board,r,c)->{
    						int current=board[r][c];
    						for(int i=-1;i<=1;i++)
    						{
    							for(int j=-1;j<=1;j++)
    							{
    								if(i!=0 && j!=0)
    								{
    									if(Solver.inside(r+i,c+i) && board[r+i][c+i]==current)
    										return false;
    								}
    							}
    						}
    						return true;
    					};
    	
    	Rule ortho=(board,r,c)->{
    						int current=board[r][c];
    						int prev=current==1?-1:current-1;
    						int next=current+1;
    						
    						if(Solver.inside(r-1,c) && (board[r-1][c]==prev || board[r-1][c]==next))
    						return false;
    						if(Solver.inside(r+1,c) && (board[r+1][c]==prev || board[r+1][c]==next))
    						return false;
    						if(Solver.inside(r,c-1) && (board[r][c-1]==prev || board[r][c-1]==next))
    						return false;
    						if(Solver.inside(r,c+1) && (board[r][c+1]==prev || board[r][c+1]==next))
    						return false;
    						
    						return true;
    					};
    	
		int[][] board=new int[9][9];
		board[4][2]=1;
		board[5][6]=2;
		
		
    	
    	s.addRule(knight);
    	s.addRule(king);
    	s.addRule(ortho);
		
		s.print(board);
		s.solve(board);
		s.print(board);
 	}
 	public static void main(String[] args)
 	{
 		example1();
 		example2();
 		example3();
 	}
}
