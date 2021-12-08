import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class task3 {
    public static void main(String[] args) {
        int counter = 0;
        double[][] table = new double[16][5];
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 16; j++) {
              table[j][counter] = readingFileAndGettingArray(args[0] + "Cash" + i + ".TXT")[j];
            }
            counter++;
        }
        System.out.println(maxAvg(gettingArrayOfAvgs(table)));
    }

    public static double[] readingFileAndGettingArray(String path) {
        double i;
        int counter = 0;
        double[] array = new double[16];
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                i = Double.parseDouble(line);
                array[counter] = i;
                counter++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
    public static double[] gettingArrayOfAvgs(double[][] table) {
        double t;
        double[] arrayOfAvg = new double[16];
        for (int k = 0; k < 16; k++) {
            t = 0;
            for (int i = 0; i < 5; i++) {
                t = t + table[k][i];
                arrayOfAvg[k] = t / 5;
            }
        }
        return arrayOfAvg;
    }
    public static int maxAvg(double[] array) {
        int t = 0;
        double max = 0;
        for (int i = 0; i < 16; i++) {
            if (array[i] > max) {
                max = array[i];
                t = i + 1;
            }
        }
        return t;
    }
}
