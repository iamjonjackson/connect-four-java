package algorithm;

import java.util.Vector;

public class MinMaxPlayer extends Player {
	private State currentState;
	private int ply = 4;

	public MinMaxPlayer() {
		super("images/blue");
		currentState = new State(false);
	}

	private int minimaxDecision(State s) {
		int v = 0;
		int alpha = -50000;
		int beta = 50000;
		if (s.nextPlayer == 1)
			v = maxValue(s, alpha, beta);
		else
			v = minValue(s, alpha, beta);

		for (int i = 0; i < s.successors.size(); i++) {
			State cell = s.successors.get(i);
			if (cell.value == v)
				return cell.action;
		}

		return (int) (Math.random() * 7);
	}

	private int maxValue(State state, int alpha, int beta) {
		if (isPlyEnd(state)) {
			int u = utility(state);
			return u;
		}

		Vector<State> successors = state.successors;
		for (int i = 0; i < successors.size(); i++) {
			State cell = (State) successors.get(i);
			alpha = Math.max(alpha, minValue(cell, alpha, beta));

			if (beta <= alpha) {
				break;
			}
		}

		state.value = alpha;
		return state.value;
	}

	private int minValue(State state, int alpha, int beta) {
		if (isPlyEnd(state)) {
			int u = utility(state);
			return u;
		}

		Vector<State> successors = state.successors;
		for (int i = 0; i < successors.size(); i++) {
			State cell = successors.get(i);
			beta = Math.min(beta, maxValue(cell, alpha, beta));

			if (beta <= alpha) {
				break;
			}
		}

		state.value = beta;
		return state.value;
	}

	public boolean isPlyEnd(State state) {
		if ((state.depth == ply) || (state.gameOver()))
			return true;
		return false;
	}

