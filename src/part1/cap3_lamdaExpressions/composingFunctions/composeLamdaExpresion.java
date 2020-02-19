package part1.cap3_lamdaExpressions.composingFunctions;

import java.util.function.Function;

public class composeLamdaExpresion {

    public static void main(String[] args) {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> i = f.compose(g);

        System.out.println(h.apply(1));
        System.out.println(i.apply(1));
    }
}
