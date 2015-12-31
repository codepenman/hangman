/**
 * Write a description of class Word here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Word implements IWord 
{
    private static String word;

    /**
     * Constructor for objects of class Word
     */
    public Word(String word)
    {
        this.word = word;
    }

    public String getWord()   {
        return word;
    }    
}
