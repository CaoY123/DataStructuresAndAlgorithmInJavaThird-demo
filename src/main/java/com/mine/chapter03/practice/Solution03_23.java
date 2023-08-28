package com.mine.chapter03.practice;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author CaoY
 * @date 2023-08-27 20:12
 * @description 练习 3.23 中缀表达式转后缀表达式（添加幂运算）
 *
 * 不添加幂运算的中缀表达式转后缀表达式见 Solution03_22 的 convertInfixToPostfix，下面对其进行升级
 */
public class Solution03_23 {

    public static void main(String[] args) {
        String expression = "3+4";
        String postfixExpression = convertInfixToPostfix(expression);
        String infixExpression = convertPostfixToInfix(postfixExpression);
        double res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "(3 + 4) * 2 - 1";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "8 + 4 / (2 - 1)";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "8 + 4 / (3 - 2 * (6 - 5))";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "8";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "(6 + 2 * 3) / (4 - 1) + 5";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "(2 + 3) * (4 - 1) / (5 + 2) - 6";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "2 + 4 * 5 / (2 + 3) * (4 - 3) / (14 - 2 * (7 - 1)) - 6";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "2^5";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "(2 + 4 * 5 / (2 + 3) * (4 - 3) / (14 - 2 * (7 - 1)) - 6) * 3^(1+ 1)";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "(3 * 2 + 1)^ 2";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "((3 * 2 + 1)^ 2)^(4-3)";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "3 + 3 + 4 - (5 + 5)";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);

