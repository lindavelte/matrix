package matrix;

import static org.junit.Assert.assertEquals;

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
		int matrix[][] = { { 5, 8 }, { 6, 1 }, { 4, 3 } };
		MatrixAnalyser analyser = new MatrixAnalyser();
		String result = analyser.longestSequence(matrix);
		assertEquals("5 6", result);
	}
}
