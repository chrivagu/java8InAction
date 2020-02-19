package part1.cap3_lamdaExpressions.constructorReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

class Fruit {
    public int weights;
    public String name;

    public Fruit() {
    }
}

class Apple extends Fruit {

    public Apple(Integer weights) {
        this.weights = weights;
    }

    public Apple(Integer weights, String name) {
        this.weights = weights;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Apple:" + this.weights + " - " + this.name;
    }


    public static List<Apple> withFunction(List<Integer> list,
                                           Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e));
        }
        return result;
    }

    public static List<Apple> withBiFunction(List<Integer> list,
                                             BiFunction<Integer, String, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e, "green"));
        }
        return result;
    }
}

class Orange extends Fruit {

    public Orange(Integer weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return "Orange:" + this.weights + " - " + this.name;
    }
}


class Main {
    public static void main(String[] args) {
        /*Function a BiFuction Example*/
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = Apple.withFunction(weights, Apple::new);
        System.out.println("apples = " + apples);
        List<Apple> apples2 = Apple.withBiFunction(weights, Apple::new);
        System.out.println("apples2 = " + apples2);

        System.out.println("------------------------------------------");
        Fruit fruta1 = giveMeFruit("orange", weights.get(0));
        Fruit fruta2 = giveMeFruit("apple", weights.get(1));
        System.out.println(fruta1);
        System.out.println(fruta2);
    }

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static Fruit giveMeFruit(String key, Integer weight) {
        /*El metodo map.get dado el key retorna el valor, en este caso una funtional interface
         * En este caso una Function la cual recibe un Integer (weight) y retorna una Fruit*/
        return map.get(key.toLowerCase())
                .apply(weight);
    }
}