package main.java.com.audition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	private static final Character STRIKE = 'X';
	private static final Character SPARE = '/';
	private static final Character MISS = '-';
	
    Map<Character, Integer> scoreMap = new HashMap<Character, Integer>(){
    	{
    	put(STRIKE,10);
	    put(SPARE,10);
	    put(MISS,0);
    	}
    };
	
	public int ScoreGame(String line) {
		return ScoreGame(line.replaceAll("\\s", ""), null);
	}
	
	public int ScoreGame(String line, Integer frameNum) {
		frameNum = frameNum == null ? 1 : frameNum;
		boolean onLastFrame = frameNum == 10;
		boolean takeSubStringAtIndexOne = false;
		int scoreForCurrentFrame;
		Character throwOne = line.charAt(0);
		Character throwTwo = line.charAt(1);
		Character throwThree = MISS;
		if (throwOne == STRIKE || throwTwo == SPARE) {
			throwThree = line.charAt(2);
			if(throwOne == STRIKE) { 
				takeSubStringAtIndexOne = true; 
			}
		}
		else {
			if (throwOne == MISS) {
				takeSubStringAtIndexOne = true;
			}
		}
		scoreForCurrentFrame = getScoreOfNextThrows(throwOne, throwTwo, throwThree);
		if(onLastFrame) {
			return scoreForCurrentFrame;
		}

		frameNum+=1;
		int subStringIndex = takeSubStringAtIndexOne ? 1 : 2;
		return scoreForCurrentFrame+ScoreGame(line.substring(subStringIndex), frameNum);
		
	}
	
	private int getScoreOfNextThrows(Character...chars) {
		int score = 0;
		Integer lastThrowsNumber = null;
		for(Character ch : chars) {
			if(Character.isDigit(ch)) {
				lastThrowsNumber = Character.getNumericValue(ch);
			}
			score += convertCharacterToScore(ch, lastThrowsNumber);
		}
		return score;
	}
	
	private int convertCharacterToScore(Character ch, Integer previousThrowScore) {
		int score;
		if(Character.isDigit(ch)) {
			score = Character.getNumericValue(ch);
		}
		else if(previousThrowScore != null && ch == SPARE){
			score = 10 - previousThrowScore;
		}
		else {
			score = scoreMap.get(ch);
		}
		return score;
	}
}