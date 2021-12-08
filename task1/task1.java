import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class task1 {

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(bubbleSort(readingFileAndGettingArray("C:\\Users\\rudes_2.0\\Desktop\\task.TXT"))));
        double[] sorted = bubbleSort(readingFileAndGettingArray(args[0]));
        System.out.printf("%.2f%n", percentile(sorted, 90));
        System.out.printf("%.2f%n", median(sorted));
        System.out.printf("%.2f%n", max(sorted));
        System.out.printf("%.2f%n", min(sorted));
        System.out.printf("%.2f%n", avg(sorted));
    }

    public static double[] readingFileAndGettingArray(String path) {
        double i;
        double[] a = new double[1000];
        int counter = 0;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                i = Double.parseDouble(line);
                a[counter] = i;
                counter++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[] array = new double[counter];
        System.arraycopy(a, 0, array, 0, counter);
        return array;
    }

    public static double[] bubbleSort(double[] array) {
        int c = array.length;
        for (int i = 0; i < array.length; i++) {
            if (c > 0) {
                for (int j = 0; j < c - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        double tmp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = tmp;
                    }
                }
            }
            c--;
        }
        return array;
    }

    public static double percentile(double[] array, int p) {
        double x = (array.length - 1) * (p * 0.01) + 1;
        int v = (int) x;
        return array[v - 1] + (x - v) * (array[v] - array[v - 1]);
    }

    public static double median(double[] array) {
        if (array.length % 2 == 1) {
            return array[array.length / 2];
        } else {
            return (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        }
    }

    public static double max(double[] array) {
        return array[array.length - 1];
    }

    public static double min(double[] array) {
        return array[0];
    }

    public static double avg(double[] array) {
        double sum = 0;
        for (double v : array) {
            sum = sum + v;
        }
        return sum / array.length;
    }

}
