package pieces;

public abstract class Piece {

	boolean white;
	int rowID;
	int colID;
	char name;
	String image;
	
	public Piece(boolean whitePlayer){
		this.white = whitePlayer;
		this.name = '?';
		this.rowID = -1;
		this.colID = -1;
		this.image = " ";
	}
	
	abstract public boolean validMove(int currRow, int currCol, int destRow, int destCol, boolean kill);
	
	public char getName(){
		char c;
		if(this.white) c = Character.toUpperCase(this.name);
		else c = Character.toLowerCase(this.name);
		return c;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public int getRow(){
		return this.rowID;
	}
	
	public int getCol(){
		return this.colID;
	}
	
	public void setRow(int r){
		this.rowID = r;
	}
	
	public void setCol(int c){
		this.colID = c;
	}
	
	public boolean getUser(){
		return this.white;
	}
}
