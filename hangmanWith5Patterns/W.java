import greenfoot.*;
import java.util.*;

/**
 * Responsibility of this class is to Recognise Character Input A, and update the Alphabet Subject.
 * This class has the details of Character Position on the world...
 * 
 * @author Harish Kumar K V 
 * @version v1.0
 */
public class W implements IMouseClickHandler, IKeyPressHandler, IAlphabet
{
    private static final String ALPHABET = "W";
    private static final int X_LOCATION = Position.W.x;
    private static final int Y_LOCATION = Position.W.y;
    private IInputHandler nextHandler;
    private Alphabet alphabetSubject;         
    
    public W(Alphabet alphabetSubject)  {
        this.alphabetSubject = alphabetSubject;       
    }
    
    public String getAlphabet() {
        return ALPHABET;
    }
    
    public void setSuccessor(IInputHandler nextHandler)    {
        this.nextHandler = nextHandler;
    }    
    
    public void handleKeyPress(String alphabet)    {
        if(alphabet.equalsIgnoreCase(ALPHABET))  {
            setAlphabet();
        }else   {
            if(nextHandler != null)    {
                ((IKeyPressHandler)nextHandler).handleKeyPress(alphabet);                
            }
        }
    }
    
    public void handleMouseClick(int x, int y)  {
        if(BoundsCheckHelper.inBound(x, y, X_LOCATION, Y_LOCATION))  {
            setAlphabet();
        }else   {
            if(nextHandler != null)    {
                ((IMouseClickHandler)nextHandler).handleMouseClick(x, y);
            }            
        }        
    }
    
    public int getX()   {
        return X_LOCATION;
    }
    
    public int getY()   {
        return Y_LOCATION;
    }
    
    private void setAlphabet()   {
        System.out.println("Handled your input " + ALPHABET);
        alphabetSubject.updateUserInput(ALPHABET);
        //update the Alphabet Subject
    }    
}
