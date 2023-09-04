package ru.astondevs.course;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Lesson_3 {

    public static void main(String[] args) {
        var lesson3 = new Lesson_3();
        var random = new Random();

        //Task 1
        lesson3.checkSum(random.nextInt(15), random.nextInt(15));

        //Task 2
        lesson3.checkNumberSignAndPrint(random.nextInt());

        //Task 3
        lesson3.checkNumberSign(random.nextInt());

        //Task 4
        lesson3.printMessage("Привет", 5);

        //Task 5
        lesson3.isLeapYear(2023);

        //Task 6
        int[] arrTask6 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        lesson3.exchangeUnitsAndZeros(arrTask6);

        //Task 7
        lesson3.createAndInitArray();

        //Task 8
        lesson3.multiplyByTwo();

        //Task 9
        lesson3.diagonals();

        //Task 10
        lesson3.createArray(random.nextInt(10), random.nextInt());
    }

    //Task 1
    private boolean checkSum(int numb1, int numb2) {
        var sum = numb1 + numb2;
        var inLimit = sum >= 10 && sum <= 20;
        System.out.printf("Сумма чисел %d и %d лежит в пределе от 10 до 20: %b%n%n", numb1, numb2, inLimit);
        return inLimit;
    }

    //Task 2
    private void checkNumberSignAndPrint(int numb) {
        var message = numb >= 0 ? "число положительное" : "число отрицательное";
        System.out.printf("%d: %s%n%n", numb, message);
    }

    //Task 3
    private boolean checkNumberSign(int numb) {
        var isPositive = numb >= 0;
        System.out.printf("Число %d положительное: %b%n%n", numb, isPositive);
        return isPositive;
    }

    //Task 4
    private void printMessage(String message, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
        System.out.println();
    }

    //Task 5
    private boolean isLeapYear(int year) {
        var isLeap = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
        System.out.printf("Год %d високосный: %b%n%n", year, isLeap);
        return isLeap;
    }

    //Task 6
    private void exchangeUnitsAndZeros(int[] arrNum) {
        System.out.printf("Исходный массив:\t\t%s%n", Arrays.toString(arrNum));

        for (int i = 0; i < arrNum.length; i++) {
            if (arrNum[i] == 0) {
                arrNum[i] = 1;
            } else if (arrNum[i] == 1) {
                arrNum[i] = 0;
            }
        }

        System.out.printf("Обновленный массив:\t\t%s%n%n", Arrays.toString(arrNum));
    }

    //Task 7
    private void createAndInitArray() {
        int[] intArray = new int[100];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i + 1;
        }

        System.out.println("Массив 100 элементов: " + Arrays.toString(intArray) + "\n");
    }

    //Task 8
    private void multiplyByTwo() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.printf("Исходный массив:\t\t%s%n", Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }

        System.out.printf("Обновленный массив:\t\t%s%n%n", Arrays.toString(arr));
    }

    //Task 9
    private void diagonals() {
        int[][] twoArr = {
                {17, 18, 19, 6},
                {20, 21, 22, 8},
                {23, 24, 25, 7},
                {30, 32, 33, 34}
        };
        var lastIndex = twoArr.length - 1;

        for (int i = 0; i <= lastIndex; i++) {
            for (int j = 0; j < twoArr[i].length; j++) {
                if ((i == j) || ((lastIndex - j) == i)) {
                    twoArr[i][j] = 1;
                }
            }
        }

        //pretty print to console
        for (int i = 0; i < twoArr.length; i++) {
            for (int j = 0; j < twoArr[i].length; j++) {
                System.out.print(twoArr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Task 10
    private int[] createArray(int length, int initialValue) {
        var arr = IntStream.generate(() -> initialValue)
                .limit(length)
                .toArray();

        System.out.printf("Массив создан (длина=%d, значение=%d): %s", length, initialValue, Arrays.toString(arr));
        return arr;
    }
}