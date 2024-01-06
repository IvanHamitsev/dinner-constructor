package ru.practicum.dinner;

import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

public class DinnerConstructor {
    private HashMap<String, ArrayList<String>> menuStorage = new HashMap<>(4);
    private Random randomizer = new Random();

    DinnerConstructor(Boolean feelFlag) {
        menuStorage.put("Первое", new ArrayList<>());
        menuStorage.put("Второе", new ArrayList<>());
        menuStorage.put("Десерт", new ArrayList<>());
        menuStorage.put("Напиток", new ArrayList<>());
        if (feelFlag) {
            testFilling();
        }
    }

    void testFilling() {
        menuStorage.get("Первое").add("Солянка");
        menuStorage.get("Первое").add("Борщ");
        menuStorage.get("Второе").add("Макароны");
        menuStorage.get("Второе").add("Каша");
        menuStorage.get("Десерт").add("Беспонтовый пирожок");
        menuStorage.get("Напиток").add("Чай");
        menuStorage.get("Напиток").add("Морс");
    }

    Boolean addNewDish(String dishType, String dishName, StringBuilder message) {
        // В задании чётко не сказано, вроде бы добавляется лишь блюдо, а не тип!
        if (!menuStorage.containsKey(dishType)) {
            message.append("Типа блюда " + dishType + " не существует.");
            return false;
        }

        if (menuStorage.get(dishType).contains(dishName)) {
            message.append("Такое блюдо уже есть в меню");
            return false;
        }

        menuStorage.get(dishType).add(dishName);
        return true;
    }

    Boolean isDishType(String name) {
        return menuStorage.containsKey(name);
    }

    Boolean generateDishCombo(int setLength, ArrayList<String> dishTypes, ArrayList<ArrayList<String>> dishSet
            , StringBuilder resultAnswer) {
        // проверим корректность входных данных
        if (setLength < 1) {
            resultAnswer.append("Как это " + setLength + " комбинаций?");
            return false;
        }

        if (dishTypes.isEmpty()) {
            resultAnswer.append("Из пустого списка блюд каши не сваришь.");
            return false;
        }

        for (int i = 0; i < setLength; i++) {
            dishSet.add(new ArrayList<>());
            for (String dishType : dishTypes) {
                if (menuStorage.get(dishType).isEmpty()) {
                    resultAnswer.append("Это провал, в категории " + dishType + " нет ни одного блюда");
                    dishSet.clear(); // всё что мы до этого нагенерили, стоит очистить т.к. ответ мы не дали
                    return false;
                } else {
                    int num = randomizer.nextInt(menuStorage.get(dishType).size());
                    dishSet.get(i).add(menuStorage.get(dishType).get(num));
                }
            }
        }

        return true;
    }

}
