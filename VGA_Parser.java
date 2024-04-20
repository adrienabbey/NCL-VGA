import java.util.ArrayList;
import java.util.Scanner;

// https://stackoverflow.com/a/33088742
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class VGA_Parser {
    public static void main(String[] args) throws IOException {
        String fileContents = loadFile();
        String[] splitFile = fileContents.split("[)],");
        Float[][][] vga_matrix = parseVGA(splitFile);
        generateImage(vga_matrix);
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

    public static Float[][][] parseVGA(String[] input) {
        ArrayList<Float> valueList = new ArrayList<Float>();
        for (String line : input) {
            // System.out.println("Unparsed: " + line);
            String noBrackets = line.replaceAll("[\\[\\](){}]", "");
            String cleanedLine = noBrackets.replaceAll("\\s", "");
            // System.out.println("Cleaned: " + cleanedLine);
            String[] splitLine = cleanedLine.split(",");
            for (String value : splitLine) {
                valueList.add(Float.parseFloat(value));
            }
            // System.out.println("Parsed: " + valueList.get(valueList.size() - 3) + ", "
            // + valueList.get(valueList.size() - 2) + ", " + valueList.get(valueList.size()
            // - 1));
        }
        Float[][][] returnValues = new Float[536][70][3];
        for (int i = 0; i < 536; i++) {
            for (int j = 0; j < 70; j++) {
                for (int k = 0; k < 3; k++) {
                    returnValues[i][j][k] = valueList.get(i * j + k);
                }
            }
        }
        return returnValues;
    }

    public static void generateImage(Float[][][] inputMatrix) throws IOException {
        // https://stackoverflow.com/a/33088742
        BufferedImage image = new BufferedImage(536, 70, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 536; i++) {
            for (int j = 0; j < 70; j++) {
                Color vgaColor = new Color(inputMatrix[i][j][0], inputMatrix[i][j][1], inputMatrix[i][j][2]);
                // System.out.println(inputMatrix[i][j][0] + ", " + inputMatrix[i][j][1] + ", "
                // + inputMatrix[i][j][2]);
                image.setRGB(i, j, vgaColor.getRGB());
            }
        }
        File output = new File("output.jpg");
        ImageIO.write(image, "jpg", output);
    }
}