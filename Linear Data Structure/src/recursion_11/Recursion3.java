package recursion_11;

public class Recursion3 {
    public int NthTermOfFibonacciSeries(int n){
        //case--1
        if (n==1){
            return 1;
        }
        if (n==0){
            return 0;
        }
      return NthTermOfFibonacciSeries(n-1) + NthTermOfFibonacciSeries(n-2);
    }
    public void printBinary(int decimal){
        if (decimal==0){
            return;
        }
        printBinary(decimal/2);
        System.out.print(decimal%2);
    }

    public void printOctal(int decimal){
        if (decimal==0){
            return;
        }
        printOctal(decimal/8);
        System.out.print(decimal%8);
    }

    public void reverseNumber(int n){
        if (n==0){
            return;
        }
        System.out.print(n%10);
        reverseNumber(n/10);
    }

    public int sumOfSquare(int n){
        if (n==0){
            return 0;
        }
        int s=0;
        return sumOfSquare(n-1) + n*n;


    }


}

class TestRecursion3{
    public static void main(String[] args) {
        Recursion3 recursion3 = new Recursion3();
        System.out.println(recursion3.NthTermOfFibonacciSeries(10));


        System.out.println("-------------------");
        recursion3.printBinary(10);
        System.out.println();

        System.out.println("-------------------");
        recursion3.printOctal(55);

        System.out.println();
        System.out.println("-------------------");
        recursion3.reverseNumber(123);

        System.out.println();
        System.out.println("-------------------");
        System.out.println(recursion3.sumOfSquare(3));


    }



}
