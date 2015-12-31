import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Actor implements IValidInputObserver,IScoreBoardObserver
{
    private Alphabet alphabetSubject;
    private Hint hintSubject;
    private IWord word;
    private int currentScore = 0;
    int flag_hintAsked=0;
    IScoreCalculator current_score_withHint;
    IScoreCalculator current_score_withoutHint;
    Actor actor = null;
    
    //
  
    /**
     * Act - do whatever the ScoreBoard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
  
    
    public ScoreBoard(Alphabet alphabetSubject,Hint hintSubject, IWord word)    {
        this.alphabetSubject = alphabetSubject;
        this.hintSubject=hintSubject;
        this.word = word;
    }
    public void act() 
    {
        // Add your action code here.
    } 
    public void hintAsked()
    {
       // currentScore=current_score_withHint.getScore();
        
        flag_hintAsked=1;
    }
    public void validInput()    {
        if(flag_hintAsked==1)
        {
         current_score_withHint = new ScoreWithHint(new BasicScore(currentScore));
        currentScore= current_score_withHint.getScore();
        System.out.println("current score is:"+currentScore);
         flag_hintAsked=0;
        }
        else
        {
        current_score_withoutHint = new BasicScore(currentScore);
        currentScore= current_score_withoutHint.getScore();
	    System.out.println("updated score is" +  currentScore);
	   }
	    paintScore();
	   }
	   
	   public void paintScore()
	   {
        actor = getGenericActor();
        Color transparent = new Color(0,0,0,0);
        actor.setImage(new GreenfootImage(""+currentScore,80, Color.black,transparent));
        Board board = (Board)getWorld();
        if(board == null)   {
            System.out.println("NULL");
        }
        board.addObject(actor,1300, 100);   
    }
    public void setLabel(String str)
    {
        setImage(new GreenfootImage(str,80, null,Color.white));
          
    }
    private Actor getGenericActor() {
        if (actor == null)
        {
            
        return new Actor(){};
    }
    else{
        return actor;
    }
    }
}
