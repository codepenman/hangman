import greenfoot.*;
import java.util.*;
import java.awt.Color;

/**
 * This class is a bucket of all the Alphabet Actors. This class is also a OBSERVER on the Alphabet SUBJECT, so attach this class to Alphabet SUBJECT.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alphabucket extends Actor implements IInValidInputObserver,IValidInputObserver
{
    private List<IAlphabet> alphabets;
    private Map<String, Text> actors;
    private Alphabet alphabetSubject;
    
    public Alphabucket(Alphabet alphabetSubject)    {
        this.alphabetSubject = alphabetSubject;
        alphabets = new ArrayList<IAlphabet>();
        actors = new HashMap<String, Text>();
    }
    
    /**
     * Act - do whatever the Alphabucket wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
    
    public void repaint()   {
        //Iterator itr = alphabets.entrySet().iterator();
        Board board = (Board)getWorld();
        List<Text> remainingActors = board.getObjects(Text.class);
        board.removeObjects(remainingActors);
        //itr = null;
        //itr = alphabets.entrySet().iterator();
        actors.clear();
        for(IAlphabet iAlphabet : alphabets)    {
            paintAlphabet(iAlphabet);
        }
    }
    public void addAlphabet(IAlphabet iAlphabet)   {
        alphabets.add(iAlphabet);
        paintAlphabet(iAlphabet);
    }
    
    public void removeAlphabet(String alphabet)    {
        Text text = actors.get(alphabet);
        Board board = (Board)getWorld();
        board.removeObject(text);
    }
    
    public void inValidInput()  {
        System.out.println(alphabetSubject.getInputChar());
        removeAlphabet(alphabetSubject.getInputChar());
    }
    
    public void validInput()    {
        System.out.println(alphabetSubject.getInputChar());
        removeAlphabet(alphabetSubject.getInputChar());
    }
    
    private void paintAlphabet(IAlphabet iAlphabet)    {
        Text text = new Text(iAlphabet.getAlphabet());
        actors.put(iAlphabet.getAlphabet(), text);
        Board board = (Board)getWorld();
        if(board == null)   {
            System.out.println("NULL");
        }
        board.addObject(text, iAlphabet.getX(), iAlphabet.getY());    
    }
    
    private class Text extends Actor    {     
        
        public Text(String text)  {         
            setText(text);  
        }  
     
        public void setText(String text)  {  
            setImage(new GreenfootImage(text, 26, Color.black, new Color(0, 0, 0, 0)));  
        }    
    }
}
