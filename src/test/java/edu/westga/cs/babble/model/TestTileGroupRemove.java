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

}
