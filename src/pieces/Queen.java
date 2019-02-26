package pieces;

public class Queen extends Piece {

	public Queen(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'Q';
		if(whitePlayer) this.image = "\u2655";
		else this.image = "\u265B";
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		int rowDiff = Math.abs(destRow-currRow);
		int colDiff = Math.abs(destCol-currCol);
		if(destRow == currRow || destCol == currCol || rowDiff == colDiff) return true;
		return false;
	}

}
