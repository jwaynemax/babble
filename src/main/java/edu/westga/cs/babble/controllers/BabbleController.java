package edu.westga.cs.babble.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class BabbleController {
	
	 @FXML
	    private ListView<String> listView;

	    @FXML
	    private Button addButton;

	    private ObservableList<String> items;

	    public void initialize() {
	        items = FXCollections.observableArrayList();
	        listView.setItems(items);
	        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    }

	    @FXML
	    private void handleAddButtonAction(ActionEvent event) {
	        items.add("New Item");
	    }

}
