import greenfoot.*;

/**
 * Write a description of class WelcomeScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeScreen extends World
{

    /**
     * Constructor for objects of class WelcomeScreen.
     * 
     */
    public WelcomeScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        PlayGame playgameInvoker = new PlayGame();
        addObject(playgameInvoker, 931, 557);
        playgameInvoker.setLocation(911, 311);
      
        Command playGame = new GameCommand();
        Command quitGame = new GameCommand();
        playGame.setReceiver(new IReceiver(){
                public void doAction() {
                    WhiteBoard h = new WhiteBoard();
                    addObject(h, 750, 250);
                   
                    DesignPatterns dPInvoker = new DesignPatterns();
                    addObject(dPInvoker, 750, 200);
                    Command designPatternCategory = new GameCommand();
                    Command UMLDiagramCategory = new GameCommand();
                    designPatternCategory.setReceiver(new IReceiver(){
                            public void doAction() {
                                System.out.println("DSClicked");
                                Greenfoot.setWorld(new Board(new DesignPatternsCategory()));
                            }});
                    dPInvoker.setCommand(designPatternCategory);
                    UMLDiagrams umlDiagramInvoker = new UMLDiagrams();
                    addObject(umlDiagramInvoker, 750, 300);
                    UMLDiagramCategory.setReceiver(new IReceiver(){
                            public void doAction() {
                                System.out.println("UMLClicked");
                                Greenfoot.setWorld(new Board(new UMLDiagramCategory()));
                            }});
                    umlDiagramInvoker.setCommand(UMLDiagramCategory);
                    //Greenfoot.setWorld(new Board());

                }});
        playgameInvoker.setCommand(playGame);
       
    }
}
