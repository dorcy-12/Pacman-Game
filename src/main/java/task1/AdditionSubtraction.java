package task1;

import static java.lang.Math.abs;

/**
 *
 */
public class AdditionSubtraction implements Function {
    private final String operator;
    private final double operand1;
    private final double operand2;

    /**
     *
     */
    public AdditionSubtraction() {
        operand1 = getOperand(1);
        operand2 = getOperand(2);
        if (operand2 < 0) {
            operator = "-";
        } else {
            operator = "+";
        }
    }

    @Override
    public double calculate() {
        return operand1 + operand2;
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
        return abs(operand2);
    }
}
