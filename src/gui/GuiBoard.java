package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import main.User;


public class GuiBoard extends JFrame {

	public Labels[][] labels = new Labels[8][8];
	
	String whiteRook = "\u2656";		String whiteKnight = "\u2658";
	String whiteBishop = "\u2657";	String whitePawn = "\u2659";
	String whiteQueen = "\u2655";	String whiteKing = "\u2654";
	
	String blackRook = "\u265C";		String blackKnight = "\u265E"; 
	String blackBishop = "\u265D";	String blackPawn = "\u265F";
	String blackQueen = "\u265B";	String blackKing = "\u265A";
	
	String empty = " ";
	
	User u;
	private MenuDemo menu;
	
	public GuiBoard(User u) {
		this.u = u;
		setMenu(new MenuDemo(u));
		setTitle("Chess board");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container contentPane = getContentPane();
		GridLayout gridLayout = new GridLayout(8, 8);

		contentPane.setLayout(gridLayout);

		for(int col = 0; col < 8; col++){
			labels[1][col] = new Labels(whitePawn, 1, col);
			labels[6][col] = new Labels(blackPawn, 6, col);
		}
		for(int row = 2; row < 6; row++) {
			for(int col = 0; col < 8; col++) {
				labels[row][col] = new Labels(empty, row, col);
			}
		}
		
		labels[0][0] = new Labels(whiteRook, 0, 0);
		labels[0][7] = new Labels(whiteRook, 0, 7);
		labels[7][0] = new Labels(blackRook, 7, 0);
		labels[7][7] = new Labels(blackRook, 7, 7);
		
		labels[0][1] = new Labels(whiteKnight, 0, 1);
		labels[0][6] = new Labels(whiteKnight, 0, 6);
		labels[7][1] = new Labels(blackKnight, 7, 1);
		labels[7][6] = new Labels(blackKnight, 7, 6);
		
		labels[0][2] = new Labels(whiteBishop, 0, 2);
		labels[0][5] = new Labels(whiteBishop, 0, 5);
		labels[7][2] = new Labels(blackBishop, 7, 2);
		labels[7][5] = new Labels(blackBishop, 7, 5);
		
		labels[0][3] = new Labels(whiteQueen, 0, 3);
		labels[0][4] = new Labels(whiteKing, 0, 4);
		
		labels[7][3] = new Labels(blackQueen, 7, 3);
		labels[7][4] = new Labels(blackKing, 7, 4);
		
		for(int row = 7; row >= 0; row--) {
			for(int col = 0; col < 8; col++) {
				labels[row][col].setUser(this.u);
				labels[row][col].addMouseListener(u.getListen());
				contentPane.add(labels[row][col]);
			}
		}
		setSize(600, 600);
		setLocationRelativeTo(null);
		
		this.setJMenuBar(getMenu().createMenuBar());
	}

	public void display() {
		setVisible(true);
	}

	public MenuDemo getMenu() {
		return menu;
	}

	public void setMenu(MenuDemo menu) {
		this.menu = menu;
	}

}