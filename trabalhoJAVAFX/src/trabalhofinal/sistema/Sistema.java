package trabalhofinal.sistema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Sistema extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
	    try {
	        // Carrega a interface gráfica a partir do arquivo FXML
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("telaPrimaria.fxml"));
	        Parent root = loader.load();

	        MainController controller = loader.getController();
	        controller.initialize(); // Adicione esta linha
	        // Crie uma cena e defina a raiz
	        Scene scene = new Scene(root);
	        
	        // Configura a janela
	        primaryStage.setTitle("Aplicativo");
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
		System.out.println("Testes");

	}

}
