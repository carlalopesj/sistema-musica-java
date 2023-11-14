package pacotetrabalho.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
	// Atributos do Usuário
	private String nome;
	private String email;
	private int id = 0;
	private static int proximoID = 1;
	private List<Playlist> playlists;

	// Construtor do Usuário
	public User(String nome, String email) {
	    this.nome = nome;
	    this.email = email;
	    this.id = proximoID;
	    proximoID++; // Gerar IDs automaticamente.
	    this.playlists = new ArrayList<>();
	}
	
	//Getters e Setters
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
	
	//Método para mostrar atributos dos Usuários
	public void mostrarUsers() {
		System.out.println("Usuário");
		System.out.println("Nome: " + this.getNome());
		System.out.println("E-mail: " + this.getEmail());
		System.out.println("ID: " + this.getId());
	}
	
	//Lista de Playlists pertencentes ao Usuário
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
}
