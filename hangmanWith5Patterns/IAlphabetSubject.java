public interface IAlphabetSubject  
{
    
     public void registerValidInputObserver(IValidInputObserver observer);
     public void registerInValidInputObserver(IInValidInputObserver observer);
     public void removeValidInputObserver(IValidInputObserver observer);
     public void removeInValidInputObserver(IInValidInputObserver observer);
     public void notifyObservers(boolean validInput);
}
