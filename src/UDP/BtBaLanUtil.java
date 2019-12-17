package UDP;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BtBaLanUtil
{
	public static String[][] operatorArr = {{"+", "-"}, {"*", "/", "%"} , {"sin", "cos", "log"}};
    public static int GetPriority(String op)
    {
        for (int i = 0; i < 3 ; i++) {
			for (int j = 0; j < operatorArr[i].length; j++) {
				if (op.equalsIgnoreCase(operatorArr[i][j])) {
					return i + 1;
				}
			}
		}
        return 0;
    }

    public static String FormatExpresion(String expression)
    {

        expression = expression.replaceAll(" ", "");
        
        String Regex = "[\\*|/|%|\\+|\\-|)|(]|(sin){1}|(cos){1}|(log){1}|]";
		
		Pattern ptn = Pattern.compile(Regex);
		Matcher mch = ptn.matcher(expression);
		while (mch.find()) {
			String oldStr = mch.group();
			String newStr = " " + oldStr + " ";
			expression = expression.replace(oldStr, newStr);
		}
		expression = expression.replaceAll("\\s{2,}", " ");
        expression = expression.trim();
        
        return expression;
    }

    public static boolean IsOperator(String str)
    {
    	String Regex = "[\\*|/|%|\\+|\\-|)|(]|(sin){1}|(cos){1}|(log){1}|]";
		Pattern ptn = Pattern.compile(Regex);
		Matcher mch = ptn.matcher(str);
        return mch.find();
    }

    public static boolean IsOperand(String str)
    {
    	String Regex = "\\d";	
		Pattern ptn = Pattern.compile(Regex);
		Matcher mch = ptn.matcher(str);
        return mch.find();
    }
    public static String InfixToPostfix(String infix) throws Exception
    {

        infix = FormatExpresion(infix);
        String[] str = infix.split(" ");
        Stack<String> stack = new Stack<String>();
        String postfix = "";
        
        for (String s : str)
        {

            if (IsOperand(s))
                postfix += (s + " ");
            else if (s.equalsIgnoreCase("("))
                stack.push(s);
            else if (s.equalsIgnoreCase(")"))
            {
                String x = "";
                while (((x = stack.pop()).equalsIgnoreCase("(")) == false)
                    postfix += (x + " ");
            }
            else if (IsOperator(s))
            {
                while (stack.size() > 0 && GetPriority(s) <= GetPriority(stack.peek()))
                    postfix += (stack.pop() + " ");
                stack.push(s);
            }
            else
            {
                throw new Exception("SYNTAX ERROR");
            }
        };

        while (stack.size() > 0)
            postfix += (stack.pop() + " ");
        postfix.trim();
        return postfix;
    }

    public static float EvaluatePostfix(String postfix)
    {

        Stack<Float> stack = new Stack<Float>();
        String[] ienum = postfix.split(" ");

        for (String s : ienum)
        {

            if (IsOperand(s))
            {

                stack.push(Float.parseFloat(s));
            }
            else if (IsOperator(s))
            {

                if (GetPriority(s) == 3)
                {
                    float x = stack.pop();
                    switch (s)
                    {
                        case "sin": x = (float) Math.sin(x); break;
                        case "cos": x = (float) Math.cos(x); break;
                        case "log": x = (float) Math.log10(x); break;
                    }
                    stack.push(x);
                }
                else
                {
                    float x = stack.pop();
                    float y = stack.pop();
                    switch (s)
                    {

                        case "+": y += x; break;
                        case "-": y -= x; break;
                        case "*": y *= x; break;
                        case "/": y /= x; break;
                        case "%": y %= x; break;
                    }
                    stack.push(y);
                }
            }
        }

        return stack.pop();
    }
//    public static void main(String[] args) throws Exception {
//    	String infix = "2 * (8*2 - 16) + sin(0) + log(100)";
//    	infix = FormatExpresion(infix);
//    	System.out.println(infix);
//    	String postfix = InfixToPostfix(infix);
//    	System.out.println(postfix);
//    	System.out.println(EvaluatePostfix(postfix));
//	}
}
