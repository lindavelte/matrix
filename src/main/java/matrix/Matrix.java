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

		// List<Integer>[][] computedKeys = new ArrayList[matrix.length][matrix[0].length];
		List<Integer> longestPath = new ArrayList<>();
		int[][] visited = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				List<Integer> path = pathForPosition2(matrix, i, j, visited);
				// update result if found path is bigger than existing
				if(path != null && path.size() > longestPath.size()) {
					longestPath = path;
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
	 * @param visited
	 * @return path
	 */
	private List<Integer> pathForPosition2(int[][] matrix, int i, int j, int[][] visited) {

		if(visited[i][j] == 1) {
			return null;
		}

		int numberOfLines = matrix.length;
		int numberOfColumns = matrix[0].length;

		List<Integer> path = new ArrayList<>();

		boolean hasNext = true;

		int nextj = j;
		int nexti = i;
		int lastFoundValue = matrix[i][j];

		while(hasNext) {
			if(nextj + 1 < numberOfColumns && matrix[nexti][nextj + 1] - lastFoundValue == 1) {
				// search right
				lastFoundValue = addToPathAndMarkAsVisited(path, matrix, visited, nexti, nextj + 1);
				nextj++;
			} else if(nexti + 1 < numberOfLines && matrix[nexti + 1][nextj] - lastFoundValue == 1) {
				// search down
				lastFoundValue = addToPathAndMarkAsVisited(path, matrix, visited, nexti + 1, nextj);
				nexti++;
			} else if(nextj - 1 >= 0 && matrix[nexti][nextj - 1] - lastFoundValue == 1) {
				// search left
				lastFoundValue = addToPathAndMarkAsVisited(path, matrix, visited, nexti, nextj - 1);
				nextj--;
			} else if(nexti - 1 >= 0 && matrix[nexti - 1][nextj] - lastFoundValue == 1) {
				// search up
				lastFoundValue = addToPathAndMarkAsVisited(path, matrix, visited, nexti - 1, nextj);
				nexti--;
			} else {
				hasNext = false;
			}
		}
		if(!path.isEmpty()) {
			path.add(0, matrix[i][j]);
			return path;
		}
		return null;
	}

	/**
	 * adds value to path and marks position as visited
	 * 
	 * @param path
	 * @param matrix
	 * @param visited
	 * @param i
	 * @param j
	 * @return found value
	 */
	private int addToPathAndMarkAsVisited(List<Integer> path, int[][] matrix, int[][] visited, int i, int j) {
		path.add(matrix[i][j]);
		visited[i][j] = 1;
		return matrix[i][j];
	}
}
