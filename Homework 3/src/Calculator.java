import javax.transaction.InvalidTransactionException;
/*
Anthony Pizzulli
111990335
R08
 */
import java.util.Scanner;
import java.util.Stack;

public class Calculator extends Equation implements Cloneable {

    static Stack<Character> charStack = new Stack<>();

    /**
     * Brief: This is the main method for the Calculator, which handles input from the user and prints the menu
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        EquationStack eqS = new EquationStack();
        String input = "";
        HistoryStack histStack = new HistoryStack();

        System.out.println("Welcome to calculat0r.");
        while (!(input.equals("q"))) {

            System.out.println("\n" +
                    "[A] Add new equation\n" +
                    "[F] Change equation from history\n" +
                    "[B] Print previous equation\n" +
                    "[P] Print full history\n" +
                    "[U] Undo\n" +
                    "[R] Redo\n" +
                    "[C] Clear history\n" +
                    "[Q] Quit ");

            System.out.println("Select an option: ");
            input = (userInput.nextLine().toLowerCase());
            switch (input) {

                case "a":
                    System.out.println("Please enter an equation (in-fix notation): ");
                    String equation = userInput.nextLine();
                    Equation e1 = new Equation(equation);
                    numEquations++;
                    histStack.push(e1);
                    boolean balanced = e1.isBalanced();
                    if (!balanced) {
                        e1.setAnswer(0.0);
                        e1.setPostfix("N/A");
                        e1.setPrefix("N/A");
                        e1.setHex("0");
                        e1.setBin("0");
                    }
                    try {
                        e1.decToHex((int) Math.ceil(e1.getAnswer()));
                        e1.decToBin((int) Math.ceil(e1.getAnswer()));
                        e1.getPostfix();
                        e1.getPrefix();
                    } catch (InvalidEquationException i) {
                        System.out.println("The equation is not balanced.");
                        break;
                    }
                    System.out.printf("The equation is balanced and the answer is %.3f", e1.getAnswer());

                    break;

                case "b":
                    System.out.println("#   Equation                           Pre-Fix                            Post-Fix                           Answer               Binary  Hexadecimal\n" +
                            "-----------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(histStack.peek().toString());
                    break;

                case "f": //BEGINNING CASE F
                    String res ="";
                    Equation thisEquation = new Equation();
                    System.out.println("Which equation would you like to change?");
                    int pos = Integer.parseInt(userInput.nextLine());
                    try {
                        thisEquation = histStack.getEquation(pos);
                        System.out.println("Equation at position " + pos + ": " + thisEquation.getEquationS());
                    } catch (InvalidIndexPositionException e) {
                        System.out.println("There is no equation at the given index.");
                        break;
                    }
                    System.out.println("What would you like to do to the equation (Replace / remove / add)?");
                    String i = userInput.nextLine();
                    switch (i.toLowerCase()) {
                        case "replace":
                            System.out.println("What position would you like to change? ");
                            char r = userInput.nextLine().charAt(0);
                            System.out.println("What would you like to replace it with? ");
                            char p = userInput.nextLine().charAt(0);
                            char[] originalString = thisEquation.getEquationS().toCharArray();
                            originalString[(r - '0') - 1] = p;
                            histStack.peek().setEquation(String.valueOf(originalString));
                            thisEquation.setEquation(String.valueOf(originalString));
                            try {
                                histStack.peek().decToHex((int) Math.ceil(histStack.peek().getAnswer()));
                                histStack.peek().decToBin((int) Math.ceil(histStack.peek().getAnswer()));
                                histStack.peek().getPostfix();
                                histStack.peek().getPrefix();
                            } catch (InvalidEquationException iv) {
                                System.out.println("Invalid Equation.");
                            }
                            System.out.println("\nEquation: " + histStack.peek().getEquationS());
                            break;
                        case "add":
                            System.out.println("What position would you like to add something? ");
                            int position = Integer.parseInt(userInput.nextLine());
                            System.out.println("What would you like to add? ");
                            char charToAdd = userInput.nextLine().charAt(0);
                            String firstHalf = histStack.peek().getEquationS().substring(0, position - 1);
                            firstHalf += charToAdd;
                            firstHalf += histStack.peek().getEquationS().substring(position - 1);
                            histStack.peek().setEquation(firstHalf);
                            thisEquation.setEquation(firstHalf);
                            try {
                                histStack.peek().decToHex((int) Math.ceil(histStack.peek().getAnswer()));
                                histStack.peek().decToBin((int) Math.ceil(histStack.peek().getAnswer()));
                                histStack.peek().getPostfix();
                                histStack.peek().getPrefix();
                            } catch (InvalidEquationException iv) {
                                System.out.println("Invalid Equation.");
                            }
                            System.out.println("\nEquation: " + histStack.peek().getEquationS());
                            break;
                        case "remove":
                            System.out.println("What position would you like to remove? ");
                            int t = Integer.parseInt(userInput.nextLine());
                            String tempEq = thisEquation.getEquationS();
                            String secHalf = thisEquation.getEquationS().substring(t);
                            String fHalf = tempEq.substring(0, t-1);
                            histStack.peek().setEquation(fHalf + secHalf);
                            thisEquation.setEquation(fHalf+secHalf);
                            try {
                                histStack.peek().decToHex((int) Math.ceil(histStack.peek().getAnswer()));
                                histStack.peek().decToBin((int) Math.ceil(histStack.peek().getAnswer()));
                                histStack.peek().getPostfix();
                                histStack.peek().getPrefix();
                            } catch (InvalidEquationException iv) {
                                System.out.println("Invalid Equation.");
                            }
                            System.out.println("\nEquation :" + histStack.peek().getEquationS());

                    }

                    System.out.println("Would you like to make any more changes? ");
                    res = userInput.nextLine();
                    while ( !(res.toLowerCase().equals("no")) & !((res.charAt(0)=='n')) ) {
                        System.out.println("What would you like to do to the equation (Replace / remove / add)?");
                        i = userInput.nextLine();
                        switch (i.toLowerCase()) {
                            case "replace":
                                System.out.println("What position would you like to change? ");
                                char r = userInput.nextLine().charAt(0);
                                System.out.println("What would you like to replace it with? ");
                                char p = userInput.nextLine().charAt(0);
                                char[] originalString = thisEquation.getEquationS().toCharArray();
                                originalString[(r - '0') - 1] = p;
                                histStack.peek().setEquation(String.valueOf(originalString));
                                thisEquation.setEquation(String.valueOf(originalString));
                                try {
                                    histStack.peek().decToHex((int) Math.ceil(histStack.peek().getAnswer()));
                                    histStack.peek().decToBin((int) Math.ceil(histStack.peek().getAnswer()));
                                    histStack.peek().getPostfix();
                                    histStack.peek().getPrefix();
                                } catch (InvalidEquationException iv) {
                                    System.out.println("Invalid Equation.");
                                }
                                System.out.println("\nEquation: " + histStack.peek().getEquationS());
                                System.out.println("Would you like to make any more changes? ");
                                res = userInput.nextLine();

                                if (res.toLowerCase().equals("n") || res.toLowerCase().equals("no")) {
                                    System.out.println("The equation is balanced and the answer is: " + histStack.peek().getAnswer());
                                }
                                break;
                            case "add":
                                System.out.println("What position would you like to add something? ");
                                int position = Integer.parseInt(userInput.nextLine());
                                System.out.println("What would you like to add? ");
                                char charToAdd = userInput.nextLine().charAt(0);
                                String firstHalf = histStack.peek().getEquationS().substring(0, position - 1);
                                firstHalf += charToAdd;
                                firstHalf += histStack.peek().getEquationS().substring(position - 1);
                                histStack.peek().setEquation(firstHalf);
                                thisEquation.setEquation(firstHalf);
                                try {
                                    histStack.peek().decToHex((int) Math.ceil(histStack.peek().getAnswer()));
                                    histStack.peek().decToBin((int) Math.ceil(histStack.peek().getAnswer()));
                                    histStack.peek().getPostfix();
                                    histStack.peek().getPrefix();
                                } catch (InvalidEquationException iv) {
                                    System.out.println("Invalid Equation.");
                                }
                                System.out.println("\nEquation: " + histStack.peek().getEquationS());
                                System.out.println("Would you like to make any more changes? ");
                                res = userInput.nextLine();

                                if (res.toLowerCase().equals("n") || res.toLowerCase().equals("no")) {
                                    System.out.println("The equation is balanced and the answer is: " + histStack.peek().getAnswer());
                                }
                                break;
                            case "remove":
                                System.out.println("What position would you like to remove? ");
                                int t = Integer.parseInt(userInput.nextLine());
                                String tempEq = thisEquation.getEquationS();
                                String secHalf = thisEquation.getEquationS().substring(t);
                                String fHalf = tempEq.substring(0, t-1);
                                histStack.peek().setEquation(fHalf + secHalf);
                                thisEquation.setEquation(fHalf+secHalf);
                                try {
                                    histStack.peek().decToHex((int) Math.ceil(histStack.peek().getAnswer()));
                                    histStack.peek().decToBin((int) Math.ceil(histStack.peek().getAnswer()));
                                    histStack.peek().getPostfix();
                                    histStack.peek().getPrefix();
                                } catch (InvalidEquationException iv) {
                                    System.out.println("Invalid Equation.");
                                }
                                System.out.println("\nEquation: " + histStack.peek().getEquationS());
                                System.out.println("Would you like to make any more changes? ");
                                res = userInput.nextLine();

                                if (res.toLowerCase().equals("n") || res.toLowerCase().equals("no")) {
                                    System.out.println("The equation is balanced and the answer is: " + histStack.peek().getAnswer());
                                }
                                break;

                            default:
                                break;
                        }

                    }
                        break;//END CASE F

                case "c":

                    Equation temp = new Equation();
                    while (!(histStack.isEmpty())) {
                        temp = histStack.pop();
                    }
                    System.out.println("Resetting calculator.");
                    break;

                case "p":

                    Equation current = new Equation();
                    System.out.println("#   Equation                           Pre-Fix                            Post-Fix                           Answer               Binary  Hexadecimal\n" +
                                    "-----------------------------------------------------------------------------------------------------------------------------------------------------");
                    while (!(histStack.isEmpty())) {
                        current = histStack.pop();
                        System.out.println(current.toString());
                    }
                    break;

                case "u":
                    Equation t = histStack.peek();
                    histStack.undo();
                    System.out.println("Equation '" + t.getEquationS() + "' undone.");
                    break;

                case "r":
                    try{
                        histStack.redo();
                    }
                    catch(NoEquationException ne){
                        System.out.println("There is nothing to redo.");
                        break;
                    }
                    System.out.println("Redoing equation '" + histStack.peek().getEquationS() + "'.");
                case "q":
                    break;
                default:
                    break;
                }
            }
        }
    }

