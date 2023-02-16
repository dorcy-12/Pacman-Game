package task1;

/**
 *
 */
public class Logarithm implements Function {
    private final double operand1;
    private final double operand2;
    private final String operator;

    /**
     *
     */
    public Logarithm() {
        operand1 = getOperand(1);
        operand2 = getOperand(2);
        operator = "log";
    }
    @Override
    public double calculate() {
        return Math.log(operand2) / Math.log(operand1);
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

    @Override
    public String showCalculation() {
        return "log"+operand1+"("+operand2+") = " + calculate();
    }

}
