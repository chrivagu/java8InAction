package part2.cap8_RefactoringTestingDebugging;

interface ValidationStrategy {
    boolean execute(String s);
}

/*class IsAllLowerCase implements ValidationStrategy {
    public boolean execute(String s){
        return s.matches("[a-z]+");
    }
}

class IsNumeric implements ValidationStrategy {
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}*/

class Validator {
    private final ValidationStrategy strategy;
    public Validator(ValidationStrategy v){
        this.strategy = v;
    }
    public boolean validate(String s){
        return strategy.execute(s);
    }

    public static void main(String[] args) {
       /*Validator numericValidator = new Validator(new IsNumeric());
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase ()); */

        /*El codigo comentado (las clases y las 2 lineas anteriores) es aquel que se puede eliminar
        de este patron si se utilizan las siguientes lineas.*/

        Validator numericValidator = new Validator((String s) -> s.matches("\\d+"));
        Validator lowerCaseValidator = new Validator((String s) -> s.matches("[a-z]+"));

        boolean b1 = numericValidator.validate("aaaa");
        boolean b2 = lowerCaseValidator.validate("bbbb");

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
    }

}
