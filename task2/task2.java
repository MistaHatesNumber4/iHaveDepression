import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    public static void main(String[] args) {
        double[][] figure = readingFileAndGettingArray(args[0]);
        double[][] points = readingFileAndGettingArray(args[1]);
        double[] A = new double[2];
        double[] B = new double[2];
        double[] C = new double[2];
        double[] D = new double[2];
        for (int i = 0; i < 2; i++) {
            A[i] = figure[0][i];
            B[i] = figure[1][i];
            C[i] = figure[2][i];
            D[i] = figure[3][i];
        }
        for (int i = 0; i < points.length; i++) {
            if (vertex(figure, points[i][0], points[i][1])) {
                System.out.println(i + " на вершине");
                continue;
            }
            String a1 = inOutOn(A[0], A[1], B[0], B[1], points[i][0], points[i][1]);
            String a2 = inOutOn(B[0], B[1], C[0], C[1], points[i][0], points[i][1]);
            String a3 = inOutOn(C[0], C[1], D[0], D[1], points[i][0], points[i][1]);
            String a4 = inOutOn(D[0], D[1], A[0], A[1], points[i][0], points[i][1]);
            if(a1.equals("на стороне") || a2.equals("на стороне") || a3.equals("на стороне") || a4.equals("на стороне")) {
                System.out.println(i + " на стороне");
            } else {
                if (a1.equals("внутри") && a2.equals("внутри") && a3.equals("внутри") && a4.equals("внутри")) {
                    System.out.println(i + " внутри");
                } else {
                    System.out.println(i + " снаружи");
                }
            }

        }
    }

    public static double[][] readingFileAndGettingArray(String path) {
        String i;
        String[] t = new String[100];
        int counter = 0;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                i = line;
                t[counter] = i;
                counter++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] a = new String[counter];
        System.arraycopy(t, 0, a, 0, counter);
        double[][] array = new double[counter][2];
        String[] temp;
        for (int j = 0; j < a.length; j++) {
            temp = a[j].split(" ");
            for (int k = 0; k < 2; k++) {
                array[j][k] = Double.parseDouble(temp[k]);
            }
        }
        return array;
    }

    public static String inOutOn(double x1, double y1, double x2, double y2, double x3, double y3) {
        double d;
        d = (x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1);
        if (d == 0) {
            return "на стороне";
        } else if (d > 0) {
            return "внутри";
        } else if (d < 0) {
            return "снаружи";
        } else {
            return "no";
        }
    }

    public static boolean vertex(double[][] figure, double x, double y) {
        for (int i = 0; i < 4; i++) {
            int j = 0;
            if (figure[i][j] == x && figure[i][j + 1] == y) {
                return true;
            }
        }
        return false;
    }


}
