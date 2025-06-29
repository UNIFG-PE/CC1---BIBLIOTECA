package com.biblioteca.app.service;

import com.biblioteca.app.model.Livro;
import com.biblioteca.app.model.Usuario;
import com.biblioteca.app.repository.LivroRepository;
import com.biblioteca.app.repository.UsuarioRepository;

import java.util.*;

public class BibliotecaService {

    private Map<String, Livro> livros = new HashMap<>();
    private Map<String, Usuario> usuarios = new HashMap<>();

    private LivroRepository livroRepository = new LivroRepository();
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public BibliotecaService() {
        carregarDados();
    }

    private void carregarDados() {
        livroRepository.carregar().forEach(livro -> livros.put(livro.getIsbn(), livro));
        usuarioRepository.carregar().forEach(usuario -> usuarios.put(usuario.getId(), usuario));
    }

    private void salvarDados() {
        livroRepository.salvar(new ArrayList<>(livros.values()));
        usuarioRepository.salvar(new ArrayList<>(usuarios.values()));
    }

    // CRUD Livros
    public boolean adicionarLivro(Livro livro) {
        if (livros.containsKey(livro.getIsbn()))
            return false;
        livros.put(livro.getIsbn(), livro);
        salvarDados();
        return true;
    }

    public Livro buscarLivro(String isbn) {
        return livros.get(isbn);
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livros.values());
    }

    public boolean atualizarLivro(String isbn, Livro livroAtualizado) {
        if (!livros.containsKey(isbn))
            return false;
        livros.put(isbn, livroAtualizado);
        salvarDados();
        return true;
    }

    public boolean removerLivro(String isbn) {
        Livro livro = livros.get(isbn);
        if (livro == null)
            return false;

        boolean emprestado = usuarios.values().stream().anyMatch(u -> u.getLivrosEmprestados().contains(livro));

        if (emprestado) {
            System.out.println("Livro emprestado. NÃ£o pode ser removido.");
            return false;
        }

        livros.remove(isbn);
        salvarDados();
        return true;
    }
}