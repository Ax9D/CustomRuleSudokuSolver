import java.util.*;

interface Rule
{
	public boolean check(int[][] board,int r,int c);
}
class Attempt
{
        int r,c;
        int i;
        public Attempt(int r,int c)
        {
                this.r=r;
                this.c=c;
                this.i=1;
        }
        public String toString()
        {
        	return "row:"+this.r+"\ncol:"+this.c+"\ni:"+i;
        }
}
class Solver
{
	int[][] board;
	ArrayList<Rule> rules;
	public Solver()
	{
		rules=new ArrayList<Rule>();
	}
	public boolean sudokuRules(int r,int c)
	{
		//Check row
        for(int i=0;i<9;i++)
        {
            if(i!=c && board[r][i]==board[r][c])
                return false;
        }
        
        //Check column
        for(int i=0;i<9;i++)
        {
            if(i!=r && board[i][c]==board[r][c])
                return false;
        }
        
        //Check box
        int boxStartR,boxEndR;
        int boxStartC,boxEndC;
        
        boxStartR=(r/3)*3;
        boxEndR=boxStartR+3;
        
        boxStartC=(c/3)*3;
        boxEndC=boxStartC+3;
        
       for(int i=boxStartR;i<boxEndR;i++)
       {
             for(int j=boxStartC;j<boxEndC;j++)
             {
                if(i!=r && j!=c && board[i][j]==board[r][c])
                    return false;
             }  
       }
       return true;
	}
    private boolean valid(int r,int c)
    {
       if(!sudokuRules(r,c))
       		return false;
       
       for(Rule rule:rules)
       	{
       		if(!rule.check(board,r,c))
       			return false;
       	}
       
       return true;
    }
    public Solver addRule(Rule r)
    {
    	rules.add(r);
    	return this;
    }
    private boolean blank(int[] bl)
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
                if(board[i][j]==0)
                {
                    bl[0]=i;
                    bl[1]=j;
                    return true;
                }
        }
        
        return false;
    }
    public static boolean inside(int r,int c)
    {
    	return !(r<0 || r>8 || c<0 || c>8);
    }
    public boolean solve(int[][] board)
    {
            Stack<Attempt> stack=new Stack<Attempt>();
            
            this.board=board;
            
            int[] bl=new int[2];
            if(!blank(bl))
                return true;
            
            stack.push(new Attempt(bl[0],bl[1]));
                
            Attempt top;
            while(!stack.empty())
            {
            	top=stack.peek();
            	
                if(top.i>9)
                {
                	stack.pop();
                	board[top.r][top.c]=0;
                }
                
                for(;top.i<10;top.i++)
                {
                		board[top.r][top.c]=top.i;
                		if(valid(top.r,top.c))
                		{
                			if(!blank(bl))
                				return true;
                			
                			stack.push(new Attempt(bl[0],bl[1]));
                			
                			top.i++;
                			break;
                		}
                }
            }
            return false;
    }
    public void print(int[][] board)
    {
    	for(int i=0;i<board.length;i++)
    	{
    		for(int j=0;j<board[0].length;j++)
    			System.out.print(board[i][j]+" ");
    		System.out.println();
    	}
    	System.out.println();
    }
 	public static boolean validSudoku(int[][] board)
 	{
 		int[][] row=new int[9][10];
 		int[][] col=new int[9][10];
 		int[][] box=new int[9][10];
 		
 		int current;
 		for(int i=0;i<9;i++)
 		{
 			for(int j=0;j<9;j++)
 			{
 				current=board[i][j];
 				row[i][current]++;
 				col[j][current]++;
 				int boxIndex=(i/3)*3+j/3;
 				box[boxIndex][current]++;
 				
 				if(row[i][current]>1 || col[j][current]>1 || box[boxIndex][current]>1)
 					return false;
 			}
 		}
 		return true;
 	}
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
 		Solver s=new Solver();
    	Rule knight=(board,r,c)->{
    						int current=board[r][c];
    						
    						if(inside(r-1,c-2) && board[r-1][c-2]==current)
    								return false;
    						if(inside(r-1,c+2) && board[r-1][c+2]==current)
    								return false;
    						if(inside(r-2,c-1) && board[r-2][c-1]==current)
    								return false;
    						if(inside(r-2,c+1) && board[r-2][c+1]==current)
    								return false;
    						if(inside(r+1,c-2) && board[r+1][c-2]==current)
    								return false;
    						if(inside(r+1,c+2) && board[r+1][c+2]==current)
    								return false;
    						if(inside(r+2,c-1) && board[r+2][c-1]==current)
    								return false;
    						if(inside(r+2,c+1) && board[r+2][c+1]==current)
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
    									if(inside(r+i,c+i) && board[r+i][c+i]==current)
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
    						
    						if(inside(r-1,c) && (board[r-1][c]==prev || board[r-1][c]==next))
    						return false;
    						if(inside(r+1,c) && (board[r+1][c]==prev || board[r+1][c]==next))
    						return false;
    						if(inside(r,c-1) && (board[r][c-1]==prev || board[r][c-1]==next))
    						return false;
    						if(inside(r,c+1) && (board[r][c+1]==prev || board[r][c+1]==next))
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
    	example2();
    }
}
