import greenfoot.*;
import java.util.*;
import java.awt.Color;

/**
 * Write a description of class Hint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hint extends Actor implements IValidInputObserver,IHintSubject{
    private IWord word;
    Alphabet subject;
    String currentWord;
    String validLettersEntered="";
    String hintLetter;
    int count=0;
    String hintLetters="";
    private ArrayList<IScoreBoardObserver> scoreboard_observers = new ArrayList<IScoreBoardObserver>();
    
   Hint(Alphabet subject,IWord word)
   {
       this.subject=subject;
       this.word=word;
       this.currentWord=word.getWord();
      System.out.println("in hint constructor and current word is:"+currentWord); 
    }
    
      public void registerScoreBoardObserver(IScoreBoardObserver observer)
      {
          scoreboard_observers.add(observer);
       }
     
    public void notifyObservers(boolean hintAsked)   {
        if(hintAsked)  {
            notifyObserversOnHintAsked();        
        }
    }
     public void notifyObserversOnHintAsked()   {
        for(IScoreBoardObserver observer : scoreboard_observers) {
            observer.hintAsked();
        }
    }
    
    public void act() 
    {
          if(Greenfoot.mousePressed(this))
       {
           System.out.println("hint clicked");
           displayHint();
          
           
       }
    } 
    public void validInput()    {
       String inputChar = subject.getInputChar();
       validLettersEntered+=inputChar;
       System.out.println("Valid letters entered so far  : " + validLettersEntered);
    }
    
    
 
    public void displayHint(){
        
        
        for(int i=0;i<currentWord.length();i++)
           {
               
               if((validLettersEntered.indexOf(currentWord.charAt(i)))==-1){
               hintLetter=String.valueOf(currentWord.charAt(i));
               System.out.println("hint letter is:"+hintLetter);
               hintLetters+=hintLetter;
               validLettersEntered+=String.valueOf(currentWord.charAt(i));
               Actor actor = getGenericActor();
               actor.setImage(getLetterImage(hintLetter,Color.black));
               Board board = (Board)getWorld();
               if(board == null){
               System.out.println("NULL");
               }
               board.addObject(actor,130,count++*75+30);
               notifyObservers(true);
               break;
               }
           }
        
         
        
    
}
    private Actor getGenericActor() {
        return new Actor(){};
    }
    
    private GreenfootImage getLetterImage(String alphabet,Color color)  {
        return new GreenfootImage(alphabet,36,color,null);
    }
    
    
}