	public int utility(State state) {
		int me = currentState.nextPlayer;
		int oppo = 3 - me;

		if (state.gameOver()) {
			if (state.winner == me) {
				state.value = 10000;

				return state.value;
			}

			if (state.winner == oppo) {
				state.value = -10000;

				return state.value;
			}

			if (state.winner == 0) {
				state.value = 0;

				return 0;
			}
		}

		int h = 0;
		int row = state.moveX;
		int col = state.moveY;

		if ((col >= 3) && (state.locations[row][col - 1] == me)
				&& (state.locations[row][col - 2] == me)
				&& (state.locations[row][col - 3] == me))
			h = h + 16;

		// right
		if ((col <= 3) && (state.locations[row][col + 1] == me)
				&& (state.locations[row][col + 2] == me)
				&& (state.locations[row][col + 3] == me))
			h = h + 16;

		// check y direction
		if ((row <= 2) && (state.locations[row + 1][col] == me)
				&& (state.locations[row + 2][col] == me)
				&& (state.locations[row + 3][col] == me))
			h = h + 16;

		// check left diagonal
		if ((col >= 3) && (row <= 2)
				&& (state.locations[row + 1][col - 1] == me)
				&& (state.locations[row + 2][col - 2] == me)
				&& (state.locations[row + 3][col - 3] == me))
			h = h + 16;

		if ((col <= 3) && (row <= 2)
				&& (state.locations[row + 1][col + 1] == me)
				&& (state.locations[row + 2][col + 2] == me)
				&& (state.locations[row + 3][col + 3] == me))
			h = h + 16;

		if ((col >= 3) && (row >= 3)
				&& (state.locations[row - 1][col - 1] == me)
				&& (state.locations[row - 2][col - 2] == me)
				&& (state.locations[row - 3][col - 3] == me))
			h = h + 16;

		if ((col <= 3) && (row >= 3)
				&& (state.locations[row - 1][col + 1] == me)
				&& (state.locations[row - 2][col + 2] == me)
				&& (state.locations[row - 3][col + 3] == me))
			h = h + 16;

		if ((col >= 2) && (state.locations[row][col - 1] == me)
				&& (state.locations[row][col - 2] == me))
			h = h + 4;

		// right
		if ((col <= 4) && (state.locations[row][col + 1] == me)
				&& (state.locations[row][col + 2] == me))
			h = h + 4;
		// check y direction
		if ((row <= 3) && (state.locations[row + 1][col] == me)
				&& (state.locations[row + 2][col] == me))
			h = h + 4;
		// check left diagonal
		if ((col >= 2) && (row <= 3)
				&& (state.locations[row + 1][col - 1] == me)
				&& (state.locations[row + 2][col - 2] == me))
			h = h + 4;

		if ((col <= 4) && (row <= 3)
				&& (state.locations[row + 1][col + 1] == me)
				&& (state.locations[row + 2][col + 2] == me))
			h = h + 4;

		if ((col >= 2) && (row >= 2)
				&& (state.locations[row - 1][col - 1] == me)
				&& (state.locations[row - 2][col - 2] == me))
			h = h + 4;

		if ((col <= 4) && (row >= 2)
				&& (state.locations[row - 1][col + 1] == me)
				&& (state.locations[row - 2][col + 2] == me))
			h = h + 4;

		if ((col >= 1) && (state.locations[row][col - 1] == me))
			h = h + 2;
		// right

		if ((col <= 5) && (state.locations[row][col + 1] == me))
			h = h + 2;
		// check y direction
		if ((row <= 4) && (state.locations[row + 1][col] == me))
			h = h + 2;
		// check left diagonal
		if ((col >= 1) && (row <= 4)
				&& (state.locations[row + 1][col - 1] == me))
			h = h + 2;

		if ((col <= 5) && (row <= 4)
				&& (state.locations[row + 1][col + 1] == me))
			h = h + 2;

		if ((col >= 1) && (row >= 1)
				&& (state.locations[row - 1][col - 1] == me))
			h = h + 2;

		if ((col <= 5) && (row >= 1)
				&& (state.locations[row - 1][col + 1] == me))
			h = h + 2;

		// check x direction.
		// left
		if ((col >= 3) && (state.locations[row][col - 1] == oppo)
				&& (state.locations[row][col - 2] == oppo)
				&& (state.locations[row][col - 3] == oppo))
			h = h - 8;
		// right
		if ((col <= 3) && (state.locations[row][col + 1] == oppo)
				&& (state.locations[row][col + 2] == oppo)
				&& (state.locations[row][col + 3] == oppo))
			h = h - 8;
		// check y direction
		if ((row <= 2) && (state.locations[row + 1][col] == oppo)
				&& (state.locations[row + 2][col] == oppo)
				&& (state.locations[row + 3][col] == oppo))
			h = h - 8;
		// check left diagonal
		if ((col >= 3) && (row <= 2)
				&& (state.locations[row + 1][col - 1] == oppo)
				&& (state.locations[row + 2][col - 2] == oppo)
				&& (state.locations[row + 3][col - 3] == oppo))
			h = h - 8;

		if ((col <= 3) && (row <= 2)
				&& (state.locations[row + 1][col + 1] == oppo)
				&& (state.locations[row + 2][col + 2] == oppo)
				&& (state.locations[row + 3][col + 3] == oppo))
			h = h - 8;

		if ((col >= 3) && (row >= 3)
				&& (state.locations[row - 1][col - 1] == oppo)
				&& (state.locations[row - 2][col - 2] == oppo)
				&& (state.locations[row - 3][col - 3] == oppo))
			h = h - 8;

		if ((col <= 3) && (row >= 3)
				&& (state.locations[row - 1][col + 1] == oppo)
				&& (state.locations[row - 2][col + 2] == oppo)
				&& (state.locations[row - 3][col + 3] == oppo))
			h = h - 8;

		if ((col >= 2) && (state.locations[row][col - 1] == oppo)
				&& (state.locations[row][col - 2] == oppo))
			h = h - 4;
		// right
		if ((col <= 4) && (state.locations[row][col + 1] == oppo)
				&& (state.locations[row][col + 2] == oppo))
			h = h - 4;
		// check y direction
		if ((row <= 3) && (state.locations[row + 1][col] == oppo)
				&& (state.locations[row + 2][col] == oppo))
			h = h - 4;
		// check left diagonal
		if ((col >= 2) && (row <= 3)
				&& (state.locations[row + 1][col - 1] == oppo)
				&& (state.locations[row + 2][col - 2] == oppo))
			h = h - 4;

		if ((col <= 4) && (row <= 3)
				&& (state.locations[row + 1][col + 1] == oppo)
				&& (state.locations[row + 2][col + 2] == oppo))
			h = h - 4;

		if ((col >= 2) && (row >= 2)
				&& (state.locations[row - 1][col - 1] == oppo)
				&& (state.locations[row - 2][col - 2] == oppo))
			h = h - 4;

		if ((col <= 4) && (row >= 2)
				&& (state.locations[row - 1][col + 1] == oppo)
				&& (state.locations[row - 2][col + 2] == oppo))
			h = h - 4;

		if ((col >= 1) && (state.locations[row][col - 1] == oppo))
			h = h - 2;
		// right

		if ((col <= 5) && (state.locations[row][col + 1] == oppo))
			h = h - 2;
		// check y direction
		if ((row <= 4) && (state.locations[row + 1][col] == oppo))
			h = h - 2;
		// check left diagonal
		if ((col >= 1) && (row <= 4)
				&& (state.locations[row + 1][col - 1] == oppo))
			h = h - 2;

		if ((col <= 5) && (row <= 4)
				&& (state.locations[row + 1][col + 1] == oppo))
			h = h - 2;

		if ((col >= 1) && (row >= 1)
				&& (state.locations[row - 1][col - 1] == oppo))
			h = h - 2;

		if ((col <= 5) && (row >= 1)
				&& (state.locations[row - 1][col + 1] == oppo))
			h = h - 2;

		state.value = h;
		return h;
	}

	public int getType() {
		return 4;
	}

	public int go(State state) {
		State currentState = new State(false);

		currentState.parseMove(state.movelist);

		createSuccessors(currentState);
		int m = minimaxDecision(currentState);

		state.move(m);

		return m;
	}

	public void setMove(int column) {
	}

	private void createSuccessors(State state) {

		Vector<State> successors = new Vector<State>();
		for (int i = 0; i < 7; i++) {
			State temp = new State(false);
			temp.parseMove(state.movelist);
			if (temp.cols[i] != 6) {
				temp.move(i);
				temp.depth = state.depth + 1;
				temp.action = i;
				temp.out = false;
				successors.add(temp);
				if (temp.depth < ply)
					createSuccessors(temp);
			}
		}
		state.successors = successors;
	}

}
