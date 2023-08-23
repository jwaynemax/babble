package edu.westga.cs.babble.controllers;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileGroup;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class BabbleController {

	@FXML
	private ListView<Character> tileListView;
	
	@FXML
	private ListView<Character> wordListView;
	
	private ObservableList<Character> item;
	private ObservableList<Character> clickedItem;
	private Button addButton;

	private TileBag tileBag = new TileBag();
	private TileRack tileRack = new TileRack();
	private TileRack wordRack = new TileRack();
	private Tile tile;

	
	public void initialize() throws EmptyTileBagException {	
		item = FXCollections.observableArrayList();
		tileListView.setItems(item);

		
		while (tileRack.getNumberOfTilesNeeded() != 0) {
			tile = tileBag.drawTile();
			tileRack.append(tile);
			item.add(tile.getLetter());
		}

        
		clickedItem = FXCollections.observableArrayList();
        wordListView.setItems(clickedItem);

        
		tileListView.setOnMouseClicked(event -> {
            Character clickedTile = tileListView.getSelectionModel().getSelectedItem();
            tile = new Tile(clickedTile);
            wordRack.append(tile);
            System.out.println(wordRack.getHand());
            clickedItem.add(clickedTile);
            //remove tile from tileRack
            
            System.out.println(tileRack.tiles().indexOf(0));
        });
		
		

	}

	@FXML
	private void handleAddButtonAction(ActionEvent event) {
		item.add('c');
	}

}
