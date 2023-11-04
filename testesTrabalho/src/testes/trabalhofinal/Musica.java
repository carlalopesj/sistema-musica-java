package testes.trabalhofinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Musica extends Application{
		
	ArrayList<User> users = new ArrayList<>();
	ArrayList<Song> songs = new ArrayList<>();
	ArrayList<Playlist> playlists = new ArrayList<>();
	
	private static void cadastrarUser(ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Por favor, insira o nome de usuário: ");
	    String nomeUsuario = scanner.nextLine();

	    System.out.println("Por favor, insira o e-mail: ");
	    String emailUsuario = scanner.nextLine();

	    User Usuario = new User(nomeUsuario, emailUsuario);
	    users.add(Usuario);
	}
	
	private static void criarMusica(ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o título da música: ");
		String tituloMusica = scanner.nextLine();
		System.out.println("Artista: ");
		String artistaMusica = scanner.nextLine();
		System.out.println("Ano de lançamento: ");
		int anoLancamentoMusica = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Gênero: ");
		String generoMusica = scanner.nextLine();
		
		Song novaMusica = new Song(tituloMusica, artistaMusica, anoLancamentoMusica, generoMusica);
		songs.add(novaMusica);
	}
	
	private static void criarPlaylist(ArrayList<Playlist> playlists, ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
	    
	    if (users.isEmpty()) {
	    	System.out.println("Não há usuário!");
	    } else {
	    	System.out.println("Nome da Playlist: ");
			String nomePlaylist = scanner.nextLine();
			System.out.println("Escolha o proprietário da playlist (digite o número do usuário):");
		    for (int i = 0; i < users.size(); i++) {
		        System.out.println(i + 1 + ": " + users.get(i).getNome());
		    }

		    int proprietarioIndex = scanner.nextInt();
		    scanner.nextLine(); // Limpar a nova linha após a leitura do número
	    	// Certificando-se de que o índice escolhido é válido
	    	if (proprietarioIndex >= 0 || proprietarioIndex < users.size()) {
		        User proprietario = users.get(proprietarioIndex - 1);
		        Playlist novaPlaylist = new Playlist(nomePlaylist, proprietario);
		        playlists.add(novaPlaylist);
		        System.out.println("Playlist criada com sucesso!");
		    } else {
		        System.out.println("Opção inválida. Playlist não criada.");
		    }
	    }
	    
	}
	
	//Verificações para listar
	
	public static void listarUsers( ArrayList<User> users) {
		System.out.println("----- USUÁRIOS -----");
		for (User user : users) {
			System.out.println("ID " + user.getId() + " -  " + user.getNome());
			System.out.println("-----------------------");
		}
	}
	
	public static void listarMusicas(ArrayList<Song> songs) {
		System.out.println("----- MÚSICAS -----");
		for (Song song : songs) {
			System.out.println(song.getCod() + " - " + song.getTitulo() + " - " + song.getArtista());
			System.out.println("------------------------");
		}
	}
	
	public static void listarPlaylists(ArrayList<Playlist> playlists) {
		System.out.println("----- PLAYLISTS -----");
		for (Playlist playlist : playlists) {
			System.out.println(playlist.getCod() + " " + playlist.getNome() + " - " + playlist.getProprietario().getNome());
			System.out.println("------------------------");
		}
	}
	
	private static Song escolherMusica(ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Escolha uma música :");

	    for (int i = 0; i < songs.size(); i++) {
	        System.out.println((i + 1) + ": " + songs.get(i).getTitulo());
	    }

	    int musicaIndex = scanner.nextInt();
	    scanner.nextLine(); // Limpar a nova linha após a leitura do número

	    if (musicaIndex >= 1 && musicaIndex <= songs.size()) {
	        return songs.get(musicaIndex - 1);
	    } else {
	        System.out.println("Opção inválida. Música não escolhida.");
	        return null;
	    }
	}
	
	private static Playlist escolherPlaylist(ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Escolha uma playlist: ");

	    for (int i = 0; i < playlists.size(); i++) {
	        System.out.println((i + 1) + ": " + playlists.get(i).getNome());
	    }

	    int playlistIndex = scanner.nextInt();
	    scanner.nextLine(); // Limpar a nova linha após a leitura do número

	    if (playlistIndex >= 1 && playlistIndex <= playlists.size()) {
	    	return playlists.get(playlistIndex - 1);
	    } else {
	    	System.out.println("Opção inválida. Música não adicionada à playlist.");
	        return null;
	    }
	}
	
	private static void adicionarMusicaPlaylist(ArrayList<Playlist> playlists, ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		if (songs.isEmpty() || playlists.isEmpty()) {
	        System.out.println("Não há músicas ou playlists disponíveis para adicionar.");
	        return;
	    }

	    Song musicaEscolhida = escolherMusica(songs);

	    System.out.println("Escolha uma playlist: ");

	    for (int i = 0; i < playlists.size(); i++) {
	        System.out.println((i + 1) + ": " + playlists.get(i).getNome());
	    }

	    int playlistIndex = scanner.nextInt();
	    scanner.nextLine(); // Limpar a nova linha após a leitura do número

	    if (playlistIndex < 1 || playlistIndex > playlists.size()) {
	        System.out.println("Opção inválida. Música não adicionada à playlist.");
	        return;
	    }

	    Playlist playlistEscolhida = playlists.get(playlistIndex - 1);
	    playlistEscolhida.adicionarMusica(musicaEscolhida);
	    System.out.println("Música adicionada à playlist com sucesso!");
	}
	
	private static void listarMusicasPlaylist(ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		
		if(playlists.isEmpty()) {
			System.out.println("Não há playlist disponíveis.");
			return;
		}
		
		Playlist playlistSelecionada = escolherPlaylist(playlists);
		playlistSelecionada.exibirMusicas();
		
	}
	
	public static void buscarUser(ArrayList<User>users, ArrayList<Playlist> playlists) {
		
		if(users.isEmpty()) {
			System.out.println("Não há usuários disponíveis.");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Escolha um usuário: ");
		for (int i = 0; i < users.size(); i++) {
	        System.out.println(i + 1 + ": " + users.get(i).getNome());
	    }

	    int userIndex = scanner.nextInt();
	    scanner.nextLine();

	    if (userIndex >= 1 && userIndex <= users.size()) {
	        User userSelecionado = users.get(userIndex - 1);
	        System.out.println("Nome: " + userSelecionado.getNome());
	        System.out.println("E-mail: " + userSelecionado.getEmail());
	        System.out.println("ID: " + userSelecionado.getId());
	        
	        boolean encontrouPlaylist = false;
	        
	        for (Playlist playlist : playlists) {
	            if (playlist.getProprietario() == userSelecionado) {
	            	encontrouPlaylist = true;
	                System.out.println("Playlists: " );
	                System.out.println("- " + playlist.getNome());
	            }
	        }
	        
	        if(!encontrouPlaylist) {
	        	System.out.println("Não há playlists.");
	        }
	        
	    } else {
	        System.out.println("Opção inválida. Usuário não escolhida.");
	        
	    }
	    
	}
	
	public static void buscarMusica(ArrayList<Song> songs) {
		Song musicaSelecionada = escolherMusica(songs);
		System.out.println("Título: " + musicaSelecionada.getTitulo());
		System.out.println("Artista: " + musicaSelecionada.getArtista());
		System.out.println("Ano de Lançamento: " + musicaSelecionada.getAnoLancamento());
		System.out.println("Gênero: " + musicaSelecionada.getGenero());
		System.out.println("Código da Música: " + musicaSelecionada.getCod());
	}
	
	public static void buscarPlaylist(ArrayList<Playlist> playlists) {
		Playlist playlistSelecionada = escolherPlaylist(playlists);
		System.out.println("Nome: " + playlistSelecionada.getNome());
		System.out.println("Proprietário: " + playlistSelecionada.getProprietario().getNome());
		System.out.println("Código da Playlist: " + playlistSelecionada.getCod());
		playlistSelecionada.exibirMusicas();
	}
	
	public static void verificarUser(ArrayList<User> users, ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o ID do usuário: ");
		int userID = scanner.nextInt();
		boolean userEncontrado = false;
		boolean userPlaylist = false;
		
		for (User user : users) {
			if (user.getId() == userID) {
				userEncontrado = true;
				System.out.println("Nome: " + user.getNome());
		        System.out.println("E-mail: " + user.getEmail());
		        System.out.println("ID: " + user.getId());
		        for (Playlist playlist : playlists) {
		        	if (playlist.getProprietario() == user) {
		        		userPlaylist = true;
		        		System.out.println("- " + playlist.getNome());
			        }
			    }
			}
		}
		
		if (!userEncontrado) {
			System.out.println("Usuário não encontrado.");
		}
		
		if(!userPlaylist) {
			System.out.println("Não há playlsts.");
		}
		
	}
	
	public static void verificarMusica(ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome da música: ");
		String musicaNome = scanner.nextLine();
		boolean musicaEncontrada = false;
		
		for (Song song : songs) {
			if (song.getTitulo().equalsIgnoreCase(musicaNome)) {
				System.out.println("Título: " + song.getTitulo());
				System.out.println("Artista: " + song.getArtista());
				System.out.println("Ano de Lançamento: " + song.getAnoLancamento());
				System.out.println("Gênero: " + song.getGenero());
				System.out.println("Código da Música: " + song.getCod());
				musicaEncontrada = true;
			} 
		}
		
		if(!musicaEncontrada) {
			System.out.println("Música não encontrada.");
		}
	}
	
	public static void verificarPlaylist(ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome da playlist: ");
		String playlistNome = scanner.nextLine();
		boolean playlistEncontrada = false;
		
		for(Playlist playlist : playlists) {
			if(playlist.getNome().equalsIgnoreCase(playlistNome)) {
				playlistEncontrada = true;
				System.out.println("Nome: " + playlist.getNome());
				System.out.println("Proprietário: " + playlist.getProprietario().getNome());
				System.out.println("Código da Playlist: " + playlist.getCod());
				if(playlist.getSongs().isEmpty()) {
					System.out.println("Não há músicas nessa playlist.");
				} else {
					playlist.exibirMusicas();
				}
			}
		}
		
		if(!playlistEncontrada) {
			System.out.println("Playlist não encontrada.");
		}
	}
	
	public static void atualizarUser(ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o ID do usuário: ");
		int userID = scanner.nextInt();
		boolean userEncontrado = false;
		
		for(User user : users) {
			if (user.getId() == userID) {
				userEncontrado = true;
				System.out.println(user.getNome());
			}
		}
		
		if(userEncontrado) {
			System.out.println("Atualizar: \n1- Nome\n2- E-mail");
			int opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			case 1:
				for (User user : users) {
					if(user.getId() == userID) {
						System.out.println("Digite o novo nome: ");
						String newNome = scanner.nextLine();
						user.setNome(newNome);
						System.out.println("Nome atualizado!");
					}
				}
				break;
			
			case 2:
				for (User user : users) {
					if(user.getId() == userID) {
						System.out.println("Digite o novo e-mail: ");
						String newEmail = scanner.nextLine();
						user.setEmail(newEmail);
						System.out.println("E-mail atualizado!");
					}
				}
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} else {
			System.out.println("Usuário não encontrado.");
		}
	}
	
	public static void atualizarMusica(ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Insira o código da música: ");
		int codMus = scanner.nextInt();
		boolean musicaEncontrada = false;
		
		for (Song song : songs) {
			if (song.getCod() == codMus) {
				musicaEncontrada = true;
			}
		}
		
		if(musicaEncontrada) {
			System.out.println("Atualizar: \n1- Título\n2- Artista\n3- Ano de Lançamento\n4- Gênero");
			int opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			case 1:
				for(Song song : songs) {
					if(song.getCod() == codMus) {
						System.out.println("Digite o novo título: ");
						String newTitulo = scanner.nextLine();
						song.setTitulo(newTitulo);
						System.out.println("Título atualizado!");
					}
				}
				break;
			case 2:
				for(Song song : songs) {
					if(song.getCod() == codMus) {
						System.out.println("Digite o(a) novo(a) artista: ");
						String newArtista = scanner.nextLine();
						song.setArtista(newArtista);
						System.out.println("Artista atualizado(a)!");
					}
				}
				break;
			case 3:
				for(Song song : songs) {
					if(song.getCod() == codMus) {
						System.out.println("Digite o novo ano de lançamento: ");
						int newAno = scanner.nextInt();
						song.setAnoLancamento(newAno);
						System.out.println("Ano de lançamento atualizado!");
					}
				}
				break;
			case 4:
				for(Song song : songs) {
					if(song.getCod() == codMus) {
						System.out.println("Digite o novo gênero: ");
						String newGenero = scanner.nextLine();
						song.setGenero(newGenero);
						System.out.println("Gênero atualizado!");
					}	
				}
				break;
			default:
				System.out.println("Opção inválido.");
				break;
			}
		} else {
			System.out.println("Música não encontrada.");
		}
	}
	
	public static void atualizarPlaylist(ArrayList<Playlist> playlists, ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o código da Playlist");
		int codPla = scanner.nextInt();
		boolean playlistEncontrada = false;
		
		for(Playlist playlist : playlists) {
			if(playlist.getCod() == codPla) {
				playlistEncontrada = true;
			}
		}
		
		if(playlistEncontrada) {
			System.out.println("Atualizar: \n1- Nome\n2- Usuário");
			int opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			case 1:
				for (Playlist playlist : playlists) {
					if (playlist.getCod() == codPla) {
						System.out.println("Insira o novo nome: ");
						String newNome = scanner.nextLine();
						playlist.setNome(newNome);
						System.out.println("Nome atuaizado!");
					}
				}
				break;
			case 2:
			    for (Playlist playlist : playlists) {
			        if (playlist.getCod() == codPla) {
			            System.out.println("Digite o ID do novo proprietário: ");
			            int newProId = scanner.nextInt();

			            // Procurar o usuário com o ID correspondente na lista de usuários
			            User newPro = null;
			            for (User user : users) {
			                if (user.getId() == newProId) {
			                    newPro = user;
			                    break;
			                }
			            }

			            if (newPro != null) {
			                playlist.setProprietario(newPro);
			                System.out.println("Proprietário atualizado!");
			            } else {
			                System.out.println("Usuário com ID " + newProId + " não encontrado.");
			            }
			        }
			    }
			    break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} else {
			System.out.println("Playlist não encontrada.");
		}
	}
	
	public static void removerUser(ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o ID do usuário que você quer remover: ");
		int idRemUser = scanner.nextInt();
		boolean userEncontrado = false;
		
		User userRemover = null;
	    
	    for (User user : users) {
	        if (user.getId() == idRemUser) {
	            userEncontrado = true;
	            userRemover = user;
	            break; // Encontrou o usuário, não é necessário continuar procurando.
	        }
	    }
	    
	    if (userRemover != null) {
	        System.out.println("Tem certeza que deseja excluir esse usuário?\n1- Sim\n2- Não");
	        int Sn = scanner.nextInt();
	        if (Sn == 1) {
	            users.remove(userRemover); // Remove o usuário da lista.
	            System.out.println("Usuário removido!");
	        } else {
	            return;
	        }
	    } else {
	        System.out.println("Usuário não encontrado.");
	    }
	}
	
	public static void removerMusica(ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o código da música que você quer remover: ");
		int idRemMus = scanner.nextInt();
		boolean musicaEncontrada = false;
		
		Song musRemover = null;
	    
	    for (Song song : songs) {
	        if (song.getCod() == idRemMus) {
	            musicaEncontrada = true;
	            musRemover = song;
	            break;
	        }
	    }
	    
	    if (musRemover != null) {
	        System.out.println("Tem certeza que deseja excluir essa música?\n1- Sim\n2- Não");
	        int Sn = scanner.nextInt();
	        if (Sn == 1) {
	            songs.remove(musRemover); 
	            System.out.println("Música removida!");
	        } else {
	            return;
	        }
	    } else {
	        System.out.println("Música não encontrada.");
	    }
	}
	
	public static void removerPlaylist(ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o código da playlist que você quer remover: ");
		int idRemPla = scanner.nextInt();
		boolean playlistEncontrada = false;
		
		Playlist plaRemover = null;
	    
	    for (Playlist playlist : playlists) {
	        if (playlist.getCod() == idRemPla) {
	            playlistEncontrada = true;
	            plaRemover = playlist;
	            break;
	        }
	    }
	    
	    if (plaRemover != null) {
	        System.out.println("Tem certeza que deseja excluir essa playlist?\n1- Sim\n2- Não");
	        int Sn = scanner.nextInt();
	        if (Sn == 1) {
	        	playlists.remove(plaRemover); 
	            System.out.println("Playlist removida!");
	        } else {
	            return;
	        }
	    } else {
	        System.out.println("Playlist não encontrada.");
	    }
	}

	public static void main(String[] args) {
		launch(args);
		
		 
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<User> users = new ArrayList<>();
		ArrayList<Song> songs = new ArrayList<>();
		ArrayList<Playlist> playlists = new ArrayList<>();
		
		User user1 = new User("Carla", "carla@example.com");
		User user2 = new User("João", "joão@example.com");
		User user3 = new User("Maria", "maria@example.com");
		User user4 = new User("Ana", "ana@example.com");
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		
		Song song1 = new Song("Perfect", "Ed Sheeran", 2017 ,"Pop");
		Song song2 = new Song("Tempo Perdido", "Legião Urbana", 1986, "Rock");
		songs.add(song1);
		songs.add(song2);
		
		Playlist playlist1 = new Playlist("Baila+", user1);
		playlists.add(playlist1);
		
		user1.mostrarUsers();
		user2.mostrarUsers();
		user3.mostrarUsers();
		song1.mostrarMusicas();
		playlist1.mostrarPlaylis();
		
		int sair = 0;
		
		do {
			System.out.println("===== MENU =====");
			System.out.println("1- Listar Usuários\n2- Listar Músicas\n3- Listar Playlists\n4- Cadastrar Usuário\n5- Criar Música\n6- Criar Playlist\n7- Sair\n8- Adicionar Música a Playlist\n9 - Mostrar músicas em uma playlist\n10- Buscar Usuário\n11- Buscar Música\n12- Buscar Playlist\n13- Verificar Usuário\n14- Verificar Música\n15- Verificar Playlist\n16- Atualizar Usuário\n17- Atualizar Música\n18- Atualizar Playlist\n19- Remover Usuário\n20- Remover Música\n21- Remover Playlist");
			int opcao = scanner.nextInt();
			switch(opcao) {
				case 1:
					listarUsers(users);
					break;
				case 2:
					listarMusicas(songs);
					break;
				case 3:
					listarPlaylists(playlists);
					break;
				case 4:
					cadastrarUser(users);
					break;
				case 5:
					criarMusica(songs);
					break;
				case 6:
					criarPlaylist(playlists, users);
					break;
				case 7:
					System.out.println("Saindo...\nVolte sempre :)");
					sair = 1;
					break;
				case 8:
					adicionarMusicaPlaylist(playlists, songs);
					break;
				case 9:
					listarMusicasPlaylist(playlists);
					break;
				case 10:
					buscarUser(users, playlists);
					break;
				case 11:
					buscarMusica(songs);
					break;
				case 12:
					buscarPlaylist(playlists);
					break;
				case 13: 
					verificarUser(users, playlists);
					break;
				case 14: 
					verificarMusica(songs);
					break;
				case 15:
					verificarPlaylist(playlists);
					break;
				case 16:
					atualizarUser(users);
					break;
				case 17:
					atualizarMusica(songs);
					break;
				case 18:
					atualizarPlaylist(playlists, users);
					break;
				case 19:
					removerUser(users);
					break;
				case 20:
					removerMusica(songs);
					break;
				case 21:
					removerPlaylist(playlists);
					break;
				default:
					System.out.println("Valor inválido!");
					break;
			}
			
			
			
			
		} while (sair == 0);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    try {
	        // Carrega a interface gráfica a partir do arquivo FXML
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
	        Parent root = loader.load();

	        MainController controller = loader.getController();
	        controller.initialize(); // Adicione esta linha
	        // Crie uma cena e defina a raiz
	        Scene scene = new Scene(root);
	        
	        // Configura a janela
	        primaryStage.setTitle("Aplicativo");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}

//Remover e atualizar música de uma playlist
//Arrumar o design (switch)
//Criar senha para remover e atualizar?

//Arrumar - quando o user não for dono de nenhuma playlist aparecer OK
//Se o user não for encontrado aparecer msg - try e catch ou if e else OK
//Arrumar proprietário OK
//Horário que foi feito o cadastro?
//Data de nascimento no cadastro
//Implementar sistema de Array OK
//Listar todos os users, músicas, playlists OK
//Mostrar informações de apenas um user, song, play OK
// - Opção de buscar, por exemplo OK
//Criar as funções para o user digitar seu cadastro, criar músicas e playlist OK
// - Tipo um questionário
//Poder atualizar OK
//Poder remover
//Arrumar a parte de adicionar músicas as playlists OK


//Fazer verificações
//Ver se dá pra usar herança (nome, código)
//Fazer a interface gráfica
//Documentação
//Apresentação