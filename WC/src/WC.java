import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WC {

	public static void wordCount(List<String> content) throws IOException {
		int chars = 0;
		for (String line : content) {
			chars += line.split(" ").length;
		}
		System.out.println("Characters: " + chars);
	}

	public static void lineCount(List<String> content) throws IOException {
		int lines = content.size();
		System.out.println("Lines: " + lines);

	}

	public static void charsCount(List<String> content) throws IOException {
		int size = 0;
		for (String line : content) {
			size += line.length();
		}
		System.out.println("Chars: " + size);
	}

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(args[0]);
		List<String> content = Files.readAllLines(path,
				Charset.defaultCharset());
		String command = "";

		if (args.length < 2) {
			wordCount(content);
			lineCount(content);
			charsCount(content);
		} else {
			command = args[1];
		}
		if (command.equals("-l")) {
			lineCount(content);
		} else if (command.equals("-w")) {
			wordCount(content);
		} else if (command.equals("-c")) {
			charsCount(content);
		}
	}
}
