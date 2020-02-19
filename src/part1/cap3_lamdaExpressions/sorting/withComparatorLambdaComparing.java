package part1.cap3_lamdaExpressions.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

class Apple {
    public Integer weight;
    public String color;
    public String country;

    public Integer getWeight() {
        return weight;
    }

    public String getCountry() {
        return country;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(String color, Integer weight, String country) {
        this.color = color;
        this.weight = weight;
        this.country = country;
    }

    @Override
    public String toString() {
        return "color:" + color + " " + "weight:" + weight + " " + "country:" + country;
    }
}

class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}


class Main {
    public static void main(String[] args) {
        Apple apple1 = new Apple("green", 150, "peru");
        Apple apple2 = new Apple("blue", 50, "colombia");
        Apple apple3 = new Apple("red", 100, "chile");
        Apple apple4 = new Apple("black", 75, "nicaragua");
        Apple apple5 = new Apple("white", 80, "ecuador");
        Apple apple6 = new Apple("white", 80, "brasil");


        List<Apple> inventory = new ArrayList<>();
        inventory.add(apple1);
        inventory.add(apple2);
        inventory.add(apple3);
        inventory.add(apple4);
        inventory.add(apple5);
        inventory.add(apple6);

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
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry));

        System.out.println("inventory with comparing: ");
        System.out.println(inventory);
    }
}

