package pieces;

/**
 * Custom Piece 2 - From the middle, it can only move within a 2 cell radius
 * @author Runwith
 *
 */
public class Middle extends Piece {

	public Middle(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'M';
		if(whitePlayer) this.image = "\u2662";
		else this.image = "\u2666";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		// TODO Auto-generated method stub
		int rowDiff = Math.abs(destRow-currRow);
		int colDiff = Math.abs(destCol-currCol);
		if(currRow < 3 || currRow > 4 || currCol < 3 || currCol > 4) return false;
		if(rowDiff == 2 || colDiff == 2) return true;
		return false;
	}

}
