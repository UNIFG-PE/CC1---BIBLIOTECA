package com.biblioteca.app.repository;

import com.biblioteca.app.model.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
	private static final String FILE_NAME = "usuarios.dat";

	public void salvar(List<Usuario> usuarios) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(usuarios);
		} catch (IOException e) {
			System.err.println("Erro ao salvar usuários: " + e.getMessage());
		}
	}

	public List<Usuario> carregar() {
		File file = new File(FILE_NAME);
		if (!file.exists())
			return new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (List<Usuario>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Erro ao carregar usuários: " + e.getMessage());
			return new ArrayList<>();
		}
	}
}
