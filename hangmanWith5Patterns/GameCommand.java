/**
 * Write a description of class PlayGameCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameCommand implements Command 
{
    // instance variables - replace the example below with your own
    IReceiver target;

    /**
     * Constructor for objects of class PlayGameCommand
     */
    public GameCommand()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void execute() {
	    target.doAction();
	}

	public void setReceiver(IReceiver receiver) {
	    target = receiver ;
	}
}
