package main;

import java.awt.Color;

import gui.GuiBoard;
import pieces.Piece;

public class Game {
	Board oldBoard;
	Board oldestBoard;
	Board board;
	User user;
	int currRow = -1, currCol = -1, destRow = -1, destCol = -1;
	GuiBoard guiBoard;
	private boolean turn = true;
	int whiteScore = 0, blackScore = 0;

	public Game() {
		board = new Board();
		oldBoard = new Board();
		oldestBoard = new Board();
		user = new User();
		user.setGame(this);
		guiBoard = new GuiBoard(user);
	}

	public GuiBoard getGui() {
		return this.guiBoard;
	}

	public User getUser() {
		return this.user;
	}

	public Board getBoard() {
		return this.board;
	}

	public int getCurrRow() {
		return this.currRow;
	}

	public int getCurrCol() {
		return this.currCol;
	}

	public int getDestRow() {
		return this.destRow;
	}

	public int getDestCol() {
		return this.destCol;
	}

	public boolean newMainLoop() {
		if (turn) oldestBoard.copy(board);
		else oldBoard.copy(board);
		
		boolean stat = this.mover(turn);
		if (!stat) return false;
		
		turn = !turn;
		this.niceDisplay();

		int gameStatus = board.gameOverCheck(turn);
		if (gameStatus > 0) {
			if (gameStatus == 2) System.out.println("Stalemate");
			if (gameStatus == 1) System.out.println("Checkmate");
			if (!turn) System.out.println("White player wins");
			else System.out.println("Black player wins");
			return true;
		}
		System.out.println("Enter a move");
		return false;
	}

	/**
	 * gets user input, tries to move, prompts again if invalid move
	 * 
	 * @param turn
	 */
	public boolean mover(boolean turn) {
		boolean stat = this.getNewMove(turn);
		if (!stat)
			return false;
		boolean possible = this.board.allowedMove(this.currRow, this.currCol, this.destRow, this.destCol, turn);
		if (possible) {
			this.board.move(currRow, currCol, destRow, destCol);
			return true;
		} else {
			System.out.println("Such a move isn't allowed");
		}
		return false;
	}

	/**
	 * Gets the move coordinates from User, translates, validates
	 * 
	 * @param turn
	 */
	public boolean getNewMove(boolean turn) {
		this.currRow = user.getCurrRow();
		this.currCol = user.getCurrCol();
		this.destRow = user.getDestRow();
		this.destCol = user.getDestCol();
		if (currRow >= 0 && currRow < 8 && currCol >= 0 && currCol < 8 && destRow >= 0 && destRow < 8 && destCol >= 0
				&& destCol < 8) {
			Tile tile = this.board.getBoard()[currRow][currCol];
			if (!tile.isEmpty()) {
				if (tile.getPiece().getUser() == turn)
					return true;
			} else
				System.out.println("Select your own piece to move");
		}
		System.out.println("Enter valid coordinates");
		return false;
	}

	public void niceDisplay() {
		for (int row = 7; row >= 0; row--) {
			for (int col = 0; col < 8; col++) {
				Piece p = board.getBoard()[row][col].getPiece();
				if (p == null)
					guiBoard.labels[row][col].setText(" ");
				else
					guiBoard.labels[row][col].setText(p.getImage());
			}
		}
		guiBoard.getMenu().setWhiteScore(whiteScore);
		guiBoard.getMenu().setBlackScore(blackScore);
		if (turn)
			this.guiBoard.getMenu().setTurnColor(Color.WHITE);
		else
			this.guiBoard.getMenu().setTurnColor(Color.BLACK);
	}

	public void reset() {
		this.board.normalSetup();
		this.turn = true;
		this.niceDisplay();
	}

	public void forfeit() {
		if (turn)
			blackScore += 1;
		else
			whiteScore += 1;
		reset();
	}

	public void custom() {
		this.board.customSetup();
		this.turn = true;
		this.niceDisplay();
	}

	public void undo() {
		if (turn)
			this.board.copy(oldestBoard);
		else
			this.board.copy(oldBoard);
		this.niceDisplay();
	}

	public static void main(String args[]) {
		Game g = new Game();
		g.getBoard().normalSetup();
		g.getGui().display();
	}
}