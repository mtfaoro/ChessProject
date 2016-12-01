package com.logicnow.hiring.player;

import com.logicnow.hiring.piece.PieceType;

public class Player {
	
	private int numberOfPawns = 0;
	
	
	public int getNumberOfPawns() {
		return numberOfPawns;
	}

	public void setNumberOfPawns(int numberOfPawns) {
		this.numberOfPawns = numberOfPawns;
	}

	/*
	 * Responsible to verify if it is possible to add a piece in the board. 
	 * This method will also update the specified pieces' amount 
	 * @param pieceType piece's type (Pawn, King, Queen, ...)
	 * @return boolean
	 */
	public boolean isPossibleAddPiece(PieceType pieceType){
		switch (pieceType){
			case Pawn: 
				 	if (numberOfPawns + 1 > pieceType.piecesMaxAmount){
				 		return false;
				 	}
				 	else {
				 		numberOfPawns++;
				 	}
					break;
		}
		return true;
	}

}
