package com.risset.controller;

import com.risset.entity.Personal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonalController {
    @FXML
    private ComboBox<Personal> profileCombo;
    @FXML
    private Button addNewBtn;
    @FXML
    private TableView<Personal> profileTable;
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn fnCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn addressCol;
    @FXML
    private TableColumn cityCol;
    @FXML
    private TableColumn pcCol;
    @FXML
    private TableColumn webCol;
}
