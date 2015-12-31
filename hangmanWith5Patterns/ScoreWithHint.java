/**
 * Write a description of class ScoreWithHint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreWithHint extends ScoreDecorator {
	 
	    public ScoreWithHint(IScoreCalculator newScore) {
	         
	        super(newScore);
	         
	        System.out.println("Hint was asked");
	         
	        
	    }
	    public int getScore(){
	         
	        System.out.println("Pounts to be deducted " + 5);
	         int temp_score=tempScore.getScore()-5;
	        return temp_score;
	         
	    }
	     
	}
