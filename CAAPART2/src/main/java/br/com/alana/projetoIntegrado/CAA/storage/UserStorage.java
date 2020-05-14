package br.com.alana.projetoIntegrado.CAA.storage;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {

	private static UserStorage instance;
	private Set<String> users;

	private UserStorage() {
		users = new HashSet<>();
	}

	public static synchronized UserStorage getInstance() {

		if (instance == null) {
			instance = new UserStorage();
		}

		return instance;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUser(String CPF) throws Exception {
		if (users.contains(CPF)) {
			throw new Exception("Usuario ja existe" + CPF);
		}
		users.add(CPF);
	}
}