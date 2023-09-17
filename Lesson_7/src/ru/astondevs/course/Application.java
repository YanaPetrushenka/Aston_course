package ru.astondevs.course;

import ru.astondevs.course.exception.MyArrayDataException;
import ru.astondevs.course.exception.MyArraySizeException;

public class Application {
    private static final int LENGTH = 4;
    private static final String LENGTH_ERROR_MESSAGE = "Длина массива не соответствует установленному значению";
    private static final String DATA_ERROR_MESSAGE = "Ошибка преобразования данных - элемент: [%d][%d], значение: %s";

    public static void main(String[] args) {
        Application app = new Application();
        String[][] data = {
                {"4", "3", "2", "1"},
                {"1", "1", "1", "1"},
                {"5", "6", "o", "8"},
                {"9", "9", "9", "9"}
        };
        var sum = 0;

        try {
            sum = app.processData(data);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка обработки массива: " + e.getMessage());
            throw e;
        }

        System.out.println("Сумма всех элементов = " + sum);
    }

    private int processData(String[][] data) {
        if (data.length != LENGTH) {
            throw new MyArraySizeException(LENGTH_ERROR_MESSAGE);
        }

        for (String[] datum : data) {
            if (datum.length != LENGTH) {
                throw new MyArraySizeException(LENGTH_ERROR_MESSAGE);
            }
        }

        int sum = 0;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                try {
                    sum += Integer.parseInt(data[i][j]);
                } catch (NumberFormatException e) {
                    var message = String.format(DATA_ERROR_MESSAGE, i, j, data[i][j]);
                    throw new MyArrayDataException(message);
                }
            }
        }
        return sum;
    }
}