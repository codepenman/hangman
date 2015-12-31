import greenfoot.*;
import java.util.*;

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends World
{

    /**
     * Constructor for objects of class Board.
     * 
     */
    int wrong = 0;
    int found = 0;
    IInputHandler firstInputHandler;
    Alphabucket alphaBucket;
    Word word;
    
    public Board(Category category)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 1400, 1);    

        prepare(category);
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare(Category category)
    {
        Hangmanboard hangmanboard = new Hangmanboard();
        addObject(hangmanboard, 355, 286);
        hangmanboard.setLocation(515, 337);
        
        String curWord = category.nextWord();
        System.out.println("Current word is: " + curWord);    
        word = new Word(curWord);       
        Alphabet alphabet = new Alphabet(word); // This class name can also be changed to name Word which contains current word....              
        alphaBucket = new Alphabucket(alphabet);
        alphabet.registerValidInputObserver(alphaBucket);
        alphabet.registerInValidInputObserver(alphaBucket);
        
        IInputHandler a = new A(alphabet);
        IInputHandler b = new B(alphabet);
        IInputHandler c = new C(alphabet);
        IInputHandler d = new D(alphabet);
        IInputHandler e = new E(alphabet);
        IInputHandler f = new F(alphabet);
        IInputHandler g = new G(alphabet);
        IInputHandler h = new H(alphabet);
        IInputHandler i = new I(alphabet);
        IInputHandler j = new J(alphabet);
        IInputHandler k = new K(alphabet);
        IInputHandler l = new L(alphabet);
        IInputHandler m = new M(alphabet);
        IInputHandler n = new N(alphabet);
        IInputHandler o = new O(alphabet);
        IInputHandler p = new P(alphabet);
        IInputHandler q = new Q(alphabet);
        IInputHandler r = new R(alphabet);
        IInputHandler s = new S(alphabet);
        IInputHandler t = new T(alphabet);
        IInputHandler u = new U(alphabet);
        IInputHandler v = new V(alphabet);
        IInputHandler w = new W(alphabet);
        IInputHandler x = new X(alphabet);
        IInputHandler y = new Y(alphabet);
        IInputHandler z = new Z(alphabet);
        
        firstInputHandler = a;        
        
        //ValidInputObserver valid = new ValidInputObserver(alpha);
        //InvalidInputObserver invalid = new InvalidInputObserver(alpha);
        Dashboard dashBoard = new Dashboard(alphabet, word);
        alphabet.registerValidInputObserver(dashBoard);
        addObject(dashBoard, 1599, 1399);
        dashBoard.paint();
        
        Hint hint=new Hint(alphabet,word);
        alphabet.registerValidInputObserver(hint);
        addObject(hint,50,100);
        
        
        
        ScoreBoard scoreboard = new ScoreBoard(alphabet,hint,word);
        alphabet.registerValidInputObserver(scoreboard);
        hint.registerScoreBoardObserver(scoreboard);
       
        addObject(scoreboard,1304, 149);
        
        alphabet.registerInValidInputObserver(hangmanboard);
        //alpha.registerObserver(dash);
        //alpha.registerObserver(valid);
        //alpha.registerObserver(invalid);
        HasLetters hasletters =  new HasLetters();
         addObject(hasletters , 1590,1399);
        HasCorrectLetters hascorrectletters = new HasCorrectLetters(hasletters);
        addObject(hascorrectletters , 1599,1399);
        
        HasWrongLetters haswrongletters = new HasWrongLetters(hasletters);
        //addObject(haswrongletters , 1599,1399);
        
        a.setSuccessor(b);
        b.setSuccessor(c);
        c.setSuccessor(d);
        d.setSuccessor(e);
        e.setSuccessor(f);
        f.setSuccessor(g);
        g.setSuccessor(h);
        h.setSuccessor(i);
        i.setSuccessor(j);
        j.setSuccessor(k);
        k.setSuccessor(l);
        l.setSuccessor(m);
        m.setSuccessor(n);
        n.setSuccessor(o);
        o.setSuccessor(p);
        p.setSuccessor(q);
        q.setSuccessor(r);
        r.setSuccessor(s);
        s.setSuccessor(t);
        t.setSuccessor(u);
        u.setSuccessor(v);
        v.setSuccessor(w);
        w.setSuccessor(x);
        x.setSuccessor(y);
        y.setSuccessor(z);                

        addObject(alphaBucket, 1169, 600); // Dont move this line, order is important...
        alphaBucket.setLocation(1255, 577);
        
        alphaBucket.addAlphabet((IAlphabet)a);
        alphaBucket.addAlphabet((IAlphabet)b);
        alphaBucket.addAlphabet((IAlphabet)c);
        alphaBucket.addAlphabet((IAlphabet)d);
        alphaBucket.addAlphabet((IAlphabet)e);
        alphaBucket.addAlphabet((IAlphabet)f);
        alphaBucket.addAlphabet((IAlphabet)g);
        alphaBucket.addAlphabet((IAlphabet)h);
        alphaBucket.addAlphabet((IAlphabet)i);
        alphaBucket.addAlphabet((IAlphabet)j);
        alphaBucket.addAlphabet((IAlphabet)k);
        alphaBucket.addAlphabet((IAlphabet)l);
        alphaBucket.addAlphabet((IAlphabet)m);
        alphaBucket.addAlphabet((IAlphabet)n);
        alphaBucket.addAlphabet((IAlphabet)o);
        alphaBucket.addAlphabet((IAlphabet)p);
        alphaBucket.addAlphabet((IAlphabet)q);       
        alphaBucket.addAlphabet((IAlphabet)r);
        alphaBucket.addAlphabet((IAlphabet)s);        
        alphaBucket.addAlphabet((IAlphabet)t);
        alphaBucket.addAlphabet((IAlphabet)u);
        alphaBucket.addAlphabet((IAlphabet)v);       
        alphaBucket.addAlphabet((IAlphabet)w);
        alphaBucket.addAlphabet((IAlphabet)x);       
        alphaBucket.addAlphabet((IAlphabet)y);        
        alphaBucket.addAlphabet((IAlphabet)z); 
               
        
        
        Pole pole = new Pole();
        addObject(pole, 400, 286);
        
    }

    public void act()   {
        if(Greenfoot.mouseClicked(null)) {
            System.out.println("Act called"); 
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            int x = mouseInfo.getX();
            int y = mouseInfo.getY();
            System.out.println("X : " + x); 
            System.out.println("Y : " + y);             
            ((IMouseClickHandler)firstInputHandler).handleMouseClick(x, y);
            //alphaBucket.removeAlphabet("A");  
            //alphaBucket.removeAlphabet("A");
            //alphaBucket.addAlphabet((IAlphabet)new A(null));            
        }else   {
          String key = Greenfoot.getKey();
          if(key != null)   {
           System.out.println("Key : " + key); 
           //((IKeyPressHandler)firstInputHandler).handleKeyPress(key);          
          }
        }        
    }

    /*public String getCurrentWord()
    {
        return word;
    }
    
    public void setCurrentWord(Category category)
    {
      //Category words = new Category();


    }*/
    
