package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Game;

public class GameTest {
	
	Game g = new Game();

	@Test
	public void testGame() {
		assert(g.equals(g));
	}

	@Test
	public void testGetGui() {
		assert(g.getGui() != null);
	}

	@Test
	public void testGetUser() {
		assert(g.getUser() != null);
	}

	@Test
	public void testGetBoard() {
		assert(g.getBoard() != null);
	}

	@Test
	public void testGetCurrRow() {
		assert(g.getCurrRow() == -1);
	}

	@Test
	public void testGetCurrCol() {
		assert(g.getCurrCol() == -1);
	}

	@Test
	public void testGetDestRow() {
		assert(g.getDestRow() == -1);
	}

	@Test
	public void testGetDestCol() {
		assert(g.getDestCol() == -1);
	}

	@Test
	public void testNewMainLoop() {
		assert(!g.newMainLoop());
		
		g.getBoard().customSetup();
		g.getUser().setCurrRow(0);
		g.getUser().setCurrCol(1);
		g.getUser().setDestRow(2);
		g.getUser().setDestCol(0);
		assert(!g.newMainLoop());
	}

	@Test
	public void testMover() {
		assert(!g.mover(true));
	}

	@Test
	public void testGetNewMove() {
		assert(!g.getNewMove(true));
	}

	@Test
	public void testNiceDisplay() {
		g.niceDisplay();
	}

	@Test
	public void testReset() {
		g.reset();
	}

	@Test
	public void testForfeit() {
		g.forfeit();
	}

	@Test
	public void testCustom() {
		g.custom();
	}

	@Test
	public void testUndo() {
		g.undo();
	}

	@Test
	public void testMain() {
		Game.main(null);
	}

}
