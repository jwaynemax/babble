package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestTileConstructor {
	Tile tile;

	@Test
	void shouldNotAllowNonLetters() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			tile = new Tile('5');
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			tile = new Tile('!');
		});
	}
	
	@Test
	void shouldCreateOnePointTiles() {
		
		tile = new Tile('e');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('a');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('i');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('o');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('n');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('r');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('t');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('l');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('s');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('u');
		assertEquals(1, tile.getPointValue());
		
		tile = new Tile('E');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('A');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('I');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('O');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('N');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('R');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('T');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('L');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('S');
		assertEquals(1, tile.getPointValue());
		tile = new Tile('U');
		assertEquals(1, tile.getPointValue());
	}
	
	@Test
	void shouldCreateTwoPointTiles() {
		
		tile = new Tile('d');
		assertEquals(2, tile.getPointValue());
		tile = new Tile('g');
		assertEquals(2, tile.getPointValue());
		
		tile = new Tile('D');
		assertEquals(2, tile.getPointValue());
		tile = new Tile('G');
		assertEquals(2, tile.getPointValue());
	}
	
	@Test
	void shouldCreateThreePointTiles() {
		
		tile = new Tile('b');
		assertEquals(3, tile.getPointValue());
		tile = new Tile('c');
		assertEquals(3, tile.getPointValue());
		tile = new Tile('m');
		assertEquals(3, tile.getPointValue());
		tile = new Tile('p');
		assertEquals(3, tile.getPointValue());
		
		tile = new Tile('B');
		assertEquals(3, tile.getPointValue());
		tile = new Tile('C');
		assertEquals(3, tile.getPointValue());
		tile = new Tile('M');
		assertEquals(3, tile.getPointValue());
		tile = new Tile('P');
		assertEquals(3, tile.getPointValue());
	}
	
	@Test
	void shouldCreateFourPointTiles() {
		
		tile = new Tile('f');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('h');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('v');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('w');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('Y');
		assertEquals(4, tile.getPointValue());

		
		tile = new Tile('F');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('H');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('V');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('W');
		assertEquals(4, tile.getPointValue());
		tile = new Tile('Y');
		assertEquals(4, tile.getPointValue());
	}
	
	@Test
	void shouldCreateFivePointTiles() {
		
		tile = new Tile('k');
		assertEquals(5, tile.getPointValue());
		
		tile = new Tile('K');
		assertEquals(5, tile.getPointValue());
	}
	
	@Test
	void shouldCreateEightPointTiles() {
		
		tile = new Tile('j');
		assertEquals(8, tile.getPointValue());
		tile = new Tile('x');
		assertEquals(8, tile.getPointValue());
		
		tile = new Tile('J');
		assertEquals(8, tile.getPointValue());
		tile = new Tile('X');
		assertEquals(8, tile.getPointValue());
	}
	
	@Test
	void shouldCreateTenPointTiles() {
		
		tile = new Tile('q');
		assertEquals(10, tile.getPointValue());
		tile = new Tile('z');
		assertEquals(10, tile.getPointValue());
		
		tile = new Tile('Q');
		assertEquals(10, tile.getPointValue());
		tile = new Tile('Z');
		assertEquals(10, tile.getPointValue());
	}

}
