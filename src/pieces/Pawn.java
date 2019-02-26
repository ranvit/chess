package pieces;

public class Pawn extends Piece {
	
	public Pawn(boolean whitePlayer) {
		super(whitePlayer);
		this.name = 'P';
		if(whitePlayer) this.image = "\u2659";
		else this.image = "\u265F";
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill) {
		// TODO Auto-generated method stub
		return pawnMoveHelper(currRow, currCol, destRow, destCol, kill);
	}
	
	public boolean pawnMoveHelper(int currRow, int currCol, int destRow, int destCol, boolean kill){
		int posneg = 0;
		int startPos = 1;
		if(this.white) posneg = 1;
		else{
			posneg = -1;
			startPos = 6;
		}
		// When pawn tries to move forward by 2 spaces
		if(currRow == startPos && currRow+(2*posneg)==destRow && currCol == destCol && !kill){
			return true;
		}
		
		// When pawn tries to move forward by 1 space
		if(currRow+(1*posneg)==destRow && currCol == destCol && !kill){
			return true;
		}
		
		// When pawn tries to move diagonally
		if(currRow+(1*posneg)==destRow && Math.abs(currCol-destCol)==1 && kill){
			return true;
		}
		return false;
	}

}
