package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.User;

public class Labels extends JLabel {

	Font font = new Font("Ariel", Font.PLAIN, 24);
	Color light = new Color(238, 238, 210);
	Color dark = new Color(186,202,68);
//	Color light = new Color(253,185,39);
//	Color dark = new Color(85,37,130);
	private boolean curr = true;
	private int row;
	private int col;
	User u; 
	
	public void setUser(User use) {
		this.u = use;
	}
	
/*	MouseListener listener = new MouseAdapter() {
		@Override
	    public void mouseClicked(MouseEvent e) {
			Labels l = (Labels) e.getSource();
			if(curr) {
				u.setCurrRow(l.row);
				u.setCurrCol(l.col);
				curr = false;
			}
			else {
				u.setDestRow(l.row);
				u.setDestCol(l.col);
				curr = true;
				u.setReader(true);
				
			}
		}
	};
*/
	Labels(String s, int row, int col) {
		super(s);
//		this.addMouseListener(listener);
		this.setRow(row);
		this.setCol(col);
		setFont(font);
		setOpaque(true);
		setBackground((col + row) % 2 == 0 ? dark : light);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}