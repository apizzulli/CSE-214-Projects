/*
Anthony Pizzulli
111990335
R08
 */
public class Equation extends EquationStack {

    private String equation;
    private String prefix;
    private String postfix;
    private double answer;
    private String binary;
    private String hex;
    private int thisIndex;
    private boolean balanced;
    public static int numEquations = 0;

    EquationStack operands = new EquationStack();
    EquationStack operators = new EquationStack();

    /**
     * brief: Default constructor for Equation Class
     */
    public Equation(){

    }

    /**
     * Brief: Additional construtor used when creating an equation from user input
     * @param equation: The new Equation string to be used for new Equation object
     */
    public Equation( String equation ){
        this.equation = equation;
        this.prefix = this.getPrefix();
        this.postfix = this.getPostfix();
    }


    /**
     * Brief: Setter method for the answer to the given Equation
     * @param d: The new answer value
     */
    public void setAnswer(double d){
        this.answer = d;
    }

    /**
     * Brief: Setter method for Postfix representation of the given Equation
     * @param pf: The new Postfix value
     */
    public void setPostfix(String pf){
        this.postfix = pf;
    }

    /**
     * Brief: Setter method for Prefix representation of the given Equation
     * @param preF: The new Prefix value
     */
    public void setPrefix(String preF){
        this.prefix = preF;
    }

    /**
     * Brief: Setter method for Hexadecimal representation of the given Equation
     * @param s: The new Hexadecimal value
     */
    public void setHex(String s){
        this.hex = s;
    }

    /**
     * Brief: Setter method for Binary representation of the given Equation
     * @param s: The new Binary value
     */
    public void setBin(String s){
        this.binary = s;
    }

    /**
     * Brief: Setter method for Equation String
     * @param s: The new Equation String
     */
    public void setEquation( String s ){
        this.equation = s;
    }

    /**
     * Brief: Getter method for Equation String
     * @return String: The given Equation object's Equation String
     */
    public String getEquationS(){
        return this.equation;
    }


    /**
     * Brief: This method contains an algorithm to process the given String equation and determine a numerical answer based on the
     *        given operands and operators.
     * @return double: The final numerical answer to the equation
     */
    public double getAnswer(){

        for(int i = 0; i < equation.length(); i++){
            String num = "";
            char currentC = equation.charAt(i);
            String currentS = Character.toString(currentC);
            if( Character.isDigit(currentC)  ){ //if character is a digit, add it to the Operand Stack
                while( Character.isDigit(currentC) && i<equation.length()-1 ){
                    num+=currentC;
                    i++;
                    currentC = equation.charAt(i);
                    currentS = Character.toString(currentC);
                }
                if(i==equation.length()-1)
                    num+=currentC;
                operands.push(num);
            }
            if ( isOperator(currentC) )
                operators.push(currentS);
            if ( currentC == ')' || (i == equation.length()-1 && !(operands.isEmpty()) && !(operators.isEmpty()) )){
                double op2 = Double.parseDouble(operands.pop());
                double op1 = Double.parseDouble(operands.pop());
                double result = 0;
                switch(operators.pop()){
                    case("+"):
                        result = op1 + op2;
                        break;
                    case("-"):
                        result = op1-op2;
                        break;
                    case("*"):
                        result = op1*op2;
                        break;
                    case("/"):
                        result = op1/op2;
                        break;
                    case("%"):
                        result = op1%op2;
                        break;
                    case("^"):
                        result = Math.pow(op1,op2);
                        break;
                }
                operands.push(Double.toString(result));
            }
        }
        this.answer = Double.parseDouble(operands.peek());
        return answer;
    }

    /**
     * Brief: This is a helper method to determine whether or not a given character is a mathematical operator.
     * @param c: The character to be tested
     * @return boolean: True if the given character is an operator, and false otherwise
     */
    public boolean isOperator( char c){
        if(c == '+' || c =='-' || c == '*' || c=='/' || c == '^' || c == '%')
            return true;
        else
            return false;
    }

    /**
     * Brief: This method uses an algorithm to produce the Postfix representation of the given Equation
     * @return String: The Postfix version of the given Equation
     */
    public String getPostfix(){
        String pf = "";
        for(int i = 0; i<equation.length(); i++){
            char currentChar = equation.charAt(i);
            String currentStr = Character.toString(currentChar);
            if( Character.isDigit(currentChar))
                pf+=currentChar;
            else if( isOperator(currentChar) ){
                pf+=" ";
                operators.push(currentStr);
            }
            else if( currentChar == ')') {
                pf+=" ";
                pf += operators.pop();
            }
        }
        if(!(operators.isEmpty()))
            pf+=" " + operators.pop();
        this.postfix = pf;
        return postfix;
    }

