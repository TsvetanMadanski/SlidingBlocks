package bg.uni_sofia.fmi.corejava.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of blocks:");
		int numOfBlocks = input.nextInt();
		int[][] startState = new int[(int) Math.sqrt(numOfBlocks + 1)][(int) Math.sqrt(numOfBlocks + 1)];
		for (int row = 0; row < startState.length; row++) {
			for (int col = 0; col < startState.length; col++) {
				System.out.print("Enter a value for block[" + row + "]" + "[" + col + "]:");
				startState[row][col] = input.nextInt();
			}
		}
		Board board = new Board(startState, 0);
		if (AStarSolution.isSolvable(board)) {
			AStarSolution solver = new AStarSolution(board);
			List<String> path = new ArrayList<String>();
			System.out.println(solver.findSolution(path));
			for (String string : path) {
				System.out.println(string);
			}
		} else {
			System.out.println("The task has no solution");
		}



	}
}
