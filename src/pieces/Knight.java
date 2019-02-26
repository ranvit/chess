package pieces;

public class Knight extends Piece {

	public Knight(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'N';
		if(whitePlayer) this.image = "\u2658";
		else this.image = "\u265E";
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		int rowDiff = Math.abs(destRow-currRow);
		int colDiff = Math.abs(destCol-currCol);
		if(rowDiff * colDiff == 2) return true;
		return false;
	}

}
