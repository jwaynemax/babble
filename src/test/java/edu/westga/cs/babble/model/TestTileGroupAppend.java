package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class HelperClass extends TileGroup {
	public HelperClass() {}

}

class TestTileGroupAppend {
	
	private HelperClass tileGroup;

	@Test
	void shouldNotAllowNull() {
		tileGroup = new HelperClass();
		assertThrows(IllegalArgumentException.class, () -> {
			tileGroup.append(null);
		});
	}
	
	@Test
	void emptyGroupShouldBeEmpty() {
		tileGroup = new HelperClass();
		assertEquals(0, tileGroup.tiles().size());
	}
	
	@Test
	void shouldHaveOneTileInTileGroup() {
		tileGroup = new HelperClass();
		Tile tile = new Tile('c');
		tileGroup.append(tile);
		assertEquals(1, tileGroup.tiles().size());
		assertEquals('C', tileGroup.tiles().get(0).getLetter());
	}
		
	@Test
	void shouldHaveManyTilesInTileGroup() {
		tileGroup = new HelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('r');
		Tile tile4 = new Tile('z');
		Tile tile5 = new Tile('q');
		Tile tile6 = new Tile('t');
		Tile tile7 = new Tile('s');
		tileGroup.append(tile1);
		tileGroup.append(tile2);
		tileGroup.append(tile3);
		tileGroup.append(tile4);
		tileGroup.append(tile5);
		tileGroup.append(tile6);
		tileGroup.append(tile7);
		
		assertEquals(7, tileGroup.tiles().size());
		assertEquals('C', tileGroup.tiles().get(0).getLetter());
		assertEquals('E', tileGroup.tiles().get(1).getLetter());
		assertEquals('R', tileGroup.tiles().get(2).getLetter());
		assertEquals('Z', tileGroup.tiles().get(3).getLetter());
		assertEquals('Q', tileGroup.tiles().get(4).getLetter());
		assertEquals('T', tileGroup.tiles().get(5).getLetter());
		assertEquals('S', tileGroup.tiles().get(6).getLetter());
	}
	
	@Test
	void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		tileGroup = new HelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('r');
		Tile tile4 = new Tile('z');
		Tile tile5 = new Tile('q');
		Tile tile6 = new Tile('t');
		Tile tile7 = new Tile('c');
		tileGroup.append(tile1);
		tileGroup.append(tile2);
		tileGroup.append(tile3);
		tileGroup.append(tile4);
		tileGroup.append(tile5);
		tileGroup.append(tile6);
		tileGroup.append(tile7);
		
		assertEquals(7, tileGroup.tiles().size());
		assertEquals('C', tileGroup.tiles().get(0).getLetter());
		assertEquals('E', tileGroup.tiles().get(1).getLetter());
		assertEquals('R', tileGroup.tiles().get(2).getLetter());
		assertEquals('Z', tileGroup.tiles().get(3).getLetter());
		assertEquals('Q', tileGroup.tiles().get(4).getLetter());
		assertEquals('T', tileGroup.tiles().get(5).getLetter());
		assertEquals('C', tileGroup.tiles().get(6).getLetter());
	}
	
	@Test
	void canNotAddSameTileTwice() {
		tileGroup = new HelperClass();
		Tile tile1 = new Tile('c');
		
		assertThrows(IllegalArgumentException.class, () -> {
			tileGroup.append(tile1);
			tileGroup.append(tile1);
		});
		
	}

}
