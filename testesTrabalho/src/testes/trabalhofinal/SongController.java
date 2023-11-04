package testes.trabalhofinal;

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

public class SongController {

	@FXML
	private TextField tfSongTiAdd, tfSongArAdd, tfSongAlAdd, tfSongGeAdd, tfSongCodBuscar, tfSongCodAtua, tfSongNovoTi, tfSongNovoAr, tfSongNovoAl, tfSongNovoGe, tfSongCodRem;
	@FXML
	private Button btnSongAdd, btnSongBuscar, btnSongAtua, btnSongRem;
	@FXML
	private Label lSongStatusAdd, lSongStatusListar, lSongStatusBuscar, lSongInfos, lSongStatusAtua, lSongStatusRem;
	@FXML
	private ListView<Song> lvSongLista;
	
	ArrayList<Song> songs = new ArrayList<>();
	
	public void initialize() {
	    	
	    	// Configure a ObservableList e defina a ListView
	        ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
	        lvSongLista.setItems(observableSongs);
	
	        // Configure a fábrica de células personalizada
	    	
	        lvSongLista.setCellFactory(param -> new ListCell<Song>() {
	            @Override
	            protected void updateItem(Song song, boolean empty) {
	                super.updateItem(song, empty);
	
	                if (empty || song == null) {
	                    setText(null);
	                } else {
	                    // Exiba o nome e o ID do usuário
	                    setText("COD: " + song.getCod() + " - " + song.getTitulo());
	                }
	            }
	        });
	}

	
	@FXML
	private void botaoSongAdicionar(ActionEvent event) {
		System.out.println("Adicionar - foi clicado!");
		
		String tituloSong = tfSongTiAdd.getText();
		String artistaSong = tfSongArAdd.getText();
		String anoSong = tfSongAlAdd.getText();
		String generoSong = tfSongGeAdd.getText();
		
		if(tituloSong.isEmpty() || artistaSong.isEmpty() || anoSong.isEmpty() || generoSong.isEmpty()) {
			lSongStatusAdd.setText("Preencha todos os campos!");
			return;
		} else {
			int anoLancSong = Integer.parseInt(anoSong);
			Song novoSong = new Song(tituloSong, artistaSong, anoLancSong, generoSong);
			songs.add(novoSong);
			lSongStatusAdd.setText("Música adicionada com sucesso.");
			
			//Chamando da função inicialize para atualizar a lista de visualização automaticamente
			ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
			lvSongLista.setItems(observableSongs);
		}
		
		tfSongTiAdd.clear();
		tfSongArAdd.clear();
		tfSongAlAdd.clear();
		tfSongGeAdd.clear();	
	}
	
	@FXML
	private void botaoSongBuscar(ActionEvent event) {
		System.out.println("Buscar - foi clicado!");
		
		String stringSongCod = tfSongCodBuscar.getText();
		
		if(stringSongCod.isEmpty()) {
			lSongStatusBuscar.setText("Preencha o campo de código!");
			lSongInfos.setText("");
		} else {
			int songCod = Integer.parseInt(stringSongCod);
			boolean songEncontrado = false;
			
			for(Song song : songs) {
				if(song.getCod() == songCod) {
					songEncontrado = true;
					lSongStatusBuscar.setText("Música encontrada.");
					lSongInfos.setText("Informações\nTítulo: " + song.getTitulo() + "\nArtista: " + song.getArtista() + "\nAno de Lançamento: " + song.getAnoLancamento() + "\nGênero: " + song.getGenero());
				}
			}
			
			if(!songEncontrado) {
				lSongStatusBuscar.setText("Música não encontrada.");
				lSongInfos.setText("");
			}
			tfSongCodBuscar.clear();
		}
	}
	
	@FXML
	private void botaoSongAtua(ActionEvent event) {
		System.out.println("Atualizar - foi clicado!");
		
		String stringSongCod = tfSongCodAtua.getText();
		
		if(stringSongCod.isEmpty()) {
			lSongStatusAtua.setText("Preencha o campo de código!");
		} else {
			int songCod = Integer.parseInt(stringSongCod);
			boolean songEncontrado = false;
			
			for(Song song : songs) {
				if(song.getCod() == songCod) {
					songEncontrado = true;
					lSongStatusAtua.setText("Música encontrada.");
					
					if(tfSongNovoTi.getText().isEmpty()) {
						song.setTitulo(song.getTitulo());
					} else {
						String novoTitulo = tfSongNovoTi.getText();
						song.setTitulo(novoTitulo);
						
						//Chamando da função inicialize para atualizar a lista de visualização automaticamente
						ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
						lvSongLista.setItems(observableSongs);
						
					}
					
					if(tfSongNovoAr.getText().isEmpty()) {
						song.setArtista(song.getArtista());
					} else {
						String novoArtista = tfSongNovoAr.getText();
						song.setArtista(novoArtista);
						lSongStatusAtua.setText("Atualização realizada com sucesso.");

					}
					
					if(tfSongNovoAl.getText().isEmpty()) {
						song.setAnoLancamento(song.getAnoLancamento());
					} else {
						String strNovoAno = tfSongNovoAl.getText();
						int novoAno = Integer.parseInt(strNovoAno);
						song.setAnoLancamento(novoAno);
						lSongStatusAtua.setText("Atualização realizada com sucesso.");
						
					}
					
					if(tfSongNovoGe.getText().isEmpty()) {
						song.setGenero(song.getGenero());
					} else {
						String novoGenero = tfSongNovoGe.getText();
						song.setGenero(novoGenero);
						lSongStatusAtua.setText("Atualização realizada com sucesso.");
						
					}		
				}
			}
			
			if(!songEncontrado) {
				lSongStatusAtua.setText("Música não encontrada");
				tfSongCodAtua.clear();
			}
			
			tfSongCodAtua.clear();
			tfSongNovoTi.clear();
			tfSongNovoAr.clear();
			tfSongNovoAl.clear();
			tfSongNovoGe.clear();
		}
		
	}
	
	@FXML
	private void botaoSongRemover() {
		System.out.println("Remover - foi clicado!");
		
		String strSongCod = tfSongCodRem.getText();
		boolean songEncontrado = false;
		
		if (strSongCod.isEmpty()) {
			lSongStatusRem.setText("Preencha o campo de código!");
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
				lSongStatusRem.setText("Música removida com sucesso.");
				
				//Chamando da função inicialize para atualizar a lista de visualização automaticamente
				ObservableList<Song> observableSongs = FXCollections.observableArrayList(songs);
				lvSongLista.setItems(observableSongs);
			}
			
			if(!songEncontrado) {
				lSongStatusRem.setText("Música não encontrada.");
				tfSongCodRem.clear();
			}
			tfSongCodRem.clear();
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

//Tabela para visualizar dados do buscar - ideia
//Opção para voltar
//Janelas - confirmação
//Tirar Status visível
//Opção de pesquisar por nome!
//Fornecer int no ano de lançamento!
