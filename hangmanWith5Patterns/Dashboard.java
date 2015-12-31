import greenfoot.*;
import java.util.*;
import java.awt.Color;

/**
 * Write a description of class Dashboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dashboard extends Actor implements IValidInputObserver/*extends ValidInputObserver*/
{
    private Alphabet subject;
    private IWord word;   
    int right = 0; 
    private HasLetters hasletters;
    //TODO://Declare a list of Actors (Each actor is a character that will be created and inserted on valid input), we need this list to clear the input when we want to refresh the screen...
    //TODO://Declare a Map as defined in the prepare method, and define the map in Constructor.    
    
    /*public Dashboard(Alphabet alpha)    {
        super(alpha);
    }*/

    public Dashboard(Alphabet subject, IWord word)    {
        this.subject = subject;
        this.word = word;
        hasletters = new HasLetters();
    }
    
    public void setWord(IWord word)   {
        this.word = word;
        paint();
    }    
    
    public void paint() {
        //TODO://Create all the dashes in this method based on the length of Word. And also have a map of Character to X,Y Co-Ordinates. This Map will be used to create Character on Valid User Input
        Board board = (Board)getWorld();        
        for(int i = 0; i < word.getWord().length(); i++)
        {
            Line line = new Line();
            int x = 120 + i *100;
            int y = 650 ;
            board.addObject(line, x, y);    
        }    
    }
    
    public void validInput()    {
        //TODO://Perform the operations step by step
        /*
           1. Fetch the Input Character from the Subject
           2. Using the Input Character as a Key to Map, fetch the (X,Y) Co-Ordinates.
           3. Create Character Actor in X,Y co-ordinates.
           4. Insert the Character Actor Reference to Actors List.
           */
          
        String inputChar = subject.getInputChar();
        update(inputChar);
        clear();
        System.out.println("Input Character : " + inputChar);
    }
    
    public void update(String letter)    {
        Board world = (Board)getWorld();
  
            for(int i=0; i<word.getWord().length();i++)
            {
              if (letter.charAt(0) == word.getWord().charAt(i))
            {
               
               int x = 120 + i * 100;
               int y = 635;
               right++;
               paintAlphabet(letter,x,y);
          }
        }
        
    }
    
    public void clear() {
        //Remove all the Character Actors present in the Actors List from the world "this"(DashBoard) actor is present..
        
     }
    
 private void paintAlphabet(String iAlphabet,int x, int y)    {
       Actor actor = getGenericActor();
       actor.setImage(getLetterImage(iAlphabet,Color.black));
        Board board = (Board)getWorld();
        if(board == null)   {
            System.out.println("NULL");
        }
        board.addObject(actor, x, y);  
        hasletters.count(right,true,word.getWord());
    }
    
    private Actor getGenericActor() {
        return new Actor(){};
    }
    
    private GreenfootImage getLetterImage(String alphabet,Color color)  {
        return new GreenfootImage(alphabet,36,color,null);
    }
    
   
    
}