        expression = "3^2^2";
        postfixExpression = convertInfixToPostfix(expression);
        infixExpression = convertPostfixToInfix(postfixExpression);
        res = calcPostfixExpression(postfixExpression);
        show(expression, postfixExpression, infixExpression, res);
    }

    /**
     * 将输入的中缀表达式转换为后缀表达式
     * @param infixExpression   合法的中缀表达式，包含整数、+、-、*、/、()、以及空格
     * @return  已字符串形式表达的后缀表达式
     */
    private static String convertInfixToPostfix(String infixExpression) {
        if (infixExpression == null || infixExpression.length() == 0) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infixExpression.length(); i++) {
            char ch = infixExpression.charAt(i);
            if (isOperator(ch)) {
                if (stack.isEmpty()) {
                    stack.push(ch);
                    continue;
                }
                if (isPriority(stack.peek(), ch)) {
                    stack.push(ch);
                } else {
                    if (ch == ')') {
                        while (stack.peek() != '(') {
                            res.append(stack.pop());
                        }
                        stack.pop();
                    } else {
                        while (!stack.isEmpty() && !isPriority(stack.peek(), ch)) {
                            res.append(stack.pop());
                        }
                        stack.push(ch);
                    }
                }
            } else {
                if (isNumber(ch)) {
                    while (isNumber(ch)) {
                        res.append(ch);
                        i++;
                        if (i < infixExpression.length()) {
                            ch = infixExpression.charAt(i);
                        } else {
                            break;
                        }
                    }
                    i--;
                }
                res.append(' ');
            }
        }

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.toString();
    }

    /**
     * 将后缀表达式转换成中缀表达式
     * @param postfixExpression 传入的以字符串描述的后缀表达式
     * @return  转换成的中缀表达式
     *
     * 注：思维开阔一点，可以尽情利用括号，而不是要 100% 形式上的还原，
     * 只要不影响最后的结果即可，所以有如下代码。
     */
    private static String convertPostfixToInfix(String postfixExpression) {
        if (postfixExpression == null || postfixExpression.length() == 0) {
            return "";
        }

        // 2 个栈实现后缀式转中缀式，一个存表达式的栈，另一个存已经处理的运算符的栈，主要判断用不用加括号
        Stack<StringBuilder> expressionStack = new Stack<>();
        StringBuilder numSb = new StringBuilder();
        for (int i = 0; i < postfixExpression.length(); i++) {
            char ch = postfixExpression.charAt(i);
            if (isNumber(ch)) {
                while (isNumber(ch)) {
                    numSb.append(ch);
                    i++;
                    if (i < postfixExpression.length()) {
                        ch = postfixExpression.charAt(i);
                    }
                }
                expressionStack.push(numSb);
                i--;
                numSb = new StringBuilder();
            } else if (ch == ' ') {
                continue;
            } else {
                StringBuilder val1 = null;
                StringBuilder val2 = null;
                if (!expressionStack.isEmpty()) {
                    val2 = expressionStack.pop();
                }
                if (!expressionStack.isEmpty()) {
                    val1 = expressionStack.pop();
                }

                StringBuilder val = new StringBuilder("(");
                val.append(val1)
                        .append(ch)
                        .append(val2)
                        .append(")");

                expressionStack.push(val);
            }
        }
        return expressionStack.pop().toString();
    }

    /**
     * 计算后缀表达式的值
     * @param expression    以字符串形式描述的后缀表达式，该后缀表达式是合法的，只含有整数、空格和运算符
     * @return  结果为 double 类型
     */
    private static double calcPostfixExpression(String expression) {
        if (expression == null || expression.length() == 0) {
            return 0;
        }

        Stack<Double> stack = new Stack<>();

        double num = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == ' ') {
                continue;
            } else if (isNumber(ch)) {
                while (i < expression.length() && isNumber(ch)) {
                    num = num * 10 + ch - '0';
                    i++;
                    if (i < expression.length()) {
                        ch = expression.charAt(i);
                    }
                }
                i--;
                stack.push(num);
                num = 0;
            } else {
                // 该字符是运算符，从栈中弹出连个运算数，开始计算过程
                double val2 = stack.pop();
                double val1 = stack.pop();
                stack.push(calcTwoNum(val1, val2, ch));
            }
        }

        return stack.pop();
    }

    /**
     * 判断传入的字符是否是数字
     * @param ch    待判断的字符
     * @return  如果是数字字符，则返回 true；反之，返回 false
     */
    private static boolean isNumber(char ch) {
        return ch <= '9' && ch >= '0';
    }

    /**
     * 根据指定的运算计算两个操作数的结果，如果传入的操作符不是 +、-、*、/，则抛出 IllegalStateException
     * @param val1  第一个操作数
     * @param val2  第二个操作数
     * @param oper  运算符，包括：+、-、*、/
     * @return      double 类型的计算结果
     */
    private static double calcTwoNum(double val1, double val2, char oper) {
        switch (oper) {
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            case '*':
                return val1 * val2;
            case '/':
                return val1 / val2;
            case '^':
                return Math.pow(val1, val2);
            default:
                throw new IllegalStateException();
        }
    }

    // 存储运算符号的 Set 集合
    private static Set<Character> operators = new HashSet<>();

    static {
        operators.add('(');
        operators.add(')');
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
        operators.add('^');
    }

    /**
     * 判断传入的字符是否是属于 operators 中的运算符
     * @param ch    带判断的字符
     * @return      如果是 operators 中的运算符，则返回 true；反之，返回 false
     */
    private static boolean isOperator(char ch) {
        return operators.contains(ch);
    }

    /**
     *
     * @param top   栈顶的运算符
     * @param ing   当前遍历到的运算符
     * @return      当返回 true 时，不需要依次弹出栈顶元素；返回 false 时，需要依次弹出栈顶的元素
     */
    private static boolean isPriority(char top, char ing) {
        if ((top == '(' && ing != ')') || ing == '(') {
            return true;
        }

        if (top == '^' && ing == '^') {
            return true;
        }

        if ((top == '*' || top == '/') && (ing == '^')) {
            return true;
        }

        if ((top == '+' || top == '-') && (ing == '*' || ing == '/' || ing == '^')) {
            return true;
        }

        return false;
    }

    /**
     * 展示结果到控制台
     * @param infixExpression   中缀表达式
     * @param postfixExpression  后缀表达式
     * @param res               最终计算结果
     */
    public static void show(String infixExpression, String postfixExpression,
                            String toInfixExpression, double res) {
        System.out.println("中缀表达式：" + infixExpression);
        System.out.println("后缀表达式：" + postfixExpression);
        System.out.println("后缀式转换成的中缀式：" + toInfixExpression);
        System.out.println("答案：" + res);
        System.out.println("**********************************");
    }
}
