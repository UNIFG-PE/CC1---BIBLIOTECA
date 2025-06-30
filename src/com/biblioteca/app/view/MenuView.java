package com.biblioteca.app.view;

import com.biblioteca.app.model.Livro;
import com.biblioteca.app.model.Usuario;
import com.biblioteca.app.service.BibliotecaService;

import java.util.List;
import java.util.Scanner;

public class MenuView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final BibliotecaService service = new BibliotecaService();

	public static void exibirMenuPrincipal() {
		int opcao;
		do {
			System.out.println("\n======= Biblioteca ========");
			System.out.println("1 - Gerenciar Livros");
			System.out.println("2 - Gerenciar Usuários");
			System.out.println("3 - Empréstimo/Devolução");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");
			System.out.println("\n==========================");
			opcao = Integer.parseInt(scanner.nextLine());

			switch (opcao) {
			case 1 -> menuLivros();
			case 2 -> menuUsuarios();
			case 3 -> menuEmprestimos();
			case 0 -> System.out.println("Saindo...");
			default -> System.out.println("Opção inválida.");
			}
		} while (opcao != 0);
	}

	private static void menuLivros() {
		System.out.println("\n--- Gerenciar Livros ---");
		System.out.println("1. Adicionar");
		System.out.println("2. Listar");
		System.out.println("3. Atualizar");
		System.out.println("4. Remover");
		System.out.print("Escolha: ");
		int opcao = Integer.parseInt(scanner.nextLine());

		switch (opcao) {
		case 1 -> adicionarLivro();
		case 2 -> listarLivros();
		case 3 -> atualizarLivro();
		case 4 -> removerLivro();
		default -> System.out.println("Opção inválida.");
		}
	}

	private static void adicionarLivro() {
		System.out.print("Título: ");
		String titulo = scanner.nextLine();
		System.out.print("Autor: ");
		String autor = scanner.nextLine();
		System.out.print("ISBN: ");
		String isbn = scanner.nextLine();
		System.out.print("Ano: ");
		int ano = Integer.parseInt(scanner.nextLine());

		Livro livro = new Livro(titulo, autor, isbn, ano);
		if (service.adicionarLivro(livro)) {
			System.out.println("Livro adicionado.");
		} else {
			System.out.println("Livro já cadastrado.");
		}
	}

	private static void listarLivros() {
		List<Livro> livros = service.listarLivros();
		livros.forEach(System.out::println);
	}

	private static void atualizarLivro() {
		System.out.print("ISBN do livro: ");
		String isbn = scanner.nextLine();

		Livro livro = service.buscarLivro(isbn);
		if (livro == null) {
			System.out.println("Livro não encontrado.");
			return;
		}

		System.out.print("Novo título: ");
		livro.setTitulo(scanner.nextLine());
		System.out.print("Novo autor: ");
		livro.setAutor(scanner.nextLine());
		System.out.print("Novo ano: ");
		livro.setAnoPublicacao(Integer.parseInt(scanner.nextLine()));

		service.atualizarLivro(isbn, livro);
		System.out.println("Livro atualizado.");
	}

	private static void removerLivro() {
		System.out.print("ISBN do livro: ");
		String isbn = scanner.nextLine();
		if (service.removerLivro(isbn)) {
			System.out.println("Livro removido.");
		} else {
			System.out.println("Erro ao remover livro.");
		}
	}

	private static void menuUsuarios() {
		System.out.println("\n--- Gerenciar Usuários ---");
		System.out.println("1. Adicionar");
		System.out.println("2. Listar");
		System.out.println("3. Remover");
		System.out.print("Escolha: ");
		int opcao = Integer.parseInt(scanner.nextLine());

		switch (opcao) {
		case 1 -> adicionarUsuario();
		case 2 -> listarUsuarios();
		case 3 -> removerUsuario();
		default -> System.out.println("Opção inválida.");
		}
	}

	private static void adicionarUsuario() {
		System.out.print("ID: ");
		String id = scanner.nextLine();
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		Usuario usuario = new Usuario(id, nome);
		if (service.adicionarUsuario(usuario)) {
			System.out.println("Usuário adicionado.");
		} else {
			System.out.println("Usuário já cadastrado.");
		}
	}

	private static void listarUsuarios() {
		List<Usuario> usuarios = service.listarUsuarios();
		usuarios.forEach(System.out::println);
	}

	private static void removerUsuario() {
		System.out.print("ID do usuário: ");
		String id = scanner.nextLine();
		if (service.removerUsuario(id)) {
			System.out.println("Usuário removido.");
		} else {
			System.out.println("Erro ao remover usuário.");
		}
	}

	private static void menuEmprestimos() {
		System.out.println("\n--- Empréstimo/Devolução ---");
		System.out.println("1. Emprestar");
		System.out.println("2. Devolver");
		System.out.print("Escolha: ");
		int opcao = Integer.parseInt(scanner.nextLine());

		switch (opcao) {
		case 1 -> emprestarLivro();
		case 2 -> devolverLivro();
		default -> System.out.println("Opção inválida.");
		}
	}

	private static void emprestarLivro() {
		System.out.print("ISBN do livro: ");
		String isbn = scanner.nextLine();
		System.out.print("ID do usuário: ");
		String id = scanner.nextLine();

		if (service.emprestarLivro(isbn, id)) {
			System.out.println("Empréstimo realizado.");
		} else {
			System.out.println("Erro no empréstimo.");
		}
	}

	private static void devolverLivro() {
		System.out.print("ISBN do livro: ");
		String isbn = scanner.nextLine();
		System.out.print("ID do usuário: ");
		String id = scanner.nextLine();

		if (service.devolverLivro(isbn, id)) {
			System.out.println("Devolução realizada.");
		} else {
			System.out.println("Erro na devolução.");
		}
	}
}