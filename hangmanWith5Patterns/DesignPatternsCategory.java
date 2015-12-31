import greenfoot.*;

/**
 * Write a description of class DesignPatternsCategory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DesignPatternsCategory implements Category
{
    /**
     * Act - do whatever the DesignPatternsCategory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static String[] words = { "OBSERVER", "COMMAND","SINGLETON"};
    static java.util.Random rand = new java.util.Random(System.currentTimeMillis());
    public String nextWord()
    {
        return words[rand.nextInt(words.length)];
    }
}
