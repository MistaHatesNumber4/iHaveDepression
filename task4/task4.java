import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class task4 {
    public static void main(String[] args) {
        howManyPeopleInTheBank(localTimeArray(readTheFile(args[0])));

        doll(getKeyByValue(howManyPeopleInTheBank(localTimeArray(readTheFile(args[0]))), analize(howManyPeopleInTheBank(localTimeArray(readTheFile(args[0]))))), readTheFile(args[0]));
    }

    public static String[][] readTheFile(String path) {
        int counter = 0;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                counter++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] array = new String[counter][2];
        counter = 0;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] temp;
            while (line != null) {
                temp = line.split(" ");
                System.arraycopy(temp, 0, array[counter], 0, 2);
                counter++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static LocalTime testDate(String strDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
        return LocalTime.parse(strDate, dtf);

    }

    public static LocalTime[][] localTimeArray(String[][] firstArray) {
        LocalTime[][] arr = new LocalTime[firstArray.length][2];
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < firstArray.length; i++) {
                arr[i][j] = testDate(firstArray[i][j]);
            }
        }
        return arr;
    }

    public static Map<Integer, Integer> howManyPeopleInTheBank(LocalTime[][] localTimeArr) {
        int temp;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < localTimeArr.length; i++) {
            temp = 0;
            for (int j = 0; j < localTimeArr.length; j++) {
                if (localTimeArr[i][0].isBefore(localTimeArr[j][1]) && localTimeArr[i][0].isAfter(localTimeArr[j][0])
                        || localTimeArr[i][0].equals(localTimeArr[j][0]) && j != i) {
                    temp++;
                }
//                if (j == i) {
//                    break;
//                }
            }

            map.put(i, temp);
        }
        return map;
    }

    public static int analize(Map<Integer, Integer> map) {
        int temp;
        int max = 0;
        for (int i = 0; i < map.size(); i++) {
            temp = map.get(i);
            if (temp > max) {
                max = temp;
            }

        }
        return max;
    }

    public static ArrayList<Integer> getKeyByValue(Map<Integer, Integer> map, int value) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    public static void doll(ArrayList<Integer> arrayList, String[][] data) {
        int i = 0;
        int start = 0;
        int counter = 0;
        while (i < arrayList.size() - 1) {
            if (arrayList.get(i + 1) - arrayList.get(i) == 1) {
                while (arrayList.get(counter + 1) - arrayList.get(counter) == 1) {
                    counter++;
                    i++;
                }
                System.out.println(data[arrayList.get(start)][0] + " " + data[arrayList.get(i)][1]);
                start = i + 1;
            } else {
                System.out.println(data[arrayList.get(i + 1)][0] + " " + data[arrayList.get(i + 1 )][1]);
                i++;
            }
        }
        if (arrayList.size() == 1) {
            System.out.println(data[arrayList.get(0)][0] + " " + data[arrayList.get(0)][1]);
        }
//        System.out.println(arrayList);
    }
}