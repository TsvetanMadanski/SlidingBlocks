package bg.uni_sofia.fmi.corejava.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStarSolution {
	private Board board;

	public AStarSolution(Board b) {
		board = b;
	}

	public static boolean isSolvable(Board b) {

		int[] inversions = new int[b.getStartState().length * b.getStartState().length];
		int k = 0;
		for (int row = 0; row < b.getStartState().length; row++) {
			for (int col = 0; col < b.getStartState()[0].length; col++) {
				if (b.getStartState()[row][col] != 0) {
					inversions[k] = b.getStartState()[row][col];
					k++;
				}
			}
		}
		int countInversions = 0;
		for (int i = 0; i < inversions.length - 1; i++) {
			for (int j = i + 1; j < inversions.length; j++) {
				if (inversions[i] > inversions[j]) {
					countInversions++;
				}
			}
		}

		return countInversions % 2 == 0;

	}

	public int findSolution(List<String> path) {
		Board finalState = new Board(board.getFinalState());
		Queue<Board> states = new PriorityQueue<Board>(new BoardComparator());
		Set<Board> visitedStates = new HashSet<Board>();
		Board currentState = null;
		states.add(board);
		while (!states.isEmpty()) {
			Board previousState = currentState;
			currentState = states.remove();

			if (path.size() == 0 || currentState.getLevel() >= previousState.getLevel())
				path.add(currentState.getDirection());

			else if (currentState.getLevel() < previousState.getLevel()) {
				int level = previousState.getLevel();
				while (level != currentState.getLevel() - 1 && path.size() > 0) {
					path.remove(path.size() - 1);
					level--;
				}
				path.add(currentState.getDirection());
			}

			if (currentState.equals(finalState)) {
				return currentState.getLevel();
			}

			visitedStates.add(currentState);
			for (Board neighbour : getNeighbours(currentState)) {
				if (!visitedStates.contains(neighbour)) {
					states.add(neighbour);
				}
			}

		}
		return -1;
	}

	private static void swap(int[][] arr, int startRowIndex, int startColIndex, int finalRowIndex, int finalColIndex) {

		int temp = arr[startRowIndex][startColIndex];
		arr[startRowIndex][startColIndex] = arr[finalRowIndex][finalColIndex];
		arr[finalRowIndex][finalColIndex] = temp;
	}

	private List<Board> getNeighbours(Board b) {
		int row = b.findGapRow();
		int col = b.findGapCol();
		List<Board> neighbours = new ArrayList<Board>();
		// down
		if (row - 1 >= 0 && row < b.getStartState().length && col >= 0 && col < b.getStartState()[0].length) {
			Board down = new Board(b);
			swap(down.getStartState(), row, col, row - 1, col);
			down.setLevel(down.getLevel() + 1);
			down.setDirection("down");
			neighbours.add(down);
		}
		// up
		if (row >= 0 && row + 1 < b.getStartState().length && col >= 0 && col < b.getStartState()[0].length) {
			Board up = new Board(b);
			swap(up.getStartState(), row, col, row + 1, col);
			up.setLevel(up.getLevel() + 1);
			up.setDirection("up");
			neighbours.add(up);
		}
		// right
		if (row >= 0 && row < b.getStartState().length && col - 1 >= 0 && col < b.getStartState()[0].length) {
			Board right = new Board(b);
			swap(right.getStartState(), row, col, row, col - 1);
			right.setLevel(right.getLevel() + 1);
			right.setDirection("right");
			neighbours.add(right);
		}
		// left
		if (row >= 0 && row < b.getStartState().length && col >= 0 && col + 1 < b.getStartState()[0].length) {
			Board left = new Board(b);
			swap(left.getStartState(), row, col, row, col + 1);
			left.setLevel(left.getLevel() + 1);
			left.setDirection("left");
			neighbours.add(left);
		}

		return neighbours;

	}
}
