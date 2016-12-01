package com.logicnow.hiring.player;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.logicnow.hiring.piece.PieceType;
import com.logicnow.hiring.player.Player;

public class PlayerTest {

	private Player player;
	
	@Before
	public void setUp(){
		player = new Player();
	}
	
	@Test
	public void ensureIsPossibleAddLimitAmountOfPawns(){
		boolean isPossibleToAdd = true;
		for (int i = 0; i < PieceType.Pawn.piecesMaxAmount; i++){
			isPossibleToAdd = player.isPossibleAddPiece(PieceType.Pawn);
		}
		assertEquals(true, isPossibleToAdd);
	}
	
	
	@Test
	public void ensureIsNotPossibleMoreThanLimitAmountOfPawns(){
		boolean isPossibleToAdd = true;
		for (int i = 0; i < PieceType.Pawn.piecesMaxAmount + 1; i++){
			isPossibleToAdd = player.isPossibleAddPiece(PieceType.Pawn);
		}
		assertEquals(false, isPossibleToAdd);
	}
}
