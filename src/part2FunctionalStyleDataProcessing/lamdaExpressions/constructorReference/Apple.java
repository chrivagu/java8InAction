package part2FunctionalStyleDataProcessing.lamdaExpressions.constructorReference;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.function.Function;

public class Apple {
    public int weights;

    public Apple(Integer integer) {
        this.weights = integer;
    }

    @Override
    public String toString(){
        return String.valueOf(this.weights);
    }

    public static void main(String[] args) {
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = miMetodo(weights, Apple::new);
        System.out.println(apples);
    }

    public static List<Apple> miMetodo(List<Integer> list,
                                       Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer e: list){
            result.add(f.apply(e));
        }
        return result;
    }
}
