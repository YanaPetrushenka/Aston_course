package ru.astondevs.course;

public class Application {
    public static void main(String[] args) {
        var appDataManager = new AppDataManager();

        String[] headers = {"header_1", "header_2"};
        int[][] values = {{1, 2}, {3, 4}};

        AppData appData = new AppData();
        appData.setHeader(headers);
        appData.setData(values);

        appDataManager.save(appData);
        appData.getData();
    }
}