    /**
     * Brief: This method uses an algorithm to produce the Prefix representation of the given Equation
     * @return String: The Postfix version of the given Equation
     */
    public String getPrefix(){
        String pref = "";
        for( int i = 0; i<equation.length(); i++){
            char cc = equation.charAt(i);
            String cs = Character.toString(cc);
            if( Character.isDigit(cc)) {
                operands.push(cs);
            }
            else if( isOperator(cc) ) {
                pref += cc + " ";
            }
            else if(cc==')') {
                pref += operands.pop();
            }
        }
        if( !(operands.isEmpty()))
            pref+= " " + operands.pop();
        this.prefix = pref;
        return prefix;
    }

    /**
     * Brief: This method uses an algorithm to determine whether or not the given Equation String has balanced parentheses
     * @return boolean: Returns true if the given Equation String contains balanced parentheses and false otherwise
     */
    public boolean isBalanced(){
        EquationStack  brackets = new EquationStack();
        for( int i = 0; i<equation.length(); i++){
            char currentChar = equation.charAt(i);
            if(currentChar == '(' || currentChar == '{' || currentChar == '['){
                brackets.push(Character.toString(currentChar));
            }
            if(currentChar == ')' || currentChar == '}' || currentChar == ']'){
                if(brackets.isEmpty() )
                    return false;
                String bracket = brackets.peek();
                if( (bracket.equals("(") && currentChar == ')') || (bracket.equals("{") && currentChar=='}') || (bracket.equals("[") && currentChar==']') ) {
                    brackets.pop();
                }
            }
        }
        if (brackets.isEmpty())
            return true;
        return false;
    }

    /**
     * Brief: This method is used to compute the binary equivalent of the given integer value
     * @param number: The value to be converted
     * @return String: The binary representation of the given integer
     * @throws InvalidEquationException: Thrown if the given equation contains unbalanced parentheses
     */
    public String decToBin(int number)throws InvalidEquationException{
        if(!(this.isBalanced()))
            throw new InvalidEquationException();
        else
            this.binary = baseConverter(number,2);

        return binary;
    }

    /**
     * Brief: This method is used to compute the hexadecimal equivalent of the given integer value
     * @param number: The value to be converted
     * @return String: The hexadecimal representation of the given integer
     * @throws InvalidEquationException: Thrown if the given equation contains unbalanced parentheses
     */
    public String decToHex(int number) throws InvalidEquationException{
        if(!(this.isBalanced()))
            throw new InvalidEquationException();
        else
            this.hex = baseConverter(number, 16);
        return hex;
    }

    /**
     * Brief: This is the toString method for the Equation class, which returns a neatly formatted table
     *        for the given Equation and its various numerical representations
     * @return String: The concatenated String representation of the given Equation object
     */
    @Override public String toString(){
        String returnS = "";
        System.out.printf("%d   %-35s%-20s               %-24s          %.3f %20s%12s", numEquations,
                                                    this.equation, this.prefix, this.postfix, this.answer, this.binary, this.hex);
        return returnS;
    }

    /**
     * Brief: This is a method that uses an algorithm to convert between numerical bases
     * @param number: The value to be converted
     * @param base: The intended base for the integer to be converted
     * @return String: The given integer represented in the new base
     */
    public static String baseConverter( int number, int base ){
        String array[] = new String[50];
        int index = 0;
        String converted = "";
        String hex="";
        if (number <= 0)
            return "";
        else
            while( number > 0){
                int rem = number%base;
                if(rem>9){
                    switch(rem){
                        case 10:
                            hex="A";
                            break;
                        case 11:
                            hex="B";
                            break;
                        case 12:
                            hex="C";
                            break;
                        case 13:
                            hex="D";
                            break;
                        case 14:
                            hex="E";
                            break;
                        case 15:
                            hex="F";
                            break;
                        default:
                            break;
                    }
                    array[index] = hex;
                    index++;
                }
                else {
                    array[index] = Integer.toString(rem);
                    index++;
                }
                number = number/base;
            }
            for( int i = index -1; i >= 0; i--){
                converted += array[i];
            }
            return converted;
    }
}
