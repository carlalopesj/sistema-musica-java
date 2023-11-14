package pacotetrabalho.java;

public class Song {
	//Atributos das Músicas
	private String titulo;
    private String artista;
    private int anoLancamento;
    private String genero; 
    private int cod;
    private static int proximoCOD = 1;

    // Construtor da Música
    public Song(String titulo, String artista, int anoLancamento, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.cod = proximoCOD; 
        proximoCOD++; //Gerar Código automaticamente, incrementando de 1 em 1
    }

    //Getters e Setters
	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
		Song.proximoCOD = proximoCOD;
	}
	
	//Método para mostrar as Músicas
	public void mostrarMusicas() {
		System.out.println("Música");
		System.out.println("Título: " + this.getTitulo());
		System.out.println("Artista: " + this.getArtista());
		System.out.println("Gênero: " + this.getGenero());
		System.out.println("Código da música: " + this.getCod());
	}

}

