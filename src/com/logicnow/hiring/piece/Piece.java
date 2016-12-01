package com.logicnow.hiring.piece;

import com.logicnow.hiring.player.PlayerColor;

public abstract class Piece {
	
	protected int xCoordinate;
    protected int yCoordinate;
    protected PlayerColor pieceColor;
    protected PieceType pieceType;
    
    public Piece(){
    	xCoordinate = -1;
    	yCoordinate = -1;
    }
    
    public int getXCoordinate() {
		return xCoordinate;
	}
	public void setXCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getYCoordinate() {
		return yCoordinate;
	}
	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public PlayerColor getPieceColor() {
		return pieceColor;
	}
	public void setPieceColor(PlayerColor pieceColor) {
		this.pieceColor = pieceColor;
	}
	
	public PieceType getPieceType() {
		return pieceType;
	}

	/*
	 * Responsible to move a piece in the chess board
	 * @param newX new piece's row
	 * @param newY new piece's column
	 */
	abstract void move(int newX, int newY);
}
