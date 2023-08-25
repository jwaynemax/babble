package edu.westga.cs.babble.controllers;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

	private TileBag tileBag = new TileBag();
	private TileRack tileRack = new TileRack();
	private PlayedWord playedWord = new PlayedWord();
	private Tile tile;
	private WordDictionary word = new WordDictionary();
	private IntegerProperty intProp = new SimpleIntegerProperty();

	public void initialize() throws EmptyTileBagException {
		tileListView.setItems(tileRack.tiles());
		wordListView.setItems(playedWord.tiles());

		// cell renderer
		tileListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> param) {
				return new TileListCell();
			}
		});
		
		this.drawTilesFromBag();
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
	
	private void drawTilesFromBag() throws EmptyTileBagException {
		while (tileRack.getNumberOfTilesNeeded() != 0) {
			tile = tileBag.drawTile();
			tileRack.append(tile);
		}
	}

	@FXML
	private void playWord(ActionEvent event) throws EmptyTileBagException {
		if (word.isValidWord(playedWord.getHand()) == true) {

			score.textProperty().bind(intProp.asString());
			intProp.set(Integer.parseInt(score.getText()) + playedWord.getScore());

			playedWord.clear();
			this.drawTilesFromBag();

		} else {
			Alert message = new Alert(AlertType.INFORMATION);
			message.setTitle("Message");

			message.setHeaderText("Message");
			message.setContentText("Not a valid word");
			message.show();
		}
	}

	@FXML
	private void reset(ActionEvent event) throws TileNotInGroupException {

		for (int i = 0; i <= playedWord.tiles().size() - 1; i++) {
			tileRack.append(playedWord.tiles().get(i));
		}

		playedWord.clear();
	}

	@FXML
	private void handleTileClicked(Event event) {
		
		if(!(tileListView.getSelectionModel().getSelectedItem() == null)) {
				
			Tile clickedTile = tileListView.getSelectionModel().getSelectedItem();
			
			if(!clickedTile.equals(null)) {
				playedWord.append(clickedTile);
			}
	
			wordListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
				@Override
				public ListCell<Tile> call(ListView<Tile> param) {
					return new TileListCell();
				}
			});
	
			try {
				tileRack.remove(clickedTile);
			} catch (TileNotInGroupException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void handleYourWordTileClicked(Event event) {
		
		if(!(wordListView.getSelectionModel().getSelectedItem() == null)) {
			Tile clickedTile = wordListView.getSelectionModel().getSelectedItem();
			
			tileRack.append(clickedTile);

			tileListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
				@Override
				public ListCell<Tile> call(ListView<Tile> param) {
					return new TileListCell();
				}
			});

			try {
				playedWord.remove(clickedTile);
			} catch (TileNotInGroupException e) {
				e.printStackTrace();
			}
		}

	}

}
