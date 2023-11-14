package pacotetrabalho.java;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Sistema {
	
	//Declarando arraylists para os usuários, músicas e playlists
	ArrayList<User> users = new ArrayList<>();
	ArrayList<Song> songs = new ArrayList<>();
	ArrayList<Playlist> playlists = new ArrayList<>();
	
	//----------------------------------- USUÁRIOS -----------------------------------------------------
	//Método para cadastrar um usuário, adicionando-a na arraylist
	public static void cadastrarUser(ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		
		//System.out.println("Digite o Nome de Usuário: ");
	    //String nomeUsuario = scanner.nextLine();
		String nomeUsuario = JOptionPane.showInputDialog(null, "Digite o Nome de Usuário: ", "Nome");
		
	    //System.out.println("Digite o E-mail: ");
	    //String emailUsuario = scanner.nextLine();
		String emailUsuario = JOptionPane.showInputDialog(null, "Digite o Email do Usuário: ", "E-mail");

	    //Cria um usuário a partir dos dados informados
	    User Usuario = new User(nomeUsuario, emailUsuario);
	    //O adiciona no arraylist users
	    users.add(Usuario);
	    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso.");
	}
	
	//Método para listar todos os usuários cadastrados, mostrando apenas o ID e nome
	/*
	public static void listarUsers(ArrayList<User> users) {
		System.out.println("----- USUÁRIOS -----");
		for (User user : users) {
			System.out.println("ID " + user.getId() + " -  " + user.getNome());
			System.out.println("-----------------------");
		}
	}*/
	public static void listarUsers(ArrayList<User> users) {
        StringBuilder userListText = new StringBuilder("----- USUÁRIOS -----\n");

        for (User user : users) {
            userListText.append("ID ").append(user.getId()).append(" -  ").append(user.getNome()).append("\n");
            userListText.append("-----------------------\n");
        }

        // Exibir a lista usando JOptionPane
        JOptionPane.showMessageDialog(null, userListText.toString(), "Lista de Usuários", JOptionPane.INFORMATION_MESSAGE);
    }
	
	//Método para visualizar info de um usuário, a partir de uma escolha por ID
	/*
	public static void buscarUser(ArrayList<User>users, ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		boolean encontrouPlaylist = false; //Verifica se um usuário possui alguma playlist
		
		//Verificando se há usuários
		if(users.isEmpty()) {
			System.out.println("Não há usuários disponíveis.");
			return;
		} else {
			System.out.println("Escolha um usuário: ");
			for (int i = 0; i < users.size(); i++) { //Lista todos os usuários disponíveis
		        System.out.println(i + 1 + ": " + users.get(i).getNome()); 
		    }
		    int userIndex = scanner.nextInt(); //Escolha a partir do index
		    scanner.nextLine();

		    //Validar se o número inserido é válido
		    if (userIndex >= 1 && userIndex <= users.size()) {
		        User userSelecionado = users.get(userIndex - 1); //Cria um usuário a partir do index informado e mostra suas informações
		        System.out.println("Nome: " + userSelecionado.getNome());
		        System.out.println("E-mail: " + userSelecionado.getEmail());
		        System.out.println("ID: " + userSelecionado.getId());
		        
		        System.out.println("Playlists: " );
		        //Mostrar as playlists que o usuário possui
		        for (Playlist playlist : playlists) {
		            if (playlist.getProprietario() == userSelecionado) {
		            	encontrouPlaylist = true;
		                System.out.println("- " + playlist.getNome());
		            }
		        }
		        
		        if(!encontrouPlaylist) {
		        	System.out.println("Não há playlists.");
		        }
		        
		    } else {
		        System.out.println("Opção inválida. Usuário não escolhido.");
		        
		    }
		}
	    
	} */
	
	public static void buscarUser(ArrayList<User> users, ArrayList<Playlist> playlists) {
        boolean encontrouPlaylist = false; // Verifica se um usuário possui alguma playlist

        // Verificando se há usuários
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há usuários disponíveis.", "Buscar Usuário", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            StringBuilder userListText = new StringBuilder("Escolha um usuário:\n");
            for (int i = 0; i < users.size(); i++) {
                userListText.append(i + 1).append(": ").append(users.get(i).getNome()).append("\n");
            }

            String userInput = JOptionPane.showInputDialog(null, userListText.toString(), "Buscar Usuário", JOptionPane.PLAIN_MESSAGE);

            if (userInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                try {
                    int userIndex = Integer.parseInt(userInput);

                    // Validar se o número inserido é válido
                    if (userIndex >= 1 && userIndex <= users.size()) {
                        User userSelecionado = users.get(userIndex - 1); // Cria um usuário a partir do index informado e mostra suas informações

                        StringBuilder userInfoText = new StringBuilder("Nome: ").append(userSelecionado.getNome()).append("\n")
                                .append("E-mail: ").append(userSelecionado.getEmail()).append("\n")
                                .append("ID: ").append(userSelecionado.getId()).append("\n")
                                .append("Playlists:\n");

                        // Mostrar as playlists que o usuário possui
                        for (Playlist playlist : playlists) {
                            if (playlist.getProprietario() == userSelecionado) {
                                encontrouPlaylist = true;
                                userInfoText.append("- ").append(playlist.getNome()).append("\n");
                            }
                        }

                        if (!encontrouPlaylist) {
                            userInfoText.append("Não há playlists.\n");
                        }

                        JOptionPane.showMessageDialog(null, userInfoText.toString(), "Informações do Usuário", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida. Usuário não escolhido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
	
	/*
	//Método para verificar se um usuário existe a partir de seu nome
	public static void verificarUser(ArrayList<User> users, ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome do usuário: ");
		String userName = scanner.nextLine();
		boolean userEncontrado = false;
		boolean userPlaylist = false;
		
		for (User user : users) {
			if (user.getNome().equalsIgnoreCase(userName)) {
				userEncontrado = true;
				System.out.println("Nome: " + user.getNome());
		        System.out.println("E-mail: " + user.getEmail());
		        System.out.println("ID: " + user.getId());
		        System.out.println("Playlists: ");
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
	} */
	
	public static void verificarUser(ArrayList<User> users, ArrayList<Playlist> playlists) {
        String userName = JOptionPane.showInputDialog(null, "Digite o nome do usuário:", "Verificar Usuário", JOptionPane.PLAIN_MESSAGE);

        if (userName != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
            boolean userEncontrado = false;
            boolean userPlaylist = false;
            StringBuilder userInfoText = new StringBuilder();

            for (User user : users) {
                if (user.getNome().equalsIgnoreCase(userName)) {
                    userEncontrado = true;
                    userInfoText.append("Nome: ").append(user.getNome()).append("\n")
                            .append("E-mail: ").append(user.getEmail()).append("\n")
                            .append("ID: ").append(user.getId()).append("\n")
                            .append("Playlists:\n");

                    for (Playlist playlist : playlists) {
                        if (playlist.getProprietario() == user) {
                            userPlaylist = true;
                            userInfoText.append("- ").append(playlist.getNome()).append("\n");
                        }
                    }
                }
            }

            if (!userEncontrado) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.", "Verificar Usuário", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (!userPlaylist) {
                    userInfoText.append("Não há playlists.\n");
                }

                JOptionPane.showMessageDialog(null, userInfoText.toString(), "Informações do Usuário", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
	
	/*
	//Método para atualizar dados de usuários por ID
	public static void atualizarUser(ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o ID do usuário: ");
		int userID = scanner.nextInt();
		boolean userEncontrado = false;
		
		//Verifica se há um user com o ID informado
		for(User user : users) {
			if (user.getId() == userID) {
				userEncontrado = true;
				System.out.println(user.getNome());
			}
		}
		
		if(userEncontrado) {
			//Permite que escolha se quer atualizar o nome ou o e-mail
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
						System.out.println("Nome atualizado.");
					}
				}
				break;
			
			case 2:
				for (User user : users) {
					if(user.getId() == userID) {
						System.out.println("Digite o novo e-mail: ");
						String newEmail = scanner.nextLine();
						user.setEmail(newEmail);
						System.out.println("E-mail atualizado.");
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
	*/
	
	public static void atualizarUser(ArrayList<User> users) {
        String userIDInput = JOptionPane.showInputDialog(null, "Digite o ID do usuário:", "Atualizar Usuário", JOptionPane.PLAIN_MESSAGE);

        if (userIDInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
            try {
                int userID = Integer.parseInt(userIDInput);
                boolean userEncontrado = false;

                // Verifica se há um usuário com o ID informado
                for (User user : users) {
                    if (user.getId() == userID) {
                        userEncontrado = true;
                        break;
                    }
                }

                if (userEncontrado) {
                    // Permite que o usuário escolha se quer atualizar o nome ou o e-mail
                    String[] opcoes = {"Nome", "E-mail"};
                    int escolha = JOptionPane.showOptionDialog(null, "Atualizar:", "Escolha uma opção",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

                    switch (escolha) {
                        case 0: // Nome
                            for (User user : users) {
                                if (user.getId() == userID) {
                                    String newNome = JOptionPane.showInputDialog(null, "Digite o novo nome:", "Atualizar Nome", JOptionPane.PLAIN_MESSAGE);
                                    if (newNome != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                                        user.setNome(newNome);
                                        JOptionPane.showMessageDialog(null, "Nome atualizado.", "Atualizar Nome", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        case 1: // E-mail
                            for (User user : users) {
                                if (user.getId() == userID) {
                                    String newEmail = JOptionPane.showInputDialog(null, "Digite o novo e-mail:", "Atualizar E-mail", JOptionPane.PLAIN_MESSAGE);
                                    if (newEmail != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                                        user.setEmail(newEmail);
                                        JOptionPane.showMessageDialog(null, "E-mail atualizado.", "Atualizar E-mail", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.", "Atualizar Usuário", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
	
	/*
	//Método para remover um usuário por ID
	public static void removerUser(ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		boolean userEncontrado = false;
		
		System.out.println("Informe o ID do usuário que você quer remover: ");
		int idRemUser = scanner.nextInt();
		//Para não remover direto do arraylist
		User userRemover = null;
	    
		//Verifica se o ID do usuário informado realmente é válido
	    for (User user : users) {
	        if (user.getId() == idRemUser) {
	            userEncontrado = true;
	            userRemover = user;
	            break;
	        }
	    }
	    
	    //Garantia para não remover o usuário errado
	    if (userRemover != null) {
	        System.out.println("Tem certeza que deseja excluir esse usuário?\n1- Sim\n2- Não");
	        int Sn = scanner.nextInt();
	        if (Sn == 1) {
	            users.remove(userRemover); 
	            System.out.println("Usuário removido.");
	        } else {
	            return;
	        }
	    } else {
	        System.out.println("Usuário não encontrado.");
	    }
	}
	*/
	
	   public static void removerUser(ArrayList<User> users) {
	        String idRemUserInput = JOptionPane.showInputDialog(null, "Informe o ID do usuário que você quer remover:", "Remover Usuário", JOptionPane.PLAIN_MESSAGE);

	        if (idRemUserInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
	            try {
	                int idRemUser = Integer.parseInt(idRemUserInput);
	                boolean userEncontrado = false;

	                // Para não remover diretamente do ArrayList
	                User userRemover = null;

	                // Verifica se o ID do usuário informado é válido
	                for (User user : users) {
	                    if (user.getId() == idRemUser) {
	                        userEncontrado = true;
	                        userRemover = user;
	                        break;
	                    }
	                }

	                // Garantia para não remover o usuário errado
	                if (userRemover != null) {
	                    String[] opcoes = {"Sim", "Não"};
	                    int escolha = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir esse usuário?", "Confirmação",
	                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[1]);

	                    if (escolha == 0) { // Se o usuário escolheu "Sim"
	                        users.remove(userRemover);
	                        JOptionPane.showMessageDialog(null, "Usuário removido.", "Remover Usuário", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        // Operação cancelada pelo usuário
	                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                } else {
	                    // Usuário não encontrado
	                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.", "Remover Usuário", JOptionPane.INFORMATION_MESSAGE);
	                }
	            } catch (NumberFormatException e) {
	                // Tratamento de exceção se o usuário não inserir um número válido
	                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            // Operação cancelada pelo usuário
	            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	
	//-------------------------------------- MÚSICAS ----------------------------------------------
	
	//Método para fazer o cadastro de uma música
	  /*
	public static void criarMusica(ArrayList<Song> songs) {
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
		
		//Cria uma nova Música
		Song novaMusica = new Song(tituloMusica, artistaMusica, anoLancamentoMusica, generoMusica);
		songs.add(novaMusica); //Adiciona no arryalist de songs
	}*/
	   
	public static void criarMusica(ArrayList<Song> songs) {
	        String tituloMusica = JOptionPane.showInputDialog(null, "Insira o título da música:", "Criar Música", JOptionPane.PLAIN_MESSAGE);

	        if (tituloMusica != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
	            String artistaMusica = JOptionPane.showInputDialog(null, "Artista:", "Criar Música", JOptionPane.PLAIN_MESSAGE);

	            if (artistaMusica != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
	                String anoLancamentoInput = JOptionPane.showInputDialog(null, "Ano de lançamento:", "Criar Música", JOptionPane.PLAIN_MESSAGE);

	                if (anoLancamentoInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
	                    try {
	                        int anoLancamentoMusica = Integer.parseInt(anoLancamentoInput);

	                        String generoMusica = JOptionPane.showInputDialog(null, "Gênero:", "Criar Música", JOptionPane.PLAIN_MESSAGE);

	                        if (generoMusica != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
	                            // Cria uma nova Música
	                            Song novaMusica = new Song(tituloMusica, artistaMusica, anoLancamentoMusica, generoMusica);
	                            songs.add(novaMusica); // Adiciona no ArrayList de songs

	                            JOptionPane.showMessageDialog(null, "Música criada com sucesso.", "Criar Música", JOptionPane.INFORMATION_MESSAGE);
	                        } else {
	                            // Operação cancelada pelo usuário
	                            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
	                        }
	                    } catch (NumberFormatException e) {
	                        // Tratamento de exceção se o usuário não inserir um número válido para o ano de lançamento
	                        JOptionPane.showMessageDialog(null, "Por favor, digite um ano de lançamento válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else {
	                    // Operação cancelada pelo usuário
	                    JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
	                }
	            } else {
	                // Operação cancelada pelo usuário
	                JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
	            }
	        } else {
	            // Operação cancelada pelo usuário
	            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
	        }
	}   
	
	/*
	//Método para listar as músicas existentes
	public static void listarMusicas(ArrayList<Song> songs) {
		System.out.println("----- MÚSICAS -----");
		for (Song song : songs) {
			System.out.println(song.getCod() + " - " + song.getTitulo() + " - " + song.getArtista());
			System.out.println("------------------------");
		}
	}
	
	//Método para a pessoa pode escolher uma música (será útil para outros métodos)
	public static Song escolherMusica(ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Escolha uma música :");

	    for (int i = 0; i < songs.size(); i++) {
	        System.out.println((i + 1) + ": " + songs.get(i).getTitulo());
	    }

	    int musicaIndex = scanner.nextInt(); //Escolhe uma música através do seu index
	    scanner.nextLine(); // Limpar a nova linha após a leitura do número

	    if (musicaIndex >= 1 && musicaIndex <= songs.size()) {
	        return songs.get(musicaIndex - 1);
	    } else {
	        System.out.println("Opção inválida. Música não escolhida.");
	        return null;
	    }
	}
	
	//Permite visualizar informações de uma música a partir da escolha por seu indice
	public static void buscarMusica(ArrayList<Song> songs) {
		Song musicaSelecionada = escolherMusica(songs);
		System.out.println("Título: " + musicaSelecionada.getTitulo());
		System.out.println("Artista: " + musicaSelecionada.getArtista());
		System.out.println("Ano de Lançamento: " + musicaSelecionada.getAnoLancamento());
		System.out.println("Gênero: " + musicaSelecionada.getGenero());
		System.out.println("Código da Música: " + musicaSelecionada.getCod());
	}
	
	//Verifica músicas existentes a partir de seu nome, mostrando suas informações correspondentes
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
	
	//Método para atualizar dados de uma música por seu código
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
						System.out.println("Título atualizado.");
					}
				}
				break;
			case 2:
				for(Song song : songs) {
					if(song.getCod() == codMus) {
						System.out.println("Digite o(a) novo(a) artista: ");
						String newArtista = scanner.nextLine();
						song.setArtista(newArtista);
						System.out.println("Artista atualizado(a).");
					}
				}
				break;
			case 3:
				for(Song song : songs) {
					if(song.getCod() == codMus) {
						System.out.println("Digite o novo ano de lançamento: ");
						int newAno = scanner.nextInt();
						song.setAnoLancamento(newAno);
						System.out.println("Ano de lançamento atualizado.");
					}
				}
				break;
			case 4:
				for(Song song : songs) {
					if(song.getCod() == codMus) {
						System.out.println("Digite o novo gênero: ");
						String newGenero = scanner.nextLine();
						song.setGenero(newGenero);
						System.out.println("Gênero atualizado.");
					}	
				}
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} else {
			System.out.println("Música não encontrada.");
		}
	}
	
	//Método para remover uma música por seu código
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
	            System.out.println("Música removida.");
	        } else {
	            return;
	        }
	    } else {
	        System.out.println("Música não encontrada.");
	    }
	}
	*/
	public static void listarMusicas(ArrayList<Song> songs) {
        StringBuilder musicasText = new StringBuilder("----- MÚSICAS -----\n");
        for (Song song : songs) {
            musicasText.append(song.getCod()).append(" - ").append(song.getTitulo()).append(" - ").append(song.getArtista()).append("\n");
            musicasText.append("------------------------\n");
        }
        JOptionPane.showMessageDialog(null, musicasText.toString(), "Listar Músicas", JOptionPane.PLAIN_MESSAGE);
    }

    public static Song escolherMusica(ArrayList<Song> songs) {
        String escolhaMusicaInput = JOptionPane.showInputDialog(null, "Escolha uma música: ", "Escolher Música", JOptionPane.PLAIN_MESSAGE);

        if (escolhaMusicaInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
            try {
                int musicaIndex = Integer.parseInt(escolhaMusicaInput);

                if (musicaIndex >= 1 && musicaIndex <= songs.size()) {
                    return songs.get(musicaIndex - 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Música não escolhida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    public static void buscarMusica(ArrayList<Song> songs) {
        Song musicaSelecionada = escolherMusica(songs);
        if (musicaSelecionada != null) {
            String infoMusica = "Título: " + musicaSelecionada.getTitulo() + "\n" +
                    "Artista: " + musicaSelecionada.getArtista() + "\n" +
                    "Ano de Lançamento: " + musicaSelecionada.getAnoLancamento() + "\n" +
                    "Gênero: " + musicaSelecionada.getGenero() + "\n" +
                    "Código da Música: " + musicaSelecionada.getCod();
            JOptionPane.showMessageDialog(null, infoMusica, "Informações da Música", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void verificarMusica(ArrayList<Song> songs) {
        String musicaNome = JOptionPane.showInputDialog(null, "Digite o nome da música:", "Verificar Música", JOptionPane.PLAIN_MESSAGE);
        boolean musicaEncontrada = false;

        for (Song song : songs) {
            if (song.getTitulo().equalsIgnoreCase(musicaNome)) {
                String infoMusica = "Título: " + song.getTitulo() + "\n" +
                        "Artista: " + song.getArtista() + "\n" +
                        "Ano de Lançamento: " + song.getAnoLancamento() + "\n" +
                        "Gênero: " + song.getGenero() + "\n" +
                        "Código da Música: " + song.getCod();
                JOptionPane.showMessageDialog(null, infoMusica, "Informações da Música", JOptionPane.PLAIN_MESSAGE);
                musicaEncontrada = true;
            }
        }

        if (!musicaEncontrada) {
            JOptionPane.showMessageDialog(null, "Música não encontrada.", "Verificar Música", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void atualizarMusica(ArrayList<Song> songs) {
        String codMusInput = JOptionPane.showInputDialog(null, "Insira o código da música:", "Atualizar Música", JOptionPane.PLAIN_MESSAGE);

        if (codMusInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
            try {
                int codMus = Integer.parseInt(codMusInput);
                boolean musicaEncontrada = false;

                for (Song song : songs) {
                    if (song.getCod() == codMus) {
                        musicaEncontrada = true;
                        break;
                    }
                }

                if (musicaEncontrada) {
                    String[] opcoes = {"Título", "Artista", "Ano de Lançamento", "Gênero"};
                    int escolha = JOptionPane.showOptionDialog(null, "Atualizar:", "Escolha uma opção",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

                    switch (escolha) {
                        case 0: // Título
                            for (Song song : songs) {
                                if (song.getCod() == codMus) {
                                    String newTitulo = JOptionPane.showInputDialog(null, "Digite o novo título:", "Atualizar Título", JOptionPane.PLAIN_MESSAGE);
                                    if (newTitulo != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                                        song.setTitulo(newTitulo);
                                        JOptionPane.showMessageDialog(null, "Título atualizado.", "Atualizar Título", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        case 1: // Artista
                            for (Song song : songs) {
                                if (song.getCod() == codMus) {
                                    String newArtista = JOptionPane.showInputDialog(null, "Digite o(a) novo(a) artista:", "Atualizar Artista", JOptionPane.PLAIN_MESSAGE);
                                    if (newArtista != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                                        song.setArtista(newArtista);
                                        JOptionPane.showMessageDialog(null, "Artista atualizado(a).", "Atualizar Artista", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        case 2: // Ano de Lançamento
                            for (Song song : songs) {
                                if (song.getCod() == codMus) {
                                    String newAnoInput = JOptionPane.showInputDialog(null, "Digite o novo ano de lançamento:", "Atualizar Ano de Lançamento", JOptionPane.PLAIN_MESSAGE);
                                    if (newAnoInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                                        try {
                                            int newAno = Integer.parseInt(newAnoInput);
                                            song.setAnoLancamento(newAno);
                                            JOptionPane.showMessageDialog(null, "Ano de lançamento atualizado.", "Atualizar Ano de Lançamento", JOptionPane.INFORMATION_MESSAGE);
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Por favor, digite um ano de lançamento válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        case 3: // Gênero
                            for (Song song : songs) {
                                if (song.getCod() == codMus) {
                                    String newGenero = JOptionPane.showInputDialog(null, "Digite o novo gênero:", "Atualizar Gênero", JOptionPane.PLAIN_MESSAGE);
                                    if (newGenero != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                                        song.setGenero(newGenero);
                                        JOptionPane.showMessageDialog(null, "Gênero atualizado.", "Atualizar Gênero", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Música não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um código de música válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void removerMusica(ArrayList<Song> songs) {
        String idRemMusInput = JOptionPane.showInputDialog(null, "Informe o código da música que você quer remover:", "Remover Música", JOptionPane.PLAIN_MESSAGE);

        if (idRemMusInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
            try {
                int idRemMus = Integer.parseInt(idRemMusInput);
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
                    String[] opcoes = {"Sim", "Não"};
                    int escolha = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir essa música?", "Confirmação",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[1]);

                    if (escolha == 0) { // Se o usuário escolheu "Sim"
                        songs.remove(musRemover);
                        JOptionPane.showMessageDialog(null, "Música removida.", "Remover Música", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Operação cancelada pelo usuário
                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Música não encontrada.", "Remover Música", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um código de música válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
	//---------------------------------------- PLAYLISTS ---------------------------------------------------
	
    /*
	//Método para criar uma playlist, dando um nome e um proprietário(user) para ela
	public static void criarPlaylist(ArrayList<Playlist> playlists, ArrayList<User> users) {
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
	
	//Lista as playlsit existentes
	public static void listarPlaylists(ArrayList<Playlist> playlists) {
		System.out.println("----- PLAYLISTS -----");
		for (Playlist playlist : playlists) {
			System.out.println(playlist.getCod() + " " + playlist.getNome() + " - " + playlist.getProprietario().getNome());
			System.out.println("------------------------");
		}
	}
	
	//Permite escolher uma playlist pelo index (será útil em outros métodos)
	public static Playlist escolherPlaylist(ArrayList<Playlist> playlists) {
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
	
	//Adiciona uma música em uma playlist criada
	public static void adicionarMusicaPlaylist(ArrayList<Playlist> playlists, ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		if (songs.isEmpty() || playlists.isEmpty()) {
	        System.out.println("Não há músicas ou playlists disponíveis para adicionar.");
	        return;
	    }
	    Song musicaEscolhida = escolherMusica(songs);
	    Playlist playlistEscolhida = escolherPlaylist(playlists);
	    playlistEscolhida.adicionarMusica(musicaEscolhida);
	    System.out.println("Música adicionada à playlist com sucesso!");
	}
	
	//Remove uma música de uma playlist
	public static void removerMusicaPlaylist(ArrayList<Playlist> playlists, ArrayList<Song> songs) {
		Scanner scanner = new Scanner(System.in);
		
		if (songs.isEmpty() || playlists.isEmpty()) {
	        System.out.println("Não há músicas ou playlists disponíveis para remover.");
	        return;
	    }
	    Song musicaEscolhida = escolherMusica(songs);
	    Playlist playlistEscolhida = escolherPlaylist(playlists);
	    playlistEscolhida.removerMusica(musicaEscolhida);
	    System.out.println("Música removida da playlist com sucesso!");
	}
	
	//Lista as músicas existentes em uma playlist
	public static void listarMusicasPlaylist(ArrayList<Playlist> playlists) {
		Scanner scanner = new Scanner(System.in);
		
		if(playlists.isEmpty()) {
			System.out.println("Não há playlist disponíveis.");
			return;
		}
		
		Playlist playlistSelecionada = escolherPlaylist(playlists);
		playlistSelecionada.exibirMusicas();
		
	}
	
	//Busca uma playlist por meio da escolha do index
	public static void buscarPlaylist(ArrayList<Playlist> playlists) {
		Playlist playlistSelecionada = escolherPlaylist(playlists);
		System.out.println("Nome: " + playlistSelecionada.getNome());
		System.out.println("Proprietário: " + playlistSelecionada.getProprietario().getNome());
		System.out.println("Código da Playlist: " + playlistSelecionada.getCod());
		playlistSelecionada.exibirMusicas();
	}
	
	//Verifica se uma playlist existe e mostra suas informações
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
	
	//Método para atualizar dados de uma playlist
	public static void atualizarPlaylist(ArrayList<Playlist> playlists, ArrayList<User> users) {
		Scanner scanner = new Scanner(System.in);
		boolean playlistEncontrada = false;
		
		System.out.println("Digite o código da Playlist");
		int codPla = scanner.nextInt();
		
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
	
	//Método para remover uma playlist
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
	*/
    public static void criarPlaylist(ArrayList<Playlist> playlists, ArrayList<User> users) {
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há usuários!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String nomePlaylist = JOptionPane.showInputDialog(null, "Nome da Playlist:", "Criar Playlist", JOptionPane.PLAIN_MESSAGE);

            if (nomePlaylist != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
                StringBuilder userOptions = new StringBuilder("Escolha o proprietário da playlist (digite o número do usuário):");
                for (int i = 0; i < users.size(); i++) {
                    userOptions.append("\n").append(i + 1).append(": ").append(users.get(i).getNome());
                }

                try {
                    int proprietarioIndex = Integer.parseInt(JOptionPane.showInputDialog(null, userOptions.toString(), "Criar Playlist", JOptionPane.PLAIN_MESSAGE));
                    if (proprietarioIndex >= 1 && proprietarioIndex <= users.size()) {
                        User proprietario = users.get(proprietarioIndex - 1);
                        Playlist novaPlaylist = new Playlist(nomePlaylist, proprietario);
                        playlists.add(novaPlaylist);
                        JOptionPane.showMessageDialog(null, "Playlist criada com sucesso!", "Criar Playlist", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida. Playlist não criada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void listarPlaylists(ArrayList<Playlist> playlists) {
        StringBuilder playlistsText = new StringBuilder("----- PLAYLISTS -----\n");
        for (Playlist playlist : playlists) {
            playlistsText.append(playlist.getCod()).append(" ").append(playlist.getNome()).append(" - ").append(playlist.getProprietario().getNome()).append("\n");
            playlistsText.append("------------------------\n");
        }
        JOptionPane.showMessageDialog(null, playlistsText.toString(), "Listar Playlists", JOptionPane.PLAIN_MESSAGE);
    }

    public static Playlist escolherPlaylist(ArrayList<Playlist> playlists) {
        String escolhaPlaylistInput = JOptionPane.showInputDialog(null, "Escolha uma playlist:", "Escolher Playlist", JOptionPane.PLAIN_MESSAGE);

        if (escolhaPlaylistInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
            try {
                int playlistIndex = Integer.parseInt(escolhaPlaylistInput);

                if (playlistIndex >= 1 && playlistIndex <= playlists.size()) {
                    return playlists.get(playlistIndex - 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Playlist não escolhida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }
    
    public static void adicionarMusicaPlaylist(ArrayList<Playlist> playlists, ArrayList<Song> songs) {
        if (songs.isEmpty() || playlists.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há músicas ou playlists disponíveis para adicionar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Song musicaEscolhida = escolherMusica(songs);
        Playlist playlistEscolhida = escolherPlaylist(playlists);

        if (musicaEscolhida != null && playlistEscolhida != null) {
            playlistEscolhida.adicionarMusica(musicaEscolhida);
            JOptionPane.showMessageDialog(null, "Música adicionada à playlist com sucesso!", "Adicionar Música", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void removerMusicaPlaylist(ArrayList<Playlist> playlists, ArrayList<Song> songs) {
        if (songs.isEmpty() || playlists.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há músicas ou playlists disponíveis para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Song musicaEscolhida = escolherMusica(songs);
        Playlist playlistEscolhida = escolherPlaylist(playlists);

        if (musicaEscolhida != null && playlistEscolhida != null) {
            playlistEscolhida.removerMusica(musicaEscolhida);
            JOptionPane.showMessageDialog(null, "Música removida da playlist com sucesso!", "Remover Música", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void listarMusicasPlaylist(ArrayList<Playlist> playlists) {
        if (playlists.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há playlists disponíveis.", "Listar Músicas da Playlist", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Playlist playlistSelecionada = escolherPlaylist(playlists);
        if (playlistSelecionada != null) {
            JOptionPane.showMessageDialog(null, playlistSelecionada.exibirMusicas().toString(), "Listar Músicas da Playlist", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
 // Busca uma playlist por meio da escolha do índice
    public static void buscarPlaylist(ArrayList<Playlist> playlists) {
        Playlist playlistSelecionada = escolherPlaylist(playlists);

        if (playlistSelecionada != null) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("Nome: ").append(playlistSelecionada.getNome()).append("\n");
            mensagem.append("Proprietário: ").append(playlistSelecionada.getProprietario().getNome()).append("\n");
            mensagem.append("Código da Playlist: ").append(playlistSelecionada.getCod()).append("\n");
            mensagem.append("Músicas na Playlist:").append("\n").append(playlistSelecionada.exibirMusicas());

            JOptionPane.showMessageDialog(null, mensagem.toString(), "Detalhes da Playlist", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Verifica se uma playlist existe e mostra suas informações
    public static void verificarPlaylist(ArrayList<Playlist> playlists) {
        if (playlists.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há playlists disponíveis.", "Verificar Playlist", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nomePlaylist = JOptionPane.showInputDialog(null, "Digite o nome da playlist:", "Verificar Playlist", JOptionPane.QUESTION_MESSAGE);

        if (nomePlaylist == null) {
            // Usuário cancelou a operação
            return;
        }

        boolean playlistEncontrada = false;

        for (Playlist playlist : playlists) {
            if (playlist.getNome().equalsIgnoreCase(nomePlaylist)) {
                playlistEncontrada = true;

                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Nome: ").append(playlist.getNome()).append("\n");
                mensagem.append("Proprietário: ").append(playlist.getProprietario().getNome()).append("\n");
                mensagem.append("Código da Playlist: ").append(playlist.getCod()).append("\n");

                if (playlist.getSongs().isEmpty()) {
                    mensagem.append("Não há músicas nessa playlist.");
                } else {
                    mensagem.append("Músicas na Playlist:").append("\n").append(playlist.exibirMusicas());
                }

                JOptionPane.showMessageDialog(null, mensagem.toString(), "Detalhes da Playlist", JOptionPane.PLAIN_MESSAGE);
                break;
            }
        }

        if (!playlistEncontrada) {
            JOptionPane.showMessageDialog(null, "Playlist não encontrada.", "Verificar Playlist", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void atualizarPlaylist(ArrayList<Playlist> playlists, ArrayList<User> users) {
        boolean playlistEncontrada = false;
        String codPlaInput = JOptionPane.showInputDialog(null, "Digite o código da Playlist:", "Atualizar Playlist", JOptionPane.PLAIN_MESSAGE);

        if (codPlaInput != null) { // Se o usuário clicou em "OK" e não em "Cancelar"
            try {
                int codPla = Integer.parseInt(codPlaInput);

                for (Playlist playlist : playlists) {
                    if (playlist.getCod() == codPla) {
                        playlistEncontrada = true;
                        break;
                    }
                }

                if (playlistEncontrada) {
                    String[] opcoes = {"Nome", "Usuário"};
                    int opcaoSelecionada = JOptionPane.showOptionDialog(null, "Atualizar:", "Atualizar Playlist", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

                    switch (opcaoSelecionada) {
                        case 0:
                            for (Playlist playlist : playlists) {
                                if (playlist.getCod() == codPla) {
                                    String newNome = JOptionPane.showInputDialog(null, "Insira o novo nome:", "Atualizar Playlist", JOptionPane.PLAIN_MESSAGE);
                                    if (newNome != null) {
                                        playlist.setNome(newNome);
                                        JOptionPane.showMessageDialog(null, "Nome atualizado!", "Atualizar Playlist", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        case 1:
                            for (Playlist playlist : playlists) {
                                if (playlist.getCod() == codPla) {
                                    String newProIdInput = JOptionPane.showInputDialog(null, "Digite o ID do novo proprietário:", "Atualizar Playlist", JOptionPane.PLAIN_MESSAGE);
                                    if (newProIdInput != null) {
                                        try {
                                            int newProId = Integer.parseInt(newProIdInput);
                                            User newPro = null;

                                            for (User user : users) {
                                                if (user.getId() == newProId) {
                                                    newPro = user;
                                                    break;
                                                }
                                            }

                                            if (newPro != null) {
                                                playlist.setProprietario(newPro);
                                                JOptionPane.showMessageDialog(null, "Proprietário atualizado!", "Atualizar Playlist", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Usuário com ID " + newProId + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Playlist não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um código de playlist válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void removerPlaylist(ArrayList<Playlist> playlists) {
        if (playlists.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há playlists disponíveis.", "Remover Playlist", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String inputCodigo = JOptionPane.showInputDialog(null, "Digite o código da playlist que você quer remover:", "Remover Playlist", JOptionPane.QUESTION_MESSAGE);

        if (inputCodigo == null) {
            // Usuário cancelou a operação
            return;
        }

        try {
            int codigoRemover = Integer.parseInt(inputCodigo);

            Playlist playlistRemover = null;

            for (Playlist playlist : playlists) {
                if (playlist.getCod() == codigoRemover) {
                    playlistRemover = playlist;
                    break;
                }
            }

            if (playlistRemover != null) {
                int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir essa playlist?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    playlists.remove(playlistRemover);
                    JOptionPane.showMessageDialog(null, "Playlist removida com sucesso!", "Remover Playlist", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Playlist não encontrada.", "Remover Playlist", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, digite um código válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
	//----------------------------------- PRINCIPAL -------------------------------------------------------

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<User> users = new ArrayList<>();
		ArrayList<Song> songs = new ArrayList<>();
		ArrayList<Playlist> playlists = new ArrayList<>();
		
		/*
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
		*/
		
		int sair = 0;
		
		do {
			
			String opcaoStr = JOptionPane.showInputDialog(null, "===== MENU =====\n1- Usuário\n2- Música\n3- Playlist\n4- Sair", "Menu", JOptionPane.QUESTION_MESSAGE);
			//System.out.println("===== MENU =====");
			//System.out.println("1- Usuário\n2- Música\n3- Playlist\n4- Sair");
			//int opcao = scanner.nextInt();
			if (opcaoStr != null) {
				try {
					int opcao = Integer.parseInt(opcaoStr);
					if (opcao == 1) {
						String opUserStr = JOptionPane.showInputDialog(null, "Qual operação deseja realizar?\n1- Cadastrar\n2- Listar\n3- Buscar\n4- Verificar\n5- Atualizar\n6- Remover\n7- Sair", "Operações", JOptionPane.QUESTION_MESSAGE);
						//System.out.println("Qual operação deseja realizar?\n1- Cadastrar\n2- Listar\n3- Buscar\n4- Verificar\n5- Atualizar\n6- Remover\n7- Sair");
						//int opUser = scanner.nextInt();
						try {
							int opUser = Integer.parseInt(opUserStr);
							switch(opUser) {
							case 1:
								cadastrarUser(users);
								break;
							case 2:
								listarUsers(users);
								break;
							case 3:
								buscarUser(users, playlists);
								break;
							case 4:
								verificarUser(users, playlists);
								break;
							case 5:
								atualizarUser(users);
								break;
							case 6:
								removerUser(users);
								break;
							case 7:
								sair = 1;
								break;
							default:
								JOptionPane.showMessageDialog(null, "Digite uma opção válida.", "Erro", JOptionPane.ERROR_MESSAGE);
								break;
						}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					} else if (opcao == 2) {
						String opSongStr = JOptionPane.showInputDialog(null, "Qual operação deseja realizar?\n1- Criar\n2- Listar\n3- Buscar\n4- Verificar\n5- Atualizar\n6- Remover\n7- Sair", "Operações", JOptionPane.QUESTION_MESSAGE);
						//System.out.println("Qual operação deseja realizar?\n1- Criar\n2- Listar\n3- Buscar\n4- Verificar\n5- Atualizar\n6- Remover\n7- Sair");
						//int opSong = scanner.nextInt();
						try {
							int opSong = Integer.parseInt(opSongStr);
							switch(opSong) {
							case 1:
								criarMusica(songs);
								break;
							case 2:
								listarMusicas(songs);
								break;
							case 3:
								buscarMusica(songs);
								break;
							case 4:
								verificarMusica(songs);
								break;
							case 5:
								atualizarMusica(songs);
								break;
							case 6:
								removerMusica(songs);
								break;
							case 7:
								sair = 1;
								break;
							default:
								JOptionPane.showMessageDialog(null, "Digite uma opção válida.", "Erro", JOptionPane.ERROR_MESSAGE);
								break;
						}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					} else if (opcao == 3) {
						String opPlstStr = JOptionPane.showInputDialog(null, "Qual operação deseja realizar?\n1- Criar\n2- Listar\n3- Buscar\n4- Verificar\n5- Atualizar\n6- Adicionar Música\n7- Remover Música\n8- Listar Músicas\n9- Remover\n10- Sair", "Operações", JOptionPane.QUESTION_MESSAGE);
						//System.out.println("Qual operação deseja realizar?\n1- Criar\n2- Listar\n3- Buscar\n4- Verificar\n5- Atualizar\n6- Adicionar Música\n7- Remover Música\n8- Listar Músicas\n9- Remover\n10- Sair");
						//int opPlaylist = scanner.nextInt();
						try {
							int opPlaylist = Integer.parseInt(opPlstStr);
							switch(opPlaylist) {
							case 1:
								criarPlaylist(playlists, users);
								break;
							case 2:
								listarPlaylists(playlists);
								break;
							case 3:
								buscarPlaylist(playlists);
								break;
							case 4:
								verificarPlaylist(playlists);
								break;
							case 5:
								atualizarPlaylist(playlists, users);
								break;
							case 6:
								adicionarMusicaPlaylist(playlists, songs);
								break;
							case 7:
								removerMusicaPlaylist(playlists, songs);
								break;
							case 8:
								listarMusicasPlaylist(playlists);
								break;
							case 9:
								removerPlaylist(playlists);
								break;
							case 10:
								sair = 1;
								break;
							default:
								JOptionPane.showMessageDialog(null, "Digite uma opção válida.", "Erro", JOptionPane.ERROR_MESSAGE);
								break;
						}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					} else if (opcao == 4) {
							JOptionPane.showMessageDialog(null, "Saindo... Volte sempre!");
							sair = 1;
							break;
					} else {
						JOptionPane.showMessageDialog(null, "Digite uma opção válida.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		} while (sair == 0);	
	}

}
