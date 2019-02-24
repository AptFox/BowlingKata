package main.java.com.audition;
import static main.java.com.audition.Constants.*;
public class StringToCharacterConverter {
	/**
	 * Takes a string representing throws in a bowling game and the index of the desired throw.
	 * Returns the character at the specified index in the supplied string. 
	 * 
	 * @param str    a string you'd like to return a bowling throw from. 
	 * @param index  the position of the character you'd like to find in the string 
	 * @return       the character at the specified index in the supplied string. 
	 */
	public static Character findThrowFromInput(String str, int index) {
		Character returnChar;
		try {
			returnChar = str.charAt(index);
		}catch(IndexOutOfBoundsException e) {
			returnChar = MISS;
		}
		return returnChar;
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
	public static Character findThirdThrow(Character throwOne, Character throwTwo, String input, int index) {
		if (throwOne != STRIKE && throwTwo != SPARE) {
			index = -1;
		}
		return findThrowFromInput(input, index);
	}
}
