package com.logicnow.hiring.piece;

import com.logicnow.hiring.board.ChessBoard;
import com.logicnow.hiring.exception.IllegalBoardPositionException;
import com.logicnow.hiring.exception.IllegalMovementOrCaptureException;
import com.logicnow.hiring.player.PlayerColor;

public class Pawn extends Piece {
    
	private static int STANDARD_MOVEMENT = 1;
	private static int INITIAL_MOVEMENT = 2;
	private static int INITIAL_WHITE_ROW = 1;
	private static int INITIAL_BLACK_ROW = 6;
	
	public Pawn(PlayerColor pieceColor) {
		this.pieceColor = pieceColor;
		this.pieceType  = PieceType.Pawn;
	}

	/*
	 * Responsible to move the Pawn when the new position are applicable to the game context
	 * If the new position is invalid an exception will be held.
	 * 
	 * @param newX new piece's row
	 * @param newY new piece's column
	 */
	public void move(int newX, int newY){
		ChessBoard chessBoard = ChessBoard.getInstance();

		if (!chessBoard.isLegalBoardPosition(newX, newY)){
			throw new IllegalBoardPositionException();
		}
			
		boolean captureMovement = yCoordinate != newY;
		boolean validMovement = false;


		if (this.pieceColor == PlayerColor.BLACK ){
			validMovement = this.isValidBlackMovement(captureMovement, chessBoard, newX, newY);
		} else {
			validMovement = this.isValidWhiteMovement(captureMovement, chessBoard, newX, newY);
		}

		if (!validMovement){
			throw new IllegalMovementOrCaptureException();
		}
			
		chessBoard.updatePiecePosition(this, newX, newY);
		this.xCoordinate = newX;
		this.yCoordinate = newY;
	}


	/*
	 * Verify if a new piece position is valid to White Pieces
	 * @return boolean
	 */
	private boolean isValidWhiteMovement(boolean captureMovement, ChessBoard chessBoard, int newX, int newY){
		if (captureMovement){
			Piece capturedPiece = chessBoard.getPieceAt(newX, newY); 

			if ( (newX != xCoordinate + STANDARD_MOVEMENT) ||
				 (newY != yCoordinate + STANDARD_MOVEMENT && newY != yCoordinate - STANDARD_MOVEMENT) ||
				 ( capturedPiece == null || capturedPiece.getPieceColor().equals(this.getPieceColor()))){
				return false;
			}		 
		} else {
			if( (newX == xCoordinate + INITIAL_MOVEMENT && xCoordinate != INITIAL_WHITE_ROW) ||
				(newX > xCoordinate + INITIAL_MOVEMENT) ||
				(newX < xCoordinate)){
				return false;
			}		
		}
		return true;
	}

	/*
	 * Verify if a new piece position is valid to Black Pieces
	 * @return boolean
	 */
	private boolean isValidBlackMovement(boolean captureMovement, ChessBoard chessBoard, int newX, int newY){
		if (captureMovement){
			Piece capturedPiece = chessBoard.getPieceAt(newX, newY); 

			if ( (newX != xCoordinate - STANDARD_MOVEMENT) ||
				 (newY != yCoordinate + STANDARD_MOVEMENT && newY != yCoordinate - STANDARD_MOVEMENT) ||
				 (capturedPiece == null || capturedPiece.getPieceColor().equals(this.getPieceColor()))){
				return false;
			}		
		} else {
			if( (newX == xCoordinate - INITIAL_MOVEMENT &&  xCoordinate != INITIAL_BLACK_ROW ) ||
				(newX < xCoordinate - INITIAL_MOVEMENT) ||
				(newX > xCoordinate)){
				return false;
			}
		}
		return true;
	}
}
