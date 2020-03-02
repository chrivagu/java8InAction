package part2.cap5_WorkingWithStreams.Reducing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SummingElements {
    public static void main(String[] args) {
        List<Integer> myNumbers = Arrays.asList(6, 8, 4, 9, 3);
        int sum = myNumbers
                .stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("sum = " + sum);
        
        Optional<Integer> max = myNumbers.stream().reduce((x, y) -> x<y?y:x);
        System.out.println("max = " + max);

        Optional<Integer> max2 = myNumbers.stream().reduce(Integer::max);
        System.out.println("max2 = " + max2);

        Optional<Integer> min = myNumbers.stream().reduce((x, y) -> x<y?x:y);
        System.out.println("min = " + min);

        Optional<Integer> min2 = myNumbers.stream().reduce(Integer::min);
        System.out.println("min2 = " + min2);
    }

}
