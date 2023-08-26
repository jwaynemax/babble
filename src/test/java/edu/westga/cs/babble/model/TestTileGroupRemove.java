package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveHelperClass extends TileGroup {
	public RemoveHelperClass() {}

}

class TestTileGroupRemove {
	private RemoveHelperClass tileGroup;


	@Test
	void shouldNotAllowNull() {
		this.tileGroup = new RemoveHelperClass();
		assertThrows(IllegalArgumentException.class, () -> {
			this.tileGroup.remove(null);
		});
	}
	
	@Test
	void canNotRemoveFromEmptyTileGroup() {
		this.tileGroup = new RemoveHelperClass();
		Tile tile = new Tile('c');
		assertThrows(TileNotInGroupException.class, () -> {
			this.tileGroup.remove(tile);
		});
	}
	
	@Test
	void canNotRemoveTileNotInTileGroup() {
		this.tileGroup = new RemoveHelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('c');
		this.tileGroup.append(tile2);

		assertThrows(TileNotInGroupException.class, () -> {
			this.tileGroup.remove(tile1);
		});
	}
	
	@Test
	void canRemoveOnlyTileInTileGroup() throws TileNotInGroupException {
		this.tileGroup = new RemoveHelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);


		assertThrows(TileNotInGroupException.class, () -> {
			this.tileGroup.remove(tile1);
		});
		assertEquals('E', tileGroup.tiles().get(0).getLetter());
		assertEquals('G', tileGroup.tiles().get(1).getLetter());

		this.tileGroup.remove(tile2);
		
		assertEquals('G', tileGroup.tiles().get(0).getLetter());
		assertEquals(1, tileGroup.tiles().size());
	}
	
	@Test
	void canRemoveFirstOfManyTilesFromTileGroup() throws TileNotInGroupException {
		this.tileGroup = new RemoveHelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('y');
		Tile tile6 = new Tile('z');
		Tile tile7 = new Tile('x');

		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		this.tileGroup.append(tile5);
		this.tileGroup.append(tile6);
		this.tileGroup.append(tile7);

		assertEquals('C', tileGroup.tiles().get(0).getLetter());
		
		this.tileGroup.remove(tile1);
		
		assertEquals('E', tileGroup.tiles().get(0).getLetter());
	}
	
	@Test
	void canRemoveLastOfManyTilesFromTileGroup() throws TileNotInGroupException {
		this.tileGroup = new RemoveHelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('y');
		Tile tile6 = new Tile('z');
		Tile tile7 = new Tile('x');

		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		this.tileGroup.append(tile5);
		this.tileGroup.append(tile6);
		this.tileGroup.append(tile7);
		
		assertEquals('X', tileGroup.tiles().get(tileGroup.tiles().size() - 1).getLetter());
		
		this.tileGroup.remove(tile7);
		
		assertEquals('Z', tileGroup.tiles().get(tileGroup.tiles().size() - 1).getLetter());
	}
	
	@Test
	void canRemoveMiddleOfManyTilesFromTileGroup() throws TileNotInGroupException {
		this.tileGroup = new RemoveHelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('y');
		Tile tile6 = new Tile('z');
		Tile tile7 = new Tile('x');

		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		this.tileGroup.append(tile5);
		this.tileGroup.append(tile6);
		this.tileGroup.append(tile7);
		
		assertEquals('G', tileGroup.tiles().get((tileGroup.tiles().size() / 2) - 1).getLetter());
		
		this.tileGroup.remove(tileGroup.tiles().get((tileGroup.tiles().size() / 2) - 1));
		
		assertEquals('T', tileGroup.tiles().get((tileGroup.tiles().size() / 2) - 1).getLetter());
	}
	
	@Test
	void canRemoveMultipleTilesFromTileGroup() throws TileNotInGroupException {
		this.tileGroup = new RemoveHelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		Tile tile4 = new Tile('t');
		Tile tile5 = new Tile('y');
		Tile tile6 = new Tile('z');
		Tile tile7 = new Tile('x');

		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		this.tileGroup.append(tile5);
		this.tileGroup.append(tile6);
		this.tileGroup.append(tile7);

		assertEquals('C', tileGroup.tiles().get(0).getLetter());
		assertEquals('E', tileGroup.tiles().get(1).getLetter());
		
		this.tileGroup.remove(tile1);
		this.tileGroup.remove(tile2);
		
		assertEquals('G', tileGroup.tiles().get(0).getLetter());
		assertEquals('T', tileGroup.tiles().get(1).getLetter());
	}
	
	@Test
	void doesNotRemoveDuplicateTilesFromTileGroup() throws TileNotInGroupException {
		this.tileGroup = new RemoveHelperClass();
		Tile tile1 = new Tile('c');
		Tile tile2 = new Tile('e');
		Tile tile3 = new Tile('g');
		Tile tile4 = new Tile('t');

		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
	
		assertThrows(TileNotInGroupException.class, () -> {
			this.tileGroup.remove(tile1);
			this.tileGroup.remove(tile1);
		});
	}

}
