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
import javafx.scene.control.ListCell;
import javafx.util.Callback;



public class BabbleController {

	@FXML
	private ListView<Tile> tileListView;
	
	@FXML
	private ListView<Tile> wordListView;
	
	private ObservableList<Tile> item;
	private ObservableList<Tile> clickedItem;
	private Button addButton;

	private TileBag tileBag = new TileBag();
	private TileRack tileRack = new TileRack();
	private TileRack wordRack = new TileRack();
	private Tile tile;

	
	public void initialize() throws EmptyTileBagException {	
		item = FXCollections.observableArrayList();
		tileListView.setItems(item);
		
		tileListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
            @Override
            public ListCell<Tile> call(ListView<Tile> param) {
                return new TileListCell();
            }
        });

		
		while (tileRack.getNumberOfTilesNeeded() != 0) {
			tile = tileBag.drawTile();
			tileRack.append(tile);
			item.add(tile);
		}

        
		clickedItem = FXCollections.observableArrayList();
        wordListView.setItems(clickedItem);

        
		tileListView.setOnMouseClicked(event -> {
            Tile clickedTile = tileListView.getSelectionModel().getSelectedItem();
            wordRack.append(clickedTile);
            System.out.println(wordRack.getHand());
            clickedItem.add(clickedTile);
            //remove tile from tileRack
            wordListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
                @Override
                public ListCell<Tile> call(ListView<Tile> param) {
                    return new TileListCell();
                }
            });
            
            try {
				tileRack.remove(clickedTile);
				item.remove(clickedTile);
			} catch (TileNotInGroupException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
		
		

	}
	
	private class TileListCell extends ListCell<Tile> {
        @Override
        protected void updateItem(Tile item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(Character.toString(item.getLetter())); // Display the string representation of the Word object
            }
        }
    }

	@FXML
	private void handleAddButtonAction(ActionEvent event) {
		item.add(tile);
	}

}
