package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField nameTextField;  

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = nameTextField.getText().trim();

        if (username.isEmpty()) {
        	test
           
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Name");
            alert.setHeaderText(null);
            alert.setContentText("Name cannot be empty. Please enter a valid name.");
            alert.showAndWait();
            return; 
        }
        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainGameScene.fxml"));
        root = loader.load();

        MainGameController engine = loader.getController();
        engine.startGame(username);
       

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
