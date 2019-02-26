package pieces;

/**
 * Custom Piece 1, which can only move to the corners of the Board,
 * only if it is currently in the middle of the board.
 * @author Runwith
 *
 */
public class Corner extends Piece {

	boolean unused;
	public Corner(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'C';
		if(whitePlayer) this.image = "\u2664";
		else this.image = "\u2660";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		// TODO Auto-generated method stub
		if(currRow < 3 || currRow > 4 || currCol < 3 || currCol > 4) return false;
		if((destRow == 0 || destRow == 7) && (destCol == 0 || destCol == 7)) return true;
		return false;
	}

}
