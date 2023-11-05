package trabalhofinal.sistema;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String nome;
	private String email;
	private int id = 0;
	private static int proximoID = 1;
	private List<Playlist> playlists;

	// Construtor
	public User(String nome, String email) {
	    this.nome = nome;
	    this.email = email;
	    this.id = proximoID; // Atribui o próximo ID disponível ao novo usuário.
	    proximoID++; // Implemente um método para gerar IDs automaticamente.
	    this.playlists = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getProximoID() {
		return proximoID;
	}

	public static void setProximoID(int proximoID) {
		User.proximoID = proximoID;
	}
		
	public void mostrarUsers() {
		System.out.println("Usuário");
		System.out.println("Nome: " + this.getNome());
		System.out.println("E-mail: " + this.getEmail());
		System.out.println("ID: " + this.getId());
	}
		
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
}