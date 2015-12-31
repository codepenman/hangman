import greenfoot.*;

/**
 * Write a description of class HasWrongLetters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HasWrongLetters extends Actor implements HangmanState
{
    HasLetters hasletters;
    public HasWrongLetters(HasLetters newhasletters) {
       hasletters = newhasletters;
        
    } 
    public void wingame() {
    }
    public void losegame() {
      Greenfoot.delay(5);  
      Greenfoot.setWorld(new LoseWorld());  
      Greenfoot.playSound("Fail.mp3");
      Greenfoot.stop();
    }
}
