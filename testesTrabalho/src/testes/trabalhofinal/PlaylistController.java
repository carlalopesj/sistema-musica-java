package testes.trabalhofinal;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlaylistController {
	
	//Tentando acessar Playlists
	private MainController mainController;
	
	public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // Para acessar a lista de usuários:
    public ArrayList<User> getUsers() {
        return mainController.getUsers();
    }
	
	@FXML
	private TextField tfPlstNomeCr, tfPlstIdPropCr, tfPlstNomeBuscar, tfPlstCodAtua, tfPlstNovoNome, tfPlstNovoProp, tfPlstCodMusAdd, tfPlstCodMusRem, tfPlstCodDel;
	@FXML
	private Button btnPlstCriar, btnPlstBuscar, btnPlstAtua, btnPlstAddMus, btnPlstRemMus, btnPlstDel, btnVoltarPlstPrinc;
	@FXML
	private Label lPlstStatusCriar, lPlstStatusLista, lPlstStatusBuscar, lPlstInfos, lPlstStatusAtua, lPlstStatusAddMus, lPlstStatusRemMus, lPlstStatusDel;
	@FXML
	private ListView<Playlist> lvPlstLista;
	
	private ArrayList<Playlist> playlists;
	//private ArrayList<User> users;
	private ArrayList<Song> songs;
	
	public PlaylistController() {
        playlists = new ArrayList<>();  // Inicialize a lista de playlists no construtor
    }
	
	public void initialize() {
		
		btnVoltarPlstPrinc.setOnAction(event -> voltarTelaPrincipal());
    	
    	// Configure a ObservableList e defina a ListView
        ObservableList<Playlist> observablePlaylists = FXCollections.observableArrayList(playlists);
        lvPlstLista.setItems(observablePlaylists);

        // Configure a fábrica de células personalizada
    	
        lvPlstLista.setCellFactory(param -> new ListCell<Playlist>() {
            @Override
            protected void updateItem(Playlist playlist, boolean empty) {
                super.updateItem(playlist, empty);

                if (empty || playlist == null) {
                    setText(null);
                } else {
                    // Exiba o nome e o ID do usuário
                    setText("COD: " + playlist.getCod() + " - " + playlist.getNome());
                }
            }
        });
	}
	
	@FXML
	private void botaoCriarPlaylist(ActionEvent event) {
	    System.out.println("Criar foi clicado!");

	    String strPlstNome = tfPlstNomeCr.getText();
	    String strnPlstId = tfPlstIdPropCr.getText();
	    boolean userEncontrado = false;

	    if (getUsers().isEmpty()) {
	        lPlstStatusCriar.setText("Não há proprietários disponíveis.");
	    } else {
	        if (strPlstNome.isEmpty() || strnPlstId.isEmpty()) {
	            lPlstStatusCriar.setText("Preencha todos os campos!");
	        } else {
	            int plstIdPro = Integer.parseInt(strnPlstId);
	            User proprietario = null;

	            for (User user : getUsers()) {
	                if (user.getId() == plstIdPro) {
	                    lPlstStatusCriar.setText("Usuário encontrado.");
	                    userEncontrado = true;
	                    proprietario = user;  // Atribua o usuário encontrado a "proprietario"
	                    break;
	                }
	            }

	            if (proprietario != null) {
	                Playlist novaPlaylist = new Playlist(strPlstNome, proprietario);
	                playlists.add(novaPlaylist);

	                lPlstStatusCriar.setText("Playlist criada com sucesso.");

	                ObservableList<Playlist> observablePlaylists = FXCollections.observableArrayList(playlists);
	                lvPlstLista.setItems(observablePlaylists);
	            }
	        }
	    }
	    
	    if(!userEncontrado) {
	    	lPlstStatusCriar.setText("Usuário não encontrado.");
	    }

	    tfPlstNomeCr.clear();
	    tfPlstIdPropCr.clear();
	}

	
	
	

	//Botão de voltar para tela inicial
	public void voltarTelaPrincipal() {
		 try {
		    System.out.println("Botão Usuário clicado!");
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
		    Parent telaInicio = loader.load();

		    // Obtém o palco (Stage) atual
		    Stage currentStage = (Stage) btnVoltarPlstPrinc.getScene().getWindow();

		    // Crie uma nova cena e defina-a no palco
		    Scene scene = new Scene(telaInicio);
		    currentStage.setScene(scene);

		 } catch (IOException e) {
		        e.printStackTrace();
		 }
	}
	
	
	
	
	
	
}



//Aparecer informações antes de tal ação. Ex: antes de adicionar a música mostrar as info dela