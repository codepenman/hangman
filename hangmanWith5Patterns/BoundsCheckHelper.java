
/**
 * 
 * @author Harish Kumar K V 
 * @version v1.0
 */
public class BoundsCheckHelper  
{
    // instance variables - replace the example below with your own
    private static final int RADIUS = 50;

    /**
     * Constructor for objects of class BoundsCheckerHelper
     */
    private BoundsCheckHelper()
    {
    }

    /**
     * This method will check if user click, lies with in Character Bounds..
     * 
     * @param  (x1, y1) -> User Click Co-Ordinates, (x2, y2) Position of Character 
     * @return true, if User CLick lies with in the Character bounds else false.
     */
    public static boolean inBound(int x1, int y1, int x2, int y2)
    {
        int radius = ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));        
        //System.out.println("Radius : " + radius);
        
        if(radius <= RADIUS)    {
            return true;
        }
        return false;
    }
}
