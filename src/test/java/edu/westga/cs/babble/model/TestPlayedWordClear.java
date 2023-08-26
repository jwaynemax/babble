package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayedWordClear {
	
	private PlayedWord playedWord = new PlayedWord();

	@Test
	void shouldClearEmptyWord() {
		assertEquals(0, playedWord.tiles().size());
		playedWord.clear();
		assertEquals(0, playedWord.tiles().size());
	}
	
	@Test
	void shouldClearWordWithOneTile() {
		Tile tile = new Tile('c');
		
		playedWord.append(tile);
		
		assertEquals(1, playedWord.tiles().size());
		assertEquals('C', playedWord.tiles().get(0).getLetter());
		playedWord.clear();
		assertEquals(0, playedWord.tiles().size());
	}
	
	@Test
	void shouldClearWordWithManyTiles() {
		Tile tile1 = new Tile('t');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('s');
		Tile tile4 = new Tile('t');

		playedWord.append(tile1);
		playedWord.append(tile2);
		playedWord.append(tile3);
		playedWord.append(tile4);
		
		assertEquals(4, playedWord.tiles().size());
		assertEquals('S', playedWord.tiles().get(2).getLetter());
		playedWord.clear();
		assertEquals(0, playedWord.tiles().size());
	}

}
