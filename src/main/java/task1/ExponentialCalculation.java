package task1;

/**
 *
 */
public class ExponentialCalculation implements Function{
    private final double operand1;
    private final double operand2;
    private final String operator;

    /**
     *
     */
    public ExponentialCalculation() {
        operand1 = getOperand( 1);
        operand2 = getOperand(2);
        operator = "^";
    }
    @Override
    public double calculate() {
        return Math.pow(operand1, operand2);
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public double getOperand1() {
        return operand1;
    }

    @Override
    public double getOperand2() {
        return operand2;
    }
}
