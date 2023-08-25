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

/**
 * Controller for BabbleGui
 * @version Fall 2023
 * @author Justin Maxwell
 */
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

	/**
	 * Entry point for FXML 
	 * @throws EmptyTileBagException
	 */
	public void initialize() throws EmptyTileBagException {
		this.tileListView.setItems(this.tileRack.tiles());
		this.wordListView.setItems(this.playedWord.tiles());
		
		this.drawTilesFromBag();
		
		this.cellFactory(this.tileListView);

	}
	
	/**
	 * Cell factory to display #getLetter from ListView tile objects to ListCell
	 * @param listView -- either 'your word' or 'tile' 
	 */
	private void cellFactory(ListView<Tile> listView) {
		listView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> param) {
				return new TileListCell();
			}
		});
	}

	/**
	 * Helper class for cell factory
	 */
	private class TileListCell extends ListCell<Tile> {
		@Override
		protected void updateItem(Tile item, boolean empty) {
			super.updateItem(item, empty);
			if (empty || item == null) {
				setText(null);
			} else {
				setText(Character.toString(item.getLetter()));
				setAccessibleText(Character.toString(item.getLetter()));
			}
		}
	}
	
	/**
	 * Draws tiles from bag at start of game and after word is played
	 * @throws EmptyTileBagException
	 */
	private void drawTilesFromBag() throws EmptyTileBagException {
		while (this.tileRack.getNumberOfTilesNeeded() != 0) {
			this.tile = this.tileBag.drawTile();
			this.tileRack.append(this.tile);
		}
	}
	
	/**
	 * Check value of letters in your word when play word is selected
	 * @param event play word button clicked
	 * @throws EmptyTileBagException
	 */
	@FXML
	private void playWord(ActionEvent event) throws EmptyTileBagException {
		if (this.word.isValidWord(this.playedWord.getHand())) {

			this.score.textProperty().bind(this.intProp.asString());
			this.intProp.set(Integer.parseInt(this.score.getText()) + this.playedWord.getScore());

			this.playedWord.clear();
			this.drawTilesFromBag();

		} else {
			Alert message = new Alert(AlertType.INFORMATION);
			message.setTitle("Message");

			message.setHeaderText("Message");
			message.setContentText("Not a valid word");
			message.show();
		}
	}
	
	/**
	 * Reset the tiles in your word
	 * @param event reset button clicked
	 * @throws TileNotInGroupException
	 */
	@FXML
	private void reset(ActionEvent event) throws TileNotInGroupException {

		for (int index = 0; index <= this.playedWord.tiles().size() - 1; index++) {
			this.tileRack.append(this.playedWord.tiles().get(index));
		}

		this.playedWord.clear();
	}
	
	/**
	 * Move single tile to your word when clicked
	 * @param event mouseClick for tile
	 */
	@FXML
	private void handleTileClicked(Event event) {
		
		if (!(this.tileListView.getSelectionModel().getSelectedItem() == null)) {
				
			Tile clickedTile = this.tileListView.getSelectionModel().getSelectedItem();
			
			if (!clickedTile.equals(null)) {
				this.playedWord.append(clickedTile);
			}
			
			this.cellFactory(this.wordListView);
	
			try {
				this.tileRack.remove(clickedTile);
			} catch (TileNotInGroupException error) {
				error.printStackTrace();
			}
		}
	}

	/**
	 * Move tile to tile field from your word when single tile clicked
	 * @param event tile clicked in your word list view
	 */
	@FXML
	private void handleYourWordTileClicked(Event event) {
		
		if (!(this.wordListView.getSelectionModel().getSelectedItem() == null)) {
			Tile clickedTile = this.wordListView.getSelectionModel().getSelectedItem();
			
			this.tileRack.append(clickedTile);

			this.tileListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
				@Override
				public ListCell<Tile> call(ListView<Tile> param) {
					return new TileListCell();
				}
			});

			try {
				this.playedWord.remove(clickedTile);
			} catch (TileNotInGroupException error) {
				error.printStackTrace();
			}
		}
	}
}
