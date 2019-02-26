package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import gui.Labels;

public class User extends MouseAdapter{
	boolean white;
	int currRow = -1, currCol = -1, destRow = -1, destCol = -1;
	private boolean reader = false;
	private boolean curr = true;
	private Game g;
	
	public void setGame(Game game) {
		this.setG(game);
	}
	
	public void setCurrRow(int currRow) {
		this.currRow = currRow;
	}

	public void setCurrCol(int currCol) {
		this.currCol = currCol;
	}

	public void setDestRow(int destRow) {
		this.destRow = destRow;
	}

	public void setDestCol(int destCol) {
		this.destCol = destCol;
	}
	
	public boolean isReader() {
		return reader;
	}

	public void setReader(boolean reader) {
		this.reader = reader;
	}
	
	public int getCurrRow() {
		return this.currRow;
	}
	public int getCurrCol() {
		return this.currCol;
	}
	public int getDestRow() {
		return this.destRow;
	}
	public int getDestCol() {
		return this.destCol;
	}
	
	MouseListener listener = new MouseAdapter() {
		@Override
	    public void mouseClicked(MouseEvent e) {
			Labels l = (Labels) e.getSource();
			if(curr) {
				currRow = (l.getRow());
				currCol = (l.getCol());
				curr = false;
			}
			else {
				destRow = (l.getRow());
				destCol = (l.getCol());
				curr = true;
				reader = true;
				//System.out.println(l.row);
				getG().newMainLoop();
			}
		}
	};
	
	public MouseListener getListen() {
		return listener;
	}

	public Game getG() {
		return g;
	}

	public void setG(Game g) {
		this.g = g;
	}
}
