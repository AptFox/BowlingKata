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

package main.java.com.audition;
import static main.java.com.audition.Constants.*;
public class BowlingScoreCalculator {
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
		String inputWithRemovedSpaces = input.replaceAll(Constants.SPACE, Constants.EMPTY_STRING);
		int score = ScoreGame(inputWithRemovedSpaces, 1);
		return score;
	}
	
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
		if(frameNum > 10) {
			return 0;
		}
		int scoreForCurrentFrame;
		Character throwOne = StringToCharacterConverter.findThrowFromInput(input, 0);
		Character throwTwo = StringToCharacterConverter.findThrowFromInput(input, 1);
		Character throwThree = StringToCharacterConverter.findThirdThrow(throwOne, throwTwo, input, 2); 
		
		scoreForCurrentFrame = FrameScorer.findFrameScore(throwOne, throwTwo, throwThree);
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


}