package com.logicnow.hiring.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.logicnow.hiring.exception.IllegalBoardPositionException;
import com.logicnow.hiring.exception.IllegalDuplicatedPiecePositionException;
import com.logicnow.hiring.exception.IllegalPieceAmountException;
import com.logicnow.hiring.piece.Pawn;
import com.logicnow.hiring.piece.PieceType;
import com.logicnow.hiring.player.PlayerColor;

public class ChessBoardTest{

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        chessBoard = ChessBoard.getInstance();
        chessBoard.resetBoard();
    }

    @Test
    public void validateInitialLineAndColumnPossiblePositions() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void validateAleatoryValidColumnAndLinePosition() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void validateInvalidLinePosition() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void validateInvalidaColumnPosition() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void validateInvalidColumnAndLinePosition(){
    	boolean isValidPosition = chessBoard.isLegalBoardPosition(-1, 9);
        assertFalse(isValidPosition);
    }
        
    @Test(expected=IllegalBoardPositionException.class)
    public void ensureExceptionIsThrownForInvalidBoardPosition(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 9, 3);
    }

    @Test(expected=IllegalDuplicatedPiecePositionException.class)
    public void avoidDuplicatePiecePosition() {
    	Pawn firstPawn = new Pawn(PlayerColor.WHITE);
        Pawn secondPawn = new Pawn(PlayerColor.WHITE);
        chessBoard.add(firstPawn, 6, 3);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        chessBoard.add(secondPawn, 6, 3);
    }

    @Test(expected=IllegalPieceAmountException.class)
    public void testLimits_The_Number_Of_Pawns(){
    	for (int x = 0; x < ChessBoard.MAX_BOARD_WIDTH; x++){
    		for (int y = 0; y < ChessBoard.MAX_BOARD_WIDTH; y++){
    			Pawn pawn = new Pawn(PlayerColor.WHITE);
    			chessBoard.add(pawn, x, y);
    		}
    	}
    }
    
    @Test 
    public void ensureIsPossibleAddMaxPawnAmount(){
    	for (int y = 0; y < PieceType.Pawn.piecesMaxAmount; y++){
    		Pawn pawn = new Pawn(PlayerColor.WHITE);
    		chessBoard.add(pawn, 1, y);
    	}
    	assertEquals(PieceType.Pawn.piecesMaxAmount, chessBoard.getWhitePlayer().getNumberOfPawns());
    }
    
    @Test
    public void ensureIsPossibleAddMaxPawnAmountForEachPlayer(){
    	
    	for (int y = 0; y < PieceType.Pawn.piecesMaxAmount; y++){
    		Pawn pawn = new Pawn(PlayerColor.WHITE);
    		chessBoard.add(pawn, 1, y);
    	}
    	
    	for (int y = 0; y < PieceType.Pawn.piecesMaxAmount; y++){
    		Pawn pawn = new Pawn(PlayerColor.BLACK);
    		chessBoard.add(pawn, 6, y);
    	}
    	
    	assertEquals(PieceType.Pawn.piecesMaxAmount, chessBoard.getBlackPlayer().getNumberOfPawns());
    	assertEquals(PieceType.Pawn.piecesMaxAmount, chessBoard.getWhitePlayer().getNumberOfPawns());
    }
    
    
    @Test
    public void ensureThatIsReturnedTheRequiredPiece(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 1, 1);
    	
    	assertEquals(chessBoard.getPieceAt(1, 1), pawn);
    }
   
    @Test
    public void ensureThatIsReturnedNulltoFreePosition(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 1, 1);
    	
    	assertEquals(chessBoard.getPieceAt(1, 1), pawn);
    	assertEquals(chessBoard.getPieceAt(2, 1), null);
    }
    
    @Test(expected=IllegalBoardPositionException.class)
    public void throwExceptionWhenTryToUpdateInvalidPosition(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
        chessBoard.updatePiecePosition(pawn, 1, 1);    
    }
    
    @Test(expected=IllegalBoardPositionException.class)
    public void throwExceptionWhenTryToUpdatePieceWithWrongInitialPosition(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	pawn.setXCoordinate(1);
    	pawn.setYCoordinate(2);
        chessBoard.updatePiecePosition(pawn, 1, 1);    
    }
    
    @Test
    public void ensurePiecePositionIsUpdateCorrectly(){
    	Pawn pawn = new Pawn(PlayerColor.WHITE);
    	chessBoard.add(pawn, 1, 2);
        chessBoard.updatePiecePosition(pawn, 2, 2);
        
        assertEquals(chessBoard.getPieceAt(1, 2), null);
    	assertEquals(chessBoard.getPieceAt(2, 2), pawn);
    }
    
}