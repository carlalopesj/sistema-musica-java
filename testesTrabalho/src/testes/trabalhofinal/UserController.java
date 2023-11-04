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

public class UserController {
	
	@FXML
	private TextField tfUserNome, tfUserEmail, tfUserIdBuscar, tfUserIdAtua, tfUserNovoNome, tfUserNovoEmail, tfUserIdDeletar;
	@FXML
	private Label lUserStatusCad,lUserStatusLista, lUserStatusBuscar, lUserInfos, lUserStatusAtua, lUserStatusDeletar;
	@FXML
	private Button btnUserCad, btnUserListar, btnUserBuscar, btnUserAtua, btnUserDeletar;
	@FXML
	private ListView<User> lvUserLista;
	
	ArrayList<User> users = new ArrayList<>();

    public void initialize() {
    	
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
}

//Precisa?

//public void setMainApp(MainController mainController) {
	// TODO Auto-generated method stub
	
//}
//Buscar ID não pode ser string, tem que ser num
//Ver depois como limpar o Label
//Adicionar campos de confirmação...