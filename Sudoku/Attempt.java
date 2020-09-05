package Sudoku;
public class Attempt
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
