package com.mine.chapter03.practice;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author CaoY
 * @date 2023-08-27 17:06
 * @description 练习 3.22 编写一个计算后缀表达式的程序
 *
 * 注：笔者临时起意，扩展了一下，新增了一个中缀式转后缀式的方法，可以直接计算中缀式的输入，
 * 然后将其转换为后缀式，最后得出结果。思路很简单，用好栈就可以了。
 */
public class Solution03_22 {

    public static void main(String[] args) {
        String expression = "3 + 4";
        String suffixExpression = convertInfixToPostfix(expression);
        double res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);

        expression = "(3 + 4) * 2 - 1";
        suffixExpression = convertInfixToPostfix(expression);
        res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);

        expression = "8 + 4 / (2 - 1)";
        suffixExpression = convertInfixToPostfix(expression);
        res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);

        expression = "8 + 4 / (3 - 2 * (6 - 5))";
        suffixExpression = convertInfixToPostfix(expression);
        res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);

        expression = "8";
        suffixExpression = convertInfixToPostfix(expression);
        res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);

        expression = "(6 + 2 * 3) / (4 - 1) + 5";
        suffixExpression = convertInfixToPostfix(expression);
        res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);

        expression = "(2 + 3) * (4 - 1) / (5 + 2) - 6";
        suffixExpression = convertInfixToPostfix(expression);
        res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);

        expression = "2 + 4 * 5 / (2 + 3) * (4 - 3) / (14 - 2 * (7 - 1)) - 6";
        suffixExpression = convertInfixToPostfix(expression);
        res = calcPostfixExpression(suffixExpression);
        show(expression, suffixExpression, res);
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
                res.append(ch);
            }
        }

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.toString();
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

        if ((top == '+' || top == '-') && (ing == '*' || ing == '/')) {
            return true;
        }

        return false;
    }

    /**
     * 展示结果到控制台
     * @param infixExpression   中缀表达式
     * @param suffixExpression  后缀表达式
     * @param res               最终计算结果
     */
    public static void show(String infixExpression, String suffixExpression, double res) {
        System.out.println("中缀表达式：" + infixExpression);
        System.out.println("后缀表达式：" + suffixExpression);
        System.out.println("答案：" + res);
        System.out.println("**********************************");
    }
}
