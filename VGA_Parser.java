import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VGA_Parser {
    public static void main(String[] args) throws FileNotFoundException {
        String fileContents = loadFile();
        String[] splitFile = fileContents.split("[)],");
        double[][][] vga_matrix;
        parseVGA(splitFile);
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

    public static Double[][][] parseVGA(String[] input) {
        ArrayList<Double> valueList = new ArrayList<Double>();
        for (String line : input) {
            String noBrackets = line.replaceAll("[\\[\\](){}]", "");
            String cleanedLine = noBrackets.replaceAll("\\s", "");
            // System.out.println(cleanedLine);
            String[] splitLine = cleanedLine.split(",");
            for (String value : splitLine) {
                valueList.add(Double.parseDouble(value));
            }
        }
        // System.out.println(valueList.size());
    }
}