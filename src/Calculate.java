import java.util.Stack;

public class Calculate {
    public static double getResult(String suffix){
        String[] strings = suffix.split(":");
        double a,b;
        Stack<Double> stack = new Stack<>();
        for (int i = 0;i < strings.length;i++){
            if ("+".equals(strings[i])){
                b = stack.pop();
                a = stack.pop();
                stack.push(a + b);
            }else if ("-".equals(strings[i])){
                b = stack.pop();
                a = stack.pop();
                stack.push(a - b);
            }else if ("*".equals(strings[i])){
                b = stack.pop();
                a = stack.pop();
                stack.push(a * b);
            }else if ("/".equals(strings[i])){
                b = stack.pop();
                a = stack.pop();
                stack.push(a / b);
            }else {
                stack.push(Double.valueOf(strings[i]));
            }
        }
        return stack.pop();
    }
}
