package com.risset.controller;

import com.risset.dao.PersonalDaoImpl;
import com.risset.entity.Personal;
import com.risset.util.MySQLConnection;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    @FXML
    private TableColumn<Personal, String> linkedinCol;
    @FXML
    private TableColumn<Personal, String> githubCol;
    @FXML
    private TableColumn<Personal, String> instagramCol;
    @FXML
    private Button printBtn;

    private double x,y;

    PersonalDaoImpl personalDao = new PersonalDaoImpl();
    ObservableList<Personal> personals = FXCollections.observableArrayList();
    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            personals.addAll(personalDao.fechAll());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        profileTable.setItems(personals);
        idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        fnCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullName()));
        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        addressCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        cityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        pcCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPostalCode()).asObject());
        linkedinCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLinkedin()));
        githubCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGithub()));
        instagramCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstagram()));
        webCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWebsite()));



    }

    @FXML
    private void printBtnAction(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                HashMap parameters = new HashMap();
                JasperPrint jp = JasperFillManager.fillReport("./src/main/resources/com/risset/reports/cvGenerator_personal_report.jasper", parameters, MySQLConnection.createConnection());
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }

    @FXML
    private void addNewBtnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(FormPersonalController.class.getResource("/com/risset/view/forms/form_personal_info.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add New Personal Info");
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
