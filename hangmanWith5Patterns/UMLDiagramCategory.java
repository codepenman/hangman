/**
 * Write a description of class UMLDiagramCategory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UMLDiagramCategory implements Category 
{
    // instance variables - replace the example below with your own
    static String[] words = { "CLASS", "SEQUENCE","ACTIVITY"};
    static java.util.Random rand = new java.util.Random(System.currentTimeMillis());
    public String nextWord()
    {
        return words[rand.nextInt(words.length)];
    }
}
