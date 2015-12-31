import greenfoot.*;
import java.util.*;

/**
 * Write a description of class Alphabet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alphabet implements IAlphabetSubject
{   
    private String inputChar;
    private IWord word;
    private ArrayList<IValidInputObserver> valid_observers = new ArrayList<IValidInputObserver>();
    private ArrayList<IInValidInputObserver> invalid_observers = new ArrayList<IInValidInputObserver>();
   
    /**
     * Constructor for objects of class Alphabet
     */
    public Alphabet(IWord word)
    {
        this.inputChar = "";
        this.word = word;
    }
           
    public void registerValidInputObserver(IValidInputObserver obj)
    {
       valid_observers.add(obj);
    }
        
    public void registerInValidInputObserver(IInValidInputObserver obj)
    {
        invalid_observers.add(obj);
    }
    
    public void removeValidInputObserver(IValidInputObserver obj)
    {
        valid_observers.remove(obj);
    }

    public void removeInValidInputObserver(IInValidInputObserver obj)
    {
        invalid_observers.remove(obj);
    }
    
    public void updateUserInput(String inputChar)   {
        this.inputChar = inputChar;
        validateAndNotify();
    }

    private void validateAndNotify()    {
//      boolean flag = false;
        boolean validInput = false;
        System.out.println("Word : " + word.getWord());
        if(word.getWord().contains(inputChar)){
            validInput = true;
        }
        
        for(int i = 0; i < word.getWord().length();i++) {
            
            System.out.println("Character : " + word.getWord().charAt(i));           
            if (inputChar.equals(word.getWord().charAt(i)))   {
               //flag = true;
               validInput = true;
               break; 
            }            
        }
        
        if(validInput)  {
            notifyObservers(true);
        } else  {
            notifyObservers(false);                        
        }
    }
    
    public void notifyObservers(boolean validInput)   {
        if(validInput)  {
            notifyObserversOnValidInput();        
        }else   {
            notifyObserversOnInValidInput();
        }
    }    
    
    public void notifyObserversOnValidInput()   {
        for(IValidInputObserver observer : valid_observers) {
            observer.validInput();
        }
    }
    
    public void notifyObserversOnInValidInput()   {
        for(IInValidInputObserver observer : invalid_observers) {
            observer.inValidInput();
        }
    }
    
    public String getInputChar()  {
        return inputChar;
    } 
}
