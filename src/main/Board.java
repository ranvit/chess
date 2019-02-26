package main;

import java.util.HashSet;
import java.util.Set;

import pieces.Bishop;
import pieces.Corner;
import pieces.King;
import pieces.Knight;
import pieces.Middle;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * @author Runwith
 *
 *         This Board class implements piece movement logic and game status
 */
public class Board {

	Tile[][] board;

	// References to all the pieces, on and off the board
	Set<Piece> whiteAlive, blackAlive, whiteDead, blackDead;

	Piece whiteKing; // A permanent reference to the Kings to
	Piece blackKing; // make looking for Checks easier

	/** 
	 * Constructor, initializes a board without any pieces
	 */
	public Board() {
		boolean white = true; // Determines the color of the tiles
		board = new Tile[8][8];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col] = new Tile(white, null, row, col); // initializing
				white = !white; // switching between black and white
			}
		}

		whiteAlive = new HashSet<Piece>();
		blackAlive = new HashSet<Piece>();
		whiteDead = new HashSet<Piece>();
		blackDead = new HashSet<Piece>();
	}

	public Tile[][] getBoard() {
		return board;
	}

	public Set<Piece> getWhiteAlive() {
		return this.whiteAlive;
	}

	public Set<Piece> getBlackAlive() {
		return this.blackAlive;
	}

	public Set<Piece> getWhiteDead() {
		return this.whiteDead;
	}

	public Set<Piece> getBlackDead() {
		return this.blackDead;
	}

	/**
	 * 
	 * @param p
	 *            Reference to the piece being added
	 * @param row
	 * @param col
	 */
	public void addPiece(Piece p, int row, int col) {
		this.board[row][col].setPiece(p);

		// if white piece
		if (p.getUser()) {
			this.whiteAlive.add(p);
			if (p instanceof King)
				this.whiteKing = p;
		} else {
			this.blackAlive.add(p);
			if (p instanceof King)
				this.blackKing = p;
		}
	}

	/**
	 * Custom Board setup
	 */
	public void customSetup() {
		normalSetup();
		board[4][3].setPiece(new Middle(true));
		board[4][4].setPiece(new Corner(true));
		board[3][3].setPiece(new Corner(false));
		board[3][4].setPiece(new Middle(false));

		whiteAlive.add(this.board[4][3].getPiece());
		blackAlive.add(this.board[3][3].getPiece());
		whiteAlive.add(this.board[4][4].getPiece());
		blackAlive.add(this.board[3][4].getPiece());
	}

	/**
	 * Sets up the Board for a conventional game of chess
	 */
	public void normalSetup() {
		// Adding Pawns
		for (int col = 0; col < 8; col++) {
			board[1][col].setPiece(new Pawn(true));
			board[6][col].setPiece(new Pawn(false));
			whiteAlive.add(this.board[1][col].getPiece());
			blackAlive.add(this.board[6][col].getPiece());
		}

		// Rooks
		board[0][0].setPiece(new Rook(true));
		board[0][7].setPiece(new Rook(true));
		board[7][0].setPiece(new Rook(false));
		board[7][7].setPiece(new Rook(false));

		// Knights
		board[0][1].setPiece(new Knight(true));
		board[0][6].setPiece(new Knight(true));
		board[7][1].setPiece(new Knight(false));
		board[7][6].setPiece(new Knight(false));

		// Bishops
		board[0][2].setPiece(new Bishop(true));
		board[0][5].setPiece(new Bishop(true));
		board[7][2].setPiece(new Bishop(false));
		board[7][5].setPiece(new Bishop(false));

		// Royalty
		board[0][3].setPiece(new Queen(true));
		board[0][4].setPiece(new King(true));
		board[7][3].setPiece(new Queen(false));
		board[7][4].setPiece(new King(false));

		// Already added Pawns to the sets,
		// this loop adds the rest of the pieces.
		for (int i = 0; i < 2; i++) {
			for (int col = 0; col < 8; col++) {
				whiteAlive.add(this.board[0 + i][col].getPiece());
				blackAlive.add(this.board[7 - i][col].getPiece());
			}
		}
		this.whiteKing = board[0][4].getPiece();
		this.blackKing = board[7][4].getPiece();

		for (int i = 2; i < 6; i++) {
			for (int col = 0; col < 8; col++) {
				board[i][col].setPiece(null);
			}
		}
	}

	/**
	 * Prints the current Board configuration Upper case letters are White pieces.
	 * Lower case letters are Black pieces. Periods are empty spaces.
	 */
	public void printBoard() {
		for (int row = 7; row >= 0; row--) { // so that white is on the bottom
			for (int col = 0; col < 8; col++) {
				Piece p = board[row][col].getPiece();
				if (p != null)
					System.out.print(p.getName());
				else
					System.out.print('.');
			}
			System.out.println();
		}
	}

	/**
	 * For a given player, assesses if their King is in Check.
	 * 
	 * @param whiteUser
	 *            - Denotes the player (true == White)
	 * @return - true if the player's King is in Check.
	 */
	public boolean inCheck(boolean whiteUser) {
		Piece king;
		if (whiteUser)
			king = this.whiteKing;
		else
			king = this.blackKing;
		int row = king.getRow();
		int col = king.getCol();
		return this.lookAroundKing(row, col, whiteUser);
	}

	/**
	 * Goes through every cell, checks if the piece in each cell belongs to
	 * opponent, checks if that piece has a valid move to the King, checks if that
	 * piece has a clear path to the King.
	 * 
	 * @param destRow
	 *            - RowID of King
	 * @param destCol
	 *            - ColID of King
	 * @param user
	 *            - Player ID as boolean
	 * @return - true if ANY piece is found Checking King. false if King is not in
	 *         Check.
	 */
	public boolean lookAroundKing(int destRow, int destCol, boolean user) {
		Piece currPiece;
		for (int currRow = 0; currRow < 8; currRow++) {
			for (int currCol = 0; currCol < 8; currCol++) {
				currPiece = this.board[currRow][currCol].getPiece();
				if (currPiece != null) {
					if (currPiece.getUser() != user && currPiece.validMove(currRow, currCol, destRow, destCol, true)) {
						boolean emptyPath = emptyPathCheck(currRow, currCol, destRow, destCol);
						if (emptyPath)
							return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Moves Current piece from Current Tile to Destination Tile If Destination is
	 * occupied, moves Destination Piece to the Dead set. Sets Current Tile to
	 * Empty, Destination Tile to Current Piece.
	 * 
	 * @param currRow
	 * @param currCol
	 * @param destRow
	 * @param destCol
	 * @return Destination Piece (Useful for un-moving)
	 */
	public Piece move(int currRow, int currCol, int destRow, int destCol) {
		Tile destTile = this.board[destRow][destCol];
		Tile currTile = this.board[currRow][currCol];
		Piece out = destTile.getPiece();
		destTile.setPiece(currTile.getPiece());
		currTile.setPiece(null);
		if (out != null) {
			if (out.getUser()) {
				this.whiteAlive.remove(out);
				this.whiteDead.add(out);
			} else {
				this.blackAlive.remove(out);
				this.blackDead.add(out);
			}
		}
		return out;
	}

	/**
	 * Moves Current Piece from Current Tile to Destination Tile, Then checks if
	 * User's King is still in Check. If true, the move is undone.
	 * 
	 * @param currRow
	 * @param currCol
	 * @param destRow
	 * @param destCol
	 * @param user
	 * @return - true if user's king is in Check after the move
	 */
	public boolean moveCheckUnmove(int currRow, int currCol, int destRow, int destCol, boolean user) {
		Piece out = move(currRow, currCol, destRow, destCol);
		boolean checkStatus = this.inCheck(user);
		move(destRow, destCol, currRow, currCol);
		this.board[destRow][destCol].setPiece(out);
		if (out == null)
			return checkStatus;
		if (out.getUser()) {
			this.whiteAlive.add(out);
			this.whiteDead.remove(out);
		} else {
			this.blackAlive.add(out);
			this.blackDead.remove(out);
		}
		return checkStatus;
	}

	/**
	 * Checks if path from Current Tile to Destination Tile is Empty, When both the
	 * tiles are in the same column or row (straight path)
	 * 
	 * @param currRow
	 * @param currCol
	 * @param destRow
	 * @param destCol
	 * @return - true if empty path exists
	 */
	public boolean straightEmpty(int currRow, int currCol, int destRow, int destCol) {
		int low = 0, high = 7;
		if (currRow == destRow) {
			low = Math.min(currCol, destCol);
			high = Math.max(currCol, destCol);
			for (int i = low + 1; i < high; i++) {
				if (!this.board[currRow][i].isEmpty())
					return false;
			}
		} else if (currCol == destCol) {
			low = Math.min(currRow, destRow);
			high = Math.max(currRow, destRow);
			for (int i = low + 1; i < high; i++) {
				if (!this.board[i][currCol].isEmpty())
					return false;
			}
		}
		return true;
	}

	/**
	 * Checks if path from Current Tile to Destination Tile is Empty, When both the
	 * tiles are in a diagonal path
	 * 
	 * @param currRow
	 * @param currCol
	 * @param destRow
	 * @param destCol
	 * @return - true if empty path exists
	 */
	public boolean diagEmpty(int currRow, int currCol, int destRow, int destCol) {
		int rowSign = 1, colSign = 1;
		if (destRow < currRow)
			rowSign = -1;
		if (destCol < currCol)
			colSign = -1;
		for (int i = 1; currCol + i * colSign != destCol; i++) {
			if (!this.board[currRow + i * rowSign][currCol + i * colSign].isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * Determines the shape of path
	 * 
	 * @param currRow
	 * @param currCol
	 * @param destRow
	 * @param destCol
	 * @return 0 - Straight, 1 - Diagonal, 2 - Leaping
	 */
	public int pathShape(int currRow, int currCol, int destRow, int destCol) {
		Piece p = this.board[currRow][currCol].getPiece();
		if (p instanceof Middle || p instanceof Corner)
			return 2;
		if (destRow == currRow || destCol == currCol)
			return 0;
		int rowDiff = Math.abs(destRow - currRow);
		int colDiff = Math.abs(destCol - currCol);
		if (rowDiff == colDiff)
			return 1;
		if (rowDiff * colDiff == 2)
			return 2;
		return -1;
	}

	/**
	 * Goes through all the Tiles on the Board, Goes through all the Pieces alive,
	 * Checks if any piece can move to the current tile Checks if this move keeps
	 * king safe. If no - then no move saves king, so the user loses If yes - then
	 * there is a possible move and the game needs to continue
	 * 
	 * @param user
	 * @return 0 - game continues, 1 - Checkmate(game over), 2 - stalemate(game
	 *         over)
	 */
	public int gameOverCheck(boolean user) {
		Set<Piece> alive = this.whiteAlive;
		if (!user)
			alive = this.blackAlive;
		for (int destRow = 0; destRow < 8; destRow++) {
			for (int destCol = 0; destCol < 8; destCol++) {
				Piece destPiece = this.board[destRow][destCol].getPiece();
				boolean kill = false;
				if (destPiece != null) {
					if (destPiece.getUser() != user)
						kill = true;
					else
						continue;
				}
				for (Piece p : alive) {
					if (p.equals(destPiece))
						continue;
					int currRow = p.getRow();
					int currCol = p.getCol();
					if (p.validMove(currRow, currCol, destRow, destCol, kill)) {
						boolean emptyPath = emptyPathCheck(currRow, currCol, destRow, destCol);
						if (emptyPath) {
							if (!this.moveCheckUnmove(currRow, currCol, destRow, destCol, user))
								return 0;
						}
					}
				}
			}
		}
		if (!this.inCheck(user))
			return 2;
		return 1;
	}

	/**
	 * Checks if this move results in the user's king not being in Check if so, it
	 * is a valid move. else, the user has to try another move
	 * 
	 * @param currRow
	 * @param currCol
	 * @param destRow
	 * @param destCol
	 * @param user
	 * @return true if move is allowed
	 */
	public boolean allowedMove(int currRow, int currCol, int destRow, int destCol, boolean user) {
		Piece p = this.board[currRow][currCol].getPiece();
		Piece destPiece = this.board[destRow][destCol].getPiece();
		if (p == null || p.equals(destPiece))
			return false;
		boolean kill = false;
		if (destPiece != null) {
			if (destPiece.getUser() != user)
				kill = true;
			else
				return false;
		}
		if (p.validMove(currRow, currCol, destRow, destCol, kill)) {
			boolean emptyPath = emptyPathCheck(currRow, currCol, destRow, destCol);
			if (emptyPath) {
				if (!this.moveCheckUnmove(currRow, currCol, destRow, destCol, user))
					return true;
			}
		}
		return false;
	}

	/**
	 * Refactored function, checks if the path from currTile to destTile is Empty by
	 * determining the shape of the path and then calling the appropriate function
	 * 
	 * @param currRow
	 * @param currCol
	 * @param destRow
	 * @param destCol
	 * @return true if the path is empty
	 */
	private boolean emptyPathCheck(int currRow, int currCol, int destRow, int destCol) {
		int pathType = this.pathShape(currRow, currCol, destRow, destCol);
		boolean emptyPath = false;
		if (pathType == 0)
			emptyPath = this.straightEmpty(currRow, currCol, destRow, destCol);
		else if (pathType == 1)
			emptyPath = this.diagEmpty(currRow, currCol, destRow, destCol);
		else if (pathType == 2)
			emptyPath = true;
		return emptyPath;
	}

	/**
	 * Makes a deep copy of the Board to keep track of past board state, for undo
	 * function.
	 * 
	 * @param b
	 */
	public void copy(Board b) {
		this.blackAlive.clear();
		this.whiteAlive.clear();
		this.blackDead.clear();
		this.whiteDead.clear();
		this.whiteAlive.addAll(b.getWhiteAlive());
		this.blackAlive.addAll(b.getBlackAlive());
		this.whiteDead.addAll(b.getWhiteDead());
		this.blackDead.addAll(b.getBlackDead());

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.board[i][j].setPiece(b.board[i][j].getPiece());
			}
		}
		return;
	}
}