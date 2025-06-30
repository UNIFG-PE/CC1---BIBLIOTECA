package com.biblioteca.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nome;
    private List<Livro> livrosEmprestados;

    public Usuario(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(List<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }

    public void adicionarLivroEmprestado(Livro livro) {
        if (livro != null && !livrosEmprestados.contains(livro)) {
            this.livrosEmprestados.add(livro);
        }
    }

    public void removerLivroEmprestado(Livro livro) {
        if (livro != null) {
            this.livrosEmprestados.remove(livro);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario: "
                + "ID = '"
                + id
                + '\''
                + ", Nome = '"
                + nome
                + '\''
                + ", livros Emprestados = "
                + livrosEmprestados.size()
                + " !!";
    }
}