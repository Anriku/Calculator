import java.util.Stack;

public class InfixToSuffix {
    public static String getSuffix(String expression) {
        //后缀表达式
        StringBuilder suffix = new StringBuilder();
        StringBuilder builder = new StringBuilder();
        //操作符中间栈
        Stack<String> opters = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*'
                    || expression.charAt(i) == '/' || expression.charAt(i) == '(' || expression.charAt(i) == ')') {

                if (opters.empty() || expression.charAt(i) == '(' || opters.lastElement().charAt(0) == '('){
                    opters.push(String.valueOf(expression.charAt(i)));
                    i++;
                }else if (expression.charAt(i) == ')'){
                    String temp = opters.pop();
                    while (!"(".equals(temp)){
                        suffix.append(temp);
                        suffix.append(":");
                        temp = opters.pop();
                    }
                    i++;
                } else if (getPriority(expression.charAt(i),opters.lastElement().charAt(0)) == '<'
                        || getPriority(expression.charAt(i),opters.lastElement().charAt(0)) == '='){
                    suffix.append(opters.pop());
                    suffix.append(":");
                }else if (getPriority(expression.charAt(i),opters.lastElement().charAt(0)) == '>'){
                    opters.push(String.valueOf(expression.charAt(i)));
                    i++;
                }
            } else {
                //while循环输入操作数
                while ((expression.charAt(i) >= '0' && expression.charAt(i) <= '9') || expression.charAt(i) == '.') {
                    builder.append(expression.charAt(i));
                    i++;
                    if (i >= expression.length()){
                        break;
                    }
                }
                suffix.append(builder.toString());
                suffix.append(":");
                builder.delete(0, builder.length());
            }
        }
        while (!opters.empty()){
            suffix.append(opters.pop());
            suffix.append(":");
        }
        return suffix.toString();
    }


    private static char getPriority(char nextOpt,char stackTop)
    {
        char priority[][] =     //算符间的优先级关系
                {
                        {
                                '=', '=', '<', '<'
                        },
                        {
                                '=', '=', '<', '<'
                        },
                        {
                                '>', '>', '=', '='
                        },
                        {
                                '>', '>', '=', '='
                        },
                };

        int index1 = getIndex(nextOpt);
        int index2 = getIndex(stackTop);
        return priority[index1][index2];
    }

    private static int getIndex(char theta) {
        int index = 0;
        switch (theta) {
            case '+':
                index = 0;
                break;
            case '-':
                index = 1;
                break;
            case '*':
                index = 2;
                break;
            case '/':
                index = 3;
                break;
            default:
                break;
        }
        return index;
    }

}
