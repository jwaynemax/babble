package edu.westga.cs.babble.controllers;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

public class BabbleController {

	@FXML
	private ListView<Tile> tileListView;

	@FXML
	private ListView<Tile> wordListView;

	@FXML
	private TextField score;

	private ObservableList<Tile> item;
	private ObservableList<Tile> clickedItem;

	private TileBag tileBag = new TileBag();
	private TileRack tileRack = new TileRack();
	private PlayedWord playedWord = new PlayedWord();
	private Tile tile;
	private WordDictionary word = new WordDictionary();
	IntegerProperty intProp = new SimpleIntegerProperty();

	public void initialize() throws EmptyTileBagException {
		item = FXCollections.observableArrayList();
		tileListView.setItems(item);

		// cell renderer
		tileListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> param) {
				return new TileListCell();
			}
		});

		// populate initial tiles in tile rack
		while (tileRack.getNumberOfTilesNeeded() != 0) {
			tile = tileBag.drawTile();
			tileRack.append(tile);
			item.add(tile);
		}

		// logic for when a tile is clicked to add it to your word
		clickedItem = FXCollections.observableArrayList();
		wordListView.setItems(clickedItem);

		tileListView.setOnMouseClicked(event -> {
			Tile clickedTile = tileListView.getSelectionModel().getSelectedItem();
			playedWord.append(clickedTile);
			clickedItem.add(clickedTile);
			// remove tile from tileRack
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
	private void playWord(ActionEvent event) throws EmptyTileBagException {
		if (word.isValidWord(playedWord.getHand()) == true) {

			score.textProperty().bind(intProp.asString());
			intProp.set(Integer.parseInt(score.getText()) + playedWord.getScore());

			playedWord.clear();
			clickedItem.clear();

			while (tileRack.getNumberOfTilesNeeded() != 0) {
				tile = tileBag.drawTile();
				tileRack.append(tile);
				item.add(tile);
			}

		} else {
			Alert message = new Alert(AlertType.INFORMATION);
			message.setTitle("Message");

			message.setHeaderText("Message");
			message.setContentText("Not a valid word");
			message.show();
		}
	}

}
