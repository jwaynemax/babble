package edu.westga.cs.babble.controllers;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileGroup;
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
	private ListView<Character> listView;
	private ObservableList<Character> item;
	private Button addButton;

	private TileBag tileBag = new TileBag();
	private TileRack tileRack = new TileRack();

	public void initialize() throws EmptyTileBagException {

		while (tileRack.getNumberOfTilesNeeded() != 0) {
			tileRack.append(tileBag.drawTile());
		}

		item = FXCollections.observableArrayList();
		int i = 0;
		while (i != tileRack.tiles().size()) {
			item.add(tileRack.tiles().get(i).getLetter());
			i++;
		}

		listView.setItems(item);
		
        listView.setOnMouseClicked(event -> {
            Character selectedTile = listView.getSelectionModel().getSelectedItem();
            System.out.println(selectedTile);
        });

		//maybe remove?
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	@FXML
	private void handleAddButtonAction(ActionEvent event) {
		item.add('c');
	}

}
