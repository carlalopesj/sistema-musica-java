package testes.trabalhofinal;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Button btnUser;

	@FXML
	private Button btnSong;

	@FXML
	private Button btnPlaylist;

	public void initialize() {
	    btnUser.setOnAction(event -> showUserScreen());
	}
	
	public void showUserScreen() {
	    try {
	    	System.out.println("Botão Usuário clicado!"); // Adicione esta linha
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("userTela.fxml"));
	        Parent telaUser = loader.load();

	        // Obtém o palco (Stage) atual
	        Stage currentStage = (Stage) btnUser.getScene().getWindow();

	        // Crie uma nova cena e defina-a no palco
	        Scene scene = new Scene(telaUser);
	        currentStage.setScene(scene);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
