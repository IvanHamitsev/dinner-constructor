package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dinnerConstructor; // стандартное имя dc малоинформативно
    static Scanner scanner;

    public static void main(String[] args) {

        dinnerConstructor = new DinnerConstructor(true);
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();
        // хочется изменяемую строку без тёмных приёмов рефлексии
        StringBuilder message = new StringBuilder();

        if (dinnerConstructor.addNewDish(dishType, dishName, message)) {
            System.out.println("Блюдо успешно добавлено.");
        } else {
            System.out.println(message);
        }
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();
        ArrayList<String> typesList = new ArrayList<>();
        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            if (!dinnerConstructor.isDishType(nextItem)) {
                System.out.println("Такого типа блюда нет, повторите ввод");
            } else {
                typesList.add(nextItem);
            }
            nextItem = scanner.nextLine();
        }
        ArrayList<ArrayList<String>> resultMenu = new ArrayList<>();
        StringBuilder message = new StringBuilder();
        if (dinnerConstructor.generateDishCombo(numberOfCombos, typesList, resultMenu, message)) {
            int i = 1;
            for (ArrayList<String> menu : resultMenu) {
                System.out.println("Комбо " + (i++));
                System.out.println(menu);
            }
            System.out.println("");
        } else {
            System.out.println(message);
        }
    }
}
