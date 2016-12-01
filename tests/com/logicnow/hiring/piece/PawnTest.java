package com.logicnow.hiring.piece;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.logicnow.hiring.board.ChessBoard;
import com.logicnow.hiring.exception.IllegalMovementOrCaptureException;
import com.logicnow.hiring.piece.Pawn;
import com.logicnow.hiring.player.PlayerColor;

public class PawnTest {

    private ChessBoard chessBoard;
   
    @Before
    public void setUp() {
        chessBoard = ChessBoard.getInstance();
        chessBoard.resetBoard();
    }

    @Test
    public void validatePawnLineAndColumn() {
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 1, 2);
    	assertEquals(1, pawn.getXCoordinate());
    	assertEquals(2, pawn.getYCoordinate());
    }
    
    @Test
    public void ensureIsPossibleAInitialDoubleMovmentWhite(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 1, 2);
    	pawn.move(3, 2);
    	
    	assertEquals(3, pawn.xCoordinate);
    	assertEquals(2, pawn.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(1, 2));
    	assertEquals(pawn, chessBoard.getPieceAt(3, 2));
    }
    
    @Test
    public void ensureIsPossibleAInitialDoubleMovmentBlack(){
    	Pawn pawn = new Pawn(PlayerColor.BLACK);
    	chessBoard.add(pawn, 6, 2);
    	pawn.move(4, 2);
    	
    	assertEquals(4, pawn.xCoordinate);
    	assertEquals(2, pawn.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(6, 2));
    	assertEquals(pawn, chessBoard.getPieceAt(4, 2));
    }

    @Test
    public void ensureIsPossibleAInitialSinggleMovementWhite(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 1, 2);
    	pawn.move(2, 2);
    	
    	assertEquals(2, pawn.xCoordinate);
    	assertEquals(2, pawn.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(1, 2));
    	assertEquals(pawn, chessBoard.getPieceAt(2, 2));
    }
    
    @Test
    public void ensureIsPossibleAInitialSinggleMovementBlack(){
    	Pawn pawn = new Pawn(PlayerColor.BLACK);
    	chessBoard.add(pawn, 6, 2);
    	pawn.move(5, 2);
    	
    	assertEquals(5, pawn.xCoordinate);
    	assertEquals(2, pawn.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(6, 2));
    	assertEquals(pawn, chessBoard.getPieceAt(5, 2));
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidTripleInitialMovementWhite(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 1, 2);
    	pawn.move(4, 2);
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidTripleInitialMovementBlack(){
    	Pawn pawn = new Pawn(PlayerColor.BLACK);
    	chessBoard.add(pawn, 6, 2);
    	pawn.move(3, 2);
    }
    
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidBackMovementWhite(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 4, 2);
    	pawn.move(3, 2);
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidBackMovementBlack(){
    	Pawn pawn = new Pawn(PlayerColor.BLACK);
    	chessBoard.add(pawn, 4, 2);
    	pawn.move(5, 2);
    }
   
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidColumnMovementSameLine(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 3, 2);
    	pawn.move(3, 3);
    	
     	assertEquals(3, pawn.xCoordinate);
    	assertEquals(2, pawn.yCoordinate);
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidDoubleMovementWhite(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 2, 2);
    	pawn.move(4, 2);
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidDoubleMovementBlack(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 4, 2);
    	pawn.move(2, 2);
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidCapitureMovementWhite(){
     	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 3, 2);
    	pawn.move(4, 3);
    }

    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidCapitureMovementBlack(){
     	Pawn pawn = new Pawn(PlayerColor.BLACK);
    	chessBoard.add(pawn, 5, 2);
    	pawn.move(4, 3);
    }
    
    @Test
    public void validRightCapitureMovementWhite(){
    	Pawn pawnA = new Pawn(PlayerColor.WHITE);
    	Pawn pawnB = new Pawn(PlayerColor.BLACK);
    	
    	chessBoard.add(pawnA, 2,1);
    	chessBoard.add(pawnB, 3,2);
    	
    	pawnA.move(3,2);
    	assertEquals(3, pawnA.xCoordinate);
    	assertEquals(2, pawnA.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(2, 1));
    	assertEquals(pawnA, chessBoard.getPieceAt(3, 2));
    }

    @Test
    public void validRightCapitureMovementBlack(){
    	Pawn pawnA = new Pawn(PlayerColor.WHITE);
    	Pawn pawnB = new Pawn(PlayerColor.BLACK);
    	
    	chessBoard.add(pawnA, 2,1);
    	chessBoard.add(pawnB, 3,2);
    	
    	pawnB.move(2,1);
    	assertEquals(2, pawnA.xCoordinate);
    	assertEquals(1, pawnA.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(3, 2));
    	assertEquals(pawnB, chessBoard.getPieceAt(2, 1));
    }
    
    @Test
    public void validLeftCapitureMovementWhite(){
    	Pawn pawnA = new Pawn(PlayerColor.WHITE);
    	Pawn pawnB = new Pawn(PlayerColor.BLACK);
    	
    	chessBoard.add(pawnA, 1,1);
    	chessBoard.add(pawnB, 2,0);
    	
    	pawnA.move(2,0);
    	assertEquals(2, pawnA.xCoordinate);
    	assertEquals(0, pawnA.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(1, 1));
    	assertEquals(pawnA, chessBoard.getPieceAt(2, 0));
    }
    
    @Test
    public void validLeftCapitureMovementBlack(){
    	Pawn pawnA = new Pawn(PlayerColor.WHITE);
    	Pawn pawnB = new Pawn(PlayerColor.BLACK);
    	
    	chessBoard.add(pawnA, 1,1);
    	chessBoard.add(pawnB, 2,0);
    	
    	pawnB.move(1,1);
    	assertEquals(1, pawnB.xCoordinate);
    	assertEquals(1, pawnB.yCoordinate);
    	assertEquals(null, chessBoard.getPieceAt(2, 1));
    	assertEquals(pawnB, chessBoard.getPieceAt(1, 1));
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidCaptureMovementSameColourWhite(){
     	Pawn pawnA = new Pawn(PlayerColor.WHITE);
    	Pawn pawnB = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawnA, 1,1);
    	chessBoard.add(pawnB, 2,0);
    	pawnA.move(2,0);
    }
    
    @Test(expected=IllegalMovementOrCaptureException.class)
    public void invalidCaptureMovementSameColourBlack(){
    	Pawn pawnA = new Pawn(PlayerColor.BLACK);
    	Pawn pawnB = new Pawn(PlayerColor.BLACK);
    	chessBoard.add(pawnA, 1,1);
    	chessBoard.add(pawnB, 2,0);
    	pawnB.move(1,1);
    }
}