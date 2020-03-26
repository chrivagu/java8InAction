package part2.cap6_CollectingDataWithStreams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class Grouping {

    public static void main(String[] args) {

        List<Dish> menu = Dish.getMenu();

        Map<Dish.CaloricLevel, List<Dish>> dishedByColoriesLevel = menu.stream().collect(
                groupingBy(s -> {
                    if (s.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                    else if (s.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                }, toList()));
        System.out.println("dishedByColoriesLevel = " + dishedByColoriesLevel);

        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu
                .stream()
                .collect(
                        groupingBy(
                                Dish::getType,
                                groupingBy(
                                        s -> {
                                            if (s.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                            else if (s.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                            else return Dish.CaloricLevel.FAT;
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

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelByType = menu
                .stream()
                .collect(groupingBy(
                        Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                    else return Dish.CaloricLevel.FAT;
                                }, toSet()
                        )
                ));

        System.out.println("caloricLevelByType = " + caloricLevelByType);

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelByType1 = menu
                .stream()
                .collect(groupingBy(
                        Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                    else return Dish.CaloricLevel.FAT;
                                }, toCollection(HashSet::new)
                        )
                ));

        System.out.println("caloricLevelByType1 = " + caloricLevelByType1);


    }
}