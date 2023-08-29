package cc.robotdreams.lessons.lesson22;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Calculator
{
    static final private Logger logger = LogManager.getLogger(Calculator.class);

    static public Calculator parse(String expression) {
        String[] parts = expression
                .trim() // Remove whitespaces at the begin and end of string
                .split("\\s+"); // Split by one or more whitespaces
        String action = null;
        Calculator result = null;
        for (int i = 0; i < parts.length; i ++) {
            String part = parts[i].trim();
            if (part.matches("\\d+")) { // If part is integer
                Integer val = Integer.parseInt(part);
                if (i == 0)
                    result = new Calculator(val);
                else {
                    if (action == null || result == null)
                        throw new WrongExpressionFormatException(expression);
                    switch(action) {
                        case "*":
                            result.multiply(val);
                            break;
                        case "/":
                            result.divide(val);
                            break;
                        case "+":
                            result.plus(val);
                            break;
                        case "-":
                            result.minus(val);
                            break;
                    }
                    action = null;
                }
            } else if (part.matches("[+-\\/\\*]")) { // If part in one of chars: + - * /
                if (i == 0 || action != null)
                    throw new WrongExpressionFormatException(expression);
                action = part;
            } else if (part.isEmpty()) {
                // Skip
            } else
                throw new WrongExpressionFormatException(expression);
        }
        if (action != null)
            throw new WrongExpressionFormatException(expression);

        logger.debug(Arrays.asList(parts));
        if (result == null)
            throw new WrongExpressionFormatException(expression);
        return result;
    }


    static public Calculator integer(Integer value) {
        return new Calculator(value);
    }

    final private List<Object> objects = new LinkedList<>();

    private Calculator(Integer initValue) {
        this.objects.add(initValue);
    }

    public Calculator plus(Integer value) {
        this.objects.add("+");
        this.objects.add(value);
        return this;
    }

    public Calculator minus(Integer value) {
        this.objects.add("-");
        this.objects.add(value);
        return this;
    }

    public Calculator multiply(Integer value) {
        this.objects.add("*");
        this.objects.add(value);
        return this;
    }

    public Calculator divide(Integer value) {
        this.objects.add("/");
        this.objects.add(value);
        return this;
    }

    public Integer calculate() {
        List<Object> calculation = new LinkedList<>(this.objects);
        Integer result = (Integer) calcRecursively(calculation).get(0);
        logger.debug("Calculation result: " + result);
        return result;
    }

    private List<Object> calcRecursively(List<Object> objects) {
        logger.debug(objects);
        boolean multiplyFound = false;
        for (int i = 0; i < objects.size(); i ++) {
            if (objects.get(i).getClass() == String.class) {
                String action = objects.get(i).toString();
                if ("*".equals(action) || "/".equals(action)) {
                    Integer int1   = (Integer) objects.get(i - 1);
                    Integer int2   = (Integer) objects.get(i + 1);
                    Integer result = 0;
                    if (objects.get(i).equals("*")) {
                        result = int1 * int2;
                    } else if (objects.get(i).equals("/")) {
                        result = int1 / int2;
                    }
                    objects.remove(i);
                    objects.add(i, result);
                    objects.remove(i + 1);
                    objects.remove(i - 1);
                    multiplyFound = true;
                    break;
                }
            }
        }
        if (multiplyFound)
            objects = calcRecursively(objects);
        else if (objects.size() > 1) {
            for (int i = 0; i < objects.size(); i ++) {
                if (objects.get(i).getClass() == String.class) {
                    Integer int1 = (Integer) objects.get(i - 1);
                    Integer int2 = (Integer) objects.get(i + 1);
                    Integer result = 0;
                    if (objects.get(i).equals("+")) {
                        result = int1 + int2;
                    } else if (objects.get(i).equals("-")) {
                        result = int1 - int2;
                    }
                    objects.remove(i);
                    objects.add(i, result);
                    objects.remove(i + 1);
                    objects.remove(i - 1);
                    break;
                }
            }
        }
        if (objects.size() > 1)
            return calcRecursively(objects);
        else
            return objects;
    }
}
