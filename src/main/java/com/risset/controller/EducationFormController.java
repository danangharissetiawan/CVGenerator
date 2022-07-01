package com.risset.controller;

import com.risset.dao.EducationDaoImpl;
import com.risset.dao.PersonalDaoImpl;
import com.risset.entity.Education;
import com.risset.entity.Personal;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EducationFormController implements Initializable {
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField schoolTxt;
    @FXML
    private Label presentCheckBox;
    @FXML
    private DatePicker sdDate;
    @FXML
    private CheckBox presentCBox;
    @FXML
    private DatePicker edDate;
    @FXML
    private TextArea desTxt;
    @FXML
    private ComboBox<Personal> personCombo;
    @FXML
    private TextField majorTxt;
    @FXML
    private TextField certifyTxt;
    @FXML
    private TextField courseTxt;
    @FXML
    private Button saveBtn;

    PersonalDaoImpl personalDao = new PersonalDaoImpl();
    EducationDaoImpl educationDao = new EducationDaoImpl();
    ObservableList<Education> educations = FXCollections.observableArrayList();
    ObservableList<Personal> personals = FXCollections.observableArrayList();

    @FXML
    private void saveBtnAction(ActionEvent actionEvent) {
        if (schoolTxt.getText().trim().isEmpty() || sdDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            Education education = new Education();
            education.setSchoolName(schoolTxt.getText());
            education.setStartDate(Date.valueOf(sdDate.getValue()));
            if (presentCBox.isSelected()) {
                edDate.setDisable(true);
                education.setIsPresent(presentCBox.isSelected());
            } else {
                if (edDate.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("if you leave the present and end date fields blank, by default is presente");
                    alert.showAndWait();
                    education.setIsPresent(presentCBox.isSelected());
                } else {
                    education.setEndDate(Date.valueOf(edDate.getValue()));
                    education.setIsPresent(false);
                }
            }


            education.setMajor(majorTxt.getText());
            education.setPersonal((Personal) personCombo.getSelectionModel().getSelectedItem());
            education.setDescription(desTxt.getText());
            education.setCertificate(certifyTxt.getText());
            education.setCoursework(courseTxt.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Are you sure you want to save?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.out.println("You clicked OK!");
                } else {
                    System.out.println("You clicked CANCEL!");
                }

                try {
                    if (educationDao.addData(education) == 1) {
                        educations.clear();
                        educations.addAll(educationDao.fechAll());
                        Stage stage = (Stage) saveBtn.getScene().getWindow();
                        stage.close();
                    }
                } catch (
                        SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            });
        }


    }


    @FXML
    private void cancelBtnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            personals.addAll(personalDao.fechAll());
            educations.addAll(educationDao.fechAll());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        personCombo.setItems(personals);
    }
}
