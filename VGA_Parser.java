import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VGA_Parser {
    public static void main(String[] args) throws FileNotFoundException {
        String fileContents = loadFile();
        String[] splitFile = fileContents.split("[)],");
        Double[][][] vga_matrix = parseVGA(splitFile);
        System.out.println(vga_matrix);
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
            String[] splitLine = cleanedLine.split(",");
            for (String value : splitLine) {
                valueList.add(Double.parseDouble(value));
            }
        }
        Double[][][] returnValues = new Double[536][70][3];
        for (int i = 0; i < 536; i++) {
            for (int j = 0; j < 70; j++) {
                for (int k = 0; k < 3; k++) {
                    returnValues[i][j][k] = valueList.get(i * j + k);
                }
            }
        }
        return returnValues;
    }
}