package tests;
import main.*;
import pieces.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTestTwo {
	
	Board b = new Board();
	Pawn p = new Pawn(true);
	
	@Test
	public void testBoard() {
		assert(b != null);
	}

	@Test
	public void testGetBoard() {
		assert(b.getBoard() != null);
	}

	@Test
	public void testGetWhiteAlive() {
		assert(b.getWhiteAlive() != null);
		
	}

	@Test
	public void testGetBlackAlive() {
		assert(b.getBlackAlive() != null);
	}

	@Test
	public void testGetWhiteDead() {
		assert(b.getWhiteDead() != null);
	}

	@Test
	public void testGetBlackDead() {
		assert(b.getBlackDead() != null);
	}

	@Test
	public void testAddPiece() {
		b.addPiece(p, 0, 0);
		assert(b.getBoard()[0][0].getPiece().equals(p));
	}

	@Test
	public void testCustomSetup() {
		b.customSetup();
		assert(b.getBoard()[4][3].getPiece() instanceof Middle);
	}

	@Test
	public void testNormalSetup() {
		b.normalSetup();
		assert(b.getBoard()[0][0].getPiece() instanceof Rook);
	}

	@Test
	public void testPrintBoard() {
		b.printBoard();
	}

	@Test
	public void testInCheck() {
		b.normalSetup();
		assert(!b.inCheck(true));
	}

	@Test
	public void testLookAroundKing() {
		b.normalSetup();
		assert(!b.lookAroundKing(0, 4, true));
	}

	@Test
	public void testMove() {
		b.normalSetup();
		assert(b.move(0, 1, 2, 0) == null);
	}

	@Test
	public void testMoveCheckUnmove() {
		b.normalSetup();
		assert(b.moveCheckUnmove(0, 1, 2, 0, true) == false);
	}

	@Test
	public void testStraightEmpty() {
		b.normalSetup();
		assert(b.straightEmpty(4, 0, 4, 4));
	}

	@Test
	public void testDiagEmpty() {
		b.normalSetup();
		assert(b.diagEmpty(2, 0, 4, 2));
	}

	@Test
	public void testPathShape() {
		b.normalSetup();
		assert(b.pathShape(0, 1, 2, 0)==2);
	}

	@Test
	public void testGameOverCheck() {
		b.normalSetup();
		assert(b.gameOverCheck(true) == 0);
	}

	@Test
	public void testAllowedMove() {
		b.normalSetup();
		b.allowedMove(0, 1, 2, 0, true);
	}

	@Test
	public void testCopy() {
		b.copy(b);
	}

}
