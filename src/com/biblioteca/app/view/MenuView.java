package com.biblioteca.app.view;

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
		System.out.println("\n==========================");
		int opcao = Integer.parseInt(scanner.nextLine());

		switch (opcao) {
		case 1 -> adicionarLivro();
		case 2 -> listarLivros();
		case 3 -> atualizarLivro();
		case 4 -> removerLivro();
		default -> System.out.println("Opção inválida.");
		}
	}