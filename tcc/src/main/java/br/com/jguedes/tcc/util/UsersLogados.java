package br.com.jguedes.tcc.util;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import br.com.jguedes.tcc.model.User;

public class UsersLogados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7598804768780436180L;

	private static ConcurrentMap<String, User> usersLogados;

	public static void add(User user) {

		if (usersLogados == null) {

			usersLogados = new ConcurrentHashMap<String, User>();

		}

		usersLogados.put(user.getUserContextoID(), user);

	}

	public static User get(String userContextoID) {

		return usersLogados.get(userContextoID);

	}

}
