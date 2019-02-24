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
 *  We will not check for valid rolls.
 *  We will not check for correct number of rolls and frames.
 *  We will not provide scores for intermediate frames.
 *  
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
	
	/**
	 * Takes an input string representing a game of bowling.
	 * The frames in the string must be separated by spaces.
	 * 'X' for strikes
	 * '/' for spares
	 * '-' for misses
	 * digits for throws that aren't strikes, spares or misses. 
	 * 
	 * @param input  a string representing a game of bowling.
	 * @return       an integer representing the total score for the game.
	 */
	public int ScoreGame(String input) {
		String inputWithRemovedSpaces = input.replaceAll(SPACE, EMPTY_STRING);
		int score = ScoreGame(inputWithRemovedSpaces, 1);
		return score;
	}
	
	//TODO Bring base case to the top of the method for readability.
	/**
	 * Takes an input string representing a game of bowling 
	 * and a starting frame. A score for the supplied frame
	 * is generated and added to the score from recursive calls
	 * to this same method.
	 * 
	 * @param input     a string representing a game of bowling.
	 * @param frameNum  an integer representing your starting frame. 
	 * @return          an integer representing the total score for the game.
	 */
	private int ScoreGame(String input, Integer frameNum) {
		int scoreForCurrentFrame;
		Character throwOne = getThrowFromInput(input, 0);
		Character throwTwo = getThrowFromInput(input, 1);
		Character throwThree = calculateValueOfThirdThrow(throwOne, throwTwo, input, 2); 
		
		scoreForCurrentFrame = calculateFrameScore(throwOne, throwTwo, throwThree);
		if(frameNum == 10) {
			return scoreForCurrentFrame;
		}

		frameNum+=1;
		String subString = determineSubString(throwOne, input);
		return scoreForCurrentFrame + ScoreGame(subString, frameNum);
	}
	
	/**
	 * Takes the character value of the first throw, a string
	 * representing the frames in a game of bowling and returns 
	 * a substring of frames still requiring scoring.
	 * 
	 * @param throwOne  character representing the first throw
	 * @param input     a string representing frames in a game of bowling
	 * @return          a substring of the supplied bowling game string
	 */
	private String determineSubString(Character throwOne, String input) {
		int substringIndex = 2;
		if (throwOne == STRIKE || throwOne == MISS) {
			substringIndex = 1; 
		}
		return input.substring(substringIndex);
	}
	
	/**
	 * Takes the character values for the previous two throws and determines a score for the third throw. 
	 * if throwOne/throwTwo is a strike/spare then the returned value will be '-'.
	 * 
	 * @param throwOne  a character representing the first throw
	 * @param throwTwo  a character representing the second throw
	 * @param input     a string containing the character value you'd like to assign to the third throw
	 * @param index     an int representing the position in the input string you'd like to assign to to the third throw 
	 * @return          the character at the supplied index in the input string
	 */
	private Character calculateValueOfThirdThrow(Character throwOne, Character throwTwo, String input, int index) {
		if (throwOne != STRIKE && throwTwo != SPARE) {
			index = -1;
		}
		return getThrowFromInput(input, index);
	}
	
	/**
	 * Takes a string representing throws in a bowling game and the index of the desired throw.
	 * Returns the character at the specified index in the supplied string. 
	 * 
	 * @param str    a string you'd like to return a bowling throw from. 
	 * @param index  the position of the character you'd like to find in the string 
	 * @return       the character at the specified index in the supplied string. 
	 */
	private Character getThrowFromInput(String str, int index) {
		Character returnChar;
		try {
			returnChar = str.charAt(index);
		}catch(IndexOutOfBoundsException e) {
			returnChar = MISS;
		}
		return returnChar;
	}
	
	/**
	 * Takes all throws associated with a frame in as chars 
	 * and returns the combined score of all supplied throws.
	 * 
	 * @param chars  all throws associated with a frame in as chars
	 * @return       an int representing the combined score of all supplied throws
	 */
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