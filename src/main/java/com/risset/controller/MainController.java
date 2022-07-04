package com.risset.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Stage stage;
    private Scene scence;
 
    private Parent root;
    @FXML
    private Label welcomeText;
    @FXML
    private Button homeBtn;
    @FXML
    private Button personalBtn;
    @FXML
    private Button educationBtn;
    @FXML
    private Button employmentBtn;
    @FXML
    private Button projectBtn;
    @FXML
    private Button awardBtn;
    @FXML
    private Button SkillBtn;
    @FXML
    private Button languageBtn;
    @FXML
    private StackPane contentArea;



    @FXML
    private void switchToHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/profiles.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToPersonal(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/profiles.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToEducation(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/educations.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToEmployment(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/employments.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToProject(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/projects.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToAward(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/awards.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToLanguage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/language.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToHobbie(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/hobbies.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToSkill(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/skills.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/risset/view/tables/profiles.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}