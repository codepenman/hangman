import greenfoot.*;

/**
 * Write a description of class PlayGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayGame extends Actor implements IInvoker
{
    /**
     * Act - do whatever the PlayGame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Command cmd ;
    public void act() 
    {
        // Add your action code here.
         if(Greenfoot.mousePressed(this))
       {
           //Greenfoot.setWorld(new Board());
           invoke();
           System.out.println("Play Game Clicked");
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
