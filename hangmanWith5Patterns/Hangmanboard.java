import greenfoot.*;

/**
 * Write a description of class Hangmanboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hangmanboard extends Actor implements IInValidInputObserver
{
    /**
     * Act - do whatever the Hangmanboard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int wrong;
    public void act() 
    {
        // Add your action code here.
    }   
    public void inValidInput(){
        wrong++;
        Actor scribe = new Actor(){};
        Board board = (Board)getWorld();   
        GreenfootImage image = null;
        switch(wrong)
        {
            case 1 : 
            image = new GreenfootImage(80,80);
            scribe.setImage(image);
            board.addObject(scribe,465,235);
            for(int i = 0; i < 45; i++)
            {
                image.rotate(8);
                image.fillOval(35,0,10,10);
                Greenfoot.delay(1);
            }
            break;
            
            case 2:
            image = new GreenfootImage(10,100);
            scribe.setImage(image);
            board.addObject(scribe,465,310);
            for(int i = 0; i < 50; i++)
            {
               
                image.fillOval(0,i*3,10,10);
                Greenfoot.delay(1);
            }
            break;
              
            case 3:
             image = new GreenfootImage(10,100);
             scribe.setImage(image);
             scribe.setRotation(60);
             board.addObject(scribe,420,320);
             for(int i = 0 ; i < 50; i++)
             {
                 image.fillOval(0,i*2,10,10);
                 Greenfoot.delay(1);
              }
              break;
              
            case 4:
            
            image =  new GreenfootImage(10,100);
            scribe.setImage(image);
            scribe.setRotation(300);
            board.addObject(scribe,513,320);
            for (int i =0;i<50;i++)
            {
                image.fillOval(0,i*2,10,10);
                Greenfoot.delay(1);
            }
            break;
            
            case 5:
            
            image = new GreenfootImage(10,100);
            scribe.setImage(image);
            scribe.setRotation(30);
            board.addObject(scribe,440,400);
            for (int i = 0 ; i<50;i++)
            {
                image.fillOval(0,i*2,10,10);
                Greenfoot.delay(1);
            }
            break;
            
            case 6:
            image = new GreenfootImage(10,100);
            scribe.setImage(image);
            scribe.setRotation(330);
            board.addObject(scribe,490,400);
            for (int i =0 ;i<50;i++)
            {
                image.fillOval(0,i*2,10,10);
                Greenfoot.delay(1);
            }
            break;
            
            
          }
        HasLetters hasletters = new HasLetters();
        hasletters.count(wrong,false,"");
        }    
}
