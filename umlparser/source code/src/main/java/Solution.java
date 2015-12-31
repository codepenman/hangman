import java.util.*;

public class Solution {
/*
    public int solution(int[] A, int[] B) {
        ArrayList<Integer> aList = new ArrayList<> ();
        for(int a : A) {
            aList.add (a);
        }

        ArrayList<Integer> bList = new ArrayList<> ();
        for(int b : B) {
            bList.add (b);
        }

        Collections.sort (aList);
        System.out.println (aList);
        Collections.sort (bList);
        System.out.println (bList);
        int commonNumber = -1;
        if(aList.size () < bList.size ())   {
            for (Integer a : aList) {
                if (bList.contains (a)) {
                    commonNumber = a;
                    break;
                }
            }
        }else   {
            for (Integer b : bList) {
                if (aList.contains (b)) {
                    commonNumber = b;
                    break;
                }
            }
        }
        return commonNumber;
    }
*/

    public int solution(String s)   {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<s.length ();i++)    {
            if(Character.isDigit (s.charAt (i)))   {
                String digitStr = String.valueOf (s.charAt (i));
                stack.push (Integer.parseInt (digitStr));
            }else if(isOperator(s.charAt (i)))    {
                if(stack.size () > 1)   {
                    Integer rightOperand = stack.pop ();
                    Integer leftOperand = stack.pop ();
                    stack.push (getResult(leftOperand, s.charAt (i), rightOperand));
                }else   {
                    return -1;
                }
            }else   {
                return -1;
            }
        }
        if(stack.size () == 1)  {
            return stack.pop ();
        }else   {
            return -1;
        }
    }

    private Integer getResult (Integer leftOperand, char c, Integer rightOperand) {
        switch (c)  {
            case '+':   {
                return leftOperand + rightOperand;
            }
            case '-':   {
                return leftOperand - rightOperand;
            }
            case '*':   {
                return leftOperand * rightOperand;
            }
            case '/':   {
                return leftOperand / rightOperand;
            }
        }
        return 0;
    }

    private boolean isOperator (char c) {
        switch (c)  {
            case '+':
            case '-':
            case '*':
            case '/':   {
                return true;
            }
        }
        return false;
    }

    public static void main(String []args)  {
        Solution solution = new Solution ();
        Integer result = solution.solution ("11++");
        System.out.println (result);
    }
}