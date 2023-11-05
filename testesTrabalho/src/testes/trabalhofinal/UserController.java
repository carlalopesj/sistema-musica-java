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

public class UserController {
	
	@FXML
	private TextField tfUserNome, tfUserEmail, tfUserIdBuscar, tfUserIdAtua, tfUserNovoNome, tfUserNovoEmail, tfUserIdDeletar;
	@FXML
	private Label lUserStatusCad,lUserStatusLista, lUserStatusBuscar, lUserInfos, lUserStatusAtua, lUserStatusDeletar;
	@FXML
	private Button btnUserCad, btnUserListar, btnUserBuscar, btnUserAtua, btnUserDeletar, btnVoltarUserPrinc;
	@FXML
	private ListView<User> lvUserLista;
	
	ArrayList<User> users = new ArrayList<>();

    public void initialize() {
    	
    	//Botão para voltar - tela principal
    	
    	btnVoltarUserPrinc.setOnAction(event -> voltarTelaPrincipal());
    	
    	
    	// Configure a ObservableList e defina a ListView
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        lvUserLista.setItems(observableUsers);

        // Configure a fábrica de células personalizada
    	
        lvUserLista.setCellFactory(param -> new ListCell<User>() {
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
    }
    
	@FXML
	private void botaoUserCadastrar(ActionEvent event) {
		System.out.println("Cadastrar - foi clicado");
	    
		 String nomeUser = tfUserNome.getText();
		 String emailUser = tfUserEmail.getText();
		 
		 if(nomeUser.isEmpty() || emailUser.isEmpty()) {
			 lUserStatusCad.setText("Preencha todos os campos!");
			 return;
		 } else {
			 User novoUser = new User(nomeUser, emailUser);
			 users.add(novoUser);
			 
			 lUserStatusCad.setText("Usuário cadastrado com sucesso.");
			 
			 //Chamando da função inicialize para atualizar a lista de visualização automaticamente
			 ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
		     lvUserLista.setItems(observableUsers);
			 
			 tfUserNome.clear();
			 tfUserEmail.clear(); 
		 }
		 
	}
	
	@FXML
	private void botaoUserBuscar(ActionEvent event) {
		System.out.println("Buscar - foi clicado");
		
		String stringuserID = tfUserIdBuscar.getText();
		
		if(stringuserID.isEmpty()) {
			lUserStatusBuscar.setText("Preencha o campo do ID!");
			lUserInfos.setText("");
		} else {
			int userID = Integer.parseInt(stringuserID);
			
			boolean userEncontrado = false;
			
			for (User user : users) {
				if (user.getId() == userID) {
					userEncontrado = true;
					lUserStatusBuscar.setText("Usuário encontrado.");
					lUserInfos.setText("INFORMAÇÕES\nNome: " + user.getNome() + "\nE-mail: " + user.getEmail() + "\nID: " + user.getId());
				}
			}
			if (!userEncontrado) {
				lUserStatusBuscar.setText("Usuário não encontrado.");
				lUserInfos.setText("");
			}
			
			tfUserIdBuscar.clear();
		}
	}
	
	@FXML
	private void botaoUserAtualizar(ActionEvent event) {
		System.out.println("Atualizar - foi clicado");
		
		String stringuserID = tfUserIdAtua.getText();
		boolean userEncontrado = false;
		boolean userAtualizado = false;
		boolean emailAtualizado = false;
		
		if(stringuserID.isEmpty()) {
			lUserStatusAtua.setText("Preencha o campo do ID!");
		} else {
			int userID = Integer.parseInt(stringuserID);
		
			for(User user : users) {
				if (user.getId() == userID) {
					userEncontrado = true;
					
					if (tfUserNovoNome.getText().isEmpty()) {
						user.setNome(user.getNome());
					} else {
						String novoNome = tfUserNovoNome.getText();
						user.setNome(novoNome);
						lUserStatusAtua.setText("Nome atualizado com sucesso.");
						userAtualizado = true;
						
						//Chamando da função inicialize para atualizar a lista de visualização automaticamente
						ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
					    lvUserLista.setItems(observableUsers);
					}
					
					if (tfUserNovoEmail.getText().isEmpty()) {
						user.setEmail(user.getEmail());
					} else {
						String novoEmail = tfUserNovoEmail.getText();
						user.setEmail(novoEmail);
						lUserStatusAtua.setText("Email atualizado com sucesso.");
						emailAtualizado = true;
						
					}
				}
			}
			
			if(userAtualizado && emailAtualizado) {
				lUserStatusAtua.setText("Nome e Email atualizados com sucesso.");
			}
			
			if(!userEncontrado) {
				lUserStatusAtua.setText("Usuário não encontrado.");
			}
			
			tfUserIdAtua.clear();
			tfUserNovoNome.clear();
			tfUserNovoEmail.clear();
		}
	}
	
	@FXML
	private void botaoUserDeletar(ActionEvent event) {
		System.out.println("Deletar - foi clicado!");
		
		String stringuserID = tfUserIdDeletar.getText();
		boolean userEncontrado = false;
		
		if(stringuserID.isEmpty()) {
			lUserStatusDeletar.setText("Preencha o campo do ID!");
		} else {
			int userID = Integer.parseInt(stringuserID);
			User usuarioParaRemover = null;
			
			for(User user : users) {
				if (user.getId() == userID) {
					userEncontrado = true;
					usuarioParaRemover = user;
				}
			}
			
			if (usuarioParaRemover != null) {
	            users.remove(usuarioParaRemover);
	            lUserStatusDeletar.setText("Usuário deletado com sucesso.");

	            // Chamando da função initialize para atualizar a lista de visualização automaticamente
	            ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
	            lvUserLista.setItems(observableUsers);
	        }
			
			if (!userEncontrado) {
				lUserStatusDeletar.setText("Usuário não encontrado.");
			}
			
			tfUserIdDeletar.clear();
		}
		
	}
	
	//Botão de voltar para tela inicial
	public void voltarTelaPrincipal() {
	    try {
	    	System.out.println("Botão Usuário clicado!");
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
	        Parent telaInicio = loader.load();

	        // Obtém o palco (Stage) atual
	        Stage currentStage = (Stage) btnVoltarUserPrinc.getScene().getWindow();

	        // Crie uma nova cena e defina-a no palco
	        Scene scene = new Scene(telaInicio);
	        currentStage.setScene(scene);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}

//Remover clicando
//Buscar ID não pode ser string, tem que ser num
//Ver depois como limpar o Label
//Adicionar campos de confirmação...