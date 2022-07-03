package com.risset.controller;

import com.risset.util.MySQLConnection;
import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.risset.Main;
import com.risset.dao.EducationDaoImpl;
import com.risset.dao.PersonalDaoImpl;
import com.risset.entity.Education;
import com.risset.entity.Personal;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EducationController implements Initializable {
    @FXML
    private ComboBox<Personal> eduCombo;
    @FXML
    private Button addNewBtn;
    @FXML
    private TableView<Education> eduTable;
    @FXML
    private TableColumn<Education, Integer> idCol;
    @FXML
    private TableColumn<Education, String> schoolCol;
    @FXML
    private TableColumn<Education, Date> sdCol;
    @FXML
    private TableColumn<Education, Date> edCol;
    @FXML
    private TableColumn<Education, Boolean> presentCol;
    @FXML
    private TableColumn<Education, String> majorCol;
    @FXML
    private TableColumn<Education, String> desCol;

    @FXML
    private StackPane contentArea;

    PersonalDaoImpl personalDao = new PersonalDaoImpl();
    EducationDaoImpl educationDao = new EducationDaoImpl();
    ObservableList<Personal> personals = FXCollections.observableArrayList();
    ObservableList<Education> educations = FXCollections.observableArrayList();

    private Education selectedEducation;

    double x, y;
    @FXML
    private TableColumn<Education, String> certificateCol;
    @FXML
    private TableColumn<Education, String> courseworkCol;

    public Education getSelectedEducation() {
        this.selectedEducation = eduTable.getSelectionModel().getSelectedItem();
        return this.selectedEducation;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            personals.addAll(personalDao.fechAll());
            educations.addAll(educationDao.fechAll());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        eduCombo.setItems(personals);
        eduTable.setItems(educations);
        idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        schoolCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSchoolName()));
        sdCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));
        edCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));
        presentCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIsPresent()));
        majorCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMajor()));
        desCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        certificateCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCertificate()));
        courseworkCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoursework()));

    }

    public void anotherScence() throws IOException {
        Parent root = FXMLLoader.load(EducationFormController.class.getResource("/com/risset/view/forms/form_education.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add New Education");
        stage.setScene(new Scene(root));
        stage.initOwner(contentArea.getScene().getWindow());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    @FXML
    private void addNewBtnAction(ActionEvent actionEvent) throws IOException {
        anotherScence();
    }

    @FXML
    private void educationTableClicked(MouseEvent mouseEvent) throws IOException {
        selectedEducation = eduTable.getSelectionModel().getSelectedItem();
        if (selectedEducation != null) {
//            anotherScence();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(EducationFormController.class.getResource("/com/risset/view/forms/form_education.fxml")));
            Parent root = loader.load();
            EducationFormController controller = loader.getController();
            controller.setEducationController(this);
            Stage stage = new Stage();
            stage.setTitle("Edit Education");
            stage.setScene(new Scene(root));
            stage.initOwner(contentArea.getScene().getWindow());
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

        }
    }

    @FXML
    private void printBtnAction(ActionEvent actionEvent) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                HashMap parameters = new HashMap();
                JasperPrint jp = JasperFillManager.fillReport("./src/main/resources/com/risset/reports/cvGenerator_education_report.jasper", parameters, MySQLConnection.createConnection());
                System.out.println("/com/risset/reports/cvGenerator_education_report.jasper");
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setVisible(true);
                return null;
            }
        };
        System.out.println("Printing...");
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();

    }
}
