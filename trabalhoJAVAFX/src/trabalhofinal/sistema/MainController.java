package trabalhofinal.sistema;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Button btnStart;
	
	public void initialize() {
		
		btnStart.setOnAction(event -> mostrarTelaSecundaria());
		
	}
	
	public void mostrarTelaSecundaria() {
	    try {
	        System.out.println("Botão Começar clicado!");
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("telaSecundaria.fxml"));
	        Parent telaSec = loader.load();

	        // Crie uma nova cena e defina-a na janela secundária
	        Scene scene = new Scene(telaSec);
	        Stage secondaryStage = new Stage();

	        // Defina o palco pai (janela principal)
	        Stage primaryStage = (Stage) btnStart.getScene().getWindow();
	        secondaryStage.initOwner(primaryStage);

	        // Defina a cena na janela secundária
	        secondaryStage.setScene(scene);

	        // Centralize a janela secundária no palco pai
	        secondaryStage.centerOnScreen();

	        // Exiba a janela secundária
	        secondaryStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	

}
