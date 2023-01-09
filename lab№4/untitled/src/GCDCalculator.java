import java.util.Scanner;

public class GCDCalculator {                          //"presentation layer" that presents user an application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int a = scanner.nextInt();
        System.out.println("Enter the second number: ");
        int b = scanner.nextInt();

        System.out.println("Would you like to see the steps in the calculation? (y/n)");
        String showSteps = scanner.next();

        int gcd = calculateGCD(a, b, showSteps);
        System.out.println("The GCD of " + a + " and " + b + " is: " + gcd);
    }

    public static int calculateGCD(int a, int b, String showSteps) {       //"Service layer" 
        if (showSteps.equals("y")) {
            System.out.println("Calculating GCD of " + a + " and " + b);
        }
        if (b == 0) {
            return a;
        }
        int aPrime = a % b;
        if (showSteps.equals("y")) {
            System.out.println(a + " mod " + b + " = " + aPrime);
        }
        return calculateGCD(b, aPrime, showSteps);
    }
}
///To find the greatest common divisor (GCD) of two numbers using the Euclid algorithm, you can use the following steps:
///1.Take the larger of the two numbers and assign it to a variable called "a". Take the smaller of the two numbers and assign it to a variable called b.
///2.Calculate the remainder of a divided by b and assign it to a variable called "r"
///3.If "r" is equal to 0, then "b" is the GCD.
///4.If "r" is not equal to 0, set "a" to "b" and "b" to "r", then go back to step 2.