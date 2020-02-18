package part2FunctionalStyleDataProcessing.lamdaExpressions.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

class Apple {
    public Integer weight;
    public String color;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "color:" + color + " " + "weight:" + weight;
    }
}

class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}


class Main {
    public static void main(String[] args) {
        Apple apple1 = new Apple("green", 150);
        Apple apple2 = new Apple("blue", 50);
        Apple apple3 = new Apple("red", 100);
        Apple apple4 = new Apple("black", 75);
        Apple apple5 = new Apple("white", 80);

        List<Apple> inventory = new ArrayList<>();
        inventory.add(apple1);
        inventory.add(apple2);
        inventory.add(apple3);
        inventory.add(apple4);
        inventory.add(apple5);

        System.out.println("inventory = " + inventory);

            /* Usando un comparator tradicional
            inventory.sort(new AppleComparator());
            System.out.println("inventory sort = " + inventory);*/

            /*Usando anonymous class
            inventory.sort(new Comparator<Apple>() {
                @Override
                public int compare(Apple o1, Apple o2) {
                    return o1.getWeight().compareTo(o2.getWeight());
                }
            });

            System.out.println("inventory sort clase anonima = " + inventory);*/

            /*Usando lambda expression
            inventory.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));
            System.out.println("inventory with lambda expression = " + inventory);*/

        /*Using comparing*/

        //Comparator loco = Comparator.comparing((Apple a) -> a.getWeight());
        //inventory.sort(loco);

        //De una forma mas concisa
        //inventory.sort(comparing((a) -> a.getWeight()));
        inventory.sort(comparing(Apple::getWeight));

        System.out.println("inventory with comparing = " + inventory);
    }
}

