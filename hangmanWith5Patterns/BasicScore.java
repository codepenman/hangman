/**
 * Write a description of class BasicScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasicScore implements IScoreCalculator 
{
    int basicScore;
    BasicScore(int basicScore)
    {
     this.basicScore=basicScore;   
    }
     public int getScore() {
          this.basicScore+=10;
          return basicScore;
	    }

}
