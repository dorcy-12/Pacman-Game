package task1;

import java.util.Scanner;

/**
 *
 */
public class Calculator {
    private boolean finish = false;
    static final Scanner scanner = new Scanner(System.in);

    /**
     * @param args not used
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.calculate();

    }

    private static final String showPossibleFunctions = """
            Chose function\s
             [1] Add/Subtract\s
             [2] Multiplication\s
             [3] Division\s
             [4] Exponential Calculation\s
             [5] Logarithm\s
            \s
             [6] Exit\s
            """;

    /**
     *
     */
    protected void calculate() {
        while (!finish) {
            System.out.println(showPossibleFunctions);
            int todo = options();
            if (todo == 6) {
                finish = true;
                return;
            }
            Function function = FunctionFactory.getFunction(todo);
            if (function != null) {
                System.out.println(function.showCalculation());
            } else {
                tryAgainMessage();
            }
            System.out.println("\n");
        }
    }

    private int options() {
        System.out.print("Input: ");
        return getIntFromSystemIn();
    }

    private void tryAgainMessage() {
        System.out.println("Couldn't find this function.");
    }

    /**
     *
     * @return double
     */
    public static double getDoubleFromSystemIn() {
        while(true) {
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                scanner.next();
                System.out.print("Please write an Double: ");
            }
        }
    }

    /**
     *
     * @return int
     */
    public static int getIntFromSystemIn() {
        while(true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.next();
                System.out.print("Please write an Integer: ");
            }
        }
    }
}
