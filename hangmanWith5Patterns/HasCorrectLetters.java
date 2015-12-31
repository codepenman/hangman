import greenfoot.*;

/**
 * Write a description of class HasCorrecLetters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HasCorrectLetters extends Actor implements HangmanState
{
    HasLetters hasletters;
    public HasCorrectLetters(HasLetters newhasletters) {
       hasletters = newhasletters;
        
    }
    
    public void wingame() {
      Greenfoot.delay(5);
      Greenfoot.setWorld(new WinWorld());
      Greenfoot.playSound("Win.wav"); 
      
      Greenfoot.stop();  
      
    }
    public void losegame() {
    }
}
