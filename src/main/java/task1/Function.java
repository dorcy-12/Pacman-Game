package task1;

/**
 *
 */
public interface Function {
    /**
     *
     * @return double
     */
    double calculate();

    /**
     *
     * @return string
     */
    String getOperator();

    /**
     *
     * @return double
     */
    double getOperand1();

    /**
     *
     * @return double
     */
    double getOperand2();

    /**
     *
     * @param number int
     * @return double
     */
    default double getOperand(int number) {
        System.out.print("- Operand " + number+ ": ");
        return Calculator.getDoubleFromSystemIn();
    }

    /**
     *
     * @return string
     */
    default String showCalculation() {
        return "==> " + getOperand1() + " " + getOperator() + " " + getOperand2() + " = " + calculate();
    }
}
