package trabalhofinal.sistema;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {
	
	//Usuários
	
	@FXML
	private TextField tfUserNome, tfUserEmail, tfUserNomeBuscar,tfUserID, tfUserNovoNome, tfUserNovoEmail;
	@FXML
	private Button btnUserCad, btnUserBuscar, btnUserAtua, btnUserDel;
	@FXML
	private Label lUserStatus, lUserInfo;
	@FXML
	private ListView<User> lvUsers;
	
	ArrayList<User> users = new ArrayList<>();
	
	//Músicas
	
	@FXML
	private TextField tfSongTitulo, tfSongArtista, tfSongAno, tfSongGenero, tfSongCod, tfSongNovoT, tfSongNovoA, tfSongNovoAL, tfSongNovoG, tfSongNomeBuscar;
	@FXML
	private Button btnSongAdd, btnSongBuscar, btnSongAtua, btnSongRem;
	@FXML
	private Label lSongStatus, lSongInfo;
	@FXML
	private ListView<Song> lvSongs;
	
	ArrayList<Song> songs = new ArrayList<>();
	
	//Playlists
	
	@FXML
	private TextField tfPlstNome, tfPlstIdProp, tfPlstNomeBuscar, tfPlstCod, tfPlstNovoNome, tfPlstNovoIdProp, tfPlstAddSong, tfPlstRemSong;
	@FXML
	private Button btnPlstAdd, btnPlstBuscar, btnPlstAtua, btnPlstRem, btnPlstAddSong, btnPlstRemSong;
	@FXML
	private Label lPlstStatus, lPlstInfo;
	@FXML
	private ListView<Playlist> lvPlaylists;
	
	ArrayList<Playlist> playlists = new ArrayList<>();
	
	public void initialize() {
		//Usuários
		//Mostra atualizações na lista em tempo real
		// Configure a ObservableList e defina a ListView
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        lvUsers.setItems(observableUsers);

        // Configure a fábrica de células personalizada
    	
        lvUsers.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);

                if (empty || user == null) {
                    setText(null);
                } else {
                    // Exiba o nome e o ID do usuário
                    setText("ID: " + user.getId() + " - " + user.getNome());
                }
            }
        });
        
        //Músicas
        ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
        lvSongs.setItems(observableSongs);
        
        lvSongs.setCellFactory(param -> new ListCell<Song>() {
            @Override
            protected void updateItem(Song song, boolean empty) {
                super.updateItem(song, empty);

                if (empty || song == null) {
                    setText(null);
                } else {
                    setText("COD: " + song.getCod() + " - " + song.getTitulo());
                }
            }
        });
	}
	
	//-----------------------Parte de Usuários-------------------------------------
	
	@FXML
	private void botaoUserCadastrar(ActionEvent event) {
		System.out.println("Cadastrar foi clicado!");
		
		String userNome = tfUserNome.getText();
		String userEmail = tfUserEmail.getText();
		
		if(userNome.isEmpty() || userEmail.isEmpty()) {
			lUserStatus.setText("Preencha todos os campos!");
			return;
		} else {
			User novoUser = new User(userNome, userEmail);
			users.add(novoUser);
			lUserStatus.setText("Usuário cadastrado com sucesso.");
			
			//Chamando da função inicialize para atualizar a lista de visualização automaticamente
			ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
		    lvUsers.setItems(observableUsers);
			 
			tfUserNome.clear();
			tfUserEmail.clear(); 	
		}
	}
	
	@FXML 
	private void botaoUserBuscar(ActionEvent event) {
		System.out.println("Buscar foi clicado!");
		
		String userNome = tfUserNomeBuscar.getText();
		boolean userEncontrado = false;
		
		if(userNome.isEmpty()) {
			lUserStatus.setText("Preencha o campo do nome!");
			return;
		} else {
			for (User user : users) {
				if (user.getNome().equalsIgnoreCase(userNome)) {
					userEncontrado = true;
					
					lUserStatus.setText("Usuário encontrado.");
					lUserInfo.setText("\nNome: " + user.getNome() + "\nEmail: " + user.getEmail() + "\nID: " + user.getId());
					
				}
			}
		}
		
		if (!userEncontrado) {
			lUserStatus.setText("Usuário não encontrado.");
		}
		
		tfUserNomeBuscar.clear();
	}
	
	
	@FXML
	private void botaoUserAtualizar(ActionEvent event) {
		System.out.println("Atualizar foi clicado!");
		
		String userNovoNome = tfUserNovoNome.getText();
		String userNovoEmail = tfUserNovoEmail.getText();
		String strUserID = tfUserID.getText();
		boolean userEncontrado = false;
		
		if (strUserID.isEmpty()) {
			lUserStatus.setText("Preencha o campo do ID");
			return;
		} else {
			int userID = Integer.parseInt(strUserID);
			for(User user : users) {
				if(user.getId() == userID) {
					userEncontrado = true;
					
					if (userNovoNome.isEmpty()) {
						user.setNome(user.getNome());
					} else {
						user.setNome(userNovoNome);
						
						//Chamando da função inicialize para atualizar a lista de visualização automaticamente
						ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
					    lvUsers.setItems(observableUsers);
						lUserStatus.setText("Atualização realizada com sucesso.");
					}
					
					if (userNovoEmail.isEmpty()) {
						user.setEmail(user.getEmail());
					} else {
						user.setEmail(userNovoEmail);
						lUserStatus.setText("Atualização realizada com sucesso.");
					}
				}
			}
			
			tfUserNovoNome.clear();
			tfUserNovoEmail.clear();
			tfUserID.clear();
		}
		
		if(!userEncontrado) {
			lUserStatus.setText("Usuário não encontrado.");
		}
	}
	
	@FXML
	private void botaoUserDeletar(ActionEvent event) {
		System.out.println("Deletar foi clicado!");
		
		String strUserID = tfUserID.getText();
		boolean userEncontrado = false;
		
		if (strUserID.isEmpty()) {
			lUserStatus.setText("Preencha o campo do ID");
			return;
		} else {
			int userID = Integer.parseInt(strUserID);
			User userRemover = null;
			
			for(User user : users) {
				if(user.getId() == userID) {
					userEncontrado = true;
					userRemover = user;
				}
			}
			
			if(userRemover != null) {
				users.remove(userRemover);
				lUserStatus.setText("Usuário deletado com sucesso.");
			
				//Chamando da função inicialize para atualizar a lista de visualização automaticamente
				ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
			    lvUsers.setItems(observableUsers);
			
			    tfUserID.clear();
			}
			
		}
		
		if (!userEncontrado) {
			lUserStatus.setText("Usuário não encontrado.");
		}
		
		
	}
	
	//-----------------------Parte de Músicas-------------------------------------
	
	@FXML
	private void botaoSongAdicionar(ActionEvent event) {
		System.out.println("Adicionar foi clicado!");
		
		String songTitulo = tfSongTitulo.getText();
		String songArtista = tfSongArtista.getText();
		String strSongAno = tfSongAno.getText();
		String songGenero = tfSongGenero.getText();
		
		if(songTitulo.isEmpty() || songArtista.isEmpty() || strSongAno.isEmpty() || songGenero.isEmpty()) {
			lSongStatus.setText("Preencha todos os campos!");
			return;
		} else {
			int songAno = Integer.parseInt(strSongAno);
			
			Song novoSong = new Song(songTitulo, songArtista, songAno, songGenero);
			songs.add(novoSong);
			
			lSongStatus.setText("Música adicionada com sucesso.");
			
			ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
	        lvSongs.setItems(observableSongs);
			
	        tfSongTitulo.clear();
	        tfSongArtista.clear();
	        tfSongAno.clear();
	        tfSongGenero.clear();
		}
	}
	
	@FXML
	private void botaoSongBuscar(ActionEvent event) {
		System.out.println("Buscar foi clicado!");
		
		String nomeSong = tfSongNomeBuscar.getText();
		boolean songEncontrado = false;
		
		if (nomeSong.isEmpty()) {
			lSongStatus.setText("Preencha o campo do código!");
			return;
		} else {
			for (Song song : songs) {
				if (song.getTitulo().equalsIgnoreCase(nomeSong)) {
					songEncontrado = true;
					
					lSongStatus.setText("Música encontrada.");
					lSongInfo.setText("\nTítulo: " + song.getTitulo() + "\nArtista: " + song.getArtista() + "\nAno de Lançamento: " + song.getAnoLancamento() + "\nGênero: " + song.getGenero() + "\nCOD: " + song.getCod());
				}
			}
			tfSongNomeBuscar.clear();
		}
		
		if (!songEncontrado) {
			lSongStatus.setText("Música não encontrada.");
		}
	}
	
	@FXML
	private void botaoSongAtualizar(ActionEvent event) {
	    System.out.println("Atualizar foi clicado!");

	    String srtSongCod = tfSongCod.getText();
	    String novoTitulo = tfSongNovoT.getText();
	    String novoArtista = tfSongNovoA.getText();
	    String strNovoAno = tfSongNovoAL.getText();
	    String novoGenero = tfSongNovoG.getText();

	    if (srtSongCod.isEmpty()) {
	        lSongStatus.setText("Preencha o campo de código!");
	        return;
	    }

	    int songCod = Integer.parseInt(srtSongCod);

	    for (Song song : songs) {
	        if (song.getCod() == songCod) {
	            boolean algumaAtualizacaoRealizada = false;

	            if (!novoTitulo.isEmpty()) {
	                song.setTitulo(novoTitulo);
	                algumaAtualizacaoRealizada = true;
	            }

	            if (!novoArtista.isEmpty()) {
	                song.setArtista(novoArtista);
	                algumaAtualizacaoRealizada = true;
	            }

	            if (!strNovoAno.isEmpty()) {
	                int songAno = Integer.parseInt(strNovoAno);
	                song.setAnoLancamento(songAno);
	                algumaAtualizacaoRealizada = true;
	            }

	            if (!novoGenero.isEmpty()) {
	                song.setGenero(novoGenero);
	                algumaAtualizacaoRealizada = true;
	            }

	            if (algumaAtualizacaoRealizada) {
	                lSongStatus.setText("Atualização realizada com sucesso.");
	                ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
	                lvSongs.setItems(observableSongs);

	                tfSongNovoT.clear();
	                tfSongNovoA.clear();
	                tfSongNovoAL.clear();
	                tfSongNovoG.clear();
	            } else {
	                lSongStatus.setText("Preencha pelo menos um campo de atualização.");
	            }

	            tfSongCod.clear();
	            return; // Saia do loop se a música for encontrada
	        }
	    }

	    lSongStatus.setText("Música não encontrada.");
	    tfSongCod.clear();
	}

	
	@FXML
	private void botaoSongRemover(ActionEvent event) {
		System.out.println("Remover foi clicado!");
		
		String strSongCod = tfSongCod.getText();	
		boolean songEncontrado = false;
		
		if (strSongCod.isEmpty()) {
			lSongStatus.setText("Preencha o campo do código!");
			return;
		} else {
			int songCod = Integer.parseInt(strSongCod);
			Song songParaRemover = null;
			
			for (Song song : songs) {
				if (song.getCod() == songCod) {
					songEncontrado = true;
					songParaRemover = song;
				}
			}
			
			if(songParaRemover != null) {
				songs.remove(songParaRemover);
				lSongStatus.setText("Música removida com sucesso.");
				
				ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
				lvSongs.setItems(observableSongs);
			}
			
			if(!songEncontrado) {
				lSongStatus.setText("Música não encontrada.");
			}
			tfSongCod.clear();
		}
	}
	
	//-----------------------Parte de Playlists-------------------------------------
	
	
}

//Fazer aparecer mais de um nome - tabela
//Mover strings para dentro das chaves - economizar espaço memória
