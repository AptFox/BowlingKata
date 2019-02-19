package main.java.com.audition;
import java.util.HashMap;
import java.util.Map;
/*
 * Prompt: 
 * 	Create a program, which, given a valid sequence of rolls for one line of American Ten-Pin Bowling, 
 * 	produces the total score for the game. 
 * 
 * 
 * Here are some things that the program will not do:
    We will not check for valid rolls.
    We will not check for correct number of rolls and frames.
    We will not provide scores for intermediate frames.

 */
public class BowlingScoreCalculator {
	
    Map<Character, Integer> scoreMap = new HashMap<Character, Integer>(){
    	{
    	put('X',10);
	    put('/',10);
    	}
    };
	
	public int ScoreGame(String line) {
		return ScoreGame(line.replaceAll("\\s", ""), null);
	}
	
	public int ScoreGame(String line, Integer frame) {
		frame = frame == null ? 1 : frame;
		boolean onLastFrame = frame == 10;
		boolean takeSubStringAtIndexOne = false;
		int scoreForCurrentFrame;
		Character throwOne = line.charAt(0);
		Character throwTwo = line.charAt(1);
		Character throwThree = null;
		if (throwOne == 'X' || throwTwo == '/') {
			throwThree = line.charAt(2);
			if(throwOne == 'X') { takeSubStringAtIndexOne = true; }
			else {throwOne = null;}
		}
		else {
			if (throwOne == '-') {
				throwOne = null;
				takeSubStringAtIndexOne = true;
			}
			else if (throwTwo == '-') { throwTwo = null; }
		}
		scoreForCurrentFrame = getScoreOfNextThrows(throwOne, throwTwo, throwThree);
		if(onLastFrame) {return scoreForCurrentFrame;}
		else {
			frame+=1;
			int subStringIndex = takeSubStringAtIndexOne ? 1 : 2;
			return scoreForCurrentFrame+ScoreGame(line.substring(subStringIndex), frame);
		}
	}

	private int getScoreOfNextThrows(Character...chars) {
		int score = 0;
		for(Character ch : chars) {
			if(ch == null) { score+=0; }
			else if(Character.isDigit(ch)) {score += Character.getNumericValue(ch);}
			else { score += scoreMap.get(ch);}
		}
		return score;
	}
}