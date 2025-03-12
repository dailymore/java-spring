package com.example.spring.utils.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public final class ArgonHash {
	private static final Argon2 argon2 = Argon2Factory.create();

	public static String hash(char[] password) {
		return argon2.hash(10, 65536, 1, password); // 10 vòng, 64MB RAM, 1 luồng CPU
	}

	public static boolean verify(String hashedPassword, char[] inputPassword) {
		return argon2.verify(hashedPassword, inputPassword);
	}
}
