package task1;

/**
 *
 */
public class FunctionFactory {
    /**
     *
     * @param function f
     * @return function
     */
    public static Function getFunction(int function) {
        return switch (function) {
            case 1 -> new AdditionSubtraction();
            case 2 -> new Multiplication();
            case 3 -> new Division();
            case 4 -> new ExponentialCalculation();
            case 5 -> new Logarithm();
            default -> null;
        };
    }
}
