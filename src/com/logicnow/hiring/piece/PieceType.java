package com.logicnow.hiring.piece;

public enum PieceType {
    Pawn(8);
	
	public int piecesMaxAmount;
	
	private PieceType(int maxAmount) {
		piecesMaxAmount = maxAmount;
	}
}
