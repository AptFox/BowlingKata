package main.java.com.audition;

import static main.java.com.audition.Constants.*;
import java.util.HashMap;
import java.util.Map;

public class FrameScorer {
	
    private static final Map<Character, Integer> scoreMap = new HashMap<Character, Integer>(){
    	{
    	put(Constants.STRIKE,10);
	    put(Constants.SPARE,10);
	    put(Constants.MISS,0);
    	}
    };
	
	/**
	 * Takes all throws associated with a frame in as chars 
	 * and returns the combined score of all supplied throws.
	 * 
	 * @param chars  all throws associated with a frame in as chars
	 * @return       an int representing the combined score of all supplied throws
	 */
	public static int findFrameScore(Character...chars) {
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
	
	/**
	 * Takes in a character representing a throw in a bowling game
	 * and the value of the previous throw. if the current throw is
	 * a spare then the score for the previous throw (when supplied) 
	 * determines the score for the current throw.
	 * 
	 * @param ch                  a character to convert to a score
	 * @param previousThrowScore  an integer representing the previous score
	 * @return                    an int representing the score for the throw. 
	 */
	private static int convertCharacterToScore(Character ch, Integer previousThrowScore) {
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
