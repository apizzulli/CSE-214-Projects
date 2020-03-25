/*
Anthony Pizzulli
111990335
R08
 */
import java.util.Stack;
public class HistoryStack extends Stack<EquationStack>{

    private int index = 0;
    public HistoryStack(){
        super();
    }
    Stack<Equation> historyStack = new Stack<Equation>();
    Stack<Equation> backup = new Stack<Equation>();
    Equation temp = new Equation();

    /**
     * Brief: This is the push method for the HistoryStack, which adds the given Equation to the stack
     * @param newEquation: The Equation to add to the HistoryStack, which is either a numerical value or an operator
     */
     public void push ( Equation newEquation ){
        historyStack.push(newEquation);
        index++;
    }

    /**
     * Brief: This is the pop method for the HistoryStack, which "pops" (removes) the most recently added
     *        Equation from the HistoryStack
     * @return Equation: The Equation that has been removed from the HistoryStack
     */
    public Equation pop(){
        index--;
        return historyStack.pop();
    }


    /**
     * Brief: This is the peek method for the HistoryStack, which returns the most recently added Equation
     *        value from the stack (without removing it)
     * @return Equation: The Equation that is at the top of the HistoryStack
     */
    public Equation peek(){
         return historyStack.peek();
    }

    /**
     * Brief: This method removes the Equation that is at the top of the stack but stores a reference to it.
     */
    public void undo(){
         backup.push(historyStack.pop());
         index--;
    }

    /**
     * Brief: This method replaces the last undone Equation back onto the HistoryStack
     * @throws NoEquationException: Thrown if there last undone Equation
     */
    public void redo() throws NoEquationException{
         if( backup.isEmpty() )
             throw new NoEquationException();
         else {
             historyStack.push(backup.pop());
             index++;
         }
    }

    /**
     * Brief: This is the size method for the HistoryStack, which gives the number of Equations in the HistoryStack
     * @return int: The number of elements (Equations) in the HistoryStack
     */
    public int size(){
         return historyStack.size();
    }

    /**
     * Brief: This is the isEmpty method for the HistoryStack, which identifies whether or not the HistoryStack is empty
     * @return boolean: Returns true if there are no Equation objects in the HistoryStack and false otherwise
     */
    public boolean isEmpty(){
         return historyStack.isEmpty();
    }


    /**
     * Brief: This method attempts to find the Equation located at the given position
     * @param position: The index of the specified Equation
     * @return Equation: The Equation located at the given position
     * @throws InvalidIndexPositionException: Thrown if the given position is greater than the number of Equations in the
     *                                        HistoryStack and false otherwise
     */
   public Equation getEquation(int position) throws InvalidIndexPositionException{
         HistoryStack tempStack = new HistoryStack();
         int y = 0 ;
         Equation target = new Equation();
         if( position > index || position <1 ){
             throw new InvalidIndexPositionException();
         }
         if( position == index)
             target = historyStack.peek();
         else
             while(y<= (historyStack.size() - position)+1 ) {
                 target = historyStack.pop();
                 tempStack.push(target);
                 y++;
             }
             if(historyStack.isEmpty()){
                 while( !(tempStack.isEmpty())){
                     historyStack.push(tempStack.pop());
                 }
             }
             return target;
    }
}
