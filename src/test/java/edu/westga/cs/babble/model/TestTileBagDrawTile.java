package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class TestTileBagDrawTile {

	@Test
	void canDrawAllTiles() throws EmptyTileBagException {
		TileBag tileBag = new TileBag();
		
		
		for (int index = 1; index <= 98; index++) {
			assertEquals(false, tileBag.isEmpty());
			tileBag.drawTile();
		}
		
		assertEquals(true, tileBag.isEmpty());
		
	}
	
	@Test
	void canNotDrawTooManyTiles() throws EmptyTileBagException {
		TileBag tileBag = new TileBag();
		
		assertThrows(EmptyTileBagException.class, () -> {
			for (int index = 1; index <= 99; index++) {
				tileBag.drawTile();
			}		
		});	
	}
	
	@Test
	void hasProperTileDistribution() throws EmptyTileBagException {
		TileBag tileBag = new TileBag();
		Tile tile;
		
		HashMap<Character, Integer> tiles = new HashMap<Character, Integer>();
		
		for (int index = 1; index <= 98; index++) {
			tile = new Tile(tileBag.drawTile().getLetter());
			if (!(tiles.containsKey(tile.getLetter()))) {
				tiles.put(tile.getLetter(), 1);

			} else {
				tiles.put(tile.getLetter(), tiles.get(tile.getLetter()) + 1);
			}
		}
		
		assertEquals(12, tiles.get('E'));
		assertEquals(9, tiles.get('A'));
		assertEquals(9, tiles.get('I'));
		assertEquals(8, tiles.get('O'));
		assertEquals(6, tiles.get('N'));
		assertEquals(6, tiles.get('R'));
		assertEquals(6, tiles.get('T'));
		assertEquals(4, tiles.get('L'));
		assertEquals(4, tiles.get('S'));
		assertEquals(4, tiles.get('U'));
		assertEquals(4, tiles.get('D'));
		assertEquals(3, tiles.get('G'));
		assertEquals(2, tiles.get('B'));
		assertEquals(2, tiles.get('C'));
		assertEquals(2, tiles.get('M'));
		assertEquals(2, tiles.get('P'));
		assertEquals(2, tiles.get('F'));
		assertEquals(2, tiles.get('H'));
		assertEquals(2, tiles.get('V'));
		assertEquals(2, tiles.get('W'));
		assertEquals(2, tiles.get('Y'));
		assertEquals(1, tiles.get('K'));
		assertEquals(1, tiles.get('J'));
		assertEquals(1, tiles.get('X'));
		assertEquals(1, tiles.get('Q'));
		assertEquals(1, tiles.get('Z'));
	}

}
