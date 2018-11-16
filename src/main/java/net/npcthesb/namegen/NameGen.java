package net.npcthesb.namegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameGen {

	private static final String FILE_NAME = "2BNameGen-Result.txt";

	private static List<Character> vowels = new ArrayList<>();
	private static List<Character> consonants = new ArrayList<>();

	private static void initializeLists() {
		vowels = Stream.of('a', 'e', 'i', 'o', 'u').collect(Collectors.toList());
		consonants = Stream.of('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w',
				'x', 'y', 'z').collect(Collectors.toList());
	}

	public static void main(String[] args) {
		initializeLists();
		int time = 0;
		boolean isVowel = false;
		List<Character> name = new ArrayList<>();

		do {
			for (int i = 1; i <= ThreadLocalRandom.current().nextInt(5, 10); i++)
				if (isVowel) {
					name.add(vowels.get(ThreadLocalRandom.current().nextInt(vowels.size())));
					isVowel = false;
				} else {
					name.add(consonants.get(ThreadLocalRandom.current().nextInt(consonants.size())));
					isVowel = true;
				}
			writeLine(FILE_NAME, listToString(name));
			time++;
			name.clear();
			isVowel = false;
		} while (time < 10);
	}

	private static void writeLine(String filePath, String msg) {
		File f = new File(filePath);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
			out.write(msg);
			out.write((System.getProperty("os.name").toLowerCase().contains("win")) ? "\r\n" : "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String listToString(List<Character> list) {
		StringBuilder builder = new StringBuilder();
		for (char value : list)
			builder.append(value);
		return builder.toString();
	}

}
