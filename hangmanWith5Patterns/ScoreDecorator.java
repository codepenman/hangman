/**
 * Write a description of class ScoreDecorator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
abstract class ScoreDecorator implements IScoreCalculator {

    protected IScoreCalculator tempScore;

    public ScoreDecorator(IScoreCalculator newScore){

        tempScore = newScore;

    }


    public int getScore() {

        return tempScore.getScore();

    }

}
