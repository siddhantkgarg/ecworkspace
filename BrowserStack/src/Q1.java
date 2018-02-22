import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Q1 {

	public static String operators[] = {"+","-","*","_"};
	
	
	
	public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
 
         // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();
 
        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();
 
        for (int i = 0; i < tokens.length; i++)
        {
             // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;
 
            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }
 
            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
 
            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
 
        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
        // Top of 'values' contains result, return it
        return values.pop();
    }
 
    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
 
    // A utility method to apply an operator 'op' on operands 'a' 
    // and 'b'. Return the result.
    public static int applyOp(char op, int b, int a)
    {
        switch (op)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException("Cannot divide by zero");
            return a / b;
        }
        return 0;
    }
	public static boolean isOperator(String op) {
		
		for(int i=0;i<4;i++) {
			if(operators[i].equals(op))
				return true;
		}
		return false;
	}
	
	public static void main(String s[]) {
		
		String expression = "";
		 List<String> div = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		List<List<String>> array= new ArrayList<List<String>>();
		int maxCol = 0;
		while(sc.hasNext()) {
		    try{
		    
		    String line = sc.nextLine();
			if(line.isEmpty())
				break;
			List<String> row = Arrays.asList(line.split(","));
			int n = row.size();
			if(n >maxCol) {
				maxCol = n;
			}
//			int rem = maxCol - n;
//			for(int i=0;i<rem;i++) {
//				row.add("0");
//			}
			array.add(row);    
		    }
		    catch(Exception ex){
		        break;
		    }
			
		}
		List<List<String>> cloned= new ArrayList<List<String>>(array);
		int row = array.size();
		int col = maxCol;
		
		boolean openBracket = false;
		try {
			for(int i=0;i<row;i++) {
				if(openBracket == true) {
					expression+=" ) ";
					openBracket = false;
					expression+=(" / "+div.get(0)+" ");
					div.remove(0);
					
				}
				for(int j=0;j<col;j++) {
					if(i==row-1 && array.get(i).get(j)=="_") {
						System.out.println("INVALID EXPRESSION");
						return;
					}
					if(array.get(i).get(j).equals("processed"))continue;
					if(array.get(i).get(j).trim().equals("evaluated")) continue;
					
					if(array.get(i).get(j).trim().equals("_")) {
						expression+=" / ";
						array.get(i).set(j, "processed");
						continue;
					}
//					if(isOperator(array.get(i).get(j).trim()))
//					{
//						expression+=array.get(i).get(j);
//						continue;
//					}
					try {
						if(i+1<row && j<array.get(i+1).size() && array.get(i+1).get(j).trim().equals("_")) {
							array.get(i+1).set(j, "evaluated");
							if(openBracket == false) {
								expression+=" ( ";
								openBracket = true;
								div.add(array.get(i+2).get(j));
								array.get(i+2).set(j, "processed");
							}
						}else {
							if(openBracket == true) {
								expression+=" ) ";
								openBracket = false;
								expression+=(" / "+div.get(0)+" ");
								div.remove(0);
							}
						}
					}catch(Exception ex) {
						if(openBracket == true) {
							expression+=")";
							openBracket = false;
							expression+=(" / "+div.get(0)+" ");
							div.remove(0);
							array.get(i+2).set(j, "processed");
						}
					}
					
					
					expression+=array.get(i).get(j)+" ";
					array.get(i).set(j, "processed");
				}
			}
		}
			catch(Exception ex) {
				 
			}
		System.out.println(expression );
		System.out.println(evaluate(expression.trim()));
		}
}
