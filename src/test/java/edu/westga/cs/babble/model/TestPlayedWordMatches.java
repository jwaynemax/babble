package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayedWordMatches {
	
	private PlayedWord playedWord = new PlayedWord();

	@Test
	void hasTilesForAMultipleLetterWord() {
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
		
		assertEquals(true, playedWord.matches("JUSTIN"));
	}
	
	@Test
	void hasTilesForASingleLetterWord() {
		Tile tile1 = new Tile('j');
		
		playedWord.append(tile1);
		
		assertEquals(true, playedWord.matches("J"));
	}
	
	@Test
	void cannotMatchWordWhenTilesAreScrambled() {
		Tile tile1 = new Tile('j');
		Tile tile2 = new Tile('u');
		Tile tile3 = new Tile('s');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('i');
		Tile tile6 = new Tile('n');

		playedWord.append(tile2);
		playedWord.append(tile5);
		playedWord.append(tile1);
		playedWord.append(tile4);
		playedWord.append(tile3);
		playedWord.append(tile6);

		assertEquals(false, playedWord.matches("JUSTIN"));
	}
	
	@Test
	void cannotMatchWordIfInsufficientTiles() {	
		Tile tile1 = new Tile('t');
		Tile tile2 = new Tile('e');

		playedWord.append(tile1);
		playedWord.append(tile2);
		
		assertEquals(false, playedWord.matches("JUSTIN"));
	}
	
	@Test
	void canMatchWordWithDuplicateLetters() {		
		Tile tile1 = new Tile('t');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('s');
		Tile tile4 = new Tile('t');

		playedWord.append(tile1);
		playedWord.append(tile2);
		playedWord.append(tile3);
		playedWord.append(tile4);

		assertEquals(true, playedWord.matches("TEST"));
	}
	
	@Test
	void nonEmptyWordShouldNotMatchEmptyText() {		
		Tile tile1 = new Tile('t');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('s');
		Tile tile4 = new Tile('t');

		playedWord.append(tile1);
		playedWord.append(tile2);
		playedWord.append(tile3);
		playedWord.append(tile4);

		assertEquals(false, playedWord.matches(""));
	}
	
	@Test
	void emptyWordShouldNotMatchEmptyText() {		
		assertEquals(false, playedWord.matches(""));
	}
	
	@Test
	void shouldNotAllowNull() {	
		assertThrows(IllegalArgumentException.class, () -> {
			assertEquals(false, playedWord.matches(null));
		});
	}

}
