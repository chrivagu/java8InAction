package part2.cap5_WorkingWithStreams.filteringAndSlicing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FilteringSlicingMatching {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        System.out.println("numbers = " + numbers);

        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        System.out.println("numbers = " + numbers);

        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> cantidadDeLetras = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("cantidadDeLetras = " + cantidadDeLetras);

        words.stream().map(s -> s.length()).collect(Collectors.toList());

        List<Integer> myNumbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squareNumbers = myNumbers.stream().map(s -> s * s).collect(toList());
        System.out.println("squareNumbers = " + squareNumbers);

        /*Given two lists of numbers, how would you return all pairs of numbers? For example,
          given a list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3,3), (3, 4)].
          For simplicity, you can represent a pair as an array with two elements.*/

        List<Integer> firstList = Arrays.asList(1, 2, 3);
        List<Integer> secondList = Arrays.asList(3, 4);

        List<int[]> pairs = firstList
                .stream()
                .flatMap(i -> secondList
                        .stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(k -> new int[]{i, k})
                ).collect(Collectors.toList());

        for (int[] a : pairs) {
            System.out.println("--->" + Arrays.toString(a));
        }

        //int a [][]  = new int[2][3];
        /*
         * 1 4 5
         * 6 7 8
         * */
        // a[0][0] = 1; a[0][1] = 4; a[0][2] = 5;
        // a[1][0] = 6; a[1][1] = 7; a[1][2] = 8;

        int a[][] = {
                {1, 4, 5},
                {6, 7, 8}
        };

        System.out.println("a[0]:" + a[0].length);
        System.out.println("a[1]:" + a[1].length);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(a[i][j]);
            }
        }

    }

}
