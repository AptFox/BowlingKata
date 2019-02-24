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
	
	private static final Character STRIKE = 'X';
	private static final Character SPARE = '/';
	private static final Character MISS = '-';
	private static final String SPACE = "\\s";
	private static final String EMPTY_STRING = "";
	
    Map<Character, Integer> scoreMap = new HashMap<Character, Integer>(){
    	{
    	put(STRIKE,10);
	    put(SPARE,10);
	    put(MISS,0);
    	}
    };
	
	public int ScoreGame(String input) {
		String inputWithRemovedSpaces = input.replaceAll(SPACE, EMPTY_STRING);
		int score = ScoreGame(inputWithRemovedSpaces, null);
		return score;
	}
	
	//TODO Bring base case to the top of the method for readability.
	public int ScoreGame(String input, Integer frameNum) {
		frameNum = frameNum == null ? 1 : frameNum;
		int scoreForCurrentFrame;
		Character throwOne = getThrowFromInput(input, 0);
		Character throwTwo = getThrowFromInput(input, 1);
		Character throwThree = calculateValueOfThirdThrow(throwOne, throwTwo, input, 2); 
		boolean takeSubStringAtIndexOne = false;
		
		if (throwOne == STRIKE || throwTwo == SPARE) {
			if(throwOne == STRIKE) { 
				takeSubStringAtIndexOne = true; 
			}
		}
		else {
			if (throwOne == MISS) {
				takeSubStringAtIndexOne = true;
			}
		}
		scoreForCurrentFrame = calculateFrameScore(throwOne, throwTwo, throwThree);
		if(frameNum == 10) {
			return scoreForCurrentFrame;
		}

		frameNum+=1;
		int subStringIndex = takeSubStringAtIndexOne ? 1 : 2;
		return scoreForCurrentFrame+ScoreGame(input.substring(subStringIndex), frameNum);
	}
	
	private Character calculateValueOfThirdThrow(Character throwOne, Character throwTwo, String input, int index) {
		//throwThree only needs a value if throwOne/throwTwo is a strike/spare
		Character value;
		if (throwOne == STRIKE || throwTwo == SPARE) {
			value = getThrowFromInput(input, 2);
		}else {
			value = MISS;
		}
		return value;
	}
	
	private Character getThrowFromInput(String str, int index) {
		Character returnChar;
		try {
			returnChar = str.charAt(index);
		}catch(IndexOutOfBoundsException e) {
			returnChar = MISS;
		}
		return returnChar;
	}
	
	private int calculateFrameScore(Character...chars) {
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
		int score = 10;
		if(Character.isDigit(ch)) {
			score = Character.getNumericValue(ch);
		}
		else if(previousThrowScore != null && ch == SPARE){
			score -= previousThrowScore;
		}
		else {
			score = scoreMap.get(ch);
		}
		return score;
	}
}