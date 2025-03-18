package com.example.spring.utils.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public final class ArgonHash {
	private static final Argon2 argon2 = Argon2Factory.create();

	/**
	 * ! Chú ý:
	 * * Đặt càng nhiều vòng, Ram thì verify càng lâu
	 * * => response càng lâu
	 * ? => giảm vòng, tăng thread Cpu
	 */

	public static String hash(char[] password) {
		return argon2.hash(1, 1 * 1024, 2, password); // 1 vòng, 1MB RAM, 1 luồng CPU
	}

	public static Boolean verify(String hashedPassword, char[] inputPassword) {
		Boolean isValid = argon2.verify(hashedPassword, inputPassword);

		if (!isValid)
			throw new Error();

		return isValid;
	}
}
