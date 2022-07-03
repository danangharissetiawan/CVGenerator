package com.risset.controller;

import com.risset.dao.EducationDaoImpl;
import com.risset.dao.PersonalDaoImpl;
import com.risset.entity.Education;
import com.risset.entity.Personal;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
    private Button updateBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button deleteBtn;

    Education education = new Education();

    private EducationController educationController;

    public void setEducationController(EducationController educationController) {
        this.educationController = educationController;
        if (educationController.getSelectedEducation() != null) {
            schoolTxt.setText(educationController.getSelectedEducation().getSchoolName());
            LocalDate start_date = Instant.ofEpochMilli(educationController.getSelectedEducation().getStartDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            sdDate.setValue(start_date);


            if (educationController.getSelectedEducation().getIsPresent()) {
                presentCBox.setSelected(true);
                edDate.setDisable(true);
            } else {
                LocalDate end_date = Instant.ofEpochMilli(educationController.getSelectedEducation().getEndDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                presentCBox.setSelected(false);
                edDate.setDisable(false);
                edDate.setValue(end_date);
            }
            majorTxt.setText(educationController.getSelectedEducation().getMajor());
            desTxt.setText(educationController.getSelectedEducation().getDescription());
            certifyTxt.setText(educationController.getSelectedEducation().getCertificate());
            courseTxt.setText(educationController.getSelectedEducation().getCoursework());
            personCombo.getSelectionModel().select(educationController.getSelectedEducation().getPersonal());
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
            resetBtn.setVisible(true);
            saveBtn.setVisible(false);

        } else {
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
            resetBtn.setVisible(false);
            saveBtn.setVisible(true);
        }

    }


    @FXML
    private void saveBtnAction(ActionEvent actionEvent) {
        if (schoolTxt.getText().trim().isEmpty() || sdDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
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

    @FXML
    private void updateBtnAction(ActionEvent actionEvent) {
        if (schoolTxt.getText().trim().isEmpty() || sdDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            education.setId(educationController.getSelectedEducation().getId());
            education.setSchoolName(schoolTxt.getText().trim());
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
                    try {
                        if (educationDao.updateData(education) == 1) {
                            educations.clear();
                            educations.addAll(educationDao.fechAll());
                            Stage stage = (Stage) updateBtn.getScene().getWindow();
                            stage.close();
                            System.out.println("You clicked OK!");
                        }
                    } catch (
                            SQLException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("You clicked CANCEL!");
                }



            });
        }

    }

    @FXML
    private void resetBtnAction(ActionEvent actionEvent) {
        resetData();

    }

    private void resetData() {
        schoolTxt.clear();
        sdDate.setValue(null);
        presentCBox.setSelected(false);
        edDate.setValue(null);
        majorTxt.clear();
        desTxt.clear();
        certifyTxt.clear();
        courseTxt.clear();
        personCombo.getSelectionModel().clearSelection();
        updateBtn.setVisible(false);
        deleteBtn.setVisible(false);
        saveBtn.setVisible(true);
    }

    @FXML
    private void deleteBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to delete?");
        alert.showAndWait().ifPresent(response -> {
            education.setId(educationController.getSelectedEducation().getId());
            if (response == ButtonType.OK) {
                try {

                    if (educationDao.deleteData(education) == 1) {
                        educations.clear();
                        educations.addAll(educationDao.fechAll());
                        resetData();
                        System.out.println("You clicked OK!");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }


            } else {
                System.out.println("You clicked CANCEL!");
            }

        });
    }

}
