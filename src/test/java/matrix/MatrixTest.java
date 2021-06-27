package matrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testSequence() {
		int matrix[][] = { { 5, 8, 7 }, { 6, 9, 1 }, { 4, 3, 2 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("1 2 3 4", result);
	}

	@Test
	public void testMultipleSequences() {
		int matrix[][] = { { 11, 12, 9, 8, }, { 1, 10, 6, 7 }, { 2, 4, 5, 3 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("4 5 6 7 8 9", result);
	}

	@Test
	public void testReverseSequence() {
		int matrix[][] = { { 12, 11, 10, 9, }, { 5, 6, 7, 8 }, { 4, 3, 2, 1 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", result);
	}

	@Test
	public void testNoSequence() {
		int matrix[][] = { { 7, 5, 8 }, { 3, 1, 4 }, { 6, 9, 2 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("No sequence", result);
	}

	@Test
	public void testUnevenMatrix() {
		int matrix[][] = { { 5, 8 }, { 6, 1 }, { 4, 7 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("5 6", result);
	}

	@Test
	public void testSingleColumnMatrix() {
		int matrix[][] = { { 5 }, { 4 }, { 3 }, { 2 }, { 1 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("1 2 3 4 5", result);
	}

	@Test
	public void testSingleLineMatrix() {
		int matrix[][] = { { 8, 9, 7, 6, 5, 4, 2, 1, 3 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("4 5 6 7", result);
	}

	@Test
	public void testSnakeMatrix() {
		int matrix[][] = { { 13, 14, 10, 9, 8 }, { 15, 16, 11, 12, 7 }, { 17, 18, 4, 5, 6 }, { 1, 2, 3, 19, 20 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", result);
	}

	@Test
	public void testBigSequence() {
		int matrix[][] = new int[1][2000];

		int number = 0;

		for(int j = 0; j < matrix[0].length; j++) {
			for(int i = 0; i < matrix.length; i++) {
				matrix[i][j] = number++;
			}
		}
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertTrue(result.startsWith("0"));
		assertTrue(result.endsWith("1999"));
	}
}
