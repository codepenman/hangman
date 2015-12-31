import greenfoot.*;

/**
 * Write a description of class DesignPatterns here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DesignPatterns extends Actor implements IInvoker
{
    /**
     * Act - do whatever the DesignPatterns wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Command cmd ;
    public void act() 
    {
        if(Greenfoot.mousePressed(this))
       {
           //Greenfoot.setWorld(new Board());
           invoke();
           System.out.println("Design Patterns Clicked");
       }
    }
    
    public void setCommand( Command c ) 
    {
        cmd = c ;
       }
    public void invoke() 
    {
        cmd.execute() ;
    }
}
