package com.biblioteca.app.repository;

import com.biblioteca.app.model.Livro;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {
    private static final String FILE_NAME = "livros.dat";

    public void salvar(List<Livro> livros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            System.err.println("Erro ao salvar livros: " + e.getMessage());
        }
    }

    public List<Livro> carregar() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Livro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar livros: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
