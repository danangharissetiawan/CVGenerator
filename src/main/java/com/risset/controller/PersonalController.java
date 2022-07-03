package com.risset.controller;

import com.risset.entity.Personal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalController implements Initializable {
    @FXML
    private ComboBox<Personal> profileCombo;
    @FXML
    private Button addNewBtn;
    @FXML
    private TableView<Personal> profileTable;
    @FXML
    private TableColumn<Personal, Integer> idCol;
    @FXML
    private TableColumn<Personal, String> fnCol;
    @FXML
    private TableColumn<Personal, String> emailCol;
    @FXML
    private TableColumn<Personal, String> phoneCol;
    @FXML
    private TableColumn<Personal, String> addressCol;
    @FXML
    private TableColumn<Personal, String> cityCol;
    @FXML
    private TableColumn<Personal, Integer> pcCol;
    @FXML
    private TableColumn<Personal, String> webCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
