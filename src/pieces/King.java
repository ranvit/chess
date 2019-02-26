package pieces;

public class King extends Piece {

	public King(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'K';
		if(whitePlayer) this.image = "\u2654";
		else this.image = "\u265A";
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		int rowDiff = Math.abs(destRow-currRow);
		int colDiff = Math.abs(destCol-currCol);
		if(rowDiff > 1 || colDiff > 1) return false;
		return true;
	}

}