/*    public List<Integer> letter(String letter)
    {
       
        List<Integer> position = new ArrayList(); 
        boolean flag = false;
        for(int i = 0; i < word.getWord().length();i++)
        {
           
            if (letter.charAt(0) == word.getWord().charAt(i))
            {
               flag = true;
               int x = 120 + i * 100;
               int y = 635;
               position.add(0,x);
               position.add(1,y);
               found++;
               addObject(new ScoreBoard(""+found), 1400, 200);
          }
          
       
        }
        if(!flag)
        {
           wrong++;
        }
        if(wrong > 0 ) createHangman();
        
        if( found == word.length()) 
        Greenfoot.playSound("Win.wav");
        return position; 
        
    }
    public void createHangman()
    {
        Actor scribe = new Actor(){};
        GreenfootImage image = null;
        switch(wrong)
        {
            case 1 : 
            image = new GreenfootImage(80,80);
            scribe.setImage(image);
            addObject(scribe,400,220);
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
            addObject(scribe,400,310);
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
             addObject(scribe,350,320);
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
addObject(scribe,450,320);
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
addObject(scribe,370,400);
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
addObject(scribe,420,400);
for (int i =0 ;i<50;i++)
{
    image.fillOval(0,i*2,10,10);
    Greenfoot.delay(1);
}
  Greenfoot.playSound("Fail.mp3");
Greenfoot.stop();
break;


        }
        
   
    } */
}
