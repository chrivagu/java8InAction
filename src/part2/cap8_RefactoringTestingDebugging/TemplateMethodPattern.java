package part2.cap8_RefactoringTestingDebugging;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class Customer {

    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}


class Database {
    public static List<Customer> allUsers = new ArrayList<>();

    static {
        Customer c1 = new Customer(100, "pepito");
        Customer c2 = new Customer(200, "mario");
        Customer c3 = new Customer(300, "camilo");
        Customer c4 = new Customer(400, "lucas");
        Customer c5 = new Customer(500, "juan");

        allUsers.add(c1);
        allUsers.add(c2);
        allUsers.add(c3);
        allUsers.add(c4);
        allUsers.add(c5);
    }

    public static Customer getCustomerWithId(int id) {

        return allUsers.stream()
                .filter(customer -> customer.getId()==id)
                .findFirst()
                .get();
    }
}

abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);
}


class Bancolombia extends OnlineBanking {
    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println("Hello " + c.getName() + " ganaste un bono bancolombia.");
    }
}


class Main  {
    public static void main(String[] args) {
        OnlineBanking online = new Bancolombia();
        online.processCustomer(400);
        new Main().processCustomer(400, (c) -> System.out.println("Hello " + c.getName() + " ganaste un bono bancoModerno."));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

}



