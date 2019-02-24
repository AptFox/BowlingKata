package test.java.com.audition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.java.com.audition.BowlingScoreCalculator;



class BowlingScoreCalculatorTest {
	
	BowlingScoreCalculator bsc = new BowlingScoreCalculator();
	
	@Test
	void testCalculateScore_AllStrikes() {
		String frameScores = "X X X X X X X X X X X X";
		int expectedScore = 300;
		int actualScore = bsc.ScoreGame(frameScores);
		assertEquals(expectedScore, actualScore);
	}
	@Test
	void testCalculateScore_AllNines() {
		String frameScores ="9- 9- 9- 9- 9- 9- 9- 9- 9- 9-";
		int expectedScore = 90;
		int actualScore = bsc.ScoreGame(frameScores);
		assertEquals(expectedScore, actualScore);
	}
	@Test
	void testCalculateScore_FivesAndSpares() {
		String frameScores = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5";
		int expectedScore = 150;
		int actualScore = bsc.ScoreGame(frameScores);
		assertEquals(expectedScore, actualScore);
	}
	
	@Test
	void testCalculateScore_EightsAndOnes() {
		String frameScores = "81 81 81 81 81 81 81 81 81 81";
		int expectedScore = 90;
		int actualScore = bsc.ScoreGame(frameScores);
		assertEquals(expectedScore, actualScore);
	}
	
	@Test
	void testCalculateScore_RandomThrows() {
		String frameScores = "12 34 52 71 9/ 1- 11 12 13 14";
		int expectedScore = 51;
		int actualScore = bsc.ScoreGame(frameScores);
		assertEquals(expectedScore, actualScore);
	}
	
}