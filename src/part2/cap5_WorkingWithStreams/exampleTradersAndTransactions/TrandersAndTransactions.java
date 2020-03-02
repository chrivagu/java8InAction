package part2.cap5_WorkingWithStreams.exampleTradersAndTransactions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class TrandersAndTransactions {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

/*1 Find all transactions in the year 2011 and sort them by value (small to high).
  2 What are all the unique cities where the traders work?
  3 Find all traders from Cambridge and sort them by name.
  4 Return a string of all traders’ names sorted alphabetically.
  5 Are any traders based in Milan?
  6 Print all transactions’ values from the traders living in Cambridge.
  7 What’s the highest value of all the transactions?
  8 Find the transaction with the smallest value. */

        /*1. */
        List<Transaction> firstQuestion = transactions
                .stream()
                .filter(y -> y.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("firstQuestion = " + firstQuestion);

        /*2. */
        List<String> secondQuestion = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("secondQuestion = " + secondQuestion);

        /*3. */
        List<Trader> thirdQuestion = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("thirdQuestion = " + thirdQuestion);

        /*4. */
        List<String> fourthQuestion = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("fourthQuestion = " + fourthQuestion);

        /*5. */
        boolean fifthQuestion = transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("fifthQuestion = " + fifthQuestion);

        /*6. */
        transactions
                .stream()
                .filter(s -> "Cambridge".equals(s.getTrader().getCity()))
                .forEach(s -> System.out.println(s));

        /*7. */
        Optional<Transaction> seventhQuestion = transactions
                .stream()
                .max(Comparator.comparing(Transaction::getValue));
        System.out.println("seventhQuestion = " + seventhQuestion);

        Optional<Integer> seventhQuestion2 = transactions
                .stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::max);
        System.out.println("seventhQuestion2 = " + seventhQuestion2);

        /*8. */
        Optional eighthQuestion = transactions
                .stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println("eighthQuestion = " + eighthQuestion);
    }
}

class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}

class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}