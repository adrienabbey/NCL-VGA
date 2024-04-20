import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VGA_Parser {
    public static void main(String[] args) throws FileNotFoundException {
        String fileContents = loadFile();
        String[] splitFile = fileContents.split("[)],");
        for (String line : splitFile) {
            System.out.println(line);
        }
    }

    public static String loadFile() throws FileNotFoundException {
        File file = new File("vga.px");
        Scanner fileScanner = new Scanner(file);
        String fileContents = "";
        while (fileScanner.hasNextLine()) {
            fileContents = fileScanner.nextLine();
        }
        fileScanner.close();
        return fileContents;
    }
}