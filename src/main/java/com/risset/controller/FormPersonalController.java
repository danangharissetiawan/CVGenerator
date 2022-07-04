package com.risset.controller;

import com.risset.dao.PersonalDaoImpl;
import com.risset.entity.Education;
import com.risset.entity.Personal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FormPersonalController implements Initializable {
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button updateBtn;

    PersonalDaoImpl personalDao = new PersonalDaoImpl();
    ObservableList personals = FXCollections.observableArrayList();
    @FXML
    private TextField fnTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField cityTxt;
    @FXML
    private TextField pcTxt;
    @FXML
    private TextField linkedinTxt;
    @FXML
    private TextField githubTxt;
    @FXML
    private TextField websiteTxt;
    @FXML
    private TextField instagramTxt;

    Personal personal = new Personal();

    @FXML
    private void saveBtnAction(ActionEvent actionEvent) {
        if (fnTxt.getText().trim().isEmpty() || emailTxt.getText().trim().isEmpty() || addressTxt.getText().trim().isEmpty() || phoneTxt.getText().trim().isEmpty() || cityTxt.getText().trim().isEmpty() || pcTxt.getText().trim().isEmpty()|| websiteTxt.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            personal.setFullName(fnTxt.getText());
            personal.setEmail(emailTxt.getText());
            personal.setAddress(addressTxt.getText());
            personal.setPhone(phoneTxt.getText());
            personal.setCity(cityTxt.getText());
            personal.setPostalCode(Integer.parseInt(pcTxt.getText()));
            personal.setLinkedin(linkedinTxt.getText());
            personal.setGithub(githubTxt.getText());
            personal.setInstagram(instagramTxt.getText());
            personal.setWebsite(websiteTxt.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Are you sure you want to save?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        if (personalDao.addData(personal) == 1) {
                            personals.clear();
                            personals.addAll(personalDao.fechAll());
                            Stage stage = (Stage) saveBtn.getScene().getWindow();
                            stage.close();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Information");
                    alert1.setHeaderText("Information");
                    alert1.setContentText("Personal information saved successfully");
                    alert1.showAndWait();
                }
            });
        }
    }

    @FXML
    private void cancelBtnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void deleteBtnAction(ActionEvent actionEvent) {
    }

    @FXML
    private void resetBtnAction(ActionEvent actionEvent) {
    }

    @FXML
    private void updateBtnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            personals.addAll(personalDao.fechAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
