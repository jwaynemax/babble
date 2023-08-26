package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayedWordGetScore {
	private PlayedWord playedWord = new PlayedWord();

	@Test
	void emptyWordShouldHaveScoreOfZero() {
		assertEquals(0, playedWord.getScore());
	}
	
	@Test
	void scoreAOneTileWord() {
		Tile tile = new Tile('a');
		playedWord.append(tile);
		assertEquals(1, playedWord.getScore());
	}
	
	@Test
	void scoreAWordWithMultipleDifferingTiles() {
		Tile tile1 = new Tile('j');
		Tile tile2 = new Tile('u');
		Tile tile3 = new Tile('s');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('i');
		Tile tile6 = new Tile('n');
		
		playedWord.append(tile1);
		playedWord.append(tile2);
		playedWord.append(tile3);
		playedWord.append(tile4);
		playedWord.append(tile5);
		playedWord.append(tile6);
		
		assertEquals(13, playedWord.getScore());
	}
	
	@Test
	void scoreAWordContainingDuplicateTiles() {
		Tile tile1 = new Tile('j');
		Tile tile2 = new Tile('u');
		Tile tile3 = new Tile('s');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('i');
		Tile tile6 = new Tile('n');
		Tile tile7 = new Tile('n');

		
		playedWord.append(tile1);
		playedWord.append(tile2);
		playedWord.append(tile3);
		playedWord.append(tile4);
		playedWord.append(tile5);
		playedWord.append(tile6);
		playedWord.append(tile7);

		
		assertEquals(14, playedWord.getScore());
	}

}
