package part2.cap6_CollectingDataWithStreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

class Partitioning {

    public static void main(String[] args) {
        List<Dish> menu = Dish.getMenu();
        Map<Boolean, List<Dish>> partitionedMenu = menu
                .stream().collect(partitioningBy(Dish::isVegetarian));

        System.out.println("partitionedMenu = " + partitionedMenu);

        List<Dish> vegeterianDishes = partitionedMenu.get(true);
        System.out.println("vegeterianDishes = " + vegeterianDishes);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu
                .stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType, toList())
                ));

        System.out.println("vegetarianDishesByType = " + vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu
                .stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)
                ));

        System.out.println("mostCaloricPartitionedByVegetarian = " + mostCaloricPartitionedByVegetarian);


        Map<Boolean, Map<Boolean, List<Dish>>> quiz1 = menu.stream().collect(partitioningBy(
                Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 500)
        ));

        System.out.println("quiz1 = " + quiz1);

        Map<Boolean, Long> quiz3 = menu.stream().collect(partitioningBy(
                Dish::isVegetarian,
                counting()
        ));

        System.out.println("quiz3 = " + quiz3);

        // prime Numbers: 2,3,5,7,11,13,17,19..
        Boolean isPrime = isPrime(19);
        System.out.println("isPrime = " + isPrime);

        int calories = menu.stream().collect(reducing(
                0, Dish::getCalories, Integer::sum
        ));

        System.out.println("calories = " + calories);

    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidateRoot % i == 0);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

}