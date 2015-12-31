import greenfoot.*;

/**
 * Write a description of class UMLDiagrams here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UMLDiagrams extends Actor implements IInvoker
{
    /**
     * Act - do whatever the UMLDiagrams wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Command cmd ;
    public void act() 
    {
        if(Greenfoot.mousePressed(this))
       {
           //Greenfoot.setWorld(new Board());
           invoke();
           System.out.println("Uml Diagram Clicked");
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
