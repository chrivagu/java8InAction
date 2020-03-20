package part2.cap6_CollectingDataWithStreams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Type {MEAT, FISH, OTHER}

    public enum CaloricLevel {DIET, NORMAL, FAT}

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        Map<CaloricLevel, List<Dish>> dishedByColoriesLevel = menu.stream().collect(
                groupingBy(s -> {
                    if (s.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (s.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, toList()));
        System.out.println("dishedByColoriesLevel = " + dishedByColoriesLevel);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu
                .stream()
                .collect(
                        groupingBy(
                                Dish::getType,
                                groupingBy(
                                        s -> {
                                            if (s.getCalories() <= 400) return CaloricLevel.DIET;
                                            else if (s.getCalories() <= 700) return CaloricLevel.NORMAL;
                                            else return CaloricLevel.FAT;
                                        }
                                        , toList())
                        )
                );

        System.out.println("dishesByTypeCaloricLevel = " + dishesByTypeCaloricLevel);


        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));

        System.out.println("typesCount = " + typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))
                        ));

        System.out.println("mostCaloricByType = " + mostCaloricByType);


        Map<Dish.Type, Dish> mostCaloricByType1 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)), Optional::get
                        )));

        System.out.println("mostCaloricByType1 = " + mostCaloricByType1);

        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)
                ));

        System.out.println("totalCaloriesByType = " + totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType = menu
                .stream()
                .collect(groupingBy(
                        Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }, toSet()
                        )
                ));

        System.out.println("caloricLevelByType = " + caloricLevelByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType1 = menu
                .stream()
                .collect(groupingBy(
                        Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }, toCollection(HashSet::new)
                        )
                ));

        System.out.println("caloricLevelByType1 = " + caloricLevelByType1);


    }
}