package pieces;

public class Rook extends Piece {

	public Rook(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'R';
		if(whitePlayer) this.image = "\u2656";
		else this.image = "\u265C";
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		if(destRow == currRow || destCol == currCol) return true;
		return false;
	}

}
