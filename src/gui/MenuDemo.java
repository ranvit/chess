package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import main.User;

import javax.swing.ImageIcon;
 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

public class MenuDemo implements ActionListener{
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    JMenu turnt, whiteScore, blackScore; 
    User u;
    
    public MenuDemo(User use){
    	this.u = use;
    }
 
    public JMenu getTurnt() {
		return turnt;
	}

	public void setTurnt(JMenu turnt) {
		this.turnt = turnt;
	}
	
	public void setTurnColor(Color c){
		turnt.setBackground(c);
	}
	
	public void setWhiteScore(int c){
		whiteScore.setText(Integer.toString(c));
	}
	
	public void setBlackScore(int c){
		blackScore.setText(Integer.toString(c));
	}

	public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        
 
        //Create the menu bar.
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
 
        //Build the first menu.
        menu = new JMenu("GameType");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
 
        //a group of JMenuItems
        menuItem = new JMenuItem("Standard", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Custom", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
 
 
        //Build second menu in the menu bar.
        menu = new JMenu("Actions");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Forfeit", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Undo", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Build second menu in the menu bar.
        turnt = new JMenu("TURN");
        turnt.setBackground(Color.WHITE);
        turnt.setForeground(Color.LIGHT_GRAY);
        turnt.setOpaque(true);
        turnt.setMnemonic(KeyEvent.VK_N);
        menuBar.add(turnt);
        
        //Build third menu in the menu bar.
        whiteScore = new JMenu("0");
        whiteScore.setBackground(Color.WHITE);
        whiteScore.setForeground(Color.LIGHT_GRAY);
        whiteScore.setOpaque(true);
        whiteScore.setMnemonic(KeyEvent.VK_N);
        menuBar.add(whiteScore);
        
        //Build fourth menu in the menu bar.
        blackScore = new JMenu("0");
        blackScore.setBackground(Color.BLACK);
        blackScore.setForeground(Color.LIGHT_GRAY);
        blackScore.setOpaque(true);
        blackScore.setMnemonic(KeyEvent.VK_N);
        menuBar.add(blackScore);
 
        return menuBar;
    }
 
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String name = source.getText();
        switch(name) {
        case "Standard": u.getG().reset(); break;
        case "Forfeit": u.getG().forfeit(); break;
        case "Custom": u.getG().custom(); break;
        case "Undo": u.getG().undo(); break;
        }
/*        if(name.equals("Standard")){
        	u.g.reset();
        }
        if(name.equals("Forfeit")){
        	u.g.forfeit();
        }
        if(name.equals("Custom")){
        	u.g.custom();
        }
        if(name.equals("Undo")){
        	u.g.custom();
        }
*/    }
}