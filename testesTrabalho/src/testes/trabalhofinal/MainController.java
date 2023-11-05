package testes.trabalhofinal;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
	
	//Tentando acessar as listas
	private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList();
    
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
	
	@FXML
	private Button btnUser;

	@FXML
	private Button btnSong;

	@FXML
	private Button btnPlaylist;

	public void initialize() {
	    btnUser.setOnAction(event -> showUserScreen());
	    btnSong.setOnAction(event -> showSongScreen());
	    btnPlaylist.setOnAction(event -> showPlaylistScreen());
	}
	
	public void showUserScreen() {
	    try {
	    	System.out.println("Botão Usuário clicado!");
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
	
	public void showSongScreen() {
        try {
        	System.out.println("Botão Música clicado!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("songTela.fxml"));
            Parent telaSong = (Parent) loader.load();

            // Obtém o palco (Stage) atual
	        Stage currentStage = (Stage) btnSong.getScene().getWindow();

	        // Crie uma nova cena e defina-a no palco
	        Scene scene = new Scene(telaSong);
	        currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void showPlaylistScreen() {
	    try {
	    	System.out.println("Botão Playlist clicado!");
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("playlistTela.fxml"));
	        Parent telaPlyalist = loader.load();

	        // Obtém o palco (Stage) atual
	        Stage currentStage = (Stage) btnPlaylist.getScene().getWindow();

	        // Crie uma nova cena e defina-a no palco
	        Scene scene = new Scene(telaPlyalist);
	        currentStage.setScene(scene);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	
}
