package main;

import pieces.Piece;

public class Tile {
	boolean white;	//	Color of the tile
	boolean empty;	// Status of tile
	Piece piece;
	int rowID;
	int colID;
	
	/**
	 * 
	 * @param color:	Which color to initialize to
	 * @param p:		Piece to set on the tile
	 */
	public Tile(boolean color, Piece p, int row, int col){
		this.white = color;
		this.empty = (p == null);
		this.piece = p;
		this.rowID = row;
		this.colID = col;
	}
	
	public Piece getPiece(){
		return this.piece;
	}
	
	public boolean isEmpty(){
		return this.empty;
	}
	
	public boolean isWhite(){
		return this.white;
	}
	
	public void setPiece(Piece p){
		this.empty = (p == null);
		this.piece = p;
		if(p != null){
			p.setRow(this.rowID);
			p.setCol(this.colID);	
		}
	}
}
