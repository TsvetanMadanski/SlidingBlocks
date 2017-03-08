package bg.uni_sofia.fmi.corejava.game;

import java.util.Comparator;

public class BoardComparator implements Comparator<Board> {

	public int compare(Board o1, Board o2) {
		if ((o1.getHeuristic() + o1.getLevel()) < (o2.getHeuristic() + o2.getLevel()))
			return -1;

		if ((o1.getHeuristic() + o1.getLevel()) > (o2.getHeuristic() + o2.getLevel()))
			return 1;

		return 0;
	}

}
