package com.arlania.net.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BetaMode {
	private static final Set<String> betaUsernames = new HashSet<>();
	private static final String BETA_FILE = "./data/saves/BetaUsernames.txt";

	public static void reloadBetaUsers() {
		File file = new File(BETA_FILE);
		if (!file.exists()) {
			System.err.println("Beta file not found");
			return;
		}
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			in.lines().forEach((line) -> {
					if (line.length() > 0) {
						betaUsernames.add(line.toLowerCase());
					}
				}
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean hasAccess(String username) {
		return betaUsernames.contains(username.toLowerCase());
	}
}
