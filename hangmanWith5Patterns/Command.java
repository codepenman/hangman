

/**
 * Write a description of class Command here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Command
{
    /**
     * Act - do whatever the Command wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public abstract void execute();
    public abstract void setReceiver(IReceiver receiver);
      
}
