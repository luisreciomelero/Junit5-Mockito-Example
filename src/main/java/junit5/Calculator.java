package junit5;

public class Calculator {

    private int result;

    public int add(int a, int b){
        result = a+b;
        return result;
    }

    public int subtract(int a, int b){
        result = a-b;
        return result;
    }

    public int divide(int a, int b){
        if(b==0){
            new ArithmeticException("No se puede dividir entre 0");
        }
        result = a/b;
        return result;
    }

    public int mult(int a, int b){
        result = a*b;
        return result;
    }
}
