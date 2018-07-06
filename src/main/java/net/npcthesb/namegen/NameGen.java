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

	private static List<String> vowels = new ArrayList<String>();
	private static List<String> consonants = new ArrayList<String>();

	private static void initializeLists() {
		vowels = Stream.of("a", "e", "i", "o", "u").collect(Collectors.toList());
		consonants = Stream.of("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w",
				"x", "y", "z").collect(Collectors.toList());
	}

	public static void main(String[] args) {
		createFile("result.txt", true);
		initializeLists();
		int time = 0;
		boolean isVowel = false;
		List<String> name = new ArrayList<String>();
		do {
			for (int i = 1; i <= ThreadLocalRandom.current().nextInt(5, 9 + 1); i++) {
				if (!isVowel) {
					name.add(consonants.get(ThreadLocalRandom.current().nextInt(consonants.size())));
					isVowel = true;
				} else {
					name.add(vowels.get(ThreadLocalRandom.current().nextInt(vowels.size())));
					isVowel = false;
				}
			}
			writeLine("result.txt", printlnList(name));
			time += 1;
			name.clear();
			isVowel = false;
		} while (time < 10);
	}

	private static void createFile(String path, boolean override) {
		File f = new File(path);
		File parent = f.getParentFile();
		if (parent != null && !parent.exists())
			parent.mkdirs();
		if (f.exists())
			if (override == true)
				f.delete();
			else
				return;
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeLine(String filePath, String msg) {
		File f = new File(filePath);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
			out.write(msg);
			out.write("\r\n");
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

	private static String printlnList(List<String> list) {
		StringBuilder builder = new StringBuilder();
		for (String value : list) {
			builder.append(value);
		}
		return builder.toString();
	}

}
