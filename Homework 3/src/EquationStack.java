/*
Anthony Pizzulli
111990335
R08
 */
import java.util.Stack;
public class EquationStack extends Stack<String>{

    /**
     * Brief: This is the default constructor for the EquationStack class
     */
    public EquationStack(){
        super();
    }

    Stack <String> eqStack = new Stack<String>();

    /**
     * Brief: This is the push method for the EquationStack, which adds the new operand/operator to the top of the stack
     * @param s: The String value to add to the EquationStack, which is either a numerical value or an operator
     * @return String: The value that was pushed to the EquationStack
     */
    @Override public String push( String s ){
        eqStack.push(s);
        return s;
    }

    /**
     * Brief: This is the pop method for the EquationStack, which "pops" (removes) the most recently added
     *        String from the EquationStack
     * @return String: The String (either an operator or operand) that has been removed from the EquationStack
     */
    @Override public String pop(){
          String s = eqStack.pop();
          return s;
    }

    /**
     * Brief: This is the peek method for the EquationStack, which returns the most recently added String
     *        value from the EquationStack (without removing it)
     * @return String: The String (either an operator or operand) that is at the top of the EquationStack
     */
    @Override public String peek(){
          return eqStack.peek();
    }

    /**
     * Brief: This is the isEmpty method for the EquationStack, which identifies whether or not the EquationStack is empty
     * @return boolean: Returns true if there are no String values in the EquationStack and false otherwise
     */
    @Override public boolean isEmpty(){
        return eqStack.isEmpty();
    }

    /**
     * Brief: This is the size method for the EquationStack, which gives the number of Strings in the stack
     * @return int: The number of elements (Strings) in the EquationStack
     */
    @Override public int size(){
        return eqStack.size();
    }


}
