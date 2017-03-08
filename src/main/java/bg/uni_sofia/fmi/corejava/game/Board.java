package bg.uni_sofia.fmi.corejava.game;

public class Board {
	private int[][] startState;
	private int[][] finalState;
	private int level;
	private String direction;

	public Board(int[][] board) {
		this.startState = board;
		this.level = 0;
		this.direction = "";
	}

	public Board(int[][] board, int level) {

		this.startState = board;
		this.level = level;
		this.direction = "";
		finalState = new int[board.length][board[0].length];

		int value = 1;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				finalState[row][col] = value;
				value++;
			}
		}
		finalState[board.length - 1][board[0].length - 1] = 0;
	}

	public Board(Board b) {
		startState = new int[b.startState.length][b.startState[0].length];
		for (int row = 0; row < b.startState.length; row++) {
			for (int col = 0; col < startState[0].length; col++) {
				startState[row][col] = b.startState[row][col];
			}
		}
		this.level = b.level;
		this.direction = "";
	}

	public int getHeuristic() {
		int heuristic = 0;
		for (int row = 0; row < startState.length; row++) {
			for (int col = 0; col < startState[0].length; col++) {
				int value = startState[row][col];

				if (value == 0)
					value = startState.length * startState[0].length;

				int sum = Math.abs((value - 1) / startState[0].length - row)
						+ Math.abs((value - 1) % startState[0].length - col);
				heuristic += sum;
			}
		}
		return heuristic;
	}

	public int findGapRow() {
		for (int row = 0; row < startState.length; row++) {
			for (int col = 0; col < startState[0].length; col++) {
				if (startState[row][col] == 0)
					return row;
			}
		}
		return -1;
	}

	public int findGapCol() {
		for (int row = 0; row < startState.length; row++) {
			for (int col = 0; col < startState[0].length; col++) {
				if (startState[row][col] == 0)
					return col;
			}
		}
		return -1;
	}

	public int[][] getStartState() {
		return startState;
	}

	public void setStartState(int[][] startState) {
		this.startState = startState;
	}

	public int[][] getFinalState() {
		return finalState;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		if (obj instanceof Board) {
			Board o = (Board) obj;
			for (int row = 0; row < startState.length; row++)
				for (int col = 0; col < startState[0].length; col++)
					if (this.startState[row][col] != o.startState[row][col])
						return false;

			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] board = { { 7, 5, 3 }, { 2, 1, 4 }, { 0, 6, 8 } };
		Board b = new Board(board, 0);
		System.out.println(b.getHeuristic());
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(b.getFinalState()[i][j] + " ");
			}
			System.out.println();
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	

}
