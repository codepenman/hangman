import greenfoot.*;

/**
 * Write a description of class HasLetters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HasLetters extends Actor implements HangmanState
{
  HangmanState hasCorrectLetters;
  HangmanState hasWrongLetters;
  HangmanState hangmanstate;
  
  public HasLetters() {
    hasCorrectLetters = new HasCorrectLetters(this);
    hasWrongLetters = new HasWrongLetters(this);
   }
   
   void setHangmanState(HangmanState newhangmanstate) {
      hangmanstate = newhangmanstate;
    }
    public void count(int ctr,boolean flag,String word) {
        if (flag == true)
        {
            setHangmanState(hasCorrectLetters);
            if(ctr == word.length()) 
            {
                hangmanstate.wingame();
            }
        }
        else
        {
            setHangmanState(hasWrongLetters);
            if(ctr == 6)
            {
                hangmanstate.losegame();
            }
        }
    }
    public void wingame() {
        hangmanstate.wingame();
    }
    public void losegame() {
        hangmanstate.losegame();
    }
    
}
