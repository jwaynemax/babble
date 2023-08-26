package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestTileRackGetNumberOfTilesNeeded {
	
	private TileRack tileRack = new TileRack();

	@Test
	void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
		assertEquals(7, tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() {
		Tile tile1 = new Tile('c');
		
		this.tileRack.append(tile1);
		
		assertEquals(6, tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	void tileRackWithSeveralTilesShouldNeedSomeTiles() {
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('y');
		Tile tile6 = new Tile('z');
		
		this.tileRack.append(tile1);
		this.tileRack.append(tile2);
		this.tileRack.append(tile3);
		this.tileRack.append(tile4);
		this.tileRack.append(tile5);
		this.tileRack.append(tile6);
		
		assertEquals(1, tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	void fullRackNeedsZeroTiles() {
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('y');
		Tile tile6 = new Tile('z');
		Tile tile7 = new Tile('x');
		
		this.tileRack.append(tile1);
		this.tileRack.append(tile2);
		this.tileRack.append(tile3);
		this.tileRack.append(tile4);
		this.tileRack.append(tile5);
		this.tileRack.append(tile6);
		this.tileRack.append(tile7);
		
		assertEquals(0, tileRack.getNumberOfTilesNeeded());
	}

}
