package ru.astondevs.course;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AppDataManager {
    private final static String CSV_FILE = "app_data.csv";
    private final static String DELIMETER = ";";
    private final int HEADER_INDEX = 0;

    public void save(AppData appData) {
        List<String> lines = new ArrayList<>();
        var headerLine = strArrToLine(appData.getHeader());
        lines.add(headerLine);
        var valuesArr = appData.getData();

        for (int[] values : valuesArr) {
            var line = intArrToLine(values);
            lines.add(line);
        }

        try {
            Files.write(Paths.get(CSV_FILE), lines);
        } catch (IOException e) {
            System.err.println("Ошибка записи файла " + CSV_FILE);
            throw new RuntimeException(e);
        }
    }

    public AppData get() {
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(CSV_FILE));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + CSV_FILE);
            throw new RuntimeException(e);
        }

        var appData = new AppData();
        appData.setHeader(lines.get(HEADER_INDEX).split(DELIMETER));
        int[][] values = new int[lines.size() - 1][];

        for (int i = 1; i < lines.size(); i++) {
            values[i] = lineToIntArr(lines.get(i));
        }

        appData.setData(values);

        return appData;
    }

    private String strArrToLine(String[] arr) {
        return String.join(DELIMETER, arr);
    }

    private String intArrToLine(int[] intArr) {
        var strArr = new String[intArr.length];

        for (int i = 0; i < intArr.length; i++) {
            strArr[i] = Integer.toString(intArr[i]);
        }

        return strArrToLine(strArr);
    }

    private String[] lineToStrArr(String line) {
        return line.split(DELIMETER);
    }

    private int[] lineToIntArr(String line) {
        var strArr = lineToStrArr(line);
        var intArr = new int[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        return intArr;
    }
}
