package pieces;

public class Bishop extends Piece {

	public Bishop(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'B';
		if(whitePlayer) this.image = "\u2657";
		else this.image = "\u265D";
	}

	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		int rowDiff = Math.abs(destRow-currRow);
		int colDiff = Math.abs(destCol-currCol);
		
		//checking for LACK of diagonal movement
		if(destRow == currRow || destCol == currCol ||
				rowDiff != colDiff) return false;
		
		return true;
	}

}
