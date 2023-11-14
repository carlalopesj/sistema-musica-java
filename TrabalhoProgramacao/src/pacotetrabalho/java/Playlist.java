package pacotetrabalho.java;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	//Atributos das Playlists
	private String nome;
    private User proprietario;
    private List<Song> songs;
    private int cod;
    private static int proximoCOD = 1;

    // Construtor da Playlist
    public Playlist(String nome, User proprietario) {
        this.nome = nome;
        this.proprietario = proprietario;
        this.songs = new ArrayList<>();
        this.cod = proximoCOD; 
        proximoCOD++; //Gerar código automaticamente
    }

    // Métodos para adicionar/retirar músicas da playlist
    public void adicionarMusica(Song musica) {
    	this.songs.add(musica);
    }

    public void removerMusica(Song musica) {
        this.songs.remove(musica);
    }

    //Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public User getProprietario() {
		return proprietario;
	}

	public void setProprietario(User proprietario) {
		this.proprietario = proprietario;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	public int getCod() {
		return cod;
	}

	public void setcod(int cod) {
		this.cod= cod;
	}

	public static int getProximoCOD() {
		return proximoCOD;
	}

	public static void setProximoCOD(int proximoCOD) {
		Playlist.proximoCOD = proximoCOD;
	}
	
	//Métodos para mostrar atributos Playlist e listar as músicas nela
	public void mostrarPlaylis() {
		System.out.println("Playlist");
		System.out.println("Nome: " + this.getNome());
		System.out.println("Proprietário: " + this.getProprietario());
		System.out.println("Músicas: ");
		
		for (Song song : songs) {
			System.out.println(songs);
		}
	}
	
	/*
	public void exibirMusicas() {
	    System.out.println("Músicas na playlist '" + nome + "':");
	    for (Song song : songs) {
	        System.out.println("- " + song.getTitulo());
	    }
	}
	*/
	public String exibirMusicas() {
	    StringBuilder mensagem = new StringBuilder();
	    mensagem.append("Músicas na playlist '").append(nome).append("':\n");

	    for (Song song : songs) {
	        mensagem.append("- ").append(song.getTitulo()).append("\n");
	    }

	    return mensagem.toString();
	}

}	
