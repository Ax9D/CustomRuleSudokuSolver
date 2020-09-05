# CustomRuleSudokuSolver

A custom rule based iterative sudoku solver implementation. 

Extra rules can be added by specifying a lambda that checks if the rule holds given a particular board state. It must return a true if the rule holds and false if it doesn't. 

This lambda for example implements the rule "Any two cells separated by a king's move cannot contain the same digit".


```java
Rule king=(board,r,c)->{
    						int current=board[r][c];
    						for(int i=-1;i<=1;i++)
    						{
    							for(int j=-1;j<=1;j++)
    							{
    								if(i!=0 && j!=0)
    								{
    									//Solver.inside(int r,int c) checks if (r,c) is a valid cell inside the grid 
    									if(Solver.inside(r+i,c+i) && board[r+i][c+i]==current)
    										return false;
    								}
    							}
    						}
    						return true;
    					};
```


