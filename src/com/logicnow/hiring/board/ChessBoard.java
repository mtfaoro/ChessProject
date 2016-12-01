package com.logicnow.hiring.board;

import com.logicnow.hiring.exception.IllegalBoardPositionException;
import com.logicnow.hiring.exception.IllegalDuplicatedPiecePositionException;
import com.logicnow.hiring.exception.IllegalPieceAmountException;
import com.logicnow.hiring.piece.Piece;
import com.logicnow.hiring.player.Player;
import com.logicnow.hiring.player.PlayerColor;

public class ChessBoard {

    private static ChessBoard instance = null;
    protected static int MAX_BOARD_WIDTH = 8;
    protected static int MAX_BOARD_HEIGHT = 8;
    private static int INITIAL_ROW = 0;
    private static int FINAL_ROW = 7;
    private static int INITIAL_COLUMN = 0;
    private static int FINAL_COLUMN = 7;
    private Player blackPlayer;
    private Player whitePlayer;
    private Piece[][] pieces;
	
    protected ChessBoard() {
    	this.resetBoard();
    }
    
    public static ChessBoard getInstance() {
    	if (instance == null){
    		instance = new ChessBoard();
    	}
    	return instance;
    }
    
    public void resetBoard(){
    	pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    	blackPlayer = new Player();
    	whitePlayer = new Player();
     }
    
    
    public Player getBlackPlayer() {
		return blackPlayer;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	
	/*
     * Responsible for adding a new piece into the board.
     * 
     * When the coordinates(x,y) are not valids, one exception will be hold.
     * 
     * @param piece Piece that will be added in the board (Pawn, Queen, King, ...)
     * @param xCoordinate Line the piece will be added
     * @param yCoordinate Column the piece will be added
     * 
     */
    public void add(Piece piece, int xCoordinate, int yCoordinate) {
    	
    	if (! isLegalBoardPosition(xCoordinate, yCoordinate)){
    		throw new IllegalBoardPositionException();
    	}
    	
    	if (! isPossibleToAddPiece(piece)){
    		throw new IllegalPieceAmountException();
    	}
    		
    	if (pieces[xCoordinate][yCoordinate] == null){
    		pieces[xCoordinate][yCoordinate] = piece; 
    		piece.setXCoordinate(xCoordinate);
    		piece.setYCoordinate(yCoordinate);
    	} else throw new IllegalDuplicatedPiecePositionException();
    }
    
    /*
     * Responsible to verify if it is possible to add a piece in the board. 
     * If there are more than the max possible amount of this piece's type in the board it will be returned false.
     * @param Piece 
     * @return boolean
     */
    private boolean isPossibleToAddPiece(Piece piece){
    	return piece.getPieceColor() == PlayerColor.BLACK ? blackPlayer.isPossibleAddPiece(piece.getPieceType()) : whitePlayer.isPossibleAddPiece(piece.getPieceType());   	
    }

    
    /*
     * Validate if a coordinate(x,y) is a valid position in the board.
     * @param xCoordinate row
     * @param yCoordinate column 
     * @return boolean 
     */
    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
    	if (xCoordinate < INITIAL_ROW|| xCoordinate > FINAL_ROW ||
    	    yCoordinate < INITIAL_COLUMN || yCoordinate > FINAL_COLUMN)
    		return false;
    	return true;
    }
    
    /*
     * Return a piece in a specific board's coordinate(x,y)
     * @param xCoordinate row
     * @param yCoordinate column 
     * @return Piece 
     */
    public Piece getPieceAt(int xCoordinate, int yCoordinate){
    	return pieces[xCoordinate][yCoordinate];
    }
    
    /*
     * Update the piece position in the board. 
     * If the new position is invalid, a exception will be held. 
     * @param piece piece that will have a new position 
     * @param xCoordinate row
     * @param yCoordinate column
     */
    public void updatePiecePosition(Piece piece, int xCoordinate, int yCoordinate){
    	
    	if(! isLegalBoardPosition(piece.getXCoordinate(), piece.getYCoordinate()) ||
    	     pieces[piece.getXCoordinate()][piece.getYCoordinate()] == null){
    		throw new IllegalBoardPositionException();
    	}
    
    	pieces[piece.getXCoordinate()][piece.getYCoordinate()] = null;
    	pieces[xCoordinate][yCoordinate] = piece;
    }
    
}
