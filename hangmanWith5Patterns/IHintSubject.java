public interface IHintSubject  
{
    // instance variables - replace the example below with your own
    public void registerScoreBoardObserver(IScoreBoardObserver observer);
    public void notifyObservers(boolean hintAsked);
}
