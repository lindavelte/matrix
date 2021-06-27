package matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * search longest sequence in matrix
 *
 * @author linda.velte
 *
 */
public class Matrix {

	public static void main(String[] args) {

		if(args.length != 2) {
			System.out.println("Specify number of columns and lines");
			System.exit(0);
		}

		int numberOfColumns = Integer.parseInt(args[0]);
		int numberOfLines = Integer.parseInt(args[1]);

		int[][] matrix = new int[numberOfLines][numberOfColumns];
		System.out.printf("Enter %d lines with %d numbers delimeted by spaces\n", numberOfLines, numberOfColumns);
		Scanner reader = new Scanner(System.in);
		for(int i = 0; i < numberOfLines; i++) {
			for(int j = 0; j < numberOfColumns; j++) {
				matrix[i][j] = reader.nextInt();
			}
		}
		reader.close();

		MatrixAnalyser analyser = new MatrixAnalyser();
		String longestPath = analyser.longestSequence(matrix);
		System.out.println(longestPath);

	}
}

class MatrixAnalyser {

	/**
	 * longest sequence
	 *
	 * @param matrix
	 * @return longest sequence
	 */
	public String longestSequence(int[][] matrix) {

		List<Integer>[][] computedKeys = new ArrayList[matrix.length][matrix[0].length];
		List<Integer> longestPath = new ArrayList<>();
		int longestSize = 0;

		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				List<Integer> path = pathForPosition(matrix, i, j, computedKeys, longestSize);
				// update result if found path is bigger than existing
				if(path != null && path.size() > longestPath.size()) {
					longestPath = path;
					longestSize = path.size();
				}
			}
		}
		return longestPath != null && longestPath.size() > 1 ? longestPath.stream().map(Object::toString).collect(Collectors.joining(" ")) : "No sequence";
	}

	/**
	 * computes path for position
	 *
	 * @param matrix
	 * @param i
	 * @param j
	 * @param computedKeys
	 * @return path
	 */
	private List<Integer> pathForPosition(int[][] matrix, int i, int j, List<Integer>[][] computedKeys, int pathSize) {

		if(computedKeys[i][j] != null) {
			return computedKeys[i][j];
		}

		int numberOfLines = matrix.length;
		int numberOfColumns = matrix[0].length;

		List<Integer> path = new ArrayList<>();

		// search path recursively, if bigger path is found free memory
		if(j + 1 < numberOfColumns && matrix[i][j + 1] - matrix[i][j] == 1) {
			// search right
			path = pathForPosition(matrix, i, j + 1, computedKeys, pathSize);
			computedKeys[i][j + 1] = null;
		} else if(i + 1 < numberOfLines && matrix[i + 1][j] - matrix[i][j] == 1) {
			// search down
			path = pathForPosition(matrix, i + 1, j, computedKeys, pathSize);
			computedKeys[i + 1][j] = null;
		} else if(j > 0 && matrix[i][j - 1] - matrix[i][j] == 1) {
			// search left
			path = pathForPosition(matrix, i, j - 1, computedKeys, pathSize);
			computedKeys[i][j - 1] = null;
		} else if(i > 0 && matrix[i - 1][j] - matrix[i][j] == 1) {
			// search up
			path = pathForPosition(matrix, i - 1, j, computedKeys, pathSize);
			computedKeys[i - 1][j] = null;
		}

		path.add(0, matrix[i][j]);

		if(path != null && path.size() >= pathSize) {
			// mark as done and store path for computed position
			computedKeys[i][j] = new ArrayList<>(path);
		}
		return path;
	}
}